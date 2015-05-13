package alcatel.contactsaggregation.Providers.Google;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import alcatel.contactsaggregation.Contact;
import alcatel.contactsaggregation.StandardFields;
import alcatel.contactsaggregation.Providers.HelperProvider;
import alcatel.contactsaggregation.Providers.Provider;

/**
 * Created by Loïc LEUILLIOT on 06/03/2015.
 */
public class GoogleProvider extends Provider {

    private static GoogleProvider instance = new GoogleProvider();
    private String m_providerContactId;

    ArrayList<Contact> contactList = new ArrayList<Contact>();

    // Debug parameters
    private static final String LOGIN_HINT = "e.elfaus@gmail.com";

    // OAuth parameters
    private long _timeout; // timestamp of timeout token
    private String _accessToken; // current access token

    public String getProviderContactId() {
        return m_providerContactId;
    }

    public void setProviderContactId(String m_providerContactId) {
        this.m_providerContactId = m_providerContactId;
    }

    public static GoogleProvider getInstance() {
        if (instance == null) {
            instance = new GoogleProvider();
        }
        return instance;
    }

    // TODO : implement
    private GoogleProvider() {
    }

    @Override
    // TODO : implement
    public void deleteContact(Contact c) {

    }

    @Override
    // TODO : implement
    public void deleteContact(List<Contact> c) {

    }

    @Override
    // TODO : implement
    public Contact getContact(Contact c) {

        return null;
    }

    @Override
    // TODO : implement
    public ArrayList<Contact> getContact() {

        // TODO : debug
        GoogleHelperProvider helper = GoogleHelperProvider.getInstance();

        String fetchContactUri = helper.getAllContactURI(LOGIN_HINT);

        // call async request
        new GoogleAsyncGet().execute(new String[] {fetchContactUri, this._accessToken});

        return this.contactList;
    }

    @Override
    // TODO : implement
    public void updateContact(Contact c) {}

    @Override
    // TODO : implement
    public void setCredentials() {}

    @Override
    // TODO : implement
    public void addContact(Contact c) {}

    @Override
    // TODO : implement
    public String getCurrentVersion() {
        return null;
    }

    @Override
    public String getAuthUri()
    {
        return GoogleHelperProvider.getInstance().getAuthURI(LOGIN_HINT);
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

    protected void setContactList(String json) {
        try {
            // TODO : implement remote fetch API
            JSONObject reader = new JSONObject(json);

            // Search for the contacts array in feed root
            JSONArray contactsArray = (JSONArray) HelperProvider.getJSONObjectByPath(reader, "feed.entry");

            if (contactsArray != null) {

                for (int i = 0; i < contactsArray.length(); i++) {
                    Contact c = new Contact();
                    // Get contact object from contacts array
                    JSONObject contactObject = contactsArray.getJSONObject(i);

                    // Search for name fields and isolate fullName value
                    Object fullName = HelperProvider.getJSONObjectByPath(contactObject, "title.$t");

                    if (fullName != null) {
                        // Affect values to fields
                        c.setField(StandardFields.TITLE, fullName.toString());
                    }

                    JSONArray addressArray = (JSONArray) HelperProvider.getJSONObjectByPath(contactObject, "gd$email");

                    if (addressArray != null) {
                        JSONObject addressObject = addressArray.getJSONObject(0);
                        c.setField(StandardFields.EMAIL, addressObject.get("address").toString());
                    }

                    Log.d("[CONTACT]", c.toString());

                    // insert the contact to the list
                    this.contactList.add(c);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
