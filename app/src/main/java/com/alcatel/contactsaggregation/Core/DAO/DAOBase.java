package com.alcatel.contactsaggregation.Core.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Base class for Data Access Objects (DAO)
 */
public abstract class DAOBase {
    private final static int VERSION = 1;
    private final static String NAME = "ContactsAggregation.db";

    protected SQLiteDatabase m_db;
    protected DatabaseHandler m_handler;

    public DAOBase(Context context) {
        m_handler = new DatabaseHandler(context, NAME, null, VERSION);
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