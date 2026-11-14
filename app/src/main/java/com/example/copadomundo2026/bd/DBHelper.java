package com.example.copadomundo2026.bd;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    private static final String NOME_BD = "CopaMundo2026.db";
    private static final int VERSAO_BD = 2;

    public DBHelper(Context context) {
        super(context, NOME_BD, null, VERSAO_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE grupos (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome CHAR(1) NOT NULL UNIQUE)");

        db.execSQL("CREATE TABLE selecoes (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome VARCHAR(100) NOT NULL, " +
                "sigla CHAR(3) NOT NULL UNIQUE, " +
                "grupo_id INTEGER, " +
                "FOREIGN KEY (grupo_id) REFERENCES grupos(id))");

        db.execSQL("CREATE TABLE fases (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome VARCHAR(50) NOT NULL, " +
                "ordem INTEGER NOT NULL)");

        db.execSQL("CREATE TABLE jogos (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "fase_id INTEGER NOT NULL, " +
                "grupo_id INTEGER, " +
                "selecao_casa_id INTEGER, " +
                "selecao_fora_id INTEGER, " +
                "numero_jogo INTEGER, " +
                "proximo_jogo_id INTEGER, " +
                "lado_proximo CHAR(1), " +
                "FOREIGN KEY (fase_id) REFERENCES fases(id), " +
                "FOREIGN KEY (grupo_id) REFERENCES grupos(id), " +
                "FOREIGN KEY (selecao_casa_id) REFERENCES selecoes(id), " +
                "FOREIGN KEY (selecao_fora_id) REFERENCES selecoes(id))");

        db.execSQL("CREATE TABLE simulacoes (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome VARCHAR(100), " +
                "data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP)");

        db.execSQL("CREATE TABLE simulacao_jogos (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "simulacao_id INTEGER NOT NULL, " +
                "jogo_id INTEGER NOT NULL, " +
                "gols_casa INTEGER DEFAULT 0, " +
                "gols_fora INTEGER DEFAULT 0, " +
                "FOREIGN KEY (simulacao_id) REFERENCES simulacoes(id), " +
                "FOREIGN KEY (jogo_id) REFERENCES jogos(id), " +
                "UNIQUE(simulacao_id, jogo_id))");

        popularDados(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("DBHelper", "Atualizando BD de v" + oldVersion + " para v" + newVersion);
        db.execSQL("DROP TABLE IF EXISTS simulacao_jogos");
        db.execSQL("DROP TABLE IF EXISTS simulacoes");
        db.execSQL("DROP TABLE IF EXISTS jogos");
        db.execSQL("DROP TABLE IF EXISTS fases");
        db.execSQL("DROP TABLE IF EXISTS selecoes");
        db.execSQL("DROP TABLE IF EXISTS grupos");
        onCreate(db);
    }

    private void popularDados(SQLiteDatabase db) {

        // Fases
        db.execSQL("INSERT INTO fases (nome, ordem) VALUES ('GRUPOS', 1)");
        db.execSQL("INSERT INTO fases (nome, ordem) VALUES ('DECIMOS', 2)");
        db.execSQL("INSERT INTO fases (nome, ordem) VALUES ('OITAVAS', 3)");
        db.execSQL("INSERT INTO fases (nome, ordem) VALUES ('QUARTAS', 4)");
        db.execSQL("INSERT INTO fases (nome, ordem) VALUES ('SEMI', 5)");
        db.execSQL("INSERT INTO fases (nome, ordem) VALUES ('FINAL', 6)");


        String[] letras = {"A","B","C","D","E","F","G","H","I","J","K","L"};
        for (String l : letras) {
            db.execSQL("INSERT INTO grupos (nome) VALUES ('" + l + "')");
        }

        // ── GRUPO A
        inserirSelecao(db, "México",            "MEX", 1);
        inserirSelecao(db, "África do Sul",     "AFE", 1);
        inserirSelecao(db, "Coreia do Sul",     "COR", 1);
        inserirSelecao(db, "República Tcheca",  "TCH", 1);

        // ── GRUPO B
        inserirSelecao(db, "Canadá",            "CAN", 2);
        inserirSelecao(db, "Bósnia e Herz.",    "BOS", 2);
        inserirSelecao(db, "Catar",             "CAT", 2);
        inserirSelecao(db, "Suíça",             "SUI", 2);

        // ── GRUPO C
        inserirSelecao(db, "Brasil",            "BRA", 3);
        inserirSelecao(db, "Marrocos",          "MAR", 3);
        inserirSelecao(db, "Haiti",             "HAI", 3);
        inserirSelecao(db, "Escócia",           "ESC", 3);


        inserirSelecao(db, "Estados Unidos",    "EUA", 4);
        inserirSelecao(db, "Paraguai",          "PAR", 4);
        inserirSelecao(db, "Austrália",         "AUS", 4);
        inserirSelecao(db, "Turquia",           "TUR", 4);


        inserirSelecao(db, "Alemanha",          "ALE", 5);
        inserirSelecao(db, "Curaçao",           "CUR", 5);
        inserirSelecao(db, "Costa do Marfim",   "CDM", 5);
        inserirSelecao(db, "Equador",           "EQU", 5);


        inserirSelecao(db, "Holanda",           "HOL", 6);
        inserirSelecao(db, "Suécia",            "SUE", 6);
        inserirSelecao(db, "Tunísia",           "TUN", 6);
        inserirSelecao(db, "Japão",             "JAP", 6);


        inserirSelecao(db, "Bélgica",           "BEL", 7);
        inserirSelecao(db, "Egito",             "EGI", 7);
        inserirSelecao(db, "Irã",               "IRA", 7);
        inserirSelecao(db, "Nova Zelândia",     "NZE", 7);


        inserirSelecao(db, "Espanha",           "ESP", 8);
        inserirSelecao(db, "Cabo Verde",        "CAB", 8);
        inserirSelecao(db, "Arábia Saudita",    "ARA", 8);
        inserirSelecao(db, "Uruguai",           "URU", 8);


        inserirSelecao(db, "França",            "FRA", 9);
        inserirSelecao(db, "Iraque",            "IRQ", 9);
        inserirSelecao(db, "Senegal",           "SEN", 9);
        inserirSelecao(db, "Noruega",           "NOR", 9);


        inserirSelecao(db, "Argentina",         "ARG", 10);
        inserirSelecao(db, "Argélia",           "ALG", 10);
        inserirSelecao(db, "Áustria",           "AUT", 10);
        inserirSelecao(db, "Jordânia",          "JOR", 10);


        inserirSelecao(db, "Portugal",          "POR", 11);
        inserirSelecao(db, "RD Congo",          "RDC", 11);
        inserirSelecao(db, "Uzbequistão",       "UZB", 11);
        inserirSelecao(db, "Colômbia",          "COL", 11);


        inserirSelecao(db, "Inglaterra",        "ING", 12);
        inserirSelecao(db, "Croácia",           "CRO", 12);
        inserirSelecao(db, "Gana",              "GAN", 12);
        inserirSelecao(db, "Panamá",            "PAN", 12);

        // montagem dos jogos da fase de grupos
        for (int g = 0; g < 12; g++) {
            int base = g * 4 + 1;
            int[] ids = {base, base+1, base+2, base+3};
            inserirJogoGrupo(db, g+1, ids[0], ids[1]);
            inserirJogoGrupo(db, g+1, ids[2], ids[3]);
            inserirJogoGrupo(db, g+1, ids[0], ids[2]);
            inserirJogoGrupo(db, g+1, ids[1], ids[3]);
            inserirJogoGrupo(db, g+1, ids[0], ids[3]);
            inserirJogoGrupo(db, g+1, ids[1], ids[2]);
        }

        // 16avos
        for (int i = 1; i <= 16; i++)
            db.execSQL("INSERT INTO jogos (fase_id, numero_jogo) VALUES (2, " + i + ")");

        // oitavas
        for (int i = 1; i <= 8; i++)
            db.execSQL("INSERT INTO jogos (fase_id, numero_jogo) VALUES (3, " + i + ")");

        // quartas
        for (int i = 1; i <= 4; i++)
            db.execSQL("INSERT INTO jogos (fase_id, numero_jogo) VALUES (4, " + i + ")");

        // semi
        for (int i = 1; i <= 2; i++)
            db.execSQL("INSERT INTO jogos (fase_id, numero_jogo) VALUES (5, " + i + ")");

        // final
        db.execSQL("INSERT INTO jogos (fase_id, numero_jogo) VALUES (6, 1)");
    }

    private void inserirSelecao(SQLiteDatabase db, String nome, String sigla, int grupoId) {
        db.execSQL("INSERT INTO selecoes (nome, sigla, grupo_id) VALUES ('" +
                nome + "', '" + sigla + "', " + grupoId + ")");
    }

    private void inserirJogoGrupo(SQLiteDatabase db, int grupoId, int casaId, int foraId) {
        db.execSQL("INSERT INTO jogos (fase_id, grupo_id, selecao_casa_id, selecao_fora_id) " +
                "VALUES (1, " + grupoId + ", " + casaId + ", " + foraId + ")");
    }
}