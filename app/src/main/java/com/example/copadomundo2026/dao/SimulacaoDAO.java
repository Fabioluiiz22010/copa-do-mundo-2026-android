package com.example.copadomundo2026.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.copadomundo2026.bd.DBHelper;
import com.example.copadomundo2026.modelos.Simulacao;

import java.util.ArrayList;
import java.util.List;

public class SimulacaoDAO {

    private SQLiteDatabase db;
    private DBHelper helper;

    public SimulacaoDAO(Context context) {
        helper = new DBHelper(context);
    }

    public void Abrir() { db = helper.getWritableDatabase(); }
    public void Fechar() { helper.close(); }

    public long Inserir(Simulacao s) {
        ContentValues dados = new ContentValues();
        dados.put("nome", s.getNome());
        return db.insert("simulacoes", null, dados);
    }

    public List<Simulacao> ListarTudo() {
        List<Simulacao> lista = new ArrayList<>();
        Cursor cursor = db.query("simulacoes",
                new String[]{"id", "nome", "data_criacao"},
                null, null, null, null, "data_criacao DESC");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Simulacao s = new Simulacao();
            s.setId(cursor.getInt(0));
            s.setNome(cursor.getString(1));
            s.setDataCriacao(cursor.getString(2));
            lista.add(s);
            cursor.moveToNext();
        }
        cursor.close();
        return lista;
    }

    public Simulacao BuscarPorId(int id) {
        Cursor cursor = db.query("simulacoes",
                new String[]{"id", "nome", "data_criacao"},
                "id = ?", new String[]{String.valueOf(id)},
                null, null, null);
        if (cursor.moveToFirst()) {
            Simulacao s = new Simulacao();
            s.setId(cursor.getInt(0));
            s.setNome(cursor.getString(1));
            s.setDataCriacao(cursor.getString(2));
            cursor.close();
            return s;
        }
        cursor.close();
        return null;
    }
}