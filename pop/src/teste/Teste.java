package teste;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;

import pop.Post;
import usuario.Normal;
import usuario.Usuario;

public class Teste {

	public static void main(String[] args) throws Exception {

		Usuario thiago = new Normal("Thiago@gmail", "thiago", "senha",
				LocalDate.now());

		Post algo = new Post(
				"mensagem mensagem mensagem. <audio>musica.mp3</audio> <imagem>imagem.jpeg</imagem> <imagem>imagem2testando.jpg</imagem> #lol #aloka",
				LocalDate.now(), LocalTime.now());

		thiago.adicionaPost(algo);
		System.out.println(thiago.getPost(0));
		System.out.println(thiago.getPostPorAtributo("imagem", 0));
		System.out.println(algo.getHashtag());
		System.out.println(algo.getMusica());
		System.out.println(algo.getImagem());

		System.out.println(thiago.toString());

		LocalDate data = LocalDate.now();

		System.out.println(data);
	}
}
