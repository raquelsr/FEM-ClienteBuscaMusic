package fem.miw.upm.es.clientebuscamusic;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BuscarArtista extends AppCompatActivity {

    private static final String CONTENT_URI = "content://fem.miw.upm.es.buscamusic.modelsArtist.provider/artistas";

    // Proyección: columnas de la tabla a recuperar
    private static String[] PROJECTION = new String[] {
            "_id",
            "nombre",
            "imagen",
            "bio_resumen"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_artista);
    }

    public void filtrarDatos(View v) {
        Log.i("MiW", "Entra filtrar datos");
        TextView txtResultados = (TextView) findViewById(R.id.txt_info);
        EditText etFiltro = (EditText) findViewById(R.id.edit_artista);
        String textoFiltro = etFiltro.getText().toString();

        txtResultados.setText("");

        String recurso = CONTENT_URI + "/" + textoFiltro;
        Uri uriContenido =  Uri.parse(recurso);

        // obtenemos el ContentResolver
        ContentResolver contentResolver = getContentResolver();

        System.out.println("ContentResolver" + contentResolver.toString());
        // Se ejecuta la consulta
        Cursor cursor = contentResolver.query(
                uriContenido,   // uri del recurso
                PROJECTION,     // Columnas a devolver
                null,           // Condición de la query
                null,           // Argumentos variables de la query
                null            // Orden de los resultados
        );

        Log.i("MiW" , "CURsOR" + cursor.toString());
        // if (cursor.getCount() != 0)
        if (cursor != null && cursor.getCount() != 0) {
            int id;
            String nombre;
            String imagen;
            String bio_resumen;

            // índices de las columnas
            int colId       = cursor.getColumnIndex(PROJECTION[0]);
            int colNombre   = cursor.getColumnIndex(PROJECTION[1]);
            int colImagen    = cursor.getColumnIndex(PROJECTION[2]);
            int colBio_Resumen = cursor.getColumnIndex(PROJECTION[3]);

            // se recuperan y muestran los resultados recuperados en el cursor
            while (cursor.moveToNext()) {
                id       = cursor.getInt(colId);
                nombre   = cursor.getString(colNombre);
                imagen = cursor.getString(colImagen);
                bio_resumen    = cursor.getString(colBio_Resumen);

                txtResultados.append("FUNCIONA???" + nombre);
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
                    // acciones que se ejecutan tras los milisegundos
                    filtrarDatos(null);
                }
            }, 1000);
        }
    }
}
