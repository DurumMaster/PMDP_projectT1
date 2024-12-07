package dam.pmdm.evaluaciont1_2;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class SeleccionAsignatura extends AppCompatActivity {

    private EditText etAsignaturaSeleccionada;
    private String asignaturaSeleccionadaTexto = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_asignatura);

        etAsignaturaSeleccionada = findViewById(R.id.etCodigoAsignatura);

        Button aceptarSeleccionAsignatura = findViewById(R.id.btnSeleccionAceptar);
        Button cancelarSeleccionAsignatura = findViewById(R.id.btnSeleccionCancelar);

        LinearLayout layout = findViewById(R.id.llBotones);
        String[] asignaturas = {"PMDM", "AD", "PSP", "DI", "SGE", "IACC", "IOS"};
        Button btnAsignatura = null;
        for (String asignatura : asignaturas) {
            btnAsignatura = new Button(this);
            btnAsignatura.setText(asignatura);
            btnAsignatura.setBackgroundColor(getResources().getColor(R.color.soft_red_background));
            btnAsignatura.setTextSize(16);
            layout.addView(btnAsignatura);

            btnAsignatura.setOnClickListener(view -> {
                etAsignaturaSeleccionada.setText(asignatura);
                asignaturaSeleccionadaTexto = asignatura;
            });
        }

        aceptarSeleccionAsignatura.setOnClickListener(v -> {
            String textoParaEnviar = etAsignaturaSeleccionada.getText().toString().trim();
            if (textoParaEnviar.isEmpty()) {
                Toast.makeText(this, getString(R.string.toast_especificar_asignatura), Toast.LENGTH_SHORT).show();
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
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("asignaturaSeleccionada", etAsignaturaSeleccionada.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        asignaturaSeleccionadaTexto = savedInstanceState.getString("asignaturaSeleccionada", "");
        etAsignaturaSeleccionada.setText(asignaturaSeleccionadaTexto);
    }
}
