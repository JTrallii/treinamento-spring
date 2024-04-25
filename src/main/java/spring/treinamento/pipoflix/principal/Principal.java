package spring.treinamento.pipoflix.principal;

import spring.treinamento.pipoflix.model.DadosEpisodios;
import spring.treinamento.pipoflix.model.DadosSeries;
import spring.treinamento.pipoflix.model.DadosTemporada;
import spring.treinamento.pipoflix.model.Episodio;
import spring.treinamento.pipoflix.service.ConsumoAPI;
import spring.treinamento.pipoflix.service.ConverteDados;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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
        System.out.println("Série: " + dadosSeries);

        List<DadosTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i <= dadosSeries.totalTemporadas() ; i++) {
            json = consumoApi.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&season=" + i + API_KEY);
            DadosTemporada dadosTemporada = converteDados.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }


        temporadas.forEach(System.out::println);

        for (int i = 0; i < dadosSeries.totalTemporadas(); i++) {
            List<DadosEpisodios> episodiosTemporada = temporadas.get(i).episodios();
            for (int j = 0; j < episodiosTemporada.size(); j++) {
                System.out.println(episodiosTemporada.get(j).titulo());
            }
        }

        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));

        List<DadosEpisodios> dadosEpisodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toList());

        dadosEpisodios.stream()
                .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(DadosEpisodios::avaliacao).reversed())
                .limit(5)
                .forEach(System.out::println);

        List<Episodio> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(d -> new Episodio(t.temporada(), d)))
                .collect(Collectors.toList());

        episodios.forEach(System.out::println);

        System.out.println("A partir de que ano você deseja ver os episódios ?");
        var ano = leitura.nextInt();
        leitura.nextLine();

        LocalDate dataBusca = LocalDate.of(ano, 1, 1);

        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        episodios.stream()
                .filter(e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(dataBusca))
                .forEach(e -> System.out.println(
                        "Temporada: " + e.getTemporada() +
                                " Episódio: " + e.getTitulo() +
                                " Data de Lançamento: " + e.getDataLancamento().format(formatador)
                ));


    }
}






























