package fem.miw.upm.es.clientebuscamusic;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.TextView;

import java.util.ArrayList;

public class AdapterComentarios extends ArrayAdapter {


    private Context context;
    private ArrayList<String> comentarios;

    AdapterComentarios(Context context, ArrayList<String> comentarios) {
        super(context, R.layout.activity_adaptador_comentarios, comentarios);
        this.context = context;
        this.comentarios = comentarios;
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
            convertView = inflater.inflate(R.layout.activity_adaptador_comentarios, null);
        }

        String comentario = comentarios.get(position);
        if (!comentario.equals("")) {
            TextView tv = convertView.findViewById(R.id.txt_comentarioadapter);
            tv.setText(comentario);
        }

        return convertView;
    }
}
