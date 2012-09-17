package br.com.midas.temperature.model;

import javax.validation.constraints.NotNull;

public class Simulator {
    
    @NotNull(message="{teste.mario}")
    private Integer temperaturaAtual;
    private Integer tempo;
    private Integer temperaturaDesejada;

    public Simulator() {
    }

    public Simulator(Integer temperaturaAtual, Integer tempo, Integer temperaturaDesejada) {
        this.temperaturaAtual = temperaturaAtual;
        this.tempo = tempo;
        this.temperaturaDesejada = temperaturaDesejada;
    }

    public Integer getTemperaturaAtual() {
        return temperaturaAtual;
    }

    public void setTemperaturaAtual(Integer temperaturaAtual) {
        this.temperaturaAtual = temperaturaAtual;
    }

    public Integer getTempo() {
        return tempo;
    }

    public void setTempo(Integer tempo) {
        this.tempo = tempo;
    }

    public Integer getTemperaturaDesejada() {
        return temperaturaDesejada;
    }

    public void setTemperaturaDesejada(Integer temperaturaDesejada) {
        this.temperaturaDesejada = temperaturaDesejada;
    }
    
}
