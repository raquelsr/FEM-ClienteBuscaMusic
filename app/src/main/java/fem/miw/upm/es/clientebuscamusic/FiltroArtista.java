package fem.miw.upm.es.clientebuscamusic;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class FiltroArtista extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtro_artista);
    }

    public void buscarArtistaFiltro(View v){
        EditText nombreArtista = (EditText) findViewById(R.id.edit_filtroartista);
        Intent i = new Intent (this, BuscarArtista.class);
        i.putExtra("ARTISTA", nombreArtista.getText().toString());
        startActivity(i);
    }
}
