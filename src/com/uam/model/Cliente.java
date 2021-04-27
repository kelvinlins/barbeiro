package com.uam.model;

public class Cliente {

    private Integer id;
    private String nome;
    private Boolean cabeloCortado;

    public Cliente(Integer id, String nome, Boolean cabeloCortado) {
        this.id = id;
        this.nome = nome;
        this.cabeloCortado = cabeloCortado;
    }

    public Cliente() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getCabeloCortado() {
        return cabeloCortado;
    }

    public void setCabeloCortado(Boolean cabeloCortado) {
        this.cabeloCortado = cabeloCortado;
    }
}
