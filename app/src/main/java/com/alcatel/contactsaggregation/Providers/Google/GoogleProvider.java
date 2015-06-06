package com.alcatel.contactsaggregation.Providers.Google;

import android.os.AsyncTask;
import android.util.Log;

import com.alcatel.contactsaggregation.Core.Models.Contact;
import com.alcatel.contactsaggregation.Providers.HelperProvider;
import com.alcatel.contactsaggregation.Providers.Provider;
import com.alcatel.contactsaggregation.StandardFields;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Loïc LEUILLIOT on 06/03/2015.
 */
public class GoogleProvider extends Provider {

    // Debug parameters
    private static final String LOGIN_HINT = "e.elfaus@gmail.com";
    private static GoogleProvider instance = new GoogleProvider();
    private ArrayList<Contact> _contactList = new ArrayList<Contact>();
    // OAuth parameters
    private long _timeout; // timestamp of timeout token
    private String _accessToken; // current access token

    private GoogleProvider() {
        this._timeout = 0;
        this._accessToken = "";
        this.register("Google");
    }

    public static GoogleProvider getInstance() {
        if (instance == null) {
            instance = new GoogleProvider();
        }
        return instance;
    }

    // - - - - -
    // OAuth section

    @Override
    public String getAuthUri()
    {
        return GoogleHelperProvider.getAuthURI(LOGIN_HINT);
    }

    @Override
    public String getToken()
    {
        return this._accessToken;
    }

    @Override
    public Long getTimeout()
    {
        return this._timeout;
    }

    @Override
    public void pullContacts() {
        String fetchContactUri = GoogleHelperProvider.getAllContactURI(LOGIN_HINT);

        // call async request
        try {
            // setup the URL
            URL url = new URL(fetchContactUri);
            // call async request using this url
            new GoogleAsyncGetContact(this._contactList).execute(url);
        } catch (MalformedURLException e) {
            Log.e("[GOOGLE-GET]", e.getMessage());
        }

    }

    @Override
    public void authCallback(String uri) {
        final long currentTimeStamp = System.currentTimeMillis()/1000;
        final HashMap<String, String> params = HelperProvider.getParameters(uri);

        this._accessToken = params.get("access_token");
        if (params.containsKey("expires_in")) {
            this._timeout = Long.parseLong(params.get("expires_in")) + currentTimeStamp;
        } else {
            this._timeout = 0;
        }
    }

    @Override
    public boolean isUpdatable() { return true; }

    // - - - - -
    // API methods / endpoints

    @Override
    // TODO : implement
    public void deleteContact(Contact c) {
        String deleteContactUri = GoogleHelperProvider.deleteContactURI(LOGIN_HINT, c);

        try {
            URL url = new URL(deleteContactUri);
            new GoogleAsyncDeleteContact(c).execute(url);
        } catch (MalformedURLException e) {
            Log.e("[GOOGLE-DEL]", e.getMessage());
        }
    }

    @Override
    // TODO : implement
    public void deleteContact(List<Contact> c) {

    }

