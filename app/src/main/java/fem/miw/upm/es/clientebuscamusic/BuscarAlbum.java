package fem.miw.upm.es.clientebuscamusic;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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

    TextView txtNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_album);
    }

    public void buscarAlbum(View v) {
        Log.i("MiW", "Entra filtrar datos");

        txtNombre = (TextView) findViewById(R.id.txt_nombreAlbum);
        TextView txtTracks = (TextView) findViewById(R.id.txt_tracksAlbum);
        ImageView imagenView = (ImageView) findViewById(R.id.image_viewAlbum);

        EditText editArtista = (EditText) findViewById(R.id.edit_artistaAlbum);
        EditText editAlbum = (EditText) findViewById(R.id.edit_album);
        String nombreArtista = editArtista.getText().toString();
        String nombreAlbum = editAlbum.getText().toString();

        txtNombre.setText("");

        String recurso = CONTENT_URI + "/" + nombreArtista + "/" + nombreAlbum;
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
            String artista = "";
            String imagen = "";
            String tracks = "";

            int colNombre = cursor.getColumnIndex(PROJECTION[0]);
            int colArtista = cursor.getColumnIndex(PROJECTION[1]);
            int colImagen = cursor.getColumnIndex(PROJECTION[2]);
            int colTracks = cursor.getColumnIndex(PROJECTION[3]);

            while (cursor.moveToNext()) {
                nombre = cursor.getString(colNombre);
                imagen = cursor.getString(colImagen);
                artista = cursor.getString(colArtista);
                tracks = cursor.getString(colTracks);
            }

            txtNombre.setText(nombre);
            txtTracks.setText(tracks);

            if (!imagen.equals("")){
                Picasso.with(getApplicationContext())
                        .load(imagen)
                        .resize(500, 500)
                        .into(imagenView);
            }

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
                    buscarAlbum(null);
                }
            }, 1000);
        }
    }

    public void puntuar(View v) {
        Intent i = new Intent(this, PuntuarArtista.class);
        i.putExtra("ARTISTA", txtNombre.getText().toString());
        startActivity(i);
    }
}
