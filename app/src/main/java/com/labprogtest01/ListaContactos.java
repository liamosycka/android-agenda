package com.labprogtest01;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

public class ListaContactos extends AppCompatActivity {
    private DatabaseHelper myDb;
    private EditText searchContact;
    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_contactos);
        myDb=new DatabaseHelper(this);
        SQLiteDatabase db = myDb.getReadableDatabase();
        searchContact=(EditText) findViewById(R.id.searchContact);
        if(db!=null){
            Cursor cursor = db.rawQuery("select * from contact_table",null);
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

            adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,array);

            ListView lista = (ListView) findViewById(R.id.listaContacto);
            lista.setTextFilterEnabled(true);
            lista.setAdapter(adapter);
            searchContact.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    (ListaContactos.this).adapter.getFilter().filter(charSequence);


                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });



        }




        final ListView listaC = (ListView) findViewById(R.id.listaContacto);
        listaC.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent informacion = new Intent(ListaContactos.this, InfoContacto.class);
                informacion.putExtra("contacto",listaC.getItemAtPosition(i).toString());
                startActivity(informacion);
            }
        });




    }

}