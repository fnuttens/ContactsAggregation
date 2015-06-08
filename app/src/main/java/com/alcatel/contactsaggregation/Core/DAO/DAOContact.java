package com.alcatel.contactsaggregation.Core.DAO;

import android.content.ContentValues;
import android.database.Cursor;

import com.alcatel.contactsaggregation.Core.Models.Contact;
import com.alcatel.contactsaggregation.Providers.Provider;
import com.alcatel.contactsaggregation.StandardFields;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Data Access Object for contacts
 */
public class DAOContact extends DAOBase {

    public DAOContact(DatabaseHandler dbHandler) {
        super(dbHandler);
    }

    public void insert(Contact newContact, String providerId) {

        // Contact insert
        m_db.execSQL("INSERT OR IGNORE INTO " + m_handler.CONTACT_TABLE_NAME + " VALUES('" + newContact.getUniqueId() + "', '" + providerId + "', 1)");

        // Contact data insert
        this.insertContactDatas(newContact);
    }

    public void delete(Contact contact) {
        this.deleteContactDatas(contact);
        m_db.delete(m_handler.CONTACT_TABLE_NAME, m_handler.CONTACT_ID_COLUMN + "= '" + contact.getUniqueId() + "'", null);
    }

    public void update(Contact contact) {
        this.updateContactDatas(contact);
    }

    public ArrayList<Contact> getContactsFromProvider(String providerId) {

        Cursor c = m_db.query(m_handler.CONTACT_TABLE_NAME,
                new String[] { m_handler.CONTACT_ID_COLUMN, m_handler.PROVIDER_ID_COLUMN },
                m_handler.PROVIDER_ID_COLUMN + "= '" + providerId + "'",
                null, null, null, null);

        ArrayList<Contact> providerContacts = new ArrayList<>();

        if(c == null || c.getCount() == 0)  return providerContacts;

        while(c.moveToNext()) {

            String nextContactId = c.getString(0);
            HashMap<StandardFields, String> contactDatas = getContactDatasFromContactId(nextContactId);
            providerContacts.add(new Contact(nextContactId, contactDatas));
        }

        return providerContacts;
    }

    public HashMap<StandardFields, String> getContactDatas(Contact contact) {
        Cursor c = m_db.query(m_handler.CONTACTDATA_TABLE_NAME,
                new String[]{m_handler.FIELD_KEY_COLUMN, m_handler.CONTACTDATA_VALUE_COLUMN},
                m_handler.CONTACT_ID_COLUMN + "= '" + contact.getUniqueId() + "'",
                null, null, null, null);

        HashMap<StandardFields, String> contactDatas = new HashMap<>();

        if(c == null || c.getCount() == 0)  return contactDatas;

        while (c.moveToNext()) {
            contactDatas.put(StandardFields.valueOf(c.getString(0)), c.getString(1));
        }

        return contactDatas;
    }

    private void insertContactDatas(Contact contact) {

        String contactId = contact.getUniqueId();

        for (Map.Entry<StandardFields, String> entry : contact.getFields().entrySet()) {
            m_db.execSQL("INSERT OR IGNORE INTO " + m_handler.CONTACTDATA_TABLE_NAME + " VALUES ('" + entry.getValue() + "', '" + contactId + "', '" + entry.getKey().name()+ "')");
        }
    }

    private void deleteContactDatas(Contact contact) {
        m_db.delete(m_handler.CONTACTDATA_TABLE_NAME, m_handler.CONTACT_ID_COLUMN + "= '" + contact.getUniqueId() + "'", null);
    }

    private void updateContactDatas(Contact contact) {

        String contactId = contact.getUniqueId();

        for (Map.Entry<StandardFields, String> entry : contact.getFields().entrySet()) {

            ContentValues values = new ContentValues();
            values.put(m_handler.CONTACTDATA_VALUE_COLUMN, entry.getValue());
            values.put(m_handler.CONTACT_ID_COLUMN, contactId);
            values.put(m_handler.FIELD_KEY_COLUMN, entry.getKey().name());

            m_db.update(m_handler.CONTACTDATA_TABLE_NAME, values, m_handler.CONTACT_ID_COLUMN + "= '" + contactId + "'", null);
        }
    }

    private HashMap<StandardFields, String> getContactDatasFromContactId(String contactId) {

        Cursor c = m_db.query(m_handler.CONTACTDATA_TABLE_NAME,
                null,
                m_handler.CONTACT_ID_COLUMN + "= '" + contactId + "'",
                null, null, null, null);

        HashMap<StandardFields, String> contactDatas = new HashMap<>();

        if(c == null || c.getCount() == 0)  return contactDatas;

        while (c.moveToNext()) {
            contactDatas.put(StandardFields.valueOf(c.getString(0)), c.getString(1));
        }

        return contactDatas;
    }
}
