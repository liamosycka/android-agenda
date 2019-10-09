package com.example.liam.labprogtest01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Opciones extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones);
    }

    public void ListaContactos(View view) {
        Intent listaC=new Intent(this,ListaContactos.class);
        startActivity(listaC);
    }

    public void ListaCumple(View view){
     Intent listBirth=new Intent(this,ListaCumple.class);
     startActivity(listBirth);
    }
}
