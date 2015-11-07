package pop;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import teste.VerificaErros;
import usuario.Normal;
import usuario.Usuario;

public class Controller {

	private List<Usuario> usuariosRegistrados;
	private List<Usuario> usuariosMaisPopulares;
	private List<Usuario> usuariosMenosPopulares;
	private List<String> topHashtags;
	private VerificaErros verificaErros;
	private Normal usuarioAtual;

	public Controller() {
		usuariosRegistrados = new ArrayList<Usuario>();
		usuariosMenosPopulares = new ArrayList<Usuario>();
		usuariosMaisPopulares = new ArrayList<Usuario>();
		topHashtags = new ArrayList<String>();
		verificaErros = new VerificaErros();
		usuarioAtual = null;
	}

	public void registraUsuario(String nome, String senha, String email,
			LocalDate dataDeNascimento) throws Exception {

		verificaErros.verificaTodosOsCampos(nome, senha, email,
				dataDeNascimento);

		Normal tempUsuario = pesquisaUsuario(email);
		verificaErros.verificaUsuario(tempUsuario);

		if (tempUsuario.getEmail().equals(email))
			throw new Exception("O usuario ja esta cadastrado!");

		Normal usuario = new Normal(email, nome, senha, dataDeNascimento);
		addUsuario(usuario);

	}

	public void registraUsuario(String nome, String senha, String email,
			LocalDate dataDeNascimento, String imagem) throws Exception {

		verificaErros.verificaTodosOsCampos(nome, senha, email,
				dataDeNascimento);

		Normal tempUsuario = pesquisaUsuario(email);
		verificaErros.verificaUsuario(tempUsuario);

		if (tempUsuario.getEmail().equals(email))
			throw new Exception("O usuario ja esta cadastrado!");

		Normal usuario = new Normal(email, nome, senha, dataDeNascimento,
				imagem);
		addUsuario(usuario);

	}

	public void atualizaRanking() {

	}

	public void atualizaPerfil(String campo, String valor) throws Exception {
		verificaErros.verificaUsuario(getUsuarioAtual());

		if (campo == null || campo.equals(""))
			throw new Exception("Campo invalido!");

		if (valor == null || valor.equals(""))
			throw new Exception("Valor invalido!");

		if (campo.equalsIgnoreCase("email"))
			getUsuarioAtual().setEmail(valor);

		else if (campo.equalsIgnoreCase("nome"))
			getUsuarioAtual().setNome(valor);

		else
			getUsuarioAtual().setFotoPerfil(valor);

	}

	public String getInfoUsuario(String campo, String valor) throws Exception {
		verificaErros.verificaUsuario(getUsuarioAtual());

		if (campo == null || campo.equals(""))
			throw new Exception("Campo invalido!");

		if (valor == null || valor.equals(""))
			throw new Exception("Valor invalido!");

		if (campo.equalsIgnoreCase("email"))
			return getUsuarioAtual().getEmail();

		if (campo.equalsIgnoreCase("senha"))
			throw new Exception("a senha do usuarix eh protegida.");

		else if (campo.equalsIgnoreCase("nome"))
			return getUsuarioAtual().getNome();

		else
			return getUsuarioAtual().getFotoPerfil();

	}

	public void atualizaDataDeNascimento(LocalDate data) throws Exception {
		verificaErros.verificaDataDeNascimento(data);
		getUsuarioAtual().setDataDeNascimento(data);
	}

	public void atualizaSenha(String senhaAntiga, String novaSenha)
			throws Exception {

		verificaErros.verificaUsuario(getUsuarioAtual());
		verificaErros.verificaSenha(senhaAntiga);
		verificaErros.verificaSenha(novaSenha);

		usuarioAtual.setSenha(senhaAntiga, novaSenha);

	}

	public void setUsuarioAtual(Normal usuario) throws Exception {
		verificaErros.verificaUsuario(usuario);
		usuarioAtual = usuario;
	}

	public Normal getUsuarioAtual() {
		return usuarioAtual;
	}

	public void deletaUsuario(String email) throws Exception {
		verificaErros.verificaEmail(email);

		Normal tempUsuario = pesquisaUsuario(email);

		verificaErros.verificaUsuario(tempUsuario);

		usuariosRegistrados.remove(tempUsuario);

	}

	public void criaPost(String mensagem, LocalDate data, LocalTime hora)
			throws Exception {
		verificaErros.verificaMensagem(mensagem);
		if (data == null || data.equals(""))
			throw new Exception("Data invalida!");

		Post post = new Post(mensagem, data, hora);
		getUsuarioAtual().adicionaPost(post);

	}

	public String getPost(int index) throws Exception {
		return getUsuarioAtual().getPost(index).toString();
	}

	public String getPost(String atributo, int index) throws Exception {
		return getUsuarioAtual().getPostPorAtributo(atributo, index);

	}

	public List<Normal> getUsuarios() {
		return usuariosRegistrados;
	}

	public void addUsuario(Normal usuario) throws Exception {
		verificaErros.verificaUsuario(usuario);
		usuariosRegistrados.add(usuario);
	}

	public void login(String email, String senha) throws Exception {
		verificaErros.verificaEmail(email);
		verificaErros.verificaSenha(senha);

		Normal usuario = pesquisaUsuario(email);
		verificaErros.verificaUsuario(usuario);

		if (!(usuario.getSenha().equals(senha))) {
			throw new Exception("Senha incorreta!");
		}

		else if (usuario.isLogado()) {
			throw new Exception("Usuario ja esta logado!");
		}

		else {
			usuario.setLogado(true);
			setUsuarioAtual(usuario);
		}
	}

	public String infoUsuario(String email) throws Exception {

		verificaErros.verificaEmail(email);
		Normal usuario = pesquisaUsuario(email);

		verificaErros.verificaUsuario(usuario);
		return usuario.toString();
	}

	public void logout(String email) throws Exception {

		verificaErros.verificaEmail(email);
		Normal usuario = pesquisaUsuario(email);

		verificaErros.verificaUsuario(usuario);

		usuario.setLogado(false);
		setUsuarioAtual(null);
	}

	public Normal pesquisaUsuario(String email) throws Exception {
		verificaErros.verificaEmail(email);

		for (int i = 0; i < usuariosRegistrados.size(); i++) {
			if (usuariosRegistrados.get(i).getEmail().equals(email))
				return usuariosRegistrados.get(i);
		}
		return null;

	}
}
