package dam.pmdm.evaluaciont1_2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SeleccionAsignatura extends AppCompatActivity {

    private EditText asignaturaSeleccionada;
    private String asignaturaSeleccionadaTexto = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_asignatura);

        asignaturaSeleccionada = findViewById(R.id.etCodigoAsignatura);

        if (savedInstanceState != null) {
            asignaturaSeleccionadaTexto = savedInstanceState.getString("asignaturaSeleccionada", "");
            asignaturaSeleccionada.setText(asignaturaSeleccionadaTexto);
        }

        Button asignatura1 = findViewById(R.id.btnAsignatura1);
        Button asignatura2 = findViewById(R.id.btnAsignatura2);
        Button asignatura3 = findViewById(R.id.btnAsignatura3);
        Button asignatura4 = findViewById(R.id.btnAsignatura4);
        Button asignatura5 = findViewById(R.id.btnAsignatura5);
        Button asignatura6 = findViewById(R.id.btnAsignatura6);
        Button asignatura7 = findViewById(R.id.btnAsignatura7);
        Button aceptarSeleccionAsignatura = findViewById(R.id.btnSeleccionAceptar);
        Button cancelarSeleccionAsignatura = findViewById(R.id.btnSeleccionCancelar);

        asignatura1.setOnClickListener(v -> {
            asignaturaSeleccionadaTexto = asignatura1.getText().toString().toUpperCase();
            asignaturaSeleccionada.setText(asignaturaSeleccionadaTexto);
        });

        asignatura2.setOnClickListener(v -> {
            asignaturaSeleccionadaTexto = asignatura2.getText().toString().toUpperCase();
            asignaturaSeleccionada.setText(asignaturaSeleccionadaTexto);
        });

        asignatura3.setOnClickListener(v -> {
            asignaturaSeleccionadaTexto = asignatura3.getText().toString().toUpperCase();
            asignaturaSeleccionada.setText(asignaturaSeleccionadaTexto);
        });

        asignatura4.setOnClickListener(v -> {
            asignaturaSeleccionadaTexto = asignatura4.getText().toString().toUpperCase();
            asignaturaSeleccionada.setText(asignaturaSeleccionadaTexto);
        });

        asignatura5.setOnClickListener(v -> {
            asignaturaSeleccionadaTexto = asignatura5.getText().toString().toUpperCase();
            asignaturaSeleccionada.setText(asignaturaSeleccionadaTexto);
        });

        asignatura6.setOnClickListener(v -> {
            asignaturaSeleccionadaTexto = asignatura6.getText().toString().toUpperCase();
            asignaturaSeleccionada.setText(asignaturaSeleccionadaTexto);
        });

        asignatura7.setOnClickListener(v -> {
            asignaturaSeleccionadaTexto = asignatura7.getText().toString().toUpperCase();
            asignaturaSeleccionada.setText(asignaturaSeleccionadaTexto);
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

        cancelarSeleccionAsignatura.setOnClickListener(v -> finish());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("asignaturaSeleccionada", asignaturaSeleccionada.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        asignaturaSeleccionadaTexto = savedInstanceState.getString("asignaturaSeleccionada", "");
        asignaturaSeleccionada.setText(asignaturaSeleccionadaTexto);
    }
}
