package com.example.nutripets;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="user_info.db";
    public static final String TABLE_NAME="user";
    public static final String COL1="ID";
    public static final String COL2="gender";
    public static final String COL3="age";
    public static final String COL4="fruits";
    public static final String COL5="vegetables";
    public static final String COL6="activity";
    public static final String COL7="pet";
    public static final String COL8="balance";
    private static final int USER_ID = 1;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("DELETE FROM " + TABLE_NAME);
        sqLiteDatabase.execSQL("CREATE TABLE user (ID INTEGER PRIMARY KEY, gender STRING, age STRING, " +
                " fruits INTEGER DEFAULT 2, vegetables INTEGER DEFAULT 3, activity STRING, pet STRING, balance INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addToTable(String column, Object value) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("ID", 1);
        if (column.equals("fruits") || column.equals("vegetables") || column.equals("balance")) {
            int i=0;
            String s = (String) value;
            if (s.equals("")) {
                i=0;
            } else {
                i =  Integer.parseInt((String)value);
            }
            values.put(column, i);
        } else {
            values.put(column, (String) value);
        }

        long res = db.insertWithOnConflict(TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        if (res == -1) {
            db.update(TABLE_NAME, values, "ID=?", new String[] {"1"});
        }
        System.out.println("Inserted");
        db.close();
    }

}
