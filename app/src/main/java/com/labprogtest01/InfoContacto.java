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

        int idContacto = obtenerId();

        Cursor cursor=myDb.getInfoContacto(idContacto);

        cursor.moveToFirst();

        editNombre.setText(cursor.getString(2));
        editApellido.setText(cursor.getString(3));
        editTelefono.setText(cursor.getString(1));
        editFecha.setText(cursor.getString(4));







    }

    public void eliminarContacto(View view){
        int idContacto = obtenerId();
        myDb.deleteContact(idContacto);
        Toast.makeText(this,"Contacto Eliminado",Toast.LENGTH_LONG).show();
        Intent volver = new Intent(InfoContacto.this,Opciones.class);
        startActivity(volver);
    }

    public void editarContacto(View view){
        int idContacto = obtenerId();
        String nuevoNombre= editNombre.getText().toString();
        String nuevoApellido= editApellido.getText().toString();
        String nuevoTelefono= editTelefono.getText().toString();
        String nuevaFecha= editFecha.getText().toString();

        myDb.editarContacto(idContacto,nuevoNombre,nuevoApellido,nuevoTelefono,nuevaFecha);
        Toast.makeText(this,"Contacto editado",Toast.LENGTH_LONG).show();
        Intent volver = new Intent(InfoContacto.this,Opciones.class);
        startActivity(volver);

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
