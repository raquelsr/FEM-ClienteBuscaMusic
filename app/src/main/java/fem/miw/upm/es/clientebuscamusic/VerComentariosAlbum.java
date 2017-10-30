package fem.miw.upm.es.clientebuscamusic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class VerComentariosAlbum extends AppCompatActivity {

    private static final String KEY_COMENTARIOTOTAL = "COMENTARIOSTOTAL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_comentarios_album);

        String comentarios = getIntent().getExtras().getString(KEY_COMENTARIOTOTAL);

        ArrayList<String> comentariosList = new ArrayList();

        String[] comentario = comentarios.split(";");
        for (int i = 0; i<comentario.length; i++){
            comentariosList.add(comentario[i]);
        }

        ListView listaComentarios = (ListView) findViewById(R.id.list_comentarios);
        AdapterComentarios adapter = new AdapterComentarios(getApplicationContext(), comentariosList);
        listaComentarios.setAdapter(adapter);
    }
}
