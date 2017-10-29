package fem.miw.upm.es.clientebuscamusic;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class BuscarTopTracks extends AppCompatActivity {

    private static final String CONTENT_URI = "content://fem.miw.upm.es.buscamusic.modelsTopTracks.provider/topTracks";

    private static String[] PROJECTION = new String[] {
            "nombre",
            "imagen",
            "artista"
    };

    TextView txtTopTracks;
    String limite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_top_tracks);

        limite = getIntent().getExtras().getString("LIMITE");
        buscarTopTracks(Integer.valueOf(limite));
    }

    public void buscarTopTracks (final int limite) {
        Log.i("MiW", "Entra filtrar datos");

        txtTopTracks = (TextView) findViewById(R.id.txt_topTracks);

        txtTopTracks.setText("");

        String recurso = CONTENT_URI + "/" + limite;
        Uri uriContenido =  Uri.parse(recurso);

        ContentResolver contentResolver = getContentResolver();

        Cursor cursor = contentResolver.query(
                uriContenido,
                PROJECTION,
                null,
                null,
                null
        );

        if (cursor != null && cursor.getCount() != 0) {

            String nombre = "";
            String imagen = "";
            String artista = "";

            int colNombre   = cursor.getColumnIndex(PROJECTION[0]);
            int colImagen    = cursor.getColumnIndex(PROJECTION[1]);
            int colArtista = cursor.getColumnIndex(PROJECTION[2]);

            String resultado = "";
            int i = 1;

            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    nombre   = cursor.getString(colNombre);
                    imagen = cursor.getString(colImagen);
                    artista    = cursor.getString(colArtista);
                    resultado = resultado.concat(i + " - " + nombre + " - " + artista + "\n");
                    i++;

                    cursor.moveToNext();
                }
            }

            txtTopTracks.setText(resultado);

            cursor.close();
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    "Buscando resultados..",
                    Toast.LENGTH_LONG
            ).show();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    buscarTopTracks(limite);
                }
            }, 1000);
        }
    }
}
