package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.copadomundo2026.R;
import com.example.copadomundo2026.modelos.Jogo;

import java.util.List;

public class JogoAdapter extends ArrayAdapter<Jogo> {

    public interface OnSalvarPlacar {
        void salvar(Jogo jogo, int golsCasa, int golsFora);
    }

    private OnSalvarPlacar listener;

    public JogoAdapter(Context context, int resource, List<Jogo> lista, OnSalvarPlacar listener) {
        super(context, resource, lista);
        this.listener = listener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.jogo_item, parent, false);
        }

        Jogo jogo = getItem(position);

        TextView tvCasa      = convertView.findViewById(R.id.tvCasa);
        TextView tvFora      = convertView.findViewById(R.id.tvFora);
        EditText edtGolsCasa = convertView.findViewById(R.id.edtGolsCasa);
        EditText edtGolsFora = convertView.findViewById(R.id.edtGolsFora);
        Button btnSalvar     = convertView.findViewById(R.id.btnSalvar);
        ImageView imgCasa    = convertView.findViewById(R.id.imgFlagCasa);
        ImageView imgFora    = convertView.findViewById(R.id.imgFlagFora);

        tvCasa.setText(jogo.getSiglaCasa() != null ? jogo.getSiglaCasa() : "???");
        tvFora.setText(jogo.getSiglaFora() != null ? jogo.getSiglaFora() : "???");
        edtGolsCasa.setText(String.valueOf(jogo.getGolsCasa()));
        edtGolsFora.setText(String.valueOf(jogo.getGolsFora()));

        setFlag(imgCasa, jogo.getSiglaCasa());
        setFlag(imgFora, jogo.getSiglaFora());

        btnSalvar.setOnClickListener(v -> {
            int gc = 0, gf = 0;
            try { gc = Integer.parseInt(edtGolsCasa.getText().toString()); } catch (Exception ignored) {}
            try { gf = Integer.parseInt(edtGolsFora.getText().toString()); } catch (Exception ignored) {}
            jogo.setGolsCasa(gc);
            jogo.setGolsFora(gf);
            listener.salvar(jogo, gc, gf);
        });

        return convertView;
    }

    private void setFlag(ImageView img, String sigla) {
        if (sigla == null) return;
        String resourceName = "flag_" + sigla.toLowerCase();
        int resId = getContext().getResources().getIdentifier(
                resourceName, "drawable", getContext().getPackageName());
        if (resId != 0) {
            img.setImageResource(resId);
        } else {
            img.setImageResource(android.R.drawable.ic_menu_globe);
        }
    }
}