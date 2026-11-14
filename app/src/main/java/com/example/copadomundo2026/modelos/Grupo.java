package com.example.copadomundo2026.modelos;

import java.io.Serializable;

public class Grupo implements Serializable {
    private int id;
    private String nome;

    public Grupo() {}

    public Grupo(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
}