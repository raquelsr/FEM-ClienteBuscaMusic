package fem.miw.upm.es.clientebuscamusic;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import android.widget.Toast;

import java.util.ArrayList;


public class BuscarTopTracks extends AppCompatActivity {

    private static final String CONTENT_URI = "content://fem.miw.upm.es.buscamusic.modelsTopTracks.provider/topTracks";

    private static String[] PROJECTION = new String[] {
            "_id",
            "nombre",
            "imagen",
            "artista"
    };

    String limite;

    private static final String KEY_LIMITE = "LIMITE";
    private static final String LOG_TAG = "MiW";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_top_tracks);

        limite = getIntent().getExtras().getString(KEY_LIMITE);
        buscarTopTracks(Integer.valueOf(limite));
    }

    public void buscarTopTracks (final int limite) {

        Log.i(LOG_TAG, "Buscar top tracks");

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

            String nombre;
            String imagen;
            String artista;
            int id;

            int colId = cursor.getColumnIndex(PROJECTION[0]);
            int colNombre   = cursor.getColumnIndex(PROJECTION[1]);
            int colImagen    = cursor.getColumnIndex(PROJECTION[2]);
            int colArtista = cursor.getColumnIndex(PROJECTION[3]);

            ArrayList<AdapterTrack> tracks = new ArrayList<>();

            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    id = cursor.getInt(colId);
                    nombre   = cursor.getString(colNombre);
                    imagen = cursor.getString(colImagen);
                    artista    = cursor.getString(colArtista);

                    AdapterTrack track = new AdapterTrack(id, nombre, artista, imagen);
                    tracks.add(track);

                    cursor.moveToNext();
                }
            }

            ListView lista = (ListView) findViewById(R.id.list_tracks);
            AdapterTopTracks adapter = new AdapterTopTracks(getApplicationContext(), tracks);
            lista.setAdapter(adapter);

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

    @Override
    public void onBackPressed() {
        Intent i = new Intent (this, MainActivity.class);
        startActivity(i);
    }
}
