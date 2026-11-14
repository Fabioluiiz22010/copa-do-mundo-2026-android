package com.example.copadomundo2026.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.copadomundo2026.bd.DBHelper;
import com.example.copadomundo2026.modelos.Selecao;

import java.util.ArrayList;
import java.util.List;

public class SelecaoDAO {

    private SQLiteDatabase db;
    private DBHelper helper;

    public SelecaoDAO(Context context) {
        helper = new DBHelper(context);
    }

    public void Abrir() { db = helper.getReadableDatabase(); }
    public void Fechar() { helper.close(); }

    public List<Selecao> ListarPorGrupo(int grupoId) {
        List<Selecao> lista = new ArrayList<>();
        Cursor cursor = db.query("selecoes",
                new String[]{"id", "nome", "sigla", "grupo_id"},
                "grupo_id = ?", new String[]{String.valueOf(grupoId)},
                null, null, "nome");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Selecao s = new Selecao(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3));
            lista.add(s);
            cursor.moveToNext();
        }
        cursor.close();
        return lista;
    }
}