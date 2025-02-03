package com.example.ecossistema;

public class Planta {
    private String nome;
    private String dataPlantio;
    private String tipo;
    private int imagem; // Alterado para int

    public Planta(String nome, String dataPlantio, String tipo, int imagem) {
        this.nome = nome;
        this.dataPlantio = dataPlantio;
        this.tipo = tipo;
        this.imagem = imagem;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataPlantio() {
        return dataPlantio;
    }

    public void setDataPlantio(String dataPlantio) {
        this.dataPlantio = dataPlantio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }
}
