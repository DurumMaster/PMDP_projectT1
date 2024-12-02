package dam.pmdm.evaluaciont1_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

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
    private TextView tvError;

    String alumno;
    List<FrameLayout> contenedores;
    ActivityResultLauncher<Intent> seleccionAlumno;

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
        tvError = findViewById(R.id.tvError);

        contenedores = Arrays.asList(
            findViewById(R.id.flNota1),
            findViewById(R.id.flNota2),
            findViewById(R.id.flNota3),
            findViewById(R.id.flNota4),
            findViewById(R.id.flNota5),
            findViewById(R.id.flNota6),
            findViewById(R.id.flNota7)
        );

        seleccionAlumno = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            alumno = data.getStringExtra("ALUMNO");
                            if (alumno != null) {
                                etAlummo.setText(alumno);
                                btnSeleccionLimpiar.setText(R.string.btn_limpiar_datos_consulta);
                                List<Alumno> asignaturas = leerAsignaturasAlumno();
                                mostrarAsignaturas(asignaturas, contenedores);
                            }
                        }
                    }
                }
        );
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
        } else {
            tvError.setText(R.string.tv_consultanotas_error_vacio);
        }


    }

    public void seleccionarAlumno(View view) {

        tvError.setText("");
        String btnText = btnSeleccionLimpiar.getText().toString();

        if (btnText.equals(getResources().getString(R.string.btn_limpiar_datos_consulta))) {
            limpiarDatos();
        } else {
            Intent intent = new Intent(this, SeleccionAlumnos.class);
            seleccionAlumno.launch(intent);
        }
    }

    private void limpiarDatos() {
        etAlummo.setText("");

        Fragment fragment;
        for (FrameLayout frame: contenedores) {
            fragment = getSupportFragmentManager().findFragmentById(frame.getId());
            if (fragment != null) {
                getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            }
        }

        btnSeleccionLimpiar.setText(getResources().getString(R.string.btn_seleccionar_alumno_consulta_notas));
    }

    private List<Alumno> leerAsignaturasAlumno() {
        List<Alumno> asignaturas = new ArrayList<Alumno>();
        Alumno asignatura = null;
        boolean acabar = false;
        //getFilesDir(),"alumnos.dat"
        File file = new File(getFilesDir(),"alumnos.dat");

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (!acabar) {
                try {
                    asignatura = (Alumno) ois.readObject();

                    if (asignatura.getNotaFinal() != 0 && asignatura.getNombre().equalsIgnoreCase(alumno))
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
