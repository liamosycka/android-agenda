package com.labprogtest01;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.sql.Date;

import static android.widget.Toast.LENGTH_LONG;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="Contactos.db";
    public static final String TABLE_NAME="contact_table";
    public static final String COL_ID="ID";
    public static final String COL_TELNUM="TELNUMBER";
    public static final String COL_NAME="NAME";
    public static final String COL_SURNAME="SURNAME";
    //public static final Date COL_BIRTH=null;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, TELNUMBER TEXT, NAME TEXT, SURNAME TEXT)");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }
    public boolean insertData(String telNumber,String name, String surname){
        boolean insert=false;
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_TELNUM,telNumber);
        contentValues.put(COL_NAME,name);
        contentValues.put(COL_SURNAME,surname);
       // contentValues.put(COL_BIRTH,birth);
        long result=db.insert(TABLE_NAME,null,contentValues);
        if(result!=-1){
            insert=true;
        }

        return insert;

    }

    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res= db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
}
