package com.example.copadomundo2026.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.copadomundo2026.bd.DBHelper;
import com.example.copadomundo2026.modelos.Jogo;

import java.util.ArrayList;
import java.util.List;

public class JogoDAO {

    private SQLiteDatabase db;
    private DBHelper helper;

    public JogoDAO(Context context) {
        helper = new DBHelper(context);
    }

    public void Abrir() { db = helper.getWritableDatabase(); }
    public void Fechar() { helper.close(); }

    // Retorna jogos da fase de grupos com nomes das seleções e placar da simulação
    public List<Jogo> ListarPorGrupo(int grupoId, int simulacaoId) {
        List<Jogo> lista = new ArrayList<>();
        String sql =
                "SELECT j.id, j.numero_jogo, " +
                        "sc.id, sc.nome, sc.sigla, " +
                        "sf.id, sf.nome, sf.sigla, " +
                        "g.nome, " +
                        "IFNULL(sj.gols_casa, 0), IFNULL(sj.gols_fora, 0) " +
                        "FROM jogos j " +
                        "JOIN selecoes sc ON sc.id = j.selecao_casa_id " +
                        "JOIN selecoes sf ON sf.id = j.selecao_fora_id " +
                        "JOIN grupos g ON g.id = j.grupo_id " +
                        "LEFT JOIN simulacao_jogos sj ON sj.jogo_id = j.id AND sj.simulacao_id = " + simulacaoId +
                        " WHERE j.grupo_id = " + grupoId +
                        " ORDER BY j.id";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Jogo jogo = new Jogo();
            jogo.setId(cursor.getInt(0));
            jogo.setNumeroJogo(cursor.getInt(1));
            jogo.setSelecaoCasaId(cursor.getInt(2));
            jogo.setNomeCasa(cursor.getString(3));
            jogo.setSiglaCasa(cursor.getString(4));
            jogo.setSelecaoForaId(cursor.getInt(5));
            jogo.setNomeFora(cursor.getString(6));
            jogo.setSiglaFora(cursor.getString(7));
            jogo.setNomeGrupo(cursor.getString(8));
            jogo.setGolsCasa(cursor.getInt(9));
            jogo.setGolsFora(cursor.getInt(10));
            lista.add(jogo);
            cursor.moveToNext();
        }
        cursor.close();
        return lista;
    }

    // Retorna jogos do mata-mata por fase
    public List<Jogo> ListarPorFase(int faseId, int simulacaoId) {
        List<Jogo> lista = new ArrayList<>();
        String sql =
                "SELECT j.id, j.numero_jogo, " +
                        "IFNULL(sc.nome, 'A definir'), IFNULL(sc.sigla, '???'), " +
                        "IFNULL(sf.nome, 'A definir'), IFNULL(sf.sigla, '???'), " +
                        "IFNULL(sj.gols_casa, 0), IFNULL(sj.gols_fora, 0) " +
                        "FROM jogos j " +
                        "LEFT JOIN selecoes sc ON sc.id = j.selecao_casa_id " +
                        "LEFT JOIN selecoes sf ON sf.id = j.selecao_fora_id " +
                        "LEFT JOIN simulacao_jogos sj ON sj.jogo_id = j.id AND sj.simulacao_id = " + simulacaoId +
                        " WHERE j.fase_id = " + faseId +
                        " ORDER BY j.numero_jogo";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Jogo jogo = new Jogo();
            jogo.setId(cursor.getInt(0));
            jogo.setNumeroJogo(cursor.getInt(1));
            jogo.setNomeCasa(cursor.getString(2));
            jogo.setSiglaCasa(cursor.getString(3));
            jogo.setNomeFora(cursor.getString(4));
            jogo.setSiglaFora(cursor.getString(5));
            jogo.setGolsCasa(cursor.getInt(6));
            jogo.setGolsFora(cursor.getInt(7));
            lista.add(jogo);
            cursor.moveToNext();
        }
        cursor.close();
        return lista;
    }

    // Salva ou atualiza placar de um jogo na simulação
    public void SalvarPlacar(int simulacaoId, int jogoId, int golsCasa, int golsFora) {
        ContentValues dados = new ContentValues();
        dados.put("simulacao_id", simulacaoId);
        dados.put("jogo_id", jogoId);
        dados.put("gols_casa", golsCasa);
        dados.put("gols_fora", golsFora);
        int rows = db.update("simulacao_jogos", dados,
                "simulacao_id = ? AND jogo_id = ?",
                new String[]{String.valueOf(simulacaoId), String.valueOf(jogoId)});
        if (rows == 0) {
            db.insert("simulacao_jogos", null, dados);
        }
    }
}