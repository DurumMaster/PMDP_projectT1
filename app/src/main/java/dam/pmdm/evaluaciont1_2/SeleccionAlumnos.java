package dam.pmdm.evaluaciont1_2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SeleccionAlumos extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seleccion_alumnos);

        Button alumno1 = findViewById(R.id.btnAlumno1);
        Button alumno2 = findViewById(R.id.btnAlumno2);
        Button alumno3 = findViewById(R.id.btnAlumno3);
        Button alumno4 = findViewById(R.id.btnAlumno4);
        Button alumno5 = findViewById(R.id.btnAlumno5);
        Button alumno6 = findViewById(R.id.btnAlumno6);
        Button alumno7 = findViewById(R.id.btnAlumno7);
        Button alumno8 = findViewById(R.id.btnAlumno8);
        Button alumno9 = findViewById(R.id.btnAlumno9);
        Button alumno10 = findViewById(R.id.btnAlumno10);
        Button alumno11 = findViewById(R.id.btnAlumno11);
        Button alumno12 = findViewById(R.id.btnAlumno12);
        Button alumno13 = findViewById(R.id.btnAlumno13);
        Button alumno14 = findViewById(R.id.btnAlumno14);
        Button alumno15 = findViewById(R.id.btnAlumno15);
        Button alumno16 = findViewById(R.id.btnAlumno16);
        Button alumno17 = findViewById(R.id.btnAlumno17);
        Button alumno18 = findViewById(R.id.btnAlumno18);
        Button alumno19 = findViewById(R.id.btnAlumno19);
        Button alumno20 = findViewById(R.id.btnAlumno20);
        Button alumno21 = findViewById(R.id.btnAlumno21);
        Button alumno22 = findViewById(R.id.btnAlumno22);
        EditText alumnoSeleccionado = findViewById(R.id.etAlumnoSeleccionado);
        Button aceptarSeleccionAlumno = findViewById(R.id.btnAceptarSeleccionAlumno);
        Button cancelarSeleccionAlumno = findViewById(R.id.btnCancelarSeleccionAlumno);

        alumno1.setOnClickListener(v -> {
            String textoBoton = alumno1.getText().toString().toUpperCase();
            alumnoSeleccionado.setText(textoBoton);
        });

        alumno2.setOnClickListener(v -> {
            String textoBoton = alumno2.getText().toString().toUpperCase();
            alumnoSeleccionado.setText(textoBoton);
        });

        alumno3.setOnClickListener(v -> {
            String textoBoton = alumno3.getText().toString().toUpperCase();
            alumnoSeleccionado.setText(textoBoton);
        });

        alumno4.setOnClickListener(v -> {
            String textoBoton = alumno4.getText().toString().toUpperCase();
            alumnoSeleccionado.setText(textoBoton);
        });

        alumno5.setOnClickListener(v -> {
            String textoBoton = alumno5.getText().toString().toUpperCase();
            alumnoSeleccionado.setText(textoBoton);
        });

        alumno6.setOnClickListener(v -> {
            String textoBoton = alumno6.getText().toString().toUpperCase();
            alumnoSeleccionado.setText(textoBoton);
        });

        alumno7.setOnClickListener(v -> {
            String textoBoton = alumno7.getText().toString().toUpperCase();
            alumnoSeleccionado.setText(textoBoton);
        });

        alumno8.setOnClickListener(v -> {
            String textoBoton = alumno8.getText().toString().toUpperCase();
            alumnoSeleccionado.setText(textoBoton);
        });

        alumno1.setOnClickListener(v -> {
            String textoBoton = alumno1.getText().toString().toUpperCase();
            alumnoSeleccionado.setText(textoBoton);
        });

        alumno9.setOnClickListener(v -> {
            String textoBoton = alumno9.getText().toString().toUpperCase();
            alumnoSeleccionado.setText(textoBoton);
        });

        alumno10.setOnClickListener(v -> {
            String textoBoton = alumno10.getText().toString().toUpperCase();
            alumnoSeleccionado.setText(textoBoton);
        });

        alumno11.setOnClickListener(v -> {
            String textoBoton = alumno11.getText().toString().toUpperCase();
            alumnoSeleccionado.setText(textoBoton);
        });

        alumno12.setOnClickListener(v -> {
            String textoBoton = alumno12.getText().toString().toUpperCase();
            alumnoSeleccionado.setText(textoBoton);
        });

        alumno13.setOnClickListener(v -> {
            String textoBoton = alumno13.getText().toString().toUpperCase();
            alumnoSeleccionado.setText(textoBoton);
        });

        alumno14.setOnClickListener(v -> {
            String textoBoton = alumno14.getText().toString().toUpperCase();
            alumnoSeleccionado.setText(textoBoton);
        });

        alumno15.setOnClickListener(v -> {
            String textoBoton = alumno15.getText().toString().toUpperCase();
            alumnoSeleccionado.setText(textoBoton);
        });

        alumno16.setOnClickListener(v -> {
            String textoBoton = alumno16.getText().toString().toUpperCase();
            alumnoSeleccionado.setText(textoBoton);
        });

        alumno17.setOnClickListener(v -> {
            String textoBoton = alumno17.getText().toString().toUpperCase();
            alumnoSeleccionado.setText(textoBoton);
        });

        alumno18.setOnClickListener(v -> {
            String textoBoton = alumno18.getText().toString().toUpperCase();
            alumnoSeleccionado.setText(textoBoton);
        });

        alumno19.setOnClickListener(v -> {
            String textoBoton = alumno19.getText().toString().toUpperCase();
            alumnoSeleccionado.setText(textoBoton);
        });

        alumno20.setOnClickListener(v -> {
            String textoBoton = alumno20.getText().toString().toUpperCase();
            alumnoSeleccionado.setText(textoBoton);
        });

        alumno21.setOnClickListener(v -> {
            String textoBoton = alumno21.getText().toString().toUpperCase();
            alumnoSeleccionado.setText(textoBoton);
        });

        alumno22.setOnClickListener(v -> {
            String textoBoton = alumno22.getText().toString().toUpperCase();
            alumnoSeleccionado.setText(textoBoton);
        });

        aceptarSeleccionAlumno.setOnClickListener(v -> {
            String textoParaEnviar = alumnoSeleccionado.getText().toString().trim();
            if (textoParaEnviar.isEmpty()) {
                Toast.makeText(this, "Debe especificar el alumno seleccionado", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent();
                intent.putExtra("ALUMNO", textoParaEnviar);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        cancelarSeleccionAlumno.setOnClickListener(v -> {
            finish();
        });

    }
}

