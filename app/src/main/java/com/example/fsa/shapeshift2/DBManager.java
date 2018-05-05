package com.example.fsa.shapeshift2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {

    private DatabaseHelperActivity dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }
    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelperActivity(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String bmi, String bmr, String whtr, String tdee) {
        ContentValues contentValue = new ContentValues();
        //contentValue.put(DatabaseHelperActivity.DATE, date);
        contentValue.put(DatabaseHelperActivity.BMI, bmi);
        contentValue.put(DatabaseHelperActivity.BMR, bmr);
        contentValue.put(DatabaseHelperActivity.WHTR, whtr);
        contentValue.put(DatabaseHelperActivity.TDEE, tdee);
        database.insert(DatabaseHelperActivity.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] { DatabaseHelperActivity.DATE, DatabaseHelperActivity.BMI, DatabaseHelperActivity.BMR,
                DatabaseHelperActivity.WHTR, DatabaseHelperActivity.TDEE};
        Cursor cursor = database.query(DatabaseHelperActivity.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long date, String bmi, String bmr, String whtr, String tdee) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(DatabaseHelperActivity.BMI, bmi);
        contentValues.put(DatabaseHelperActivity.BMR, bmr);
        contentValues.put(DatabaseHelperActivity.WHTR, whtr);
        contentValues.put(DatabaseHelperActivity.TDEE, tdee);
        int i = database.update(DatabaseHelperActivity.TABLE_NAME, contentValues, DatabaseHelperActivity.DATE + " = " + date, null);
        return i;
    }
    public void delete(long date) {
        database.delete(DatabaseHelperActivity.TABLE_NAME, DatabaseHelperActivity.DATE + "=" + date, null);
    }

}
