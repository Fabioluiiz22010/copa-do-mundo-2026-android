package com.example.copadomundo2026.modelos;

import java.io.Serializable;

public class Simulacao implements Serializable {
    private int id;
    private String nome;
    private String dataCriacao;

    public Simulacao() {}

    public Simulacao(String nome) {
        this.nome = nome;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(String dataCriacao) { this.dataCriacao = dataCriacao; }
}