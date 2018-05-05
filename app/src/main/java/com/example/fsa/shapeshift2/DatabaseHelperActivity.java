package com.example.fsa.shapeshift2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Eddie on 4/21/18.
 */

public class DatabaseHelperActivity extends SQLiteOpenHelper {
    //table columns and database info
    //public static final String LOG = "DatabaseHelper";
    public static final String DB_NAME = "loglist.db";
    public static final String TABLE_NAME = "loglist_data";
    public static final String DATE = "date";
    public static final String BMI = "bmi";
    public static final String BMR = "bmr";
    public static final String WHTR = "whtr";
    public static final String TDEE = "tdee";

    //Database version
    public static int DB_VERSION =1;

    //creating the table
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + DATE
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + BMI + " TEXT NOT NULL, " + BMR + " TEXT, " +
            "" + WHTR + " TEXT, " + TDEE + " TEXT );";

    public DatabaseHelperActivity(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }



/*
    public DatabaseHelperActivity(Context context) {
        super(context, DATABASE_NAME, null, 1);
        //SQLiteDatabase db = this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = ("CREATE TABLE" + TABLE_NAME + " (DATE INTEGER PRIMARY KEY," + COL2 + "TEXT)");
        db.execSQL(createTable);
    }

 @Override
 public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

 }

    public boolean addData(String bmi) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //contentValues.put(COL1, date);
        contentValues.put(COL2, bmi);
        //contentValues.put(COL3, bmr);
        //contentValues.put(COL4, whtr);
        //contentValues.put(COL5, tdee);
        Log.d(LOG, "addData: Adding" + bmi + "to" + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        }
        else {
            return true;

        }
    }

    //returns all the data from the database
    public Cursor getListContents() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }
/*
    public Cursor displayData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }

    public boolean updateData(String date, String bmi, String bmr, String whtr, String tdee) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put(COL1, date);
        contentvalues.put(COL2, bmi);
        contentvalues.put(COL3, bmr);
        contentvalues.put(COL4, whtr);
        contentvalues.put(COL5, tdee);
        db.update(TABLE_NAME, contentvalues, "Date = ?", new String[]{date});
        return true;
    }

    public Integer deleteData(String date){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[] {date});
    }


    */
/*
    public Cursor getItemDate (String bmi) {

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + "FROM" + TABLE_NAME +
                " WHERE " + COL2 + " = '" + bmi + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    public void updateBMI(String newBMI, int date, String oldBMI) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL2 +
                " = '" + newBMI + "' WHERE " + COL1 + " = '" + date + "'" +
                " AND " + COL2 + " = '" + oldBMI + "'";
        Log.d(LOG, "updateBMI: query: " + query);
        Log.d(LOG, "updateBMI: Setting BMI to " + newBMI);
        db.execSQL(query);
    }
    public void deleteBMI(int date, String bmi){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL1 + " = '" + date + "'" +
                " AND " + COL2 + " = '" + bmi + "'";
        Log.d(LOG, "deleteBMI: query: " + query);
        Log.d(LOG, "deleteBMI: Deleting " + bmi + " from database.");
        db.execSQL(query);


    }

*/
}