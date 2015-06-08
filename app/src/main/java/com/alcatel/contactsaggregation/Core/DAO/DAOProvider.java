package com.alcatel.contactsaggregation.Core.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import com.alcatel.contactsaggregation.Providers.Provider;

/**
 * Provider DAO
 */
public class DAOProvider extends DAOBase {

    public DAOProvider(DatabaseHandler dbHandler) {
        super(dbHandler);
    }

    public void insert(Provider newProvider) {
        m_db.execSQL("INSERT OR IGNORE INTO " + m_handler.PROVIDER_TABLE_NAME + " VALUES('" + newProvider.getClass().getSimpleName() + "', '" +
        newProvider.getClass().getPackage().getName() + "');");
    }

    public int delete(Provider provider) {

        String id = provider.getClass().getSimpleName();
        return m_db.delete(m_handler.PROVIDER_TABLE_NAME, m_handler.PROVIDER_ID_COLUMN + "= '" + id + "'", null);
    }

    public int update(String idPro, Provider provider) {

        ContentValues values = new ContentValues();
        values.put(m_handler.PROVIDER_ID_COLUMN, provider.getClass().getSimpleName());
        values.put(m_handler.PROVIDER_PACKAGE_COLUMN, provider.getClass().getPackage().getName());

        return m_db.update(m_handler.PROVIDER_TABLE_NAME, values, m_handler.PROVIDER_ID_COLUMN + "= '" + idPro + "'", null);
    }

    public void insertCredential(String login, Provider provider, String token, long timeout) {
        m_db.execSQL("INSERT OR IGNORE INTO " + m_handler.CREDENTIAL_TABLE_NAME + " VALUES('" + login + "', '" + provider.getClass().getName() + "', '" + token + "'," + timeout + ");");
    }

    public boolean exists(Provider provider) {

        String id = provider.getClass().getSimpleName();

        Cursor providers = m_db.query(m_handler.PROVIDER_TABLE_NAME,
                new String[]{m_handler.PROVIDER_ID_COLUMN},
                m_handler.PROVIDER_ID_COLUMN + "= '" + id + "'",
                null, null, null, null);

        //providers.close();
        return (providers.getCount() != 0);
    }
}
