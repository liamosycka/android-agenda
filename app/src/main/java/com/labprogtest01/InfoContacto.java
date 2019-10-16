package com.labprogtest01;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.IDNA;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class InfoContacto extends AppCompatActivity {
    private DatabaseHelper myDb;
    private EditText editNombre,editApellido,editTelefono,editFecha;
    private Button delete;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_contacto);
        myDb=new DatabaseHelper(this);
        editNombre=(EditText)findViewById(R.id.editNombre);
        editApellido=(EditText)findViewById(R.id.editApellido);
        editTelefono=(EditText)findViewById((R.id.editTelefono));
        editFecha=(EditText)findViewById(R.id.editFecha);

        String nombre=" ",apellido="";
        Bundle bundle = getIntent().getExtras();
        String frase = bundle.getString("contacto");
        String[] parte = frase.split(" ");
        int cantidad=parte.length-1;

        for(int i=0;i<cantidad;i++) {
            nombre = nombre + " " + parte[i];
        }
        nombre=nombre.trim();
        apellido = apellido + " " +parte[cantidad];
        apellido = apellido.trim();
       Cursor cursor=myDb.getInfoContacto(nombre,apellido);

        if(cursor.getCount()==0){
            nombre = "";
            apellido = "";
            for(int j=0;j<cantidad-1;j++){
                nombre = nombre + " " + parte[j];
            }
            nombre = nombre.trim();
            apellido = apellido + " " +parte[cantidad-1]+ " " + parte[cantidad];
            apellido=apellido.trim();
        }
         cursor=myDb.getInfoContacto(nombre,apellido);


            cursor.moveToFirst();

            editNombre.setText(cursor.getString(1));
            editApellido.setText(cursor.getString(2));
            editTelefono.setText(cursor.getString(0));
            editFecha.setText(cursor.getString(3));







    }

    public void eliminarContacto(View view){
        Bundle bundle = getIntent().getExtras();
        String frase = bundle.getString("contacto");
        String[] parte = frase.split(" ");
        String nombre="",apellido="";
        int cantidad=parte.length-1;

        for(int i=0;i<cantidad;i++) {
            nombre = nombre + " " + parte[i];
        }
        nombre=nombre.trim();
        apellido = parte[cantidad].trim();
        Cursor cursor=myDb.getInfoContacto(nombre,apellido);

        if(cursor.getCount()==0){
            nombre="";
            apellido="";
            for(int j=0;j<cantidad-1;j++){
                nombre = nombre +  " " + parte[j];
            }
            nombre = nombre.trim();
            apellido = parte[cantidad-1]+ " " + parte[cantidad];
            apellido = apellido.trim();

        }
        myDb.deleteContact(nombre,apellido);
        Toast.makeText(this,"Contacto Eliminado",Toast.LENGTH_LONG).show();
        Intent volver = new Intent(InfoContacto.this,Opciones.class);
        startActivity(volver);

    }

    public void editarContacto(View view){
        Bundle bundle = getIntent().getExtras();
        String frase = bundle.getString("contacto");
        String[] parte = frase.split(" ");
        String nombre="",apellido="";
        int cantidad=parte.length-1;

        for(int i=0;i<cantidad;i++) {
            nombre = (nombre + " " + parte[i]);
        }
        nombre=nombre.trim();
        apellido = parte[cantidad].trim();
        Cursor cursor=myDb.getInfoContacto(nombre,apellido);

        if(cursor.getCount()==0){
            nombre = "";
            apellido = "";
            for(int j=0;j<cantidad-1;j++){
                nombre = (nombre+ " " +parte[j]).trim();
            }

            apellido = parte[cantidad-1]+ " " + parte[cantidad];
            apellido = apellido.trim();
        }


        String nuevoNombre= editNombre.getText().toString().trim();
        String nuevoApellido= editApellido.getText().toString().trim();
        String nuevoTelefono= editTelefono.getText().toString();
        String nuevaFecha= editFecha.getText().toString();

        String[] apellidos = nuevoApellido.split(" ");

        if (nuevoNombre.length() == 0) { Toast.makeText(InfoContacto.this, "Debe agregar nombre", Toast.LENGTH_LONG).show();
        }else if (nuevoApellido.length() == 0) {
            Toast.makeText(InfoContacto.this, "Debe agregar apellido", Toast.LENGTH_LONG).show();
        }else if(apellidos.length>2){
        Toast.makeText(InfoContacto.this, "maximo dos apellidos", Toast.LENGTH_LONG).show();
    }
        if(nuevoNombre.length()!=0 && nuevoApellido.length()!=0 &&  apellidos.length<=2) {
            myDb.editarContacto(nombre, apellido, nuevoNombre, nuevoApellido, nuevoTelefono, nuevaFecha);
            Toast.makeText(this, "Contacto editado", Toast.LENGTH_LONG).show();
            Intent volver = new Intent(InfoContacto.this, Opciones.class);
            startActivity(volver);
        }

    }

    public int obtenerId(){
        Bundle bundle = getIntent().getExtras();

        String frase = bundle.getString("contacto");
        String[] parte = frase.split(" ");
        /*Obtenemos el id del contacto seleccionado*/
        int idContacto=Integer.parseInt(parte[0]);
        return idContacto;
    }

    public void llamarContacto(View view){
        Intent callIntent=new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:"+editTelefono.getText()));
        startActivity(callIntent);
    }

}
