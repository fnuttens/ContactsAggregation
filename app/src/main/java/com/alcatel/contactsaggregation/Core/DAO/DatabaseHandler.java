package com.alcatel.contactsaggregation.Core.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Class responsible for creating/updating the database
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    // TABLE Provider
    //------------------------------------------------------------------------------------------------------------------
    public final static String PROVIDER_TABLE_NAME = "Provider";
    public final static String PROVIDER_ID_COLUMN = "idPro";
    public final static String PROVIDER_PACKAGE_COLUMN = "package";
    // TABLE Credential
    //------------------------------------------------------------------------------------------------------------------
    public final static String CREDENTIAL_TABLE_NAME = "Credential";
    public final static String CREDENTIAL_LOGIN_COLUMN = "login";
    public final static String CREDENTIAL_TOKEN_COLUMN = "token";
    public final static String CREDENTIAL_TIMEOUT_COLUMN = "timeout";
    // TABLE Field
    //------------------------------------------------------------------------------------------------------------------
    public final static String FIELD_TABLE_NAME = "Field";
    public final static String FIELD_KEY_COLUMN = "key";
    public final static String FIELD_DESCRIPTION_COLUMN = "description";
    // TABLE MergedContact
    //------------------------------------------------------------------------------------------------------------------
    public final static String MERGEDCONTACT_TABLE_NAME = "MergedContact";
    public final static String MERGEDCONTACT_ID_COLUMN = "idMer";
    // TABLE Contact
    //------------------------------------------------------------------------------------------------------------------
    public final static String CONTACT_TABLE_NAME = "Contact";
    public final static String CONTACT_ID_COLUMN = "idCon";
    // TABLE ContactData
    //------------------------------------------------------------------------------------------------------------------
    public final static String CONTACTDATA_TABLE_NAME = "ContactData";
    public final static String CONTACTDATA_VALUE_COLUMN = "value";
    private final static String PROVIDER_TABLE_CREATE = "CREATE TABLE " + PROVIDER_TABLE_NAME + "(" +
            PROVIDER_ID_COLUMN + " TEXT PRIMARY KEY," +
            PROVIDER_PACKAGE_COLUMN + " TEXT);";
    private final static String PROVIDER_TABLE_DROP = "DROP TABLE IF EXISTS " + PROVIDER_TABLE_NAME + ";";
    private final static String CREDENTIAL_TABLE_CREATE = "CREATE TABLE " + CREDENTIAL_TABLE_NAME + "(" +
            CREDENTIAL_LOGIN_COLUMN + " TEXT PRIMARY KEY," +
            PROVIDER_ID_COLUMN + " TEXT," +
            CREDENTIAL_TOKEN_COLUMN + " TEXT NOT NULL," +
            CREDENTIAL_TIMEOUT_COLUMN + " REAL," +
            "FOREIGN KEY(" + PROVIDER_ID_COLUMN + ") REFERENCES " +
            PROVIDER_TABLE_NAME + "(" + PROVIDER_ID_COLUMN + "));";
    private final static String CREDENTIAL_TABLE_DROP = "DROP TABLE IF EXISTS " + CREDENTIAL_TABLE_NAME + ";";
    private final static String FIELD_TABLE_CREATE = "CREATE TABLE " + FIELD_TABLE_NAME + "(" +
            FIELD_KEY_COLUMN + " TEXT PRIMARY KEY," +
            FIELD_DESCRIPTION_COLUMN + " TEXT);";
    private final static String FIELD_TABLE_DROP = "DROP TABLE IF EXISTS " + FIELD_TABLE_NAME + ";";
    private final static String MERGEDCONTACT_TABLE_CREATE = "CREATE TABLE " + MERGEDCONTACT_TABLE_NAME + "(" +
            MERGEDCONTACT_ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT);";
    private final static String MERGEDCONTACT_TABLE_DROP = "DROP TABLE IF EXISTS " + MERGEDCONTACT_TABLE_NAME + ";";
    private final static String CONTACT_TABLE_CREATE = "CREATE TABLE " + CONTACT_TABLE_NAME + "(" +
            CONTACT_ID_COLUMN + " TEXT PRIMARY KEY," +
            MERGEDCONTACT_ID_COLUMN + " INTEGER," +
            "FOREIGN KEY(" + MERGEDCONTACT_ID_COLUMN + ") REFERENCES " +
            MERGEDCONTACT_TABLE_NAME + "(" + MERGEDCONTACT_ID_COLUMN + "));";
    private final static String CONTACT_TABLE_DROP = "DROP TABLE IF EXISTS " + CONTACT_TABLE_NAME + ";";
    private final static String CONTACTDATA_TABLE_CREATE = "CREATE TABLE " + CONTACTDATA_TABLE_NAME + "(" +
            CONTACTDATA_VALUE_COLUMN + " TEXT PRIMARY KEY," +
            PROVIDER_ID_COLUMN + " TEXT," +
            CONTACT_ID_COLUMN + " TEXT," +
            FIELD_KEY_COLUMN + " TEXT," +
            "FOREIGN KEY(" + PROVIDER_ID_COLUMN + ") REFERENCES " +
            PROVIDER_TABLE_NAME + "(" + PROVIDER_ID_COLUMN + ")," +
            "FOREIGN KEY(" + CONTACT_ID_COLUMN + ") REFERENCES " +
            CONTACT_TABLE_NAME + "(" + CONTACT_ID_COLUMN + ")," +
            "FOREIGN KEY(" + FIELD_KEY_COLUMN + ") REFERENCES " +
            FIELD_TABLE_NAME + "(" + FIELD_KEY_COLUMN + "));";
    private final static String CONTACTDATA_TABLE_DROP = "DROP TABLE IF EXISTS " + FIELD_TABLE_NAME + ";";

    // Initializing data insertion
    //------------------------------------------------------------------------------------------------------------------
    private final String FIELD_TABLE_INSERT = "INSERT INTO " + FIELD_TABLE_NAME + " VALUES('FN', 'first name');" +
            "INSERT INTO " + FIELD_TABLE_NAME + " VALUES('N', 'last name');" +
            "INSERT INTO " + FIELD_TABLE_NAME + " VALUES('NICKNAME', 'nickname');" +
            "INSERT INTO " + FIELD_TABLE_NAME + " VALUES('PHOTO', 'profile picture');" +
            "INSERT INTO " + FIELD_TABLE_NAME + " VALUES('BDAY', 'birth day');" +
            "INSERT INTO " + FIELD_TABLE_NAME + " VALUES('ANNIVERSARY', 'anniversary');" +
            "INSERT INTO " + FIELD_TABLE_NAME + " VALUES('GENDER', 'masculine (M) or feminine (F)');" +
            "INSERT INTO " + FIELD_TABLE_NAME + " VALUES('ADR', 'postal address');" +
            "INSERT INTO " + FIELD_TABLE_NAME + " VALUES('TEL', 'telephone number');" +
            "INSERT INTO " + FIELD_TABLE_NAME + " VALUES('EMAIL', 'email address');" +
            "INSERT INTO " + FIELD_TABLE_NAME + " VALUES('IMPP', '');" +
            "INSERT INTO " + FIELD_TABLE_NAME + " VALUES('LANG', 'language');" +
            "INSERT INTO " + FIELD_TABLE_NAME + " VALUES('TZ', '');" +
            "INSERT INTO " + FIELD_TABLE_NAME + " VALUES('GEO', 'localization');" +
            "INSERT INTO " + FIELD_TABLE_NAME + " VALUES('TITLE', 'title');" +
            "INSERT INTO " + FIELD_TABLE_NAME + " VALUES('ROLE', 'role');" +
            "INSERT INTO " + FIELD_TABLE_NAME + " VALUES('LOGO', 'logo');" +
            "INSERT INTO " + FIELD_TABLE_NAME + " VALUES('ORG', 'organisation');" +
            "INSERT INTO " + FIELD_TABLE_NAME + " VALUES('MEMBER', 'member');" +
            "INSERT INTO " + FIELD_TABLE_NAME + " VALUES('RELATED', 'related');" +
            "INSERT INTO " + FIELD_TABLE_NAME + " VALUES('CATEGORIES', 'categories');" +
            "INSERT INTO " + FIELD_TABLE_NAME + " VALUES('NOTE', 'note');" +
            "INSERT INTO " + FIELD_TABLE_NAME + " VALUES('PRODID', 'prodid');" +
            "INSERT INTO " + FIELD_TABLE_NAME + " VALUES('REV', '');" +
            "INSERT INTO " + FIELD_TABLE_NAME + " VALUES('SOUND', 'sound');" +
            "INSERT INTO " + FIELD_TABLE_NAME + " VALUES('UID', 'unique identifier');" +
            "INSERT INTO " + FIELD_TABLE_NAME + " VALUES('CLIENTPIDMAP', '');" +
            "INSERT INTO " + FIELD_TABLE_NAME + " VALUES('URL', 'url');" +
            "INSERT INTO " + FIELD_TABLE_NAME + " VALUES('VERSION', 'version');" +
            "INSERT INTO " + FIELD_TABLE_NAME + " VALUES('KEY', 'key');" +
            "INSERT INTO " + FIELD_TABLE_NAME + " VALUES('FBURL', '');" +
            "INSERT INTO " + FIELD_TABLE_NAME + " VALUES('CALADRURI', '');" +
            "INSERT INTO " + FIELD_TABLE_NAME + " VALUES('CALURI', '');";


    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PROVIDER_TABLE_CREATE);
        db.execSQL(CREDENTIAL_TABLE_CREATE);
        db.execSQL(FIELD_TABLE_CREATE);
        db.execSQL(MERGEDCONTACT_TABLE_CREATE);
        db.execSQL(CONTACT_TABLE_CREATE);
        db.execSQL(CONTACTDATA_TABLE_CREATE);

        db.execSQL(FIELD_TABLE_INSERT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(CONTACTDATA_TABLE_DROP);
        db.execSQL(CONTACT_TABLE_DROP);
        db.execSQL(MERGEDCONTACT_TABLE_DROP);
        db.execSQL(FIELD_TABLE_DROP);
        db.execSQL(CREDENTIAL_TABLE_DROP);
        db.execSQL(PROVIDER_TABLE_DROP);
        onCreate(db);
    }
}
