package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.copadomundo2026.R;
import com.example.copadomundo2026.modelos.Simulacao;

import java.util.List;

public class SimulacaoAdapter extends ArrayAdapter<Simulacao> {

    public SimulacaoAdapter(Context context, int resource, List<Simulacao> lista) {
        super(context, resource, lista);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.simulacao_item, parent, false);
        }
        Simulacao s = getItem(position);
        TextView tvNome = convertView.findViewById(R.id.tvNomeSimulacao);
        TextView tvData = convertView.findViewById(R.id.tvDataSimulacao);
        tvNome.setText(s.getNome());
        tvData.setText(s.getDataCriacao());
        return convertView;
    }
}