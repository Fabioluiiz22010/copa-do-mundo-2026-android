package com.example.copadomundo2026.modelos;

import java.io.Serializable;

public class Jogo implements Serializable {
    private int id;
    private int faseId;
    private int grupoId;
    private int selecaoCasaId;
    private int selecaoForaId;
    private int numeroJogo;

    // Para exibição (nomes/siglas)
    private String nomeCasa;
    private String nomeForа;
    private String siglaCasa;
    private String siglaFora;
    private String nomeGrupo;
    private String nomeFase;

    // Placar da simulação atual
    private int golsCasa;
    private int golsFora;

    public Jogo() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getFaseId() { return faseId; }
    public void setFaseId(int faseId) { this.faseId = faseId; }
    public int getGrupoId() { return grupoId; }
    public void setGrupoId(int grupoId) { this.grupoId = grupoId; }
    public int getSelecaoCasaId() { return selecaoCasaId; }
    public void setSelecaoCasaId(int selecaoCasaId) { this.selecaoCasaId = selecaoCasaId; }
    public int getSelecaoForaId() { return selecaoForaId; }
    public void setSelecaoForaId(int selecaoForaId) { this.selecaoForaId = selecaoForaId; }
    public int getNumeroJogo() { return numeroJogo; }
    public void setNumeroJogo(int numeroJogo) { this.numeroJogo = numeroJogo; }
    public String getNomeCasa() { return nomeCasa; }
    public void setNomeCasa(String nomeCasa) { this.nomeCasa = nomeCasa; }
    public String getNomeFora() { return nomeForа; }
    public void setNomeFora(String nomeFora) { this.nomeForа = nomeFora; }
    public String getSiglaCasa() { return siglaCasa; }
    public void setSiglaCasa(String siglaCasa) { this.siglaCasa = siglaCasa; }
    public String getSiglaFora() { return siglaFora; }
    public void setSiglaFora(String siglaFora) { this.siglaFora = siglaFora; }
    public String getNomeGrupo() { return nomeGrupo; }
    public void setNomeGrupo(String nomeGrupo) { this.nomeGrupo = nomeGrupo; }
    public String getNomeFase() { return nomeFase; }
    public void setNomeFase(String nomeFase) { this.nomeFase = nomeFase; }
    public int getGolsCasa() { return golsCasa; }
    public void setGolsCasa(int golsCasa) { this.golsCasa = golsCasa; }
    public int getGolsFora() { return golsFora; }
    public void setGolsFora(int golsFora) { this.golsFora = golsFora; }
}