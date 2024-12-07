package dam.pmdm.evaluaciont1_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String languageCode = GuardarIdioma.getSavedLanguage(this);
        ManejarIdioma.setAppLocale(this, languageCode);

        setContentView(R.layout.activity_main);
    }

    public void accederRegistroNota(View view) {
        Intent intent = new Intent(this, RegistroNota.class);
        startActivity(intent);
    }

    public void accederConsultarNota(View view) {
        Intent intent = new Intent(this, ConsultaNotas.class);
        startActivity(intent);
    }

    public void cambiarIdioma(View view) {
        String currentLanguage = GuardarIdioma.getSavedLanguage(this);

        String newLanguage = currentLanguage.equals("es") ? "en" : "es";

        GuardarIdioma.saveLanguage(this, newLanguage);

        ManejarIdioma.setAppLocale(this, newLanguage);

        recreate();

        Toast.makeText(this, "Idioma cambiado a " + (newLanguage.equals("es") ? "Español" : "Inglés"), Toast.LENGTH_SHORT).show();
    }
}
