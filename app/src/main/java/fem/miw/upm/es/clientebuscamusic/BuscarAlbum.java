package fem.miw.upm.es.clientebuscamusic;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class BuscarAlbum extends AppCompatActivity {

    private static final String CONTENT_URI = "content://fem.miw.upm.es.buscamusic.modelsAlbum.provider/albums";

    private static String[] PROJECTION = new String[]{
            "nombre",
            "artista",
            "imagen",
            "tracks"
    };

    String artista;
    String album;

    private static final String KEY_ARTISTA = "ARTISTA";
    private static final String KEY_ALBUM = "ALBUM";
    private static final String LOG_TAG = "MiW";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_album);

        artista = getIntent().getExtras().getString(KEY_ARTISTA);
        album = getIntent().getExtras().getString(KEY_ALBUM);
        buscarAlbum(artista, album);

    }

    public void buscarAlbum(final String artista, final String album) {

        Log.i(LOG_TAG, "Buscar album");

        TextView txtAlbum = (TextView) findViewById(R.id.txt_nombreAlbum);
        TextView txtArtista = (TextView) findViewById(R.id.txt_nombreArtistaAlbum);
        TextView txtTracks = (TextView) findViewById(R.id.txt_albumTracks);
        ImageView imagenView = (ImageView) findViewById(R.id.iv_Album);

        txtAlbum.setText("");
        txtArtista.setText("");
        txtTracks.setText("");

        String recurso = CONTENT_URI + "/" + artista + "/" + album;
        Uri uriContenido = Uri.parse(recurso);

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
            String artista_bd = "";
            String imagen = "";
            String tracks = "";

            int colNombre = cursor.getColumnIndex(PROJECTION[0]);
            int colArtista = cursor.getColumnIndex(PROJECTION[1]);
            int colImagen = cursor.getColumnIndex(PROJECTION[2]);
            int colTracks = cursor.getColumnIndex(PROJECTION[3]);

            while (cursor.moveToNext()) {
                nombre = cursor.getString(colNombre);
                imagen = cursor.getString(colImagen);
                artista_bd = cursor.getString(colArtista);
                tracks = cursor.getString(colTracks);
            }

            txtAlbum.setText(nombre);
            txtArtista.setText(artista_bd);

            String resultadoTracks = "";
            String[] splitTracks = tracks.split(";");
            for (int i=0; i<splitTracks.length; i++){
                resultadoTracks = resultadoTracks.concat(String.valueOf(i+1) + " - " + splitTracks[i] + "\n");
            }

            txtTracks.setText(resultadoTracks);

            if (!imagen.equals("")){
                Picasso.with(getApplicationContext())
                        .load(imagen)
                        .into(imagenView);
            }

            cursor.close();
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    "Buscando resultados...",
                    Toast.LENGTH_LONG
            ).show();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    buscarAlbum(artista, album);
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
