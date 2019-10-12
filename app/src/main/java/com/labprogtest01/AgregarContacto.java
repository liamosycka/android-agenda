package com.labprogtest01;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AgregarContacto extends AppCompatActivity {
    private DatabaseHelper myDb;
    private EditText txtFechaNac, editNombre, editApellido, editTelefono;
    private Button btnAddData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_contacto);
        myDb=new DatabaseHelper(this);
        txtFechaNac = findViewById(R.id.fecha);


    txtFechaNac.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final Calendar calendar = Calendar.getInstance();
            DatePickerDialog datePickerDialog = new DatePickerDialog(AgregarContacto.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    Calendar calendarResult = Calendar.getInstance();
                    calendar.set(Calendar.YEAR,year);
                    calendar.set(Calendar.MONTH,month);
                    calendar.set(Calendar.DAY_OF_MONTH,day);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/mm/yyyy", Locale.getDefault());
                    Date date = calendarResult.getTime();
                    String fecha = simpleDateFormat.format(date);
                    txtFechaNac.setText(fecha);
                }
            },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        }
    });


        editNombre = (EditText) findViewById(R.id.editTextNombre);
        editApellido = (EditText) findViewById(R.id.editTextApellido);
        editTelefono = (EditText) findViewById(R.id.editTextTelefono);
        btnAddData = (Button) findViewById(R.id.buttonAgregar);





    }

    public void addData(View view) {
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean insert = myDb.insertData(editTelefono.getText().toString(), editNombre.getText().toString(), editApellido.getText().toString());
                if (insert) {
                    Toast.makeText(AgregarContacto.this, "Contacto agregado", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(AgregarContacto.this, "Error", Toast.LENGTH_LONG).show();
                }
            }


        });
    }
}
