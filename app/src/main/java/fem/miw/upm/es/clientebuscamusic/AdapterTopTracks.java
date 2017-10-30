package fem.miw.upm.es.clientebuscamusic;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


class AdapterTopTracks extends ArrayAdapter {

    private Context context;
    private ArrayList<AdapterTrack> resultados;

    AdapterTopTracks(Context context, ArrayList<AdapterTrack> resultados) {
        super(context, R.layout.adapter_toptracks, resultados);
        this.context = context;
        this.resultados = resultados;
        setNotifyOnChange(true);
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.adapter_toptracks, null);
        }

        AdapterTrack track = resultados.get(position);
        if (track != null) {

            TextView tv_numer = convertView.findViewById(R.id.txt_numtrack);
            tv_numer.setText(String.valueOf(track.getN()));

            TextView tv = convertView.findViewById(R.id.txt_track);
            tv.setText(track.getTrack());

            TextView tv_artista = convertView.findViewById(R.id.txt_trackArtista);
            tv_artista.setText(track.getArtista());

            ImageView iv = convertView.findViewById(R.id.iv_trackimage);

            Picasso.with(context)
                    .load(track.getImagen())
                    .into(iv);
        }

        return convertView;
    }
}
