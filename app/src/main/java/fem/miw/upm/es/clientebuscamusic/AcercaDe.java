package fem.miw.upm.es.clientebuscamusic;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class AcercaDe extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca_de);
    }

    public void volver (View v){
        onBackPressed();
    }
}
