package com.alcatel.contactsaggregation.Providers.Facebook;

import java.util.ArrayList;
import java.util.List;

import com.alcatel.contactsaggregation.Core.Models.Contact;
import com.alcatel.contactsaggregation.Providers.Provider;

/**
 * Created by Loïc LEUILLIOT on 06/03/2015.
 */
public class FacebookProvider extends Provider {

    private static FacebookProvider instance = new FacebookProvider();

    private ArrayList<Contact> _contactList = new ArrayList<Contact>();

    // OAuth parameters
    private long _timeout; // timestamp of timeout token
    private String _accessToken; // current access token

    private FacebookProvider() {
        this._timeout = 0;
        this._accessToken = "";
    }

    public static FacebookProvider getInstance() {
        if (instance == null) {
            instance = new FacebookProvider();
        }
        return instance;
    }

    // - - - - -
    // OAuth section

    @Override
    public String getAuthUri()
    {
        return "";
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
    public void pullContacts() {}

    @Override
    public void authCallback(String uri) {}

    @Override
    public boolean isUpdatable() { return true; }

    // - - - - -
    // API methods / endpoints

    @Override
    // TODO : implement
    public void deleteContact(Contact c) {}

    @Override
    // TODO : implement
    public void deleteContact(List<Contact> c) {}

    @Override
    // TODO : implement
    public Contact getContact(Contact c) {return null;}

    @Override
    public ArrayList<Contact> getContacts() {
        return this._contactList;
    }

    @Override
    // TODO : implement
    public void updateContact(Contact c) {}

    @Override
    // TODO : implement
    public void addContact(Contact c) {}
}
