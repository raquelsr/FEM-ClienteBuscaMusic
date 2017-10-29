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

public class ComentarAlbum extends Activity {

    private static final String CONTENT_URI = "content://fem.miw.upm.es.buscamusic.modelsAlbum.provider/albums";

    private static final String KEY_ALBUM = "ALBUM";
    private static final String KEY_ARTISTA = "ARTISTA";
    private static final String KEY_COMENTARIO = "COMENTARIOS";
    private static final String KEY_COMENTARIOSTOTAL = "COMENTARIOSTOTAL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentar_album);

        final EditText comentario = findViewById(R.id.edit_comentario);

        final String nombreAlbum = getIntent().getExtras().getString(KEY_ALBUM);
        final String nombreArtista = getIntent().getExtras().getString(KEY_ARTISTA);


        Button btnAñadir = findViewById(R.id.btn_añadircomen);
        btnAñadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (comentario.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Introduce comentario.", Toast.LENGTH_SHORT).show();
                } else {
                    String recurso = CONTENT_URI + "/" + nombreArtista + "/" + nombreAlbum;
                    Uri uriContenido =  Uri.parse(recurso);

                    ContentResolver contentResolver = getContentResolver();

                    String totalComentarios = getIntent().getExtras().getString(KEY_COMENTARIOSTOTAL);
                    if (totalComentarios==null){
                        totalComentarios = "";
                    }
                    totalComentarios = totalComentarios.concat(comentario.getText().toString()).concat(";");
                    ContentValues valores = new ContentValues();
                    valores.put(KEY_COMENTARIO, totalComentarios);

                    contentResolver.update(uriContenido, valores, null, null);

                    Toast.makeText(getApplicationContext(), "Comentario guardado. ¡Gracias por la colaboración!" ,Toast.LENGTH_SHORT).show();

                    Intent i = new Intent (getApplicationContext(), BuscarAlbum.class);
                    i.putExtra(KEY_ARTISTA, nombreArtista);
                    i.putExtra(KEY_ALBUM, nombreAlbum);
                    startActivity(i);
                }
            }
        });
    }
}
