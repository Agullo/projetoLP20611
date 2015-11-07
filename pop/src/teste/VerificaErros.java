package teste;

import java.time.LocalDate;

import usuario.Normal;

public class VerificaErros {

	public VerificaErros() {

	}

	public void verificaTodosOsCampos(String nome, String senha, String email,
			LocalDate dataDeNascimento) throws Exception {
		verificaNome(nome);
		verificaSenha(senha);
		verificaEmail(email);
		verificaDataDeNascimento(dataDeNascimento);

	}

	// asdasdasasdasdasdasdasasd

	public void verificaNome(String nome) throws Exception {
		if (nome == null || nome.equals(""))
			throw new Exception("Nome invalido!");
	}

	public void verificaSenha(String senha) throws Exception {
		if (senha == null || senha.equals(""))
			throw new Exception("Senha invalida!");
	}

	public void verificaEmail(String email) throws Exception {
		if (email == null || email.equals(""))
			throw new Exception("Email invalido");
	}

	public void verificaDataDeNascimento(LocalDate dataDeNascimento)
			throws Exception {
		if (dataDeNascimento == null || dataDeNascimento.equals(""))
			throw new Exception("Data invalida!");
	}

	public void verificaMensagem(String mensagem) throws Exception {
		if (mensagem == null)
			throw new Exception("Mensagem nao pode ser null!");
		if (mensagem.length() > 400)
			throw new Exception("Mensagem nao pode ter mais de 400 caracteres");
	}

	public void verificaFotoPerfil(String fotoPerfil) throws Exception {
		if (fotoPerfil == null || fotoPerfil.equals(""))
			throw new Exception("Diretorio invalido!");
	}

	public void verificaUsuario(Normal usuario) throws Exception {
		if (usuario == null)
			throw new Exception("Usuario inexistente!");
	}

	public void verificaImagemPerfil(String imagem) throws Exception {
		if (imagem == null || imagem.equals(""))
			throw new Exception("Imagem invalida");
	}
}
