package com.alcatel.contactsaggregation.Core.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.alcatel.contactsaggregation.Core.Models.Contact;

/**
 * Created by Florent on 06/06/2015.
 */
public class DAOContact extends DAOBase {

    public DAOContact(Context context) {
        super(context);
    }

    public void insert(Contact newContact) {

        ContentValues values = new ContentValues();
        values.put(m_handler.PROVIDER_ID_COLUMN, newContact.getClass().getName());
        values.put(m_handler.PROVIDER_PACKAGE_COLUMN, "package");

        SQLiteDatabase db = getDb();
        db.insert(m_handler.PROVIDER_TABLE_NAME, null, values);
        close();
    }

    public void delete(Contact contact) {

    }
}
