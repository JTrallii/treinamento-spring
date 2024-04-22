package spring.treinamento.pipoflix.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true) //JsonIgnoreProperties serve para ignorar o resto dos parametros que não vamos mapear na API
public record DadosSeries(@JsonAlias("Title") String titulo,
                          @JsonAlias("totalSeasons") int totalTemporadas,
                          @JsonAlias("imdbRating") String avaliacao,
                          @JsonProperty("imdbVotes") String votos) {
    //A diferença é que o JsonProperty pode ser utilizado tanto no processo de serialização quanto de desserialização.
    //
    //Isso significa que, se no fim do código escrevermo o trecho @JsonProperty("imdbVotes") String votos, ao gerar um json com dados sequenciais será incluído o nome "imdb votes". Então, tenta ler imdb votes e escrever também.
    //
    //Já o JsonAlias é utilizado apenas para ler o json. Sendo assim, lerá o title, porém quando for escrever utilizará o nome original do atributo, que é título.
    //
    //Outro recurso interessante é que se buscássemos por outras APIs, poderíamos procurar por um array de nomes.
    //
    //Suponhamos que em vez de imdb utilizamos lmdb, um nome fictício, que ao invés de title fosse título.
    //
    //Nesse caso, na primeira linha, poderíamos passar como @JsonAlias("Title", "Titulo") String titulo com palavras que devem ser procuradas para ser possível desserializar tudo isso para a classe.


}
