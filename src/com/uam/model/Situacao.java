package com.uam.model;

public class Situacao {

    private Integer tempo;

    private String tipoTempo;

    private Integer idCorte;

    public Situacao() {
    }

    public Situacao(Integer tempo, String tipoTempo, Integer idCorte) {
        this.tempo = tempo;
        this.tipoTempo = tipoTempo;
        this.idCorte = idCorte;
    }

    public Integer getTempo() {
        return tempo;
    }

    public void setTempo(Integer tempo) {
        this.tempo = tempo;
    }

    public String getTipoTempo() {
        return tipoTempo;
    }

    public void setTipoTempo(String tipoTempo) {
        this.tipoTempo = tipoTempo;
    }

    public Integer getIdCorte() {
        return idCorte;
    }

    public void setIdCorte(Integer idCorte) {
        this.idCorte = idCorte;
    }
}
