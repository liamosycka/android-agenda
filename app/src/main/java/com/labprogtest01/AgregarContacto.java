package com.labprogtest01;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class AgregarContacto extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private EditText txtFechaNac, editNombre, editApellido, editTelefono;
    private Button btnAddData;
    private TextView editFecha;
    private DatabaseHelper myDb;
    private Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_contacto);
        myDb = new DatabaseHelper(this);


        Button button = (Button) findViewById(R.id.buttonC);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePick = new DatePickerFragment();
                datePick.show(getSupportFragmentManager(), "Fecha");

            }
        });


        editNombre = (EditText) findViewById(R.id.editNombre);
        editApellido = (EditText) findViewById(R.id.editApellido);
        editTelefono = (EditText) findViewById(R.id.editTelefono);
        btnAddData = (Button) findViewById(R.id.buttonAgregar);
        editFecha=(TextView)findViewById(R.id.editFecha);


    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int mes, int dia) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, mes);
        c.set(Calendar.DAY_OF_MONTH, dia);
        String currentDate = DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(c.getTime());
        editFecha= (TextView) findViewById(R.id.editFecha);
        editFecha.setText(currentDate);
    }

    public void addData(View view) {
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean insert = myDb.insertData(editTelefono.getText().toString(), editNombre.getText().toString(), editApellido.getText().toString(),editFecha.getText().toString());
                if (insert) {
                    Toast.makeText(AgregarContacto.this, "Contacto agregado", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(AgregarContacto.this, "Error", Toast.LENGTH_LONG).show();
                }
            }


        });

    }
}