package adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.copadomundo2026.R;
import com.example.copadomundo2026.modelos.Grupo;

import java.util.List;

public class ClassificacaoAdapter extends BaseAdapter {

    private static final int TIPO_GRUPO = 0;
    private static final int TIPO_SELECAO = 1;

    private Context context;
    private List<Object> itens;

    public ClassificacaoAdapter(Context context, List<Object> itens) {
        this.context = context;
        this.itens = itens;
    }

    @Override public int getCount() { return itens.size(); }
    @Override public Object getItem(int position) { return itens.get(position); }
    @Override public long getItemId(int position) { return position; }
    @Override public int getItemViewType(int position) {
        return itens.get(position) instanceof Grupo ? TIPO_GRUPO : TIPO_SELECAO;
    }
    @Override public int getViewTypeCount() { return 2; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int tipo = getItemViewType(position);

        if (tipo == TIPO_GRUPO) {
            if (convertView == null)
                convertView = LayoutInflater.from(context).inflate(R.layout.classificacao_header, parent, false);
            Grupo g = (Grupo) itens.get(position);
            TextView tv = convertView.findViewById(R.id.tvHeaderGrupo);
            tv.setText("GRUPO " + g.getNome());

        } else {
            if (convertView == null)
                convertView = LayoutInflater.from(context).inflate(R.layout.classificacao_item, parent, false);

            Object[] par = (Object[]) itens.get(position);
            String sigla    = (String) par[0];
            String nome     = (String) par[1];
            int pontos      = (int) par[2];
            int vitorias    = (int) par[3];
            int empates     = (int) par[4];
            int derrotas    = (int) par[5];
            int saldo       = (int) par[6];
            int posicao     = (int) par[7];

            TextView tvPos    = convertView.findViewById(R.id.tvPosicao);
            TextView tvNome   = convertView.findViewById(R.id.tvSelecaoNome);
            TextView tvPts    = convertView.findViewById(R.id.tvPontos);
            TextView tvV      = convertView.findViewById(R.id.tvVitorias);
            TextView tvE      = convertView.findViewById(R.id.tvEmpates);
            TextView tvD      = convertView.findViewById(R.id.tvDerrotas);
            TextView tvSG     = convertView.findViewById(R.id.tvSaldo);
            ImageView imgFlag = convertView.findViewById(R.id.imgFlagClassificacao);

            tvPos.setText(String.valueOf(posicao));
            tvNome.setText(sigla);
            tvPts.setText(String.valueOf(pontos));
            tvV.setText(String.valueOf(vitorias));
            tvE.setText(String.valueOf(empates));
            tvD.setText(String.valueOf(derrotas));
            tvSG.setText((saldo >= 0 ? "+" : "") + saldo);
            tvSG.setTextColor(saldo > 0
                    ? Color.parseColor("#00C853")
                    : saldo < 0 ? Color.parseColor("#FF3D3D")
                    : Color.parseColor("#8A9BB5"));

            // Destaca os 2 primeiros (classificados)
            tvPos.setTextColor(posicao <= 2
                    ? Color.parseColor("#00D4FF")
                    : Color.parseColor("#8A9BB5"));

            setFlag(imgFlag, sigla);
        }

        return convertView;
    }

    private void setFlag(ImageView img, String sigla) {
        if (sigla == null) return;
        String resourceName = "flag_" + sigla.toLowerCase();
        int resId = context.getResources().getIdentifier(
                resourceName, "drawable", context.getPackageName());
        if (resId != 0) {
            img.setImageResource(resId);
        } else {
            img.setImageResource(android.R.drawable.ic_menu_globe);
        }
    }
}