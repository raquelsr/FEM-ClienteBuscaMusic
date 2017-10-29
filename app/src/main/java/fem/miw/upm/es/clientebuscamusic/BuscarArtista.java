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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class BuscarArtista extends AppCompatActivity {

    private static final String CONTENT_URI = "content://fem.miw.upm.es.buscamusic.modelsArtist.provider/artistas";

    private static String[] PROJECTION = new String[] {
            "nombre",
            "imagen",
            "bio_contenido",
            "puntuacion"
    };

    String nombreArtista;
    TextView txtNombre;

    private static final String KEY_ARTISTA = "ARTISTA";
    private static final String LOG_TAG = "MiW";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_artista);

        nombreArtista = getIntent().getExtras().getString(KEY_ARTISTA);
        buscarArtista(nombreArtista);

    }

    public void buscarArtista (String artista) {

        Log.i(LOG_TAG, "Buscar artista");

        txtNombre = (TextView) findViewById(R.id.txt_nombreArtista);
        TextView txtInfo = (TextView) findViewById(R.id.txt_infoArtista);
        TextView txtPuntuacion = (TextView) findViewById(R.id.txt_puntuacionArtista);
        ImageView imagenView = (ImageView) findViewById(R.id.image_view);

        txtNombre.setText("");
        txtInfo.setText("");
        txtPuntuacion.setText("");

        String recurso = CONTENT_URI + "/" + artista;
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
            String bio_contenido = "";
            String puntuacion = "";

            int colNombre   = cursor.getColumnIndex(PROJECTION[0]);
            int colImagen    = cursor.getColumnIndex(PROJECTION[1]);
            int colBio_Resumen = cursor.getColumnIndex(PROJECTION[2]);
            int colPuntuacion = cursor.getColumnIndex(PROJECTION[3]);

            while (cursor.moveToNext()) {
                nombre   = cursor.getString(colNombre);
                imagen = cursor.getString(colImagen);
                bio_contenido    = cursor.getString(colBio_Resumen);
                puntuacion = String.valueOf(cursor.getInt(colPuntuacion));
            }

            txtNombre.setText(nombre);

            if (puntuacion.equals("-1")){
                txtPuntuacion.setText(" - ");
            } else {
                txtPuntuacion.setText(puntuacion);
            }

            if (bio_contenido.startsWith(" <a")){
                txtInfo.setText("No hay información");
            }else {
                txtInfo.setText(bio_contenido);
            }

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
                    buscarArtista(nombreArtista);
                }
            }, 1000);
        }
    }

    public void puntuar(View v){
        Intent i = new Intent (this, PuntuarArtista.class);
        i.putExtra(KEY_ARTISTA, txtNombre.getText().toString());
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent (this, MainActivity.class);
        startActivity(i);
    }
}
