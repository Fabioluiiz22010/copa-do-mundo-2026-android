package com.example.copadomundo2026;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.copadomundo2026.dao.JogoDAO;
import com.example.copadomundo2026.modelos.Grupo;
import com.example.copadomundo2026.modelos.Jogo;
import com.example.copadomundo2026.modelos.Simulacao;

import java.util.List;

import adapter.JogoAdapter;

public class JogosActivity extends AppCompatActivity {

    ListView lsvJogos;
    TextView tvTituloGrupo;

    JogoDAO jDAO;
    List<Jogo> lista;
    Simulacao simulacao;
    Grupo grupo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogos);

        lsvJogos = findViewById(R.id.lsvJogos);
        tvTituloGrupo = findViewById(R.id.tvTituloGrupo);

        simulacao = (Simulacao) getIntent().getSerializableExtra("simulacao");
        grupo = (Grupo) getIntent().getSerializableExtra("grupo");

        tvTituloGrupo.setText("Grupo " + grupo.getNome());

        jDAO = new JogoDAO(this);
        jDAO.Abrir();
        lista = jDAO.ListarPorGrupo(grupo.getId(), simulacao.getId());
        jDAO.Fechar();

        JogoAdapter adapter = new JogoAdapter(this, R.layout.jogo_item, lista, (jogo, golsCasa, golsFora) -> {
            jDAO.Abrir();
            jDAO.SalvarPlacar(simulacao.getId(), jogo.getId(), golsCasa, golsFora);
            jDAO.Fechar();
            Toast.makeText(this, "Placar salvo!", Toast.LENGTH_SHORT).show();
        });

        lsvJogos.setAdapter(adapter);
    }
}