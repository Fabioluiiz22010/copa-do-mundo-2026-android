package com.example.copadomundo2026;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.copadomundo2026.dao.GrupoDAO;
import com.example.copadomundo2026.modelos.Grupo;
import com.example.copadomundo2026.modelos.Simulacao;

import java.util.List;

import adapter.GrupoAdapter;

public class GruposActivity extends AppCompatActivity {

    ListView lsvGrupos;
    TextView tvTituloSimulacao;

    GrupoDAO gDAO;
    List<Grupo> lista;
    Simulacao simulacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupos);

        lsvGrupos = findViewById(R.id.lsvGrupos);
        tvTituloSimulacao = findViewById(R.id.tvTituloSimulacao);

        simulacao = (Simulacao) getIntent().getSerializableExtra("simulacao");
        tvTituloSimulacao.setText("Simulação: " + simulacao.getNome());

        gDAO = new GrupoDAO(this);
        gDAO.Abrir();
        lista = gDAO.ListarTudo();
        gDAO.Fechar();

        GrupoAdapter adapter = new GrupoAdapter(this, R.layout.grupo_item, lista);
        lsvGrupos.setAdapter(adapter);

        lsvGrupos.setOnItemClickListener((parent, view, position, id) -> {
            Grupo g = lista.get(position);
            Intent intent = new Intent(this, JogosActivity.class);
            intent.putExtra("simulacao", simulacao);
            intent.putExtra("grupo", g);
            startActivity(intent);
        });
    }

    public void MataMataClique(View view) {
        Intent intent = new Intent(this, MataMataActivity.class);
        intent.putExtra("simulacao", simulacao);
        startActivity(intent);
    }

    public void ClassificacaoClique(View view) {
        Intent intent = new Intent(this, ClassificacaoActivity.class);
        intent.putExtra("simulacao", simulacao);
        startActivity(intent);
    }
}