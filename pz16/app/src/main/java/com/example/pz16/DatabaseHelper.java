package com.example.pz16;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "usercontact11.db";
    private static final int SCHEMA = 1;
    static final String TABLE = "users";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_YEAR = "year";
    public static final String COLUMN_GENDER = "gender";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE users (" + COLUMN_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT, "
                + COLUMN_YEAR + " INTEGER, "
                + COLUMN_GENDER + " TEXT)");
        db.execSQL("INSERT INTO "+ TABLE +" (" + COLUMN_NAME
                + ", "+ COLUMN_YEAR + ", "+ COLUMN_GENDER + ") VALUES ('Руслан Гамшитов', 1978, 'male'),('Татьяна Стрельцова', 1992, 'female');");
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }
}