package com.example.formulario;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    Button sigButton;
    TextInputEditText inputNombre;
    TextInputEditText inputFecha;
    TextInputEditText inputTelefono;
    TextInputEditText inputEmail;
    TextInputEditText inputDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputNombre = (TextInputEditText) findViewById(R.id.inputNombre);
        inputFecha = (TextInputEditText) findViewById(R.id.inputDate);
        inputTelefono = (TextInputEditText) findViewById(R.id.inputTelefono);
        inputEmail = (TextInputEditText) findViewById(R.id.inputEmail);
        inputDescripcion = (TextInputEditText) findViewById(R.id.inputDescripcion);
        sigButton = (Button) findViewById(R.id.sigButton);

        inputFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(view);
            }
        });

        sigButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Usuario us = leerDatosUsuario();

                Intent i = new Intent(MainActivity.this, DatosUsuario.class);
                i.putExtra(getResources().getString(R.string.pNombre), us.getNombre());
                i.putExtra(getResources().getString(R.string.pFecha), us.getFechaNacimiento());
                i.putExtra(getResources().getString(R.string.pTelefono), us.getTelefono());
                i.putExtra(getResources().getString(R.string.pEmail), us.getEmail());
                i.putExtra(getResources().getString(R.string.pDescripcion), us.getDescripcion());
                startActivity(i);
                finish();
            }
        });

        llenarInformacion();
    }

    public void showDatePickerDialog(View v) {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                final String selectedDate = day + " / " + (month+1) + " / " + year;
                inputFecha.setText(selectedDate);
            }
        });

        newFragment.show(getFragmentManager(), "datePicker");
    }


    private Usuario leerDatosUsuario(){
        String nombre = inputNombre.getText().toString();
        String fecha = inputFecha.getText().toString();
        String telefono = inputTelefono.getText().toString();
        String email = inputEmail.getText().toString();
        String descripcion = inputDescripcion.getText().toString();

        Usuario us = new Usuario(nombre, fecha, telefono, email, descripcion);
        return us;
    }

    private void llenarInformacion(){

        Bundle extras = getIntent().getExtras();

        if(extras == null){
            return;
        }

        String nombre = extras.getString(getResources().getString(R.string.pNombre));
        String fecha = extras.getString(getResources().getString(R.string.pFecha));
        String telefono = extras.getString(getResources().getString(R.string.pTelefono));
        String email = extras.getString(getResources().getString(R.string.pEmail));
        String descripcion = extras.getString(getResources().getString(R.string.pDescripcion));

        inputNombre.setText(nombre);
        inputFecha.setText(fecha);
        inputTelefono.setText(telefono);
        inputEmail.setText(email);
        inputDescripcion.setText(descripcion);
    }


}