package dam.pmdm.evaluaciont1_2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SeleccionAlumnos extends AppCompatActivity {

    private EditText etAlumnoSeleccionado;
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

        etAlumnoSeleccionado = findViewById(R.id.etAlumnoSeleccionado);

        Button aceptarSeleccionAlumno = findViewById(R.id.btnAceptarSeleccionAlumno);
        Button cancelarSeleccionAlumno = findViewById(R.id.btnCancelarSeleccionAlumno);

        String[] alumnos = {
                "Víctor Anguita Martínez de Velasco",
                "Miguel Cañizares Chichon",
                "Nander Antonio Cueva Machuca",
                "Bony Pablo Diez Ateca",
                "Ana Coral Fernández Arteta",
                "Ignacio Fernández Periáñez",
                "Bernardino Font García",
                "Alvaro García Guimaraens",
                "Mario Gómez Pérez",
                "Carlos Hernández Borja",
                "Axel León Arroyo",
                "Camilo Armando Maita Bracamonte",
                "Oscar Martín García",
                "Guillermo Martín Muñoz",
                "Clara Nuevo Mota",
                "Alejandro Martínez Bravo",
                "Adrian Ignacio Ocaña de Frutos",
                "Lucas René Oliveira Urrutia",
                "Alejandro Ramírez Gómez",
                "Roberto Ruíz",
                "Angel Santana Aguilera",
                "Cindy Vanessa Taboada Guerra"
        };

        LinearLayout layout = findViewById(R.id.llBotonesAlumno);
        Button btnAlumno;
        for (String alumno : alumnos) {
            btnAlumno = new Button(this);
            btnAlumno.setText(alumno);
            btnAlumno.setTextColor(getResources().getColor(R.color.black));
            btnAlumno.setBackgroundColor(getResources().getColor(R.color.soft_red_background));
            layout.addView(btnAlumno);

            btnAlumno.setOnClickListener(view -> {
                etAlumnoSeleccionado.setText(alumno);
                alumnoSeleccionadoTexto = alumno;
            });
        }

        aceptarSeleccionAlumno.setOnClickListener(v -> {
            if (alumnoSeleccionadoTexto.isEmpty()) {
                Toast.makeText(this, getString(R.string.toast_seleccion_alumno), Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent();
                intent.putExtra("ALUMNO", alumnoSeleccionadoTexto);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        cancelarSeleccionAlumno.setOnClickListener(v -> finish());
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("alumnoSeleccionado", alumnoSeleccionadoTexto);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            alumnoSeleccionadoTexto = savedInstanceState.getString("alumnoSeleccionado", "");
            etAlumnoSeleccionado.setText(alumnoSeleccionadoTexto);
        }
    }
}
