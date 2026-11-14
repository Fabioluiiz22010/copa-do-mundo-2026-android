package com.example.copadomundo2026;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.copadomundo2026.dao.GrupoDAO;
import com.example.copadomundo2026.dao.JogoDAO;
import com.example.copadomundo2026.modelos.Grupo;
import com.example.copadomundo2026.modelos.Jogo;
import com.example.copadomundo2026.modelos.Selecao;
import com.example.copadomundo2026.modelos.Simulacao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adapter.ClassificacaoAdapter;

public class ClassificacaoActivity extends AppCompatActivity {

    ListView lsvClassificacao;
    TextView tvTitulo;

    Simulacao simulacao;
    GrupoDAO gDAO;
    JogoDAO jDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classificacao);

        lsvClassificacao = findViewById(R.id.lsvClassificacao);
        tvTitulo = findViewById(R.id.tvTituloClassificacao);

        simulacao = (Simulacao) getIntent().getSerializableExtra("simulacao");
        tvTitulo.setText("Classificação: " + simulacao.getNome());

        gDAO = new GrupoDAO(this);
        jDAO = new JogoDAO(this);

        gDAO.Abrir();
        List<Grupo> grupos = gDAO.ListarTudo();
        gDAO.Fechar();


        List<Object> itens = new ArrayList<>();
        for (Grupo g : grupos) {
            itens.add(g);
            itens.addAll(calcularClassificacao(g));
        }

        ClassificacaoAdapter adapter = new ClassificacaoAdapter(this, itens);
        lsvClassificacao.setAdapter(adapter);
    }

    private List<Object[]> calcularClassificacao(Grupo grupo) {
        jDAO.Abrir();
        List<Jogo> jogos = jDAO.ListarPorGrupo(grupo.getId(), simulacao.getId());
        jDAO.Fechar();

        Map<Integer, int[]> tabela = new HashMap<>();
        Map<Integer, String> nomes = new HashMap<>();
        Map<Integer, String> siglas = new HashMap<>();

        for (Jogo j : jogos) {
            int cId = j.getSelecaoCasaId();
            int fId = j.getSelecaoForaId();
            tabela.putIfAbsent(cId, new int[6]);
            tabela.putIfAbsent(fId, new int[6]);
            nomes.put(cId, j.getNomeCasa());
            nomes.put(fId, j.getNomeFora());
            siglas.put(cId, j.getSiglaCasa());
            siglas.put(fId, j.getSiglaFora());

            int gc = j.getGolsCasa(), gf = j.getGolsFora();
            int[] casa = tabela.get(cId);
            int[] fora = tabela.get(fId);
            casa[4] += gc; casa[5] += gf;
            fora[4] += gf; fora[5] += gc;
            if (gc > gf)      { casa[0] += 3; casa[1]++; fora[3]++; }
            else if (gc == gf){ casa[0]++; casa[2]++; fora[0]++; fora[2]++; }
            else              { fora[0] += 3; fora[1]++; casa[3]++; }
        }

        List<int[]> entries = new ArrayList<>();
        for (int id : tabela.keySet()) {
            int[] v = tabela.get(id);
            entries.add(new int[]{id, v[0], v[1], v[2], v[3], v[4], v[5], v[4]-v[5]});
        }
        Collections.sort(entries, (a, b) -> {
            if (b[1] != a[1]) return b[1] - a[1];
            if (b[7] != a[7]) return b[7] - a[7];
            return b[5] - a[5];
        });

        List<Object[]> resultado = new ArrayList<>();
        for (int i = 0; i < entries.size(); i++) {
            int[] e = entries.get(i);
            resultado.add(new Object[]{
                    siglas.get(e[0]),  // [0] sigla
                    nomes.get(e[0]),   // [1] nome
                    e[1],              // [2] pontos
                    e[2],              // [3] vitórias
                    e[3],              // [4] empates
                    e[4],              // [5] derrotas
                    e[7],              // [6] saldo
                    i + 1              // [7] posição
            });
        }
        return resultado;
    }


    private Selecao criarEstatistica(int[] e) {
        // pts | V | E | D | GP | GC | SG
        Selecao stat = new Selecao();
        stat.setNome(e[1] + "pts  " + e[2] + "V  " + e[3] + "E  " + e[4] + "D  " +
                e[5] + "x" + e[6] + "  SG:" + e[7]);
        return stat;
    }
}