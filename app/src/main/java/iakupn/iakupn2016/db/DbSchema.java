package iakupn.iakupn2016.db;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by malfunction on 23/04/16.
 */
public class DbSchema {

    public static final String DATABASE_NAME = "person_db";
    public static final int DATABASE_VERSION = 1;

    public static final String PERSON_TABLE_NAME = "person";
    public static final String COLUMN_PERSON_ID = "_id";
    public static final String COLUMN_PERSON_NAME = "name"; // string
    public static final String COLUMN_PERSON_ADDRESS = "address"; // text
    public static final String COLUMN_PERSON_AGE = "age"; //int
    public static final String COLUMN_PERSON_IS_MARRIED = "is_married";

    public static final String CREATE_PERSON_TABLE = "CREATE "
            + PERSON_TABLE_NAME + " ("
            + COLUMN_PERSON_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_PERSON_NAME + " VARCHAR(255) NOT NULL,"
            + COLUMN_PERSON_ADDRESS + " TEXT NOT NULL,"
            + COLUMN_PERSON_AGE + " INTEGER DEFAULT 0,"
            + COLUMN_PERSON_IS_MARRIED + " INTEGER DEFAULT 0"
            + ")";

    public static void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PERSON_TABLE);
    }

    public static void onDrop(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + PERSON_TABLE_NAME);
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onDrop(db);
        onCreate(db);
    }
}
