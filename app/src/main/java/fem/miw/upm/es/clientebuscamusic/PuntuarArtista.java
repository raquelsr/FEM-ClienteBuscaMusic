package fem.miw.upm.es.clientebuscamusic;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PuntuarArtista extends Activity {

    private static final String CONTENT_URI = "content://fem.miw.upm.es.buscamusic.modelsArtist.provider/artistas";

    private static final String KEY_ARTISTA = "ARTISTA";
    private static final String KEY_PUNTUACION = "PUNTUACION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntuar_artista);

        final EditText punt = findViewById(R.id.edit_puntuacion);
        final String nombreArtista = getIntent().getExtras().getString(KEY_ARTISTA);


        Button btnAceptar =  findViewById(R.id.btn_aceptarPuntuacion);
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (punt.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Introduce puntuación", Toast.LENGTH_SHORT).show();
                }else if ((Integer.valueOf(punt.getText().toString())>10) || (Integer.valueOf(punt.getText().toString())<0)){
                    Toast.makeText(getApplicationContext(), "La puntuación debe ser entre 0 y 10.", Toast.LENGTH_SHORT).show();
                } else {
                    String recurso = CONTENT_URI + "/" + nombreArtista;
                    Uri uriContenido =  Uri.parse(recurso);

                    ContentResolver contentResolver = getContentResolver();

                    ContentValues valores = new ContentValues();
                    valores.put(KEY_PUNTUACION, Integer.valueOf(punt.getText().toString()));

                    contentResolver.update(uriContenido, valores, null, null);

                    Toast.makeText(getApplicationContext(), "Puntuación registrada. ¡Gracias por la colaboración!" ,Toast.LENGTH_SHORT).show();

                    Intent i = new Intent (getApplicationContext(), BuscarArtista.class);
                    i.putExtra(KEY_ARTISTA, nombreArtista);
                    startActivity(i);
                }
            }
        });
    }
}
