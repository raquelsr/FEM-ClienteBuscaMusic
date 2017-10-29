package fem.miw.upm.es.clientebuscamusic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class FiltroTopTracks extends Activity {

    private static final String KEY_LIMITE = "LIMITE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtro_top_tracks);
    }

    public void buscarTopTracks (View v){
        EditText limit = findViewById(R.id.edit_filtroTopTracks);
        Intent i = new Intent(this, BuscarTopTracks.class);
        i.putExtra(KEY_LIMITE, limit.getText().toString());
        startActivity(i);
    }
}
