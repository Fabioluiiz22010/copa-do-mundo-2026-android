package com.example.copadomundo2026.modelos;

import java.io.Serializable;

public class Selecao implements Serializable {
    private int id;
    private String nome;
    private String sigla;
    private int grupoId;

    public Selecao() {}

    public Selecao(int id, String nome, String sigla, int grupoId) {
        this.id = id;
        this.nome = nome;
        this.sigla = sigla;
        this.grupoId = grupoId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getSigla() { return sigla; }
    public void setSigla(String sigla) { this.sigla = sigla; }
    public int getGrupoId() { return grupoId; }
    public void setGrupoId(int grupoId) { this.grupoId = grupoId; }
}