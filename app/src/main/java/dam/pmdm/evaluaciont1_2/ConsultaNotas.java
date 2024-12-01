package dam.pmdm.evaluaciont1_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConsultaNotas extends AppCompatActivity {

    private EditText etAlummo;
    private Button btnSeleccionLimpiar;
    private FrameLayout flNota1;
    private FrameLayout flNota2;
    private FrameLayout flNota3;
    private FrameLayout flNota4;
    private FrameLayout flNota5;
    private FrameLayout flNota6;
    private FrameLayout flNota7;

    String alumno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.consulta_notas);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etAlummo = findViewById(R.id.etAlumnoConsulta);
        btnSeleccionLimpiar = findViewById(R.id.btnSeleccionarAlumnoConsulta);

        /*
        flNota1 = findViewById(R.id.flNota1);
        flNota2 = findViewById(R.id.flNota2);
        flNota3 = findViewById(R.id.flNota3);
        flNota4 = findViewById(R.id.flNota4);
        flNota5 = findViewById(R.id.flNota5);
        flNota6 = findViewById(R.id.flNota6);
        flNota7 = findViewById(R.id.flNota7);
        */

        List<FrameLayout> contenedores = Arrays.asList(
            findViewById(R.id.flNota1),
            findViewById(R.id.flNota2),
            findViewById(R.id.flNota3),
            findViewById(R.id.flNota4),
            findViewById(R.id.flNota5),
            findViewById(R.id.flNota6),
            findViewById(R.id.flNota7)
        );


        alumno = getIntent().getStringExtra("ALUMNO");

        if (alumno != null) {
            etAlummo.setText(alumno);
            btnSeleccionLimpiar.setText(R.string.btn_limpiar_datos_consulta);
            List<Alumno> asignaturas = leerAsignaturasAlumno();
            mostrarAsignaturas(asignaturas, contenedores);
        }
    }

    private void mostrarAsignaturas(List<Alumno> asignaturas, List<FrameLayout> contenedores) {

        if (!asignaturas.isEmpty()) {
            Alumno asignatura;
            for (int i = 0; i < asignaturas.size(); i++) {
                asignatura = asignaturas.get(i);
                getSupportFragmentManager().beginTransaction()
                        .replace(contenedores.get(i).getId(), NotaFragment.newInstance(asignatura.getAsignatura(), String.valueOf(asignatura.getNotaFinal())))
                        .commit();
            }
        }


    }

    public void seleccionarAlumno(View view) {

        String btnText = btnSeleccionLimpiar.getText().toString();

        if (btnText.equals(getResources().getString(R.string.btn_limpiar_datos_consulta))) {
            limpiarDatos();
            btnSeleccionLimpiar.setText(getResources().getString(R.string.btn_seleccionar_alumno_consulta_notas));
        } else {
            Intent intent = new Intent(this, SeleccionAlumnos.class);
            startActivity(intent);
        }
    }

    private void limpiarDatos() {
        etAlummo.setText("");
        // TODO: Variar datos layouts
    }

    private List<Alumno> leerAsignaturasAlumno() {
        List<Alumno> asignaturas = new ArrayList<Alumno>();
        Alumno asignatura;
        boolean acabar = false;
        File file = new File(getExternalFilesDir(null), "alumnos.dat");

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (!acabar) {
                try {
                    asignatura = (Alumno) ois.readObject();

                    if (asignatura.getNotaFinal() != 0 && asignatura.getNombre().equals(alumno))
                        asignaturas.add(asignatura);
                } catch (IOException | ClassNotFoundException e) {
                    acabar = true;
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return asignaturas;
    }

}
