package fem.miw.upm.es.clientebuscamusic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class FiltroAlbum extends Activity {

    private static final String KEY_ARTISTA = "ARTISTA";
    private static final String KEY_ALBUM = "ALBUM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtro_album);
    }

    public void buscarFiltroAlbum (View v){
        EditText editArtista = findViewById(R.id.edit_filtroAlbumartista);
        EditText editAlbum = findViewById(R.id.edit_filtroAlbumalbum);

        Intent i = new Intent(this, BuscarAlbum.class);
        i.putExtra(KEY_ARTISTA , editArtista.getText().toString());
        i.putExtra(KEY_ALBUM, editAlbum.getText().toString());
        startActivity(i);
    }
}
