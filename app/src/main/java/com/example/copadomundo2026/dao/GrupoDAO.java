package com.example.copadomundo2026.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.copadomundo2026.bd.DBHelper;
import com.example.copadomundo2026.modelos.Grupo;

import java.util.ArrayList;
import java.util.List;

public class GrupoDAO {

    private SQLiteDatabase db;
    private DBHelper helper;

    public GrupoDAO(Context context) {
        helper = new DBHelper(context);
    }

    public void Abrir() { db = helper.getReadableDatabase(); }
    public void Fechar() { helper.close(); }

    public List<Grupo> ListarTudo() {
        List<Grupo> lista = new ArrayList<>();
        Cursor cursor = db.query("grupos",
                new String[]{"id", "nome"},
                null, null, null, null, "nome");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Grupo g = new Grupo(cursor.getInt(0), cursor.getString(1));
            lista.add(g);
            cursor.moveToNext();
        }
        cursor.close();
        return lista;
    }
}