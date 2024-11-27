package dam.pmdm.evaluaciont1_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class RegistroNota extends AppCompatActivity {

    private EditText etAlumno;
    private EditText etAsignatura;
    private EditText etNotaExamen;
    private EditText etNotaActividades;
    private EditText etResultado;

    private static ArrayList<Alumno> listaAlumnos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro_nota);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        listaAlumnos = new ArrayList<Alumno>();

        etAlumno = findViewById(R.id.etAlumno);
        etAsignatura = findViewById(R.id.etAsignatura);
        etNotaExamen = findViewById(R.id.etNotaExamen);
        etNotaActividades = findViewById(R.id.etNotaActividades);
        etResultado = findViewById(R.id.etResultado);

        String alumno = getIntent().getStringExtra("ALUMNO");
        if(alumno != null){
            etAlumno.setText(alumno);
        }

        String asignatura = getIntent().getStringExtra("ASIGNATURA");
        if(asignatura != null){
            etAsignatura.setText(asignatura);
        }

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

                etResultado.setText(String.valueOf(notaFinal));

            } else {
                Toast.makeText(this, R.string.toast_registro_validacion_nota_actividades, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, R.string.toast_registro_validacion_nota_examen, Toast.LENGTH_SHORT).show();
        }
    }

    public void onSeleccionarAsignaturaClick(View view){
        Intent intent = new Intent(this, SeleccionAsignatura.class);
        startActivity(intent);
    }

    public void onSeleccionarAlumnoClick(View view){
//        Intent intent = new Intent(this, SeleccionAlumnos.class);
//        startActivity(intent);
    }

    public void onGuardarDatosClick(View view){
        leerArchivoBinario();
        String nombre = etAlumno.getText().toString();
        String asignatura = etAsignatura.getText().toString();
        Alumno alumno = null;
        if(!nombre.isEmpty() && !asignatura.isEmpty()){
            for (int i = 0; i < listaAlumnos.size(); i++) {
                alumno = listaAlumnos.get(i);
                if(alumno.getNombre().equals(nombre) && alumno.getAsignatura().equals(asignatura)){
                    alumno.setNotaExamenes(Double.parseDouble(etNotaExamen.getText().toString()));
                    alumno.setNotaActividades(Double.parseDouble(etNotaActividades.getText().toString()));
                    alumno.setNotaFinal(Double.parseDouble(etResultado.getText().toString()));
                    listaAlumnos.set(i, alumno);
                }
            }
            escribirArchivoBinario();
        } else {
            Toast.makeText(this, R.string.toast_registro_datos_alumno, Toast.LENGTH_SHORT).show();
        }
    }

    private void escribirArchivoBinario() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Ficheros/alumnos.dat"));
            for (Alumno alumno : listaAlumnos){
                oos.writeObject(alumno);
            }
            oos.close();
            Toast.makeText(this, R.string.texto_datos_actualizados, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void leerArchivoBinario(){
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Ficheros/alumnos.dat"));
            boolean seguir = true;

            while(seguir){
                try{
                    listaAlumnos.add((Alumno) ois.readObject());
                } catch (Exception e){
                    seguir = false;
                }
            }
            ois.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        escribirArchivoBinario();
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
}