package spring.treinamento.pipoflix;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import spring.treinamento.pipoflix.principal.Principal;

@SpringBootApplication
public class PipoflixApplication implements CommandLineRunner {
	//Aqui estamos implementando a linha de comandos

	public static void main(String[] args) {
		SpringApplication.run(PipoflixApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Principal principal = new Principal();
		principal.exibeMenu();
		
	}
}






























