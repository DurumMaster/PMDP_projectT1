package dam.pmdm.evaluaciont1_2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class RegistroNota extends AppCompatActivity {

    private EditText etAlumno;
    private EditText etAsignatura;
    private EditText etNotaExamen;
    private EditText etNotaActividades;
    private EditText etResultado;

    private static ArrayList<Alumno> listaAlumnos;
    ActivityResultLauncher<Intent> seleccionAlumno;
    ActivityResultLauncher<Intent> seleccionAsignatura;
    private String alumno;
    private String asignatura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String idiomaGuardado = GuardarIdioma.getSavedLanguage(this);
        ManejarIdioma.setAppLocale(this, idiomaGuardado);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro_nota);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        listaAlumnos = new ArrayList<Alumno>();
        leerArchivoBinario();

        etAlumno = findViewById(R.id.etAlumno);
        etAsignatura = findViewById(R.id.etAsignatura);
        etNotaExamen = findViewById(R.id.etNotaExamen);
        etNotaActividades = findViewById(R.id.etNotaActividades);
        etResultado = findViewById(R.id.etResultado);

        seleccionAlumno = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            alumno = data.getStringExtra("ALUMNO");
                            if (alumno != null) {
                                etAlumno.setText(alumno);
                            }
                        }
                    }
                }
        );

        seleccionAsignatura = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            asignatura = data.getStringExtra("ASIGNATURA");
                            if (asignatura != null) {
                                etAsignatura.setText(asignatura);
                            }
                        }
                    }
                }
        );
    }

    public void onLimpiarClick(View view){
        etNotaExamen.setText("");
        etResultado.setText("");
        etNotaActividades.setText("");
        etAlumno.setText("");
        etAsignatura.setText("");
    }

    public void onCalcularNotaClick(View view){
        double notaFinal = 0;
        double notaExamen = -1;
        double notaActividades = -1;

        if(!etNotaExamen.getText().toString().isEmpty()){
            notaExamen = Double.parseDouble(etNotaExamen.getText().toString());
            if(!etNotaActividades.getText().toString().isEmpty()){
                notaActividades = Double.parseDouble(etNotaActividades.getText().toString());

                if(notaExamen >= 0 && notaExamen <= 10){
                    if(notaActividades >= 0 && notaActividades <= 10){
                        if(notaExamen >= 4.5 && notaActividades >= 7.5){
                            notaFinal = notaExamen * 0.7 + notaActividades * 0.3;
                        } else if(notaExamen < 4.5){
                            notaFinal = notaExamen;
                        } else {
                            notaFinal = notaExamen * 0.7 + notaActividades * 0.3;
                        }
                    } else {
                        Toast.makeText(this, R.string.toast_nota_actividades, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, R.string.toast_nota_examen, Toast.LENGTH_SHORT).show();
                }

                etResultado.setText(String.format("%.2f", notaFinal));

            } else {
                Toast.makeText(this, R.string.toast_registro_validacion_nota_actividades, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, R.string.toast_registro_validacion_nota_examen, Toast.LENGTH_SHORT).show();
        }
    }

    public void onSeleccionarAsignaturaClick(View view){
        Intent intent = new Intent(this, SeleccionAsignatura.class);
        seleccionAsignatura.launch(intent);
    }

    public void onSeleccionarAlumnoClick(View view){
        Intent intent = new Intent(this, SeleccionAlumnos.class);
        seleccionAlumno.launch(intent);
    }

    public void onGuardarDatosClick(View view){
        String nombre = etAlumno.getText().toString();
        String asignatura = etAsignatura.getText().toString();
        String notaExamen = etNotaExamen.getText().toString();
        String notaActividades = etNotaActividades.getText().toString();
        String notaFinal = etResultado.getText().toString();
        Alumno alumno = null;
        boolean encontrado = false;
        if(!nombre.isEmpty() && !asignatura.isEmpty()){
            if(!notaExamen.isEmpty() && !notaActividades.isEmpty() && !notaFinal.isEmpty()){
                for (int i = 0; i < listaAlumnos.size() && !encontrado; i++) {
                    alumno = listaAlumnos.get(i);
                    if(nombre.equalsIgnoreCase(alumno.getNombre()) && asignatura.equalsIgnoreCase(alumno.getAsignatura())){
                        alumno.setNotaExamenes(Double.parseDouble(notaExamen));
                        alumno.setNotaActividades(Double.parseDouble(notaActividades));
                        alumno.setNotaFinal(Double.parseDouble(notaFinal));
                        listaAlumnos.set(i, alumno);
                        encontrado = true;
                    }
                }
            } else {
                Toast.makeText(this, R.string.toast_registro_notas_vacias, Toast.LENGTH_SHORT).show();
            }
            escribirArchivoBinario();
        } else {
            Toast.makeText(this, R.string.toast_registro_datos_alumno, Toast.LENGTH_SHORT).show();
        }
    }

    private void escribirArchivoBinario() {
        try {
            File archivo = new File("/data/data/dam.pmdm.evaluaciont1_2/files/alumnos.dat");
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo));
            for (Alumno alumno : listaAlumnos){
                oos.writeObject(alumno);
                Log.d("Pruebas-w", alumno.getNombre() + " - " + alumno.getAsignatura() + " - " + alumno.getNotaFinal());
            }
            Log.d("Pruebas-w", String.valueOf(listaAlumnos.size()));
            oos.close();
            limpiarCampos();
            Toast.makeText(this, R.string.texto_datos_actualizados, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void limpiarCampos() {
        etNotaExamen.setText("");
        etNotaActividades.setText("");
        etResultado.setText("");
        etAsignatura.setText("");
        etAlumno.setText("");
    }

    private void leerArchivoBinario(){
        listaAlumnos.clear();
        try {
            File archivo = new File("/data/data/dam.pmdm.evaluaciont1_2/files/alumnos.dat");
            if(archivo.exists()){
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo));
                boolean seguir = true;
                Alumno alumno = null;
                while(seguir){
                    try{
                        alumno = (Alumno) ois.readObject();
                        listaAlumnos.add(alumno);
                        Log.d("Pruebas-r", alumno.getNombre() + " - " + alumno.getAsignatura() + " - " + alumno.getNotaFinal());
                    } catch (Exception e){
                        seguir = false;
                    }
                    Log.d("Pruebas-r", String.valueOf(listaAlumnos.size()));
                }
                ois.close();
            } else {
                listaAlumnos = Alumno.cargaInicialAlumnosTxt();
            }

        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void volverAtras(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("NOMRE_ALUMNO", etAlumno.getText().toString());
        outState.putString("NOMRE_ASIGNATURA", etAsignatura.getText().toString());
        outState.putString("NOTA_EXAMEN", etNotaExamen.getText().toString());
        outState.putString("NOTA_ACT", etNotaActividades.getText().toString());
        outState.putString("NOTA_FINAL", etResultado.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        etAlumno.setText(savedInstanceState.getString("NOMRE_ALUMNO"));
        etAsignatura.setText(savedInstanceState.getString("NOMRE_ASIGNATURA"));
        etNotaExamen.setText(savedInstanceState.getString("NOTA_EXAMEN"));
        etNotaActividades.setText(savedInstanceState.getString("NOTA_ACT"));
        etResultado.setText(savedInstanceState.getString("NOTA_FINAL"));
    }
}
