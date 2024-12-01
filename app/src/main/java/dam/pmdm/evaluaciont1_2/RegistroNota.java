package dam.pmdm.evaluaciont1_2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
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
        startActivity(intent);
    }

    public void onSeleccionarAlumnoClick(View view){
//        Intent intent = new Intent(this, SeleccionAlumnos.class);
//        startActivity(intent);
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
                leerArchivoBinario();
                for (int i = 0; i < listaAlumnos.size() && !encontrado; i++) {
                    alumno = listaAlumnos.get(i);
                    if(nombre.equals(alumno.getNombre()) && asignatura.equals(alumno.getAsignatura())){
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
            File archivo = new File(getExternalFilesDir(null), "alumnos.dat");
            int i = 0;
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo));
            for (Alumno alumno : listaAlumnos){
                oos.writeObject(alumno);
            }
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
        try {
            File archivo = new File(getExternalFilesDir(null), "alumnos.dat");
            if(archivo.exists()){
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo));
                boolean seguir = true;
                Alumno alumno = null;
                while(seguir){
                    try{
                        alumno = (Alumno) ois.readObject();
                        listaAlumnos.add(alumno);
                        Log.d("Pruebas", alumno.getNombre() + " - " + alumno.getAsignatura() + " - " + alumno.getNotaFinal());
                    } catch (Exception e){
                        seguir = false;
                    }
                }
                ois.close();
            } else {
                BufferedReader reader = new BufferedReader(new FileReader("/data/data/dam.pmdm.evaluaciont1_2/files/Alumnos.txt"));
                String linea;
                String [] datos;
                while((linea = reader.readLine()) != null){
                    datos = linea.split(",");
                    listaAlumnos.add(new Alumno(datos[0], datos[1], Double.parseDouble(datos[2]), Double.parseDouble(datos[3]), Double.parseDouble(datos[4])));
                }
                Log.d("Pruebas", String.valueOf(listaAlumnos.size()));
                reader.close();
            }

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