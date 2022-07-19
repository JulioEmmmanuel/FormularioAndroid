package com.example.formulario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class DatosUsuario extends AppCompatActivity {

    Usuario us;
    Button editButton;
    TextView textNombre;
    TextView textFecha;
    TextView textTelefono;
    TextView textEmail;
    TextView textDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_usuario);

        textNombre = (TextView) findViewById(R.id.textNombre);
        textFecha = (TextView) findViewById(R.id.textFecha);
        textEmail = (TextView) findViewById(R.id.textEmail);
        textTelefono = (TextView) findViewById(R.id.textTelefono);
        textDescripcion = (TextView) findViewById(R.id.textDescripcion);
        editButton = (Button) findViewById(R.id.editButton);

        llenarInformacion();
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DatosUsuario.this, MainActivity.class);
                i.putExtra(getResources().getString(R.string.pNombre), us.getNombre());
                i.putExtra(getResources().getString(R.string.pFecha), us.getFechaNacimiento());
                i.putExtra(getResources().getString(R.string.pTelefono), us.getTelefono());
                i.putExtra(getResources().getString(R.string.pEmail), us.getEmail());
                i.putExtra(getResources().getString(R.string.pDescripcion), us.getDescripcion());
                startActivity(i);
                finish();
            }
        });

    }

    private void llenarInformacion(){
        Bundle extras = getIntent().getExtras();
        String nombre = extras.getString(getResources().getString(R.string.pNombre));
        String fecha = extras.getString(getResources().getString(R.string.pFecha));
        String telefono = extras.getString(getResources().getString(R.string.pTelefono));
        String email = extras.getString(getResources().getString(R.string.pEmail));
        String descripcion = extras.getString(getResources().getString(R.string.pDescripcion));

        textNombre.setText(getResources().getString(R.string.nombre) + ": " + nombre);
        textFecha.setText(getResources().getString(R.string.fecha) + ": " + fecha);
        textTelefono.setText(getResources().getString(R.string.telefono) + ": " + telefono);
        textEmail.setText(getResources().getString(R.string.email) + ": " + email);
        textDescripcion.setText(getResources().getString(R.string.descripcion) + ": " + descripcion);

        us = new Usuario(nombre, fecha, telefono, email, descripcion);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Intent intent= new Intent(DatosUsuario.this, MainActivity.class);
            startActivity(intent);
        }

        return super.onKeyDown(keyCode, event);
    }
}