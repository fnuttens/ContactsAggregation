package com.alcatel.contactsaggregation.Core.DAO;

import android.content.ContentValues;
import android.database.Cursor;

import com.alcatel.contactsaggregation.Providers.Provider;

/**
 * Provider DAO
 */
public class DAOProvider extends DAOBase {

    public DAOProvider(DatabaseHandler dbHandler) {
        super(dbHandler);
    }

    public long insert(Provider newProvider) {

        ContentValues values = new ContentValues();
        values.put(m_handler.PROVIDER_ID_COLUMN, newProvider.getClass().getName());
        values.put(m_handler.PROVIDER_PACKAGE_COLUMN, newProvider.getClass().getPackage().getName());

        return m_db.insert(m_handler.PROVIDER_TABLE_NAME, null, values);
    }

    public int delete(Provider provider) {

        String id = provider.getClass().getName();
        return m_db.delete(m_handler.PROVIDER_TABLE_NAME, m_handler.PROVIDER_ID_COLUMN + "=" + id, null);
    }

    public int update(String idPro, Provider provider) {

        ContentValues values = new ContentValues();
        values.put(m_handler.PROVIDER_ID_COLUMN, provider.getClass().getName());
        values.put(m_handler.PROVIDER_PACKAGE_COLUMN, provider.getClass().getPackage().getName());

        return m_db.update(m_handler.PROVIDER_TABLE_NAME, values, m_handler.PROVIDER_ID_COLUMN + "=" + idPro, null);
    }

    //TODO: implement
    public long insertCredential(String login, Provider provider, String token, long timeout) {

        ContentValues values = new ContentValues();
        values.put(m_handler.CREDENTIAL_LOGIN_COLUMN, login);
        values.put(m_handler.PROVIDER_ID_COLUMN, provider.getClass().getName());
        values.put(m_handler.CREDENTIAL_TOKEN_COLUMN, token);
        values.put(m_handler.CREDENTIAL_TIMEOUT_COLUMN, timeout);

        return m_db.insert(m_handler.CREDENTIAL_TABLE_NAME, null, values);
    }

    public boolean exists(Provider provider) {

        String id = provider.getClass().getName();

        Cursor providers = m_db.query(m_handler.PROVIDER_TABLE_NAME,
                new String[]{m_handler.PROVIDER_ID_COLUMN},
                m_handler.PROVIDER_ID_COLUMN + "=" + id,
                null, null, null, null);

        providers.close();
        return (providers.getCount() != 0);
    }
}
