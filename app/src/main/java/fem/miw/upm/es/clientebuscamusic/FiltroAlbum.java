package fem.miw.upm.es.clientebuscamusic;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class FiltroAlbum extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtro_album);
    }

    public void buscarFiltroAlbum (View v){
        EditText editArtista = (EditText) findViewById(R.id.edit_filtroAlbumartista);
        EditText editAlbum = (EditText) findViewById(R.id.edit_filtroAlbumalbum);

        Intent i = new Intent(this, BuscarAlbum.class);
        i.putExtra("ARTISTA" , editArtista.getText().toString());
        i.putExtra("ALBUM", editAlbum.getText().toString());
        startActivity(i);
    }
}
