package spring.treinamento.pipoflix;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import spring.treinamento.pipoflix.model.DadosSeries;
import spring.treinamento.pipoflix.service.ConsumoAPI;
import spring.treinamento.pipoflix.service.ConverteDados;

@SpringBootApplication
public class PipoflixApplication implements CommandLineRunner {
	//Aqui estamos implementando a linha de comandos

	public static void main(String[] args) {
		SpringApplication.run(PipoflixApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumoApi = new ConsumoAPI();
		var json = consumoApi.obterDados("https://www.omdbapi.com/?t=Gilmore+Girls&apikey=a13c3dfc");
		System.out.println(json);

		ConverteDados conversor = new ConverteDados();
		DadosSeries dados = conversor.obterDados(json, DadosSeries.class);

		System.out.println(dados);
	}
}
