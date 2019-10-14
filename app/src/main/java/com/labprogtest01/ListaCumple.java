package com.labprogtest01;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ListaCumple extends AppCompatActivity {

    private DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_cumple);
        myDb = new DatabaseHelper(this);
        SQLiteDatabase db = myDb.getReadableDatabase();
        Date date=new Date();
        SimpleDateFormat format=new SimpleDateFormat("dd/MM");
        String fechaHoy=format.format(date);
        if(db!=null){
            Cursor cursor = db.rawQuery("select * from contact_table where BIRTH like '"+fechaHoy+"%'",null);
            int cantidad = cursor.getCount();
            int i=0;
            String[] array = new String[cantidad];
            if(cursor.moveToFirst()){
                do{


                    String linea = cursor.getString(1) + " " + cursor.getString(2);

                    array[i] = linea;
                    i++;

                }while(cursor.moveToNext());
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,array);
            ListView lista = (ListView) findViewById(R.id.ListCumple);
            lista.setAdapter(adapter);
        }


    }

}

