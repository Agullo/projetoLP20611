package pop;

import java.time.LocalDate;
import java.time.LocalTime;

public class Facade {

	private Controller controller;

	public Facade() {
		controller = new Controller();

	}

	public void registraUsuario(String email, String nome, String senha,
			LocalDate dataDeNascimento) throws Exception {

		controller.registraUsuario(nome, senha, email, dataDeNascimento);
	}

	public void login(String email, String senha) throws Exception {

		controller.login(email, senha);
	}

	public void logout(String email) throws Exception {
		controller.logout(email);
	}

	public void atualizaPerfil(String campo, String valor) throws Exception {
		controller.atualizaPerfil(campo, valor);
	}

	public void getInfoUsuario(String email) throws Exception {

		controller.infoUsuario(email);
	}

	public void atualizaSenha(String senhaAntiga, String novaSenha)
			throws Exception {
		controller.atualizaSenha(senhaAntiga, novaSenha);
	}

	public void criaPost(String mensagem, LocalDate dataDePublicacao,
			LocalTime horaDePublicacao) throws Exception {
		controller.criaPost(mensagem, dataDePublicacao, horaDePublicacao);
	}

	public void getPost(int index) throws Exception {
		controller.getPost(index);
	}

	public void getPost(String atributo, int index) throws Exception {
		controller.getPost(atributo, index);
	}

}
