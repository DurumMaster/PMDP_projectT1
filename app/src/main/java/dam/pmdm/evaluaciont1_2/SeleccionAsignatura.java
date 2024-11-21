package dam.pmdm.evaluaciont1_2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SeleccionAsignatura extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_asignatura);

        Button asignatura1 = findViewById(R.id.btnAsignatura1);
        Button asignatura2 = findViewById(R.id.btnAsignatura2);
        Button asignatura3 = findViewById(R.id.btnAsignatura3);
        Button asignatura4 = findViewById(R.id.btnAsignatura4);
        Button asignatura5 = findViewById(R.id.btnAsignatura5);
        Button asignatura6 = findViewById(R.id.btnAsignatura6);
        Button asignatura7 = findViewById(R.id.btnAsignatura7);
        EditText asignaturaSeleccionada = findViewById(R.id.etCodigoAsignatura);
        Button aceptarSeleccionAsignatura = findViewById(R.id.btnSeleccionAceptar);
        Button cancelarSeleccionAsignatura = findViewById(R.id.btnSeleccionCancelar);

        asignatura1.setOnClickListener(v -> {
            String textoBoton = asignatura1.getText().toString().toUpperCase();
            asignaturaSeleccionada.setText(textoBoton);
        });

        asignatura2.setOnClickListener(v -> {
            String textoBoton = asignatura2.getText().toString().toUpperCase();
            asignaturaSeleccionada.setText(textoBoton);
        });

        asignatura3.setOnClickListener(v -> {
            String textoBoton = asignatura3.getText().toString().toUpperCase();
            asignaturaSeleccionada.setText(textoBoton);
        });

        asignatura4.setOnClickListener(v -> {
            String textoBoton = asignatura4.getText().toString().toUpperCase();
            asignaturaSeleccionada.setText(textoBoton);
        });

        asignatura5.setOnClickListener(v -> {
            String textoBoton = asignatura5.getText().toString().toUpperCase();
            asignaturaSeleccionada.setText(textoBoton);
        });

        asignatura6.setOnClickListener(v -> {
            String textoBoton = asignatura6.getText().toString().toUpperCase();
            asignaturaSeleccionada.setText(textoBoton);
        });

        asignatura7.setOnClickListener(v -> {
            String textoBoton = asignatura7.getText().toString().toUpperCase();
            asignaturaSeleccionada.setText(textoBoton);
        });

        aceptarSeleccionAsignatura.setOnClickListener(v -> {
            String textoParaEnviar = asignaturaSeleccionada.getText().toString().trim();
            if (textoParaEnviar.isEmpty()) {
                Toast.makeText(this, "Debe especificar la asignatura", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent();
                intent.putExtra("ASIGNATURA", textoParaEnviar);
                setResult(RESULT_OK, intent);
                finish();
            }
        });


        cancelarSeleccionAsignatura.setOnClickListener(v -> {
            finish();
        });
    }
}
