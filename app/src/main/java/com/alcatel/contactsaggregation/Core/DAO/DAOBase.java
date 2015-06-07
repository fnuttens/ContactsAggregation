package com.alcatel.contactsaggregation.Core.DAO;

import android.database.sqlite.SQLiteDatabase;

/**
 * Base class for Data Access Objects (DAO)
 */
public abstract class DAOBase {
    protected SQLiteDatabase m_db;
    protected DatabaseHandler m_handler;

    public DAOBase(DatabaseHandler dbHandler) {
        m_handler = dbHandler;
    }

    public SQLiteDatabase getDb() {
        return m_db;
    }

    public void open() {
        m_db = m_handler.getWritableDatabase();
    }

    public void close() {
        m_db.close();
    }
}