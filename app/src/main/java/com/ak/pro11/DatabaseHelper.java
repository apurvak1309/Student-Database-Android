package com.ak.pro11;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="Student.db";
    public static final String TABLE_NAME="Stud1";
    public static final String col_1="ID";
    public static final String col_2="ROLL_NO";
    public static final String col_3="NAME";
    public static final String col_4="BRANCH";
    public static final String col_5="MARKS";
    public static final String col_6="PERCENTAGE";
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,roll_no INTEGER,name TEXT,branch TEXT,marks text,percentage text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
    }
    public boolean insertData(int rollno,String name,String branch,String marks,String percentage){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(col_2,rollno);
        contentValues.put(col_3,name);
        contentValues.put(col_4,branch);
        contentValues.put(col_5,marks);
        contentValues.put(col_6,percentage);
        long result=db.insert(TABLE_NAME,null,contentValues);
        return result != -1;
    }
    public boolean deleteData(int roll1){
        SQLiteDatabase db=this.getWritableDatabase();
        String str1= String.valueOf(roll1);
        long res=db.delete(TABLE_NAME,"`"+col_2+"`=?",new String[]{str1});
        if(res==-1){
            return false;
        }
        else {
            return true;
        }
    }
    public Cursor getAllData(int roll){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+TABLE_NAME+" where `"+col_2+"`="+roll ,null);
        return res;
    }
}

