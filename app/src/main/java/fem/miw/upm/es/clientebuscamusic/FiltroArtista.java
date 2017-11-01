package fem.miw.upm.es.clientebuscamusic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class FiltroArtista extends Activity {

    private static final String KEY_ARTISTA = "ARTISTA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtro_artista);
    }

    public void buscarArtistaFiltro(View v){
        EditText nombreArtista = findViewById(R.id.edit_filtroartista);
        if (nombreArtista.getText().toString().equals("")){
            Toast.makeText(this, R.string.introduceArtista, Toast.LENGTH_SHORT).show();
        } else {
            Intent i = new Intent (this, BuscarArtista.class);
            i.putExtra(KEY_ARTISTA, nombreArtista.getText().toString());
            startActivity(i);
        }
    }
}
