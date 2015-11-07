package usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import pop.Post;
import teste.VerificaErros;

public class IconePop implements Usuario, Comparable<Usuario> {

	private String email;
	private String senha;
	private String nome;
	private LocalDate dataDeNascimento;
	private String fotoPerfil;
	private int popularidade;
	private List<Post> mural;
	private List<Normal> amigos;
	private boolean logado;
	private VerificaErros verificaErros;

	public IconePop(String email, String nome, String senha,
			LocalDate dataDeNascimento) throws Exception {
		verificaErros = new VerificaErros();

		verificaErros.verificaTodosOsCampos(nome, senha, email,
				dataDeNascimento);

		this.email = email;
		this.senha = senha;
		this.nome = nome;
		this.dataDeNascimento = dataDeNascimento;
		fotoPerfil = "diretorioFotoPadrao";
		mural = new ArrayList<Post>();
		amigos = new ArrayList<Normal>();
		logado = false;
		popularidade = 0;
	}

	public IconePop(String email, String nome, String senha,
			LocalDate dataDeNascimento, String imagem) throws Exception {
		verificaErros = new VerificaErros();

		verificaErros.verificaTodosOsCampos(nome, senha, email,
				dataDeNascimento);

		this.email = email;
		this.senha = senha;
		this.nome = nome;
		this.dataDeNascimento = dataDeNascimento;
		fotoPerfil = imagem;
		mural = new ArrayList<Post>();
		logado = false;
		popularidade = 0;
	}

	public void adicionaPost(Post post) {
		mural.add(post);
	}

	public void removePost(String horaDePublicacao) {
		for (Post post : mural) {
			if (post.getHoraDePublicacao().equals(horaDePublicacao))
				mural.remove(post);
		}
	}

	public String getPostPorAtributo(String atributo, int index)
			throws Exception {
		Post tempPost = this.getPost(index);

		if (atributo.equalsIgnoreCase("hashtag"))
			return tempPost.getHashtag().toString();

		else if (atributo.equalsIgnoreCase("mensagem"))
			return tempPost.getMensagem();

		else if (atributo.equalsIgnoreCase("data"))
			return tempPost.getHoraDePublicacao().toString();

		else if (atributo.equalsIgnoreCase("musica"))
			return tempPost.getMusica();

		else if (atributo.equalsIgnoreCase("imagem"))
			return tempPost.getHoraDePublicacao().toString();

		else
			return null;
	}

	public Post getPost(int index) throws Exception {
		if (mural.size() < index)
			throw new Exception("Numero maior do que a quantidade de posts");

		return mural.get(index);
	}

	public Post getPost(String horaDePublicacao) {
		for (int i = 0; i < mural.size(); i++) {
			if (mural.get(i).getHoraDePublicacao().equals(horaDePublicacao))
				return mural.get(i);

		}

		return null;
	}

	public void setFotoPerfil(String fotoPerfil) throws Exception {
		verificaErros.verificaFotoPerfil(fotoPerfil);
		this.fotoPerfil = fotoPerfil;
	}

	public String getFotoPerfil() {
		return fotoPerfil;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws Exception {
		verificaErros.verificaEmail(email);
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senhaAntiga, String novaSenha) throws Exception {
		if (!(this.senha.equals(senhaAntiga)))
			throw new Exception("Senha antiga incorreta!");

		senha = novaSenha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws Exception {
		verificaErros.verificaNome(nome);
		this.nome = nome;
	}

	public LocalDate getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(LocalDate dataDeNascimento)
			throws Exception {
		verificaErros.verificaDataDeNascimento(dataDeNascimento);
		this.dataDeNascimento = dataDeNascimento;
	}

	public int getPopularidade() {
		return popularidade;
	}

	public void setPopularidade(int popularidade) {
		this.popularidade += popularidade;
	}

	public boolean isLogado() {
		return logado;
	}

	public void setLogado(boolean logado) {
		this.logado = logado;
	}

	public void adicionaAmigo(Normal usuario) {
		amigos.add(usuario);
	}

	public void removeAmigo(String email) throws Exception {
		for (Normal usuario : amigos) {
			if (usuario.getEmail().equalsIgnoreCase(email)) {
				amigos.remove(usuario);
				break;
			}
		}

		throw new Exception("Esse usuario nao esta na sua lista de amigos");
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Normal))
			return false;

		Normal outroUsuario = (Normal) obj;

		return this.getEmail().equals(outroUsuario.getEmail())
				&& this.getNome().equals(outroUsuario.getNome())
				&& this.getSenha().equals(outroUsuario.getSenha())
				&& this.getDataDeNascimento().equals(
						outroUsuario.getDataDeNascimento());
	}

	@Override
	public String toString() {
		return "Nome: " + this.getNome() + "\nEmail: " + this.getEmail()
				+ "\nData de nascimento: " + this.getDataDeNascimento()
				+ "\nPopularidade: " + this.getPopularidade()
				+ "\nSenha: *******";
	}

	@Override
	public void setPopularidade() {
		// TODO Auto-generated method stub

	}

	@Override
	public void curtePost() {
		// TODO Auto-generated method stub

	}

	@Override
	public void DescurtePost() {
		// TODO Auto-generated method stub

	}

	@Override
	public int compareTo(Usuario outroUsuario) {
		if (this.getPopularidade() < outroUsuario.getPopularidade())
			return -1;

		if (this.getPopularidade() > outroUsuario.getPopularidade())
			return 1;

		return 0;
	}
}
