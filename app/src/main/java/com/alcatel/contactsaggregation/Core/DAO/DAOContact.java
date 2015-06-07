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
 * Created by Florent on 06/06/2015.
 */
public class DAOContact extends DAOBase {

    public DAOContact(DatabaseHandler dbHandler) {
        super(dbHandler);
    }

    public void insert(Contact newContact) {

        // Insert new merged contact
        m_db.insert(m_handler.MERGEDCONTACT_TABLE_NAME, null, null);

        // Contact insert
        ContentValues values = new ContentValues();
        values.put(m_handler.CONTACT_ID_COLUMN, newContact.getUniqueId());
        values.put(m_handler.MERGEDCONTACT_ID_COLUMN, 1);

        m_db.insert(m_handler.CONTACT_TABLE_NAME, null, values);
        this.insertContactDatas(newContact);
    }

    public void delete(Contact contact) {
        this.deleteContactDatas(contact);
        m_db.delete(m_handler.CONTACT_TABLE_NAME, m_handler.CONTACT_ID_COLUMN + "= '" + contact.getUniqueId() + "'", null);
    }

    public void update(Contact contact) {
        this.updateContactDatas(contact);
    }

    public ArrayList<Contact> getContactsFromProvider(Provider provider) {

        String providerId = provider.getClass().getName();
        ArrayList<Contact> providerContacts = new ArrayList<>();

        Cursor c = m_db.query(m_handler.CONTACTDATA_TABLE_NAME,
                null,
                m_handler.PROVIDER_ID_COLUMN + "= '" + providerId + "'",
                null, null, null, null);

        while (c.moveToNext()) {
            //TODO: create contacts from datas
        }

        c.close();
        return providerContacts;
    }

    public HashMap<StandardFields, String> getContactDatas(Contact contact) {
        Cursor c = m_db.query(m_handler.CONTACTDATA_TABLE_NAME,
                new String[]{m_handler.FIELD_KEY_COLUMN, m_handler.CONTACTDATA_VALUE_COLUMN},
                m_handler.CONTACT_ID_COLUMN + "= '" + contact.getUniqueId() + "'",
                null, null, null, null);

        HashMap<StandardFields, String> contactDatas = new HashMap<>();

        while (c.moveToNext()) {
            contactDatas.put(StandardFields.valueOf(c.getString(0)), c.getString(1));
        }

        c.close();
        return contactDatas;
    }

    private void insertContactDatas(Contact contact) {

        String contactId = contact.getUniqueId();

        for (Map.Entry<StandardFields, String> entry : contact.getFields().entrySet()) {

            ContentValues values = new ContentValues();
            values.put(m_handler.CONTACTDATA_VALUE_COLUMN, entry.getValue());
            values.put(m_handler.PROVIDER_ID_COLUMN, "TODO");
            values.put(m_handler.CONTACT_ID_COLUMN, contactId);
            values.put(m_handler.FIELD_KEY_COLUMN, entry.getKey().name());

            m_db.insert(m_handler.CONTACTDATA_TABLE_NAME, null, values);
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
            values.put(m_handler.PROVIDER_ID_COLUMN, "TODO");
            values.put(m_handler.CONTACT_ID_COLUMN, contactId);
            values.put(m_handler.FIELD_KEY_COLUMN, entry.getKey().name());

            m_db.update(m_handler.CONTACTDATA_TABLE_NAME, values, m_handler.CONTACT_ID_COLUMN + "= '" + contactId + "'", null);
        }
    }
}
