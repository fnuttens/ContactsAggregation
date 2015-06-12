package com.alcatel.contactsaggregation.Providers;

import com.alcatel.contactsaggregation.Core.DAO.DAOProvider;
import com.alcatel.contactsaggregation.Core.Models.Contact;
import com.alcatel.contactsaggregation.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public abstract class Provider {

    /**
     * Use by activities to know if the provider publish endpoints which enable to edit or delete
     * data from its API
     * @return True if such endpoints are available, False in other cases
     */
    public abstract boolean isUpdatable();

    /**
     * Has the logic to delete a contact from the provider
     * Can do nothing if the provider don't give the ability to delete data to its API
     * This method is called by activities if isUpdatable() method return true
     * @param c The contact to delete
     */
    public abstract void deleteContact(Contact c);

    /**
     * Has the logic to delete a contact from the provider
     * Can do nothing if the provider don't give the ability to delete data to its API
     * This method is called by activities if isUpdatable() method return true
     * @param c The list of contacts to be deleted
     */
    public abstract void deleteContact(List<Contact> c);

    /**
     * Return a specific contact which has been pull from the API by activities
     * @param c The contact from which getting data
     * @return Contact object with hydrated data
     */
    public abstract Contact getContact(Contact c);

    /**
     * Return the list of contact which has been pull from the API by activities
     * @return Contact object list with hydrated data
     */
    public abstract ArrayList<Contact> getContacts();

    /**
     * Has the logic to edit a contact from the provider
     * Can do nothing if the provider don't give the ability to edit data to its API
     * This method is called by activities if isUpdatable() method return true
     * @param c Contact object with data to send to the API
     */
    public abstract void updateContact(Contact c);

    /**
     * Has the logic to add a contact to the provider
     * Can do nothing if the provider don't give the ability to add data to its API
     * This method is called by activities if isUpdatable() method return true
     * @param c Contact Object with data to the to the API
     */
    public abstract void addContact(Contact c);

    /**
     * Has the logic to take back data from the provider API for all available contacts
     */
    public abstract void pullContacts();

    /**
     * This method allow the application to get the auth endpoint uri in order
     * to run it inside a view and getting a new token
     * @return OAuth authentication endpoint uri with parameters
     */
    public abstract String getAuthUri();

    /**
     * Has the logic to update attributes of the provider instance like timeout and token
     * @param uri The uri returned by the API
     */
    public abstract void authCallback(String uri);

    /**
     * @return The current token set to the Provider
     */
    public abstract String getToken();

    /**
     * @return The current timeout set to the Provider
     */
    public abstract Long getTimeout();

    /**
     * @param provider
     */
    public void register(Provider provider) {
        DAOProvider daoProvider = new DAOProvider(MainActivity.bdd);
        daoProvider.open();

        // If the provider already exists in the database, we don't have to insert it a second time
        if (daoProvider.exists(provider)) {
            return;
        }

        daoProvider.insert(provider);
        daoProvider.close();
    }
}
