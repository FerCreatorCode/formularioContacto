package com.fercreatorcode.formulariocontacto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    DatePicker datePicker;
    TextInputEditText textInputEditTextNombre;
    TextInputEditText textInputEditTextTelefono;
    TextInputEditText textInputEditTextEmail;
    TextInputEditText textInputEditTextDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setData();
    }
    public void setData(){
        Intent intent =getIntent();
        if (intent.hasExtra("Nombre")){
            textInputEditTextNombre = (TextInputEditText) findViewById(R.id.txtNombre);
            textInputEditTextNombre.setText(intent.getStringExtra("Nombre"));

        }
        if (intent.hasExtra("Telefono")) {
            textInputEditTextNombre = (TextInputEditText) findViewById(R.id.txtTelefono);
            textInputEditTextNombre.setText(intent.getStringExtra("Telefono"));
        }
        if (intent.hasExtra("Email")) {
            textInputEditTextNombre = (TextInputEditText) findViewById(R.id.txtEmail);
            textInputEditTextNombre.setText(intent.getStringExtra("Email"));
        }
        if (intent.hasExtra("Desc")) {
            textInputEditTextNombre = (TextInputEditText) findViewById(R.id.txtDescripcion);
            textInputEditTextNombre.setText(intent.getStringExtra("Desc"));
        }
        showPicker(intent);

    }
    public void showPicker(Intent intent) {
        datePicker = (DatePicker) findViewById(R.id.dpFecha);
        if (intent.hasExtra("YYYY") &&
                intent.hasExtra("MM") &&
                intent.hasExtra("DD")) {
            int year = Integer.parseInt(intent.getStringExtra("YYYY"));
            int month = Integer.parseInt(intent.getStringExtra("MM")) - 1;
            int day = Integer.parseInt(intent.getStringExtra("DD"));
            datePicker.init(year,month,day,null);
        } else {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            datePicker.init(year,month,day,null);
        }
    }

    public void btnSiguiente(View view) {
        datePicker = (DatePicker) findViewById(R.id.dpFecha);
        textInputEditTextNombre = (TextInputEditText) findViewById(R.id.txtNombre);
        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.txtEmail);
        textInputEditTextTelefono = (TextInputEditText) findViewById(R.id.txtTelefono);
        textInputEditTextDescripcion = (TextInputEditText) findViewById(R.id.txtDescripcion);

        Intent intent = new Intent(this, Confirmar_Datos.class);
        intent.putExtra("NOMBRE", textInputEditTextNombre.getText().toString());
        intent.putExtra("TELEFONO", textInputEditTextTelefono.getText().toString());
        intent.putExtra("EMAIL", textInputEditTextEmail.getText().toString());
        intent.putExtra("DESC", textInputEditTextDescripcion.getText().toString());
        intent.putExtra("YYYY", Integer.toString(datePicker.getYear()));
        intent.putExtra("MM", Integer.toString(datePicker.getMonth() + 1));
        intent.putExtra("DD", Integer.toString(datePicker.getDayOfMonth()));
        startActivity(intent);
        finish();
    }
}