package com.example.copadomundo2026;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.copadomundo2026.dao.SimulacaoDAO;
import com.example.copadomundo2026.modelos.Simulacao;

import java.util.List;

import adapter.SimulacaoAdapter;

public class MainActivity extends AppCompatActivity {

    ListView lsvSimulacoes;
    EditText edtNomeSimulacao;
    Button btnNovaSimulacao;

    SimulacaoDAO sDAO;
    List<Simulacao> lista;

    ActivityResultLauncher<Intent> simulacaoLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lsvSimulacoes = findViewById(R.id.lsvSimulacoes);
        edtNomeSimulacao = findViewById(R.id.edtNomeSimulacao);
        btnNovaSimulacao = findViewById(R.id.btnNovaSimulacao);

        sDAO = new SimulacaoDAO(this);

        simulacaoLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    sDAO.Abrir();
                    Listar();
                    sDAO.Fechar();
                });

        sDAO.Abrir();
        Listar();
        sDAO.Fechar();

        lsvSimulacoes.setOnItemClickListener((parent, view, position, id) -> {
            Simulacao s = lista.get(position);
            Intent intent = new Intent(this, GruposActivity.class);
            intent.putExtra("simulacao", s);
            simulacaoLauncher.launch(intent);
        });
    }

    public void NovaSimulacaoClique(View view) {
        String nome = edtNomeSimulacao.getText().toString().trim();
        if (nome.isEmpty()) {
            Toast.makeText(this, "Digite um nome para a simulação!", Toast.LENGTH_SHORT).show();
            return;
        }
        Simulacao s = new Simulacao(nome);
        sDAO.Abrir();
        long novoId = sDAO.Inserir(s);
        Listar();
        sDAO.Fechar();

        if (novoId > 0) {
            edtNomeSimulacao.setText("");
            Toast.makeText(this, "Simulação criada!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Erro ao criar simulação.", Toast.LENGTH_SHORT).show();
        }
    }

    private void Listar() {
        lista = sDAO.ListarTudo();
        SimulacaoAdapter adapter = new SimulacaoAdapter(this, R.layout.simulacao_item, lista);
        lsvSimulacoes.setAdapter(adapter);
    }
}