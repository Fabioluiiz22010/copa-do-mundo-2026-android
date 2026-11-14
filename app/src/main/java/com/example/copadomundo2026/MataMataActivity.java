package com.example.copadomundo2026;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.copadomundo2026.dao.JogoDAO;
import com.example.copadomundo2026.modelos.Jogo;
import com.example.copadomundo2026.modelos.Simulacao;

import java.util.List;

import adapter.JogoAdapter;

public class MataMataActivity extends AppCompatActivity {

    ListView lsvOitavas, lsvQuartas, lsvSemi, lsvFinal;
    TextView tvTitulo;

    JogoDAO jDAO;
    Simulacao simulacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matamata);

        lsvOitavas = findViewById(R.id.lsvOitavas);
        lsvQuartas = findViewById(R.id.lsvQuartas);
        lsvSemi    = findViewById(R.id.lsvSemi);
        lsvFinal   = findViewById(R.id.lsvFinal);
        tvTitulo   = findViewById(R.id.tvTituloMataMata);

        simulacao = (Simulacao) getIntent().getSerializableExtra("simulacao");
        tvTitulo.setText("Mata-Mata: " + simulacao.getNome());

        jDAO = new JogoDAO(this);

        carregarFase(lsvOitavas, 2);
        carregarFase(lsvQuartas, 3);
        carregarFase(lsvSemi, 4);
        carregarFase(lsvFinal, 5);
    }

    private void carregarFase(ListView lsv, int faseId) {
        jDAO.Abrir();
        List<Jogo> lista = jDAO.ListarPorFase(faseId, simulacao.getId());
        jDAO.Fechar();

        JogoAdapter adapter = new JogoAdapter(this, R.layout.jogo_item, lista, (jogo, golsCasa, golsFora) -> {
            jDAO.Abrir();
            jDAO.SalvarPlacar(simulacao.getId(), jogo.getId(), golsCasa, golsFora);
            jDAO.Fechar();
            Toast.makeText(this, "Placar salvo!", Toast.LENGTH_SHORT).show();
        });
        lsv.setAdapter(adapter);
    }
}