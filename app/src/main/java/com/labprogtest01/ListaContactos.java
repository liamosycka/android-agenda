package com.labprogtest01;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ListaContactos extends AppCompatActivity {
    private DatabaseHelper myDb;
    private TextView textView01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_contactos);
        myDb=new DatabaseHelper(this);
        textView01=(TextView)findViewById(R.id.textView01);
        Cursor res=myDb.getAllData();
        if(res.getCount()==0){
            //show message
        }
        StringBuffer buffer= new StringBuffer();
        while(res.moveToNext()){
            buffer.append(res.getString(2));
            textView01.setText(buffer.toString());
        }

    }
    public void pressButonInfo(View view) {

    }
}
