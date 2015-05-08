package alcatel.contactsaggregation.Providers;

import java.util.ArrayList;
import java.util.List;

import alcatel.contactsaggregation.Contact;

/**
 * Created by elfaus on 06/03/2015.
 */
public abstract class Provider {

    private String name;
    private String version;
    private boolean updatable;

    public abstract void deleteContact(Contact c);

    public abstract void deleteContact(List<Contact> c);

    public abstract Contact getContact(Contact c);

    public abstract ArrayList<Contact> getContact();

    public abstract void updateContact(Contact c);

    public abstract void setCredentials();

    public abstract void addContact(Contact c);

    private String getInstalledVersion()
    {
        return "";
    }

    public boolean isUpdatable() {
        return updatable;
    }

    public void setUpdatable(boolean updatable) {
        this.updatable = updatable;
    }

    public abstract String getCurrentVersion();

    /**
     * This method allow the application to get the auth endpoint uri in order
     * to run it inside a view getting a new token
     * @return OAuth auth endpoint uri with parameters
     */
    public abstract String getAuthUri();

    public abstract String getToken();
    public abstract Long getTimeout();

    // todo implement create, alter table
    public void load()
    {
        this.getInstalledVersion();
        this.getCurrentVersion();
    }
}
