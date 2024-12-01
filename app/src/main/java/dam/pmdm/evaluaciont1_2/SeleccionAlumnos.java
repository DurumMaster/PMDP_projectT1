package dam.pmdm.evaluaciont1_2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SeleccionAlumnos extends AppCompatActivity {

    private EditText alumnoSeleccionado;
    private String alumnoSeleccionadoTexto = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.seleccion_alumnos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

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

        alumnoSeleccionado = findViewById(R.id.etAlumnoSeleccionado);

        alumno1.setOnClickListener(v -> setAlumnoSeleccionado(alumno1));
        alumno2.setOnClickListener(v -> setAlumnoSeleccionado(alumno2));
        alumno3.setOnClickListener(v -> setAlumnoSeleccionado(alumno3));
        alumno4.setOnClickListener(v -> setAlumnoSeleccionado(alumno4));
        alumno5.setOnClickListener(v -> setAlumnoSeleccionado(alumno5));
        alumno6.setOnClickListener(v -> setAlumnoSeleccionado(alumno6));
        alumno7.setOnClickListener(v -> setAlumnoSeleccionado(alumno7));
        alumno8.setOnClickListener(v -> setAlumnoSeleccionado(alumno8));
        alumno9.setOnClickListener(v -> setAlumnoSeleccionado(alumno9));
        alumno10.setOnClickListener(v -> setAlumnoSeleccionado(alumno10));
        alumno11.setOnClickListener(v -> setAlumnoSeleccionado(alumno11));
        alumno12.setOnClickListener(v -> setAlumnoSeleccionado(alumno12));
        alumno13.setOnClickListener(v -> setAlumnoSeleccionado(alumno13));
        alumno14.setOnClickListener(v -> setAlumnoSeleccionado(alumno14));
        alumno15.setOnClickListener(v -> setAlumnoSeleccionado(alumno15));
        alumno16.setOnClickListener(v -> setAlumnoSeleccionado(alumno16));
        alumno17.setOnClickListener(v -> setAlumnoSeleccionado(alumno17));
        alumno18.setOnClickListener(v -> setAlumnoSeleccionado(alumno18));
        alumno19.setOnClickListener(v -> setAlumnoSeleccionado(alumno19));
        alumno20.setOnClickListener(v -> setAlumnoSeleccionado(alumno20));
        alumno21.setOnClickListener(v -> setAlumnoSeleccionado(alumno21));
        alumno22.setOnClickListener(v -> setAlumnoSeleccionado(alumno22));

        Button aceptarSeleccionAlumno = findViewById(R.id.btnAceptarSeleccionAlumno);
        Button cancelarSeleccionAlumno = findViewById(R.id.btnCancelarSeleccionAlumno);

        aceptarSeleccionAlumno.setOnClickListener(v -> {
            if (alumnoSeleccionadoTexto.isEmpty()) {
                Toast.makeText(this, "Debe especificar el alumno seleccionado", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent();
                intent.putExtra("ALUMNO", alumnoSeleccionadoTexto);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        cancelarSeleccionAlumno.setOnClickListener(v -> finish());
    }

    private void setAlumnoSeleccionado(Button alumnoButton) {
        alumnoSeleccionadoTexto = alumnoButton.getText().toString().toUpperCase();
        alumnoSeleccionado.setText(alumnoSeleccionadoTexto);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("alumnoSeleccionado", alumnoSeleccionadoTexto);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            alumnoSeleccionadoTexto = savedInstanceState.getString("alumnoSeleccionado", "");
            alumnoSeleccionado.setText(alumnoSeleccionadoTexto);
        }
    }
}
