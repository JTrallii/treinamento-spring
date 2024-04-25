package spring.treinamento.pipoflix.model;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;

public class Episodio {
    private int temporada;
    private String titulo;
    private int numeroEpisodio;
    private double avaliacao;
    private LocalDate dataLancamento;

    public Episodio(int numeroTemporada, DadosEpisodios listDadosEpisodios) {
        this.temporada = numeroTemporada;
        this.titulo = listDadosEpisodios.titulo();
        this.numeroEpisodio = listDadosEpisodios.numero();

        try {
            this.avaliacao = Double.parseDouble(listDadosEpisodios.avaliacao());
        } catch (NumberFormatException e) {
            this.avaliacao = 0.0;
        }

        try {
            this.dataLancamento = LocalDate.parse(listDadosEpisodios.dataLancamento());
        } catch (DateTimeException e) {
            this.dataLancamento = null;
        }

    }


    public int getTemporada() {
        return temporada;
    }

    public void setTemporada(int temporada) {
        this.temporada = temporada;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getNumero() {
        return numeroEpisodio;
    }

    public void setNumero(int numero) {
        this.numeroEpisodio = numero;
    }

    public double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    @Override
    public String toString() {
        return "Temporada = " + temporada +
                ", Titulo = " + titulo + '\'' +
                ", Numero do episodio = " + numeroEpisodio +
                ", Avaliação = " + avaliacao +
                ", Datade lançamento = " + dataLancamento;
    }
}
