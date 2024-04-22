package spring.treinamento.pipoflix.principal;

import spring.treinamento.pipoflix.model.DadosEpisodios;
import spring.treinamento.pipoflix.model.DadosSeries;
import spring.treinamento.pipoflix.model.DadosTemporada;
import spring.treinamento.pipoflix.service.ConsumoAPI;
import spring.treinamento.pipoflix.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    Scanner leitura = new Scanner(System.in);
    //Além disso, é uma boa prática declarar as constantes como static caso elas pertençam a uma
    // classe e sejam compartilhadas por vários objetos. Dessa forma, as constantes podem ser
    // acessadas diretamente através do nome da classe, sem a necessidade de instanciar um objeto.
    // public static final int ANO_ATUAL = 2022;
    final String ENDERECO = "https://www.omdbapi.com/?t=";
    final String API_KEY = "&apikey=a13c3dfc";
    ConsumoAPI consumoApi = new ConsumoAPI();
    ConverteDados converteDados = new ConverteDados();

    public void exibeMenu() {
        System.out.println("Digite o nome da Série para buscar: ");
        var nomeSerie = leitura.nextLine();

        var json = consumoApi.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);

        DadosSeries dadosSeries = converteDados.obterDados(json, DadosSeries.class);
        System.out.println(dadosSeries);

        List<DadosTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i < dadosSeries.totalTemporadas() ; i++) {
            json = consumoApi.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&season=" + i + API_KEY);
            DadosTemporada dadosTemporada = converteDados.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }


        temporadas.forEach(System.out::println);

        for (int i = 0; i < 5; i++) {

            System.out.println(dadosSeries.totalTemporadas());

            List<DadosEpisodios> episodiosTemporada = temporadas.get(i).episodios();

            System.out.println("Episodios---- " + episodiosTemporada);

            for (int j = 0; j < episodiosTemporada.size(); j++) {

                System.out.println(episodiosTemporada.get(j).titulo());

            }
        }

    }
}






























