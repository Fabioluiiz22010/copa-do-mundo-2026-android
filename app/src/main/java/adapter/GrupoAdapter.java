package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.copadomundo2026.R;
import com.example.copadomundo2026.modelos.Grupo;

import java.util.List;

public class GrupoAdapter extends ArrayAdapter<Grupo> {

    public GrupoAdapter(Context context, int resource, List<Grupo> lista) {
        super(context, resource, lista);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.grupo_item, parent, false);
        }
        Grupo g = getItem(position);
        TextView tvGrupo = convertView.findViewById(R.id.tvNomeGrupo);
        tvGrupo.setText("Grupo " + g.getNome());
        return convertView;
    }
}