    @Override
    // TODO : implement
    public Contact getContact(Contact c) {
        for (Contact tmpContact : this._contactList) {
            if (tmpContact.getField(StandardFields.FN) == c.getField(StandardFields.FN)) {
                return tmpContact;
            } else if (tmpContact.getField(StandardFields.TITLE) == c.getField(StandardFields.TITLE)) {
                return tmpContact;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Contact> getContacts() {
        return this._contactList;
    }

    @Override
    // TODO : implement
    public void updateContact(Contact c) {
        String putContactUri = GoogleHelperProvider.updateContactURI(LOGIN_HINT, c);
        try {
            URL url = new URL(putContactUri);
            new GoogleAsyncUpdateContact(c).execute(url);
        } catch (MalformedURLException e) {
            Log.e("[GOOGLE-PUT]", e.getMessage());
        }
    }

    @Override
    // TODO : implement
    public void addContact(Contact c) {}

    // - - - - -

    /**
     * Created by Loïc LEUILLIOT on 08/05/2015.
     * https://developers.google.com/google-apps/contacts/v3/#retrieving_all_contacts
     */
    private class GoogleAsyncGetContact extends AsyncTask<URL, Integer, String> {

        private ArrayList<Contact> _contacts;

        public GoogleAsyncGetContact(ArrayList<Contact> data) {
            this._contacts = data;
        }

        @Override
        /**
         * Return a body http content or an empty string
         */
        protected String doInBackground(URL... getUrls) {
            String response = "";

            // Iterate over request packet
            for (URL url : getUrls) {

                // Init a new http socket
                HttpURLConnection urlConnection;

                try {
                    // open the socket using the found url
                    urlConnection = (HttpURLConnection) url.openConnection();
                    // adding the authentication header with our seed
                    urlConnection.addRequestProperty("Authorization", "Bearer " + _accessToken);
                    // get the stream
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    // TODO : use response code / response message in order to manage authentication exception
                    // get the stream content and store it in order to return it after the connection
                    response = HelperProvider.readStream(in);
                    // close the socket
                    urlConnection.disconnect();
                } catch (IOException e) {
                    Log.e("[GOOGLE-GET]", e.getMessage());
                }
            }

            return response;
        }

        @Override
        /**
         * Iterate over the JSON result in order to isolate contact data and store them into
         * the arrayList
         */
        protected void onPostExecute(String result) {
            Log.d("[GOOGLE-GET]", result);
            try {
                JSONObject reader = new JSONObject(result);

                // Search for the contacts array in feed root
                JSONArray contactsArray = (JSONArray) HelperProvider.getJSONObjectByPath(reader, "feed.entry");

                if (contactsArray != null) {

                    for (int i = 0; i < contactsArray.length(); i++) {

                        Contact c = new Contact();
                        // Get contact object from contacts array
                        JSONObject contactObject = contactsArray.getJSONObject(i);

                        // - - - - -

                        // Search for name fields and isolate fullName value
                        Object fullName = HelperProvider.getJSONObjectByPath(contactObject, "title.$t");

                        if (fullName != null) {
                            // Affect values to fields
                            c.setField(StandardFields.TITLE, fullName.toString());
                        }

                        // - - - - -

                        // A contact can have multiple mail, so prepare to iterate on them
                        JSONArray mailArray = (JSONArray) HelperProvider.getJSONObjectByPath(contactObject, "gd$email");

                        if (mailArray != null) {
                            for (int mailIterator = 0; mailIterator < mailArray.length(); mailIterator++) {
                                JSONObject mailObject = mailArray.getJSONObject(mailIterator);
                                c.setField(StandardFields.EMAIL, mailObject.get("address").toString());
                            }
                        }

                        // - - - - -

                        // Google stores uri in contact card. One of them is the contact photo and has a type attribute set to image/*
                        JSONArray linkArray = (JSONArray) HelperProvider.getJSONObjectByPath(contactObject, "link");

                        if (linkArray != null) {
                            for (int j=0; i < linkArray.length(); i++) {
                                JSONObject linkObject = linkArray.getJSONObject(i);
                                if (linkObject.get("type").equals("image/*")) {
                                    c.setField(StandardFields.PHOTO, linkObject.getString("href"));
                                }
                            }
                        }

                        // - - - - -

                        Log.d("[CONTACT]", c.toString());

                        // insert the contact to the list
                        this._contacts.add(c);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Created by Loïc LEUILLIOT on 08/05/2015.
     * https://developers.google.com/google-apps/contacts/v3/#updating_contacts
     */
    private class GoogleAsyncUpdateContact extends AsyncTask<URL, Integer, String> {

        private Contact _contact;

        public GoogleAsyncUpdateContact(Contact data) {
            this._contact = data;
        }

        @Override
        protected String doInBackground(URL... putUrls) {
            String response = "";

            // Iterate over request packet
            for (URL url : putUrls) {

                // Init a new http socket
                HttpURLConnection urlConnection;

                try {
                    // open the socket using the found url
                    urlConnection = (HttpURLConnection) url.openConnection();
                    // adding the authentication header with our seed
                    urlConnection.addRequestProperty("Authorization", "Bearer " + _accessToken);
                    // get the stream
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    // TODO : use response code / response message in order to manage authentication exception
                    // get the stream content and store it in order to return it after the connection
                    response = HelperProvider.readStream(in);
                    // close the socket
                    urlConnection.disconnect();
                } catch (IOException e) {
                    Log.e("[GOOGLE-GET]", e.getMessage());
                }
            }

            return response;
        }

    }

    /**
     * Created by Loïc LEUILLIOT on 29/05/2015.
     * https://developers.google.com/google-apps/contacts/v3/#deleting_contacts
     */
    private class GoogleAsyncDeleteContact extends AsyncTask<URL, Integer, String> {

        private Contact _contact;

        public GoogleAsyncDeleteContact(Contact data) {
            this._contact = data;
        }

        @Override
        protected String doInBackground(URL... deleteUrls) {
            String response = "";

            // Iterate over request packet
            for (URL url : deleteUrls) {

                // Init a new http socket
                HttpURLConnection urlConnection;

                try {
                    // open the socket using the found url
                    urlConnection = (HttpURLConnection) url.openConnection();
                    // adding the authentication header with our seed
                    urlConnection.addRequestProperty("Authorization", "Bearer " + _accessToken);
                    // set the socket to a http-delete
                    urlConnection.setDoInput(true);
                    urlConnection.setRequestMethod("DELETE");
                    // get the stream
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    // TODO : use response code / response message in order to manage authentication exception
                    // get the stream content and store it in order to return it after the connection
                    response = HelperProvider.readStream(in);
                    // close the socket
                    urlConnection.disconnect();
                } catch (IOException e) {
                    Log.e("[GOOGLE-GET]", e.getMessage());
                }
            }

            return response;
        }

    }
}
