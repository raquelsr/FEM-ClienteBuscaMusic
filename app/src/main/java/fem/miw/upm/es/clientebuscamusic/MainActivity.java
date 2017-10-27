package fem.miw.upm.es.clientebuscamusic;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String CONTENT_URI = "content://fem.miw.upm.es.buscamusic.modelsArtist.provider/artist";

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
        setContentView(R.layout.activity_main);

        Button btn_buscar = (Button) findViewById(R.id.btn_buscar);
        btn_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filtrarDatos();
            }
        });
    }

    public void filtrarDatos() {
        TextView txtResultados = (TextView) findViewById(R.id.txt_info);
        EditText etFiltro = (EditText) findViewById(R.id.edit_artista);
        String textoFiltro = etFiltro.getText().toString();

        txtResultados.setText("");

        String recurso ="content://fem.miw.upm.es.buscamusic.modelsArtist.provider/artist";
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
                    "No se han obtenido datos",
                    Toast.LENGTH_LONG
            ).show();
        }
    }
}
