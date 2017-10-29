package fem.miw.upm.es.clientebuscamusic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_buscarArtista = (Button) findViewById(R.id.btn_buscarArtista);
        Button btn_buscarAlbum = (Button) findViewById(R.id.btn_buscarAlbum);
        Button btn_buscarTopTrakcs = (Button) findViewById(R.id.btn_buscarTopTracks);

        btn_buscarArtista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), FiltroArtista.class);
                startActivity(i);
            }
        });

        btn_buscarAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), FiltroAlbum.class);
                startActivity(i);
            }
        });

        btn_buscarTopTrakcs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), FiltroTopTracks.class);
                startActivity(i);
            }
        });
    }
}
