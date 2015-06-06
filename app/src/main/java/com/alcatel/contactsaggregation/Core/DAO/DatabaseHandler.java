package com.alcatel.contactsaggregation.Core.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Class responsible for creating the database
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    // TABLE Credential
    //------------------------------------------------------------------------------------------------------------------
    public final static String CREDENTIAL_TABLE_NAME = "Credential";
    public final static String CREDENTIAL_ID_COLUMN = "idCre";
    public final static String CREDENTIAL_LOGIN_COLUMN = "login";
    public final static String CREDENTIAL_TOKEN_COLUMN = "token";
    public final static String CREDENTIAL_TIMEOUT_COLUMN = "timeout";
    public final static String CREDENTIAL_TABLE_CREATE = "CREATE TABLE" + CREDENTIAL_TABLE_NAME + "(" +
            CREDENTIAL_ID_COLUMN + " INTEGER PRIMARY KEY," +
            CREDENTIAL_LOGIN_COLUMN + " TEXT NOT NULL," +
            CREDENTIAL_TOKEN_COLUMN + " TEXT NOT NULL," +
            CREDENTIAL_TIMEOUT_COLUMN + " TEXT);";
    public final static String CREDENTIAL_TABLE_DROP = "DROP TABLE IF EXISTS " + CREDENTIAL_TABLE_NAME + ";";

    // TABLE Provider
    //------------------------------------------------------------------------------------------------------------------
    public final static String PROVIDER_TABLE_NAME = "Provider";
    public final static String PROVIDER_ID_COLUMN = "idPro";
    public final static String PROVIDER_PACKAGE_COLUMN = "package";
    public final static String PROVIDER_TABLE_CREATE = "CREATE TABLE " + PROVIDER_TABLE_NAME + "(" +
            PROVIDER_ID_COLUMN + " TEXT PRIMARY KEY," +
            CREDENTIAL_ID_COLUMN + " INTEGER," +
            PROVIDER_PACKAGE_COLUMN + " TEXT," +
            "FOREIGN KEY(" + CREDENTIAL_ID_COLUMN + ") REFERENCES " +
            CREDENTIAL_TABLE_NAME + "(" + CREDENTIAL_ID_COLUMN + ");";
    public final static String PROVIDER_TABLE_DROP = "DROP TABLE IF EXISTS " + PROVIDER_TABLE_NAME + ";";

    // TABLE Field
    //------------------------------------------------------------------------------------------------------------------
    public final static String FIELD_TABLE_NAME = "Field";
    public final static String FIELD_KEY_COLUMN = "key";
    public final static String FIELD_DESCRIPTION_COLUMN = "description";
    public final static String FIELD_TABLE_CREATE = "CREATE TABLE " + FIELD_TABLE_NAME + "(" +
            FIELD_KEY_COLUMN + " TEXT PRIMARY KEY," +
            FIELD_DESCRIPTION_COLUMN + " TEXT);";
    public final static String FIELD_TABLE_DROP = "DROP TABLE IF EXISTS " + FIELD_TABLE_NAME + ";";

    // TABLE MergedContact
    //------------------------------------------------------------------------------------------------------------------
    public final static String MERGEDCONTACT_TABLE_NAME = "MergedContact";
    public final static String MERGEDCONTACT_ID_COLUMN = "idMer";
    public final static String MERGEDCONTACT_TABLE_CREATE = "CREATE TABLE " + MERGEDCONTACT_TABLE_NAME + "(" +
            MERGEDCONTACT_ID_COLUMN + " TEXT PRIMARY KEY)";
    public final static String MERGEDCONTACT_TABLE_DROP = "DROP TABLE IF EXISTS " + MERGEDCONTACT_TABLE_NAME + ";";

    // TABLE Contact
    //------------------------------------------------------------------------------------------------------------------
    public final static String CONTACT_TABLE_NAME = "Contact";
    public final static String CONTACT_ID_COLUMN = "idCon";
    public final static String CONTACT_TABLE_CREATE = "CREATE TABLE " + CONTACT_TABLE_NAME + "(" +
            CONTACT_ID_COLUMN + " TEXT PRIMARY KEY," +
            MERGEDCONTACT_ID_COLUMN + " TEXT," +
            "FOREIGN KEY(" + MERGEDCONTACT_ID_COLUMN + ") REFERENCES " +
            MERGEDCONTACT_TABLE_NAME + "(" + MERGEDCONTACT_ID_COLUMN + ");";
    public final static String CONTACT_TABLE_DROP = "DROP TABLE IF EXISTS " + CONTACT_TABLE_NAME + ";";

    // TABLE ContactData
    //------------------------------------------------------------------------------------------------------------------
    public final static String CONTACTDATA_TABLE_NAME = "ContactData";
    public final static String CONTACTDATA_VALUE_COLUMN = "value";
    public final static String CONTACTDATA_TABLE_CREATE = "CREATE TABLE " + CONTACTDATA_TABLE_NAME + "(" +
            CONTACTDATA_VALUE_COLUMN + " TEXT PRIMARY KEY," +
            PROVIDER_ID_COLUMN + " TEXT," +
            CONTACT_ID_COLUMN + " TEXT," +
            FIELD_KEY_COLUMN + " TEXT," +
            "FOREIGN KEY(" + PROVIDER_ID_COLUMN + ") REFERENCES " +
            PROVIDER_TABLE_NAME + "(" + PROVIDER_ID_COLUMN + ")," +
            "FOREIGN KEY(" + CONTACT_ID_COLUMN + ") REFERENCES" +
            CONTACT_TABLE_NAME + "(" + CONTACT_ID_COLUMN + ")," +
            "FOREIGN KEY(" + FIELD_KEY_COLUMN + ") REFERENCES " +
            FIELD_TABLE_NAME + "(" + FIELD_KEY_COLUMN + ");";
    public final static String CONTACTDATA_TABLE_DROP = "DROP TABLE IF EXISTS " + FIELD_TABLE_NAME + ";";

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREDENTIAL_TABLE_CREATE);
        db.execSQL(PROVIDER_TABLE_CREATE);
        db.execSQL(FIELD_TABLE_CREATE);
        db.execSQL(MERGEDCONTACT_TABLE_CREATE);
        db.execSQL(CONTACT_TABLE_CREATE);
        db.execSQL(CONTACTDATA_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(CONTACTDATA_TABLE_DROP);
        db.execSQL(CONTACT_TABLE_DROP);
        db.execSQL(MERGEDCONTACT_TABLE_DROP);
        db.execSQL(FIELD_TABLE_DROP);
        db.execSQL(PROVIDER_TABLE_DROP);
        db.execSQL(CREDENTIAL_TABLE_DROP);
        onCreate(db);
    }
}
