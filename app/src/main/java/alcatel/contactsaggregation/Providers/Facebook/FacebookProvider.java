package alcatel.contactsaggregation.Providers.Facebook;

import java.util.ArrayList;
import java.util.List;

import alcatel.contactsaggregation.Contact;
import alcatel.contactsaggregation.Providers.Provider;

/**
 * Created by elfaus on 06/03/2015.
 */
public class FacebookProvider extends Provider {

    private static FacebookProvider ourInstance = new FacebookProvider();
    private String m_providerContactId;

    // TODO : implement
    private FacebookProvider() {
    }

    public static FacebookProvider getInstance() {
        return ourInstance;
    }

    public String getProviderContactId() {
        return m_providerContactId;
    }

    public void setProviderContactId(String m_providerContactId) {
        this.m_providerContactId = m_providerContactId;
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
        return null;
    }

    @Override
    // TODO : implement
    public void updateContact(Contact c) {

    }

    @Override
    // TODO : implement
    public void setCredentials() {

    }

    @Override
    // TODO : implement
    public void addContact(Contact c) {

    }

    @Override
    // TODO : implement
    public String getCurrentVersion() {
        return null;
    }

    @Override
    public String getAuthUri() {
        return null;
    }

    @Override
    public String getToken() {
        return null;
    }

    @Override
    public Long getTimeout() {
        return null;
    }
}
