package fem.miw.upm.es.clientebuscamusic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Raquel on 29/10/17.
 */

public class AdapterTopTracks extends ArrayAdapter {

    private Context context;
    private ArrayList<AdapterTrack> resultados;

    public AdapterTopTracks(Context context, ArrayList<AdapterTrack> resultados) {
        super(context, R.layout.adapter_toptracks, resultados);
        this.context = context;
        this.resultados = resultados;
        setNotifyOnChange(true);
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.adapter_toptracks, null);
        }

        AdapterTrack track = resultados.get(position);
        if (track != null) {

           TextView tv = (TextView) convertView.findViewById(R.id.txt_track);
            tv.setText(track.getTrack());

            TextView tv_artista = (TextView) convertView.findViewById(R.id.txt_trackArtista);
            tv_artista.setText(track.getArtista());

            ImageView iv = (ImageView) convertView.findViewById(R.id.iv_trackimage);

            Picasso.with(context)
                    .load(track.getImagen())
                    .into(iv);
        }

        return convertView;
    }
}
