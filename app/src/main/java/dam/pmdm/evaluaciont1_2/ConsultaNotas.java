package dam.pmdm.evaluaciont1_2;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import java.util.List;
import java.util.Locale;

public class ConsultaNotas extends AppCompatActivity {

    private EditText etAlumno;
    private Button btnSeleccionLimpiar;
    private TextView tvError;
    private LinearLayout llFragmentsNotas;

    String alumno;
    ActivityResultLauncher<Intent> seleccionAlumno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consulta_notas);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etAlumno = findViewById(R.id.etAlumnoConsulta);
        btnSeleccionLimpiar = findViewById(R.id.btnSeleccionarAlumnoConsulta);
        tvError = findViewById(R.id.tvError);
        llFragmentsNotas = findViewById(R.id.llFragmentsNotas);

        seleccionAlumno = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            alumno = data.getStringExtra("ALUMNO");
                            if (alumno != null) {
                                etAlumno.setText(alumno);
                                btnSeleccionLimpiar.setText(R.string.btn_limpiar_datos_consulta);
                                List<Alumno> asignaturas = leerAsignaturasAlumno();
                                mostrarAsignaturas(asignaturas);
                            }
                        }
                    }
                }
        );
    }

    public void cambiarIdioma(View view) {
        String idiomaActual = Locale.getDefault().getLanguage();
        Locale nuevoIdioma;

        if ("es".equals(idiomaActual)) {
            nuevoIdioma = new Locale("en");
        } else {
            nuevoIdioma = new Locale("es");
        }

        Locale.setDefault(nuevoIdioma);
        Configuration config = new Configuration();
        config.setLocale(nuevoIdioma);
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        Intent intent = new Intent(this, ConsultaNotas.class);
        finish();
        startActivity(intent);
    }

    private void mostrarAsignaturas(List<Alumno> asignaturas) {
        if (!asignaturas.isEmpty()) {
            Alumno asignatura;
            FrameLayout flNota;
            Fragment fragment;
            for (int i = 0; i < asignaturas.size(); i++) {
                flNota = new FrameLayout(this);
                flNota.setId(View.generateViewId());

                asignatura = asignaturas.get(i);

                fragment = getSupportFragmentManager().findFragmentByTag("fragment_" + i);
                if (fragment == null) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(flNota.getId(), NotaFragment.newInstance(asignatura.getAsignatura(), String.valueOf(asignatura.getNotaFinal())), "fragment_" + i)
                            .commit();
                }
                llFragmentsNotas.addView(flNota);
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
        etAlumno.setText("");

        FrameLayout frame;
        Fragment fragment;
        for (int i = 0; i < llFragmentsNotas.getChildCount(); i++) {
            frame = (FrameLayout) llFragmentsNotas.getChildAt(i);
            fragment = getSupportFragmentManager().findFragmentById(frame.getId());
            if (fragment != null) {
                getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            }
        }

        btnSeleccionLimpiar.setText(getResources().getString(R.string.btn_seleccionar_alumno));
    }

    private List<Alumno> leerAsignaturasAlumno() {
        List<Alumno> asignaturas = new ArrayList<Alumno>();
        Alumno asignatura = null;
        boolean acabar = false;
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        ArrayList<String> asignaturas = new ArrayList<>();
        ArrayList<String> notas = new ArrayList<>();

        FrameLayout fl;
        NotaFragment fragment;

        for (int i = 0; i < llFragmentsNotas.getChildCount(); i++) {
            fl = (FrameLayout) llFragmentsNotas.getChildAt(i);
            fragment = (NotaFragment) getSupportFragmentManager().findFragmentById(fl.getId());

            if (fragment != null && fragment.getArguments() != null) {
                asignaturas.add(fragment.getArguments().getString("asignatura"));
                notas.add(fragment.getArguments().getString("nota"));
            }
        }

        outState.putStringArrayList("ASIGNATURAS", asignaturas);
        outState.putStringArrayList("NOTAS", notas);
        outState.putString("TXT_BTN", btnSeleccionLimpiar.getText().toString());
        outState.putString("TXT_ERROR", tvError.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        ArrayList<String> asignaturas = savedInstanceState.getStringArrayList("ASIGNATURAS");
        ArrayList<String> notas = savedInstanceState.getStringArrayList("NOTAS");

        if (asignaturas != null && notas != null) {
            FrameLayout flNota;
            for (int i = 0; i < asignaturas.size(); i++) {
                flNota = new FrameLayout(this);
                flNota.setId(View.generateViewId());

                getSupportFragmentManager().beginTransaction()
                        .add(flNota.getId(), NotaFragment.newInstance(asignaturas.get(i), notas.get(i)), "fragment_" + i)
                        .commit();

                llFragmentsNotas.addView(flNota);
            }
        }
        btnSeleccionLimpiar.setText(savedInstanceState.getString("TXT_BTN"));
        tvError.setText(savedInstanceState.getString("TXT_ERROR"));
    }
}
