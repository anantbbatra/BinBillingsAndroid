package com.example.anantbhushanbatra.binbillings;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.location.Location;

import java.util.ArrayList;

public class DatabaseManager {

    public static final String DATABASE_NAME = "Studentverts";
    public static final String TABLE_NAME_LOGIN = "FavouritedAds";
    public static final String TABLE_NAME_PAYMENT = "Payment";

    public static final int DATABASE_VERSION = 1;

    String username, password, ccnum, cvvnum, cclastdigits, ccexpiry, cardHolderName;

    private static final String DATABASE_CREATE_LOGINTABLE_STATEMENT =
            "create table " + TABLE_NAME_LOGIN + " (ccnum TEXT NOT NULL, cvvnum TEXT PRIMARY KEY, " +
                    "cclastdigits INTEGER NOT NULL, ccexpiry TEXT NOT NULL, cardHolderName TEXT);";

    private static final String DATABASE_CREATE_PAYMENTTABLE_STATEMENT =
            "create table " + TABLE_NAME_LOGIN + " (ccnum TEXT NOT NULL, cvvnum TEXT PRIMARY KEY, " +
                    "cclastdigits INTEGER NOT NULL, ccexpiry TEXT NOT NULL, cardHolderName TEXT);";

    private final Context context;

    private DatabaseHelper dBHelper;
    private SQLiteDatabase db;

    public DatabaseManager(Context c){
        this.context = c;
        dBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context c){
            super(c, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase db){
            db.execSQL(DATABASE_CREATE_LOGINTABLE_STATEMENT);
            db.execSQL(DATABASE_CREATE_PAYMENTTABLE_STATEMENT);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        }
    }

    public DatabaseManager open() throws SQLException
    {
        db = dBHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dBHelper.close();
    }


    public void clearPaymentInfo(){
        db.execSQL("DELETE from " + TABLE_NAME_PAYMENT);
    }

    public void clearLoginInfo(){
        db.execSQL("DELETE from " + TABLE_NAME_LOGIN);
    }



}
