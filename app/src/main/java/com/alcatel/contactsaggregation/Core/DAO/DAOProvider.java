package com.alcatel.contactsaggregation.Core.DAO;

import android.content.ContentValues;
import android.content.Context;

import com.alcatel.contactsaggregation.Providers.Provider;

/**
 * Provider DAO
 */
public class DAOProvider extends DAOBase {

    public DAOProvider(Context context) {
        super(context);
    }

    //TODO: finish insert method
    public long insert(Provider newProvider) {

        ContentValues values = new ContentValues();
        values.put(m_handler.PROVIDER_ID_COLUMN, newProvider.getClass().getName());
        values.put(m_handler.PROVIDER_PACKAGE_COLUMN, "package");

        return m_db.insert(m_handler.PROVIDER_TABLE_NAME, null, values);
    }

    public void delete(Provider provider) {

    }
}
