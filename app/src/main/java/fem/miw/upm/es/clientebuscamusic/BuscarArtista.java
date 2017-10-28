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

public class BuscarArtista extends AppCompatActivity {

    private static final String CONTENT_URI = "content://fem.miw.upm.es.buscamusic.modelsArtist.provider/artistas";

    private static String[] PROJECTION = new String[] {
            "_id",
            "nombre",
            "imagen",
            "bio_resumen"
    };

    TextView txtNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_artista);
    }

    public void buscarArtista (View v) {
        Log.i("MiW", "Entra filtrar datos");

        txtNombre = (TextView) findViewById(R.id.txt_nombreArtista);
        TextView txtResumen = (TextView) findViewById(R.id.txt_resumenArtista);
        ImageView imagenView = (ImageView) findViewById(R.id.image_view);
        ImageView ii = (ImageView) findViewById(R.id.ii);

        EditText editArtista = (EditText) findViewById(R.id.edit_artista);
        String nombreArtista = editArtista.getText().toString();


        txtNombre.setText("");

        String recurso = CONTENT_URI + "/" + nombreArtista;
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
            String bio_resumen = "";

            int colNombre   = cursor.getColumnIndex(PROJECTION[1]);
            int colImagen    = cursor.getColumnIndex(PROJECTION[2]);
            int colBio_Resumen = cursor.getColumnIndex(PROJECTION[3]);

            while (cursor.moveToNext()) {
                nombre   = cursor.getString(colNombre);
                imagen = cursor.getString(colImagen);
                bio_resumen    = cursor.getString(colBio_Resumen);
            }

            txtNombre.setText(nombre);
            if (bio_resumen.startsWith(" <a")){
                txtResumen.setText("No hay información");
            }else {
                txtResumen.setText(bio_resumen);
            }
            Picasso.with(getApplicationContext())
                    .load("https://lastfm-img2.akamaized.net/i/u/300x300/898dd9f0f3474ff9ad595bbc2e7cb785.png")
                    .resize(500,500)
                    .into(imagenView);

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
                    buscarArtista(null);
                }
            }, 1000);
        }
    }

    public void puntuar(View v){
        Intent i = new Intent (this, PuntuarArtista.class);
        i.putExtra("ARTISTA", txtNombre.getText().toString());
        startActivity(i);
    }
}
