package pop;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import teste.VerificaErros;
import usuario.CelebridadePop;
import usuario.Normal;
import usuario.Usuario;

public class Post {

	private int popularidade;
	private LocalDate dataDePublicacao;
	private LocalTime horaDePublicacao;
	private String mensagemOriginal;
	private String mensagem;
	private String musica;
	private List<String> imagem;
	private VerificaErros verificaErros;
	private List<String> hashtag;

	public Post(String mensagem, LocalDate dataDePublicacao,
			LocalTime horaDePublicacao) throws Exception {

		this.mensagemOriginal = mensagem;
		this.dataDePublicacao = dataDePublicacao;
		this.horaDePublicacao = horaDePublicacao;
		popularidade = 0;
		verificaErros = new VerificaErros();
		hashtag = new ArrayList<String>();
		musica = null;
		mensagem = null;
		imagem = new ArrayList<String>();
		separaMensagem();
		procuraHashtag();
		procuraArquivoImagem();
		procuraArquivoMusica();
	}

	public void likePost(Usuario usuario) {
		if (usuario instanceof Normal)
			this.aumentaPopularidade(10);

		else if (usuario instanceof CelebridadePop) {
			if (this.getDataDePublicacao().equals(LocalDate.now()))
				this.aumentaPopularidade(25 + 10);
			else
				this.aumentaPopularidade(25);

		} else {
			this.aumentaPopularidade(50);
			hashtag.add("#epicwin");

		}
	}

	public void deslikePost(Usuario usuario) {
		if (usuario instanceof Normal)
			this.diminuiPopularidade(10);

		else if (usuario instanceof CelebridadePop) {
			if (this.getDataDePublicacao().equals(LocalDate.now()))
				this.diminuiPopularidade(25 + 10);
			else
				this.diminuiPopularidade(25);

		} else {
			this.aumentaPopularidade(50);
			hashtag.add("#epicfail");

		}
	}

	public void setMusica(String musica) {
		this.musica = musica;
	}

	public String getMusica() {
		return musica;
	}

	public void setImagem(List<String> imagem) {
		this.imagem = imagem;
	}

	public List<String> getImagem() {
		return imagem;
	}

	public void setMensagem(String mensagem) throws Exception {
		verificaErros.verificaMensagem(mensagem);

		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return this.mensagem;
	}

	public void setMensagemOriginal(String mensagem) throws Exception {
		verificaErros.verificaMensagem(mensagem);

		this.mensagemOriginal = mensagem;
	}

	public String getMensagemOriginal() {
		return mensagemOriginal;
	}

	public void setHashtag(List<String> hashtag) {
		this.hashtag = hashtag;
	}

	public List<String> getHashtag() {
		return this.hashtag;
	}

	public void aumentaPopularidade(int valor) {
		popularidade += valor;
	}

	public void diminuiPopularidade(int valor) {
		popularidade -= valor;
	}

	public LocalTime getHoraDePublicacao() {
		return horaDePublicacao;
	}

	public LocalDate getDataDePublicacao() {
		return dataDePublicacao;
	}

	public int getPopularidade() {
		return popularidade;
	}

	@Override
	public String toString() {
		return "Hora de publicacao: " + this.horaDePublicacao
				+ "\nPopularidade: " + this.popularidade + "\nMensagem: "
				+ this.mensagemOriginal;
	}

	private void separaMensagem() throws Exception {
		String tempMensagem = getMensagemOriginal();
		String[] mensagem = tempMensagem.split(" ");
		String novaMensagem = null;

		for (int i = 0; i < mensagem.length; i++) {
			if (mensagem[i].startsWith("#")
					|| mensagem[i].startsWith("<audio>")
					|| mensagem[i].startsWith("<imagem>"))
				break;
			else
				novaMensagem += mensagem[i] + " ";

		}
		novaMensagem = novaMensagem.substring(0, novaMensagem.length() - 1);
		this.setMensagem(novaMensagem);
	}

	private void procuraHashtag() throws Exception {
		String tempMensagem = getMensagemOriginal();
		String[] mensagem = tempMensagem.split(" ");

		for (int i = 0; i < mensagem.length; i++) {
			if (mensagem[i].startsWith("#")) {
				for (int j = i; j < mensagem.length; j++) {
					if (mensagem[j].startsWith("#"))
						hashtag.add(mensagem[j]);
					else
						throw new Exception("Hashtag deve comecar com '#'");
				}
				break;
			}

		}

	}

	private void procuraArquivoMusica() {
		String tempMensagem = getMensagemOriginal();
		String[] mensagem = tempMensagem.split(" ");

		for (int i = 0; i < mensagem.length; i++) {
			if (mensagem[i].startsWith("<audio>")) {
				this.musica = mensagem[i];
				break;
			}
		}
	}

	private void procuraArquivoImagem() {
		String tempMensagem = getMensagemOriginal();
		String[] mensagem = tempMensagem.split(" ");

		for (int i = 0; i < mensagem.length; i++) {
			if (mensagem[i].startsWith("<imagem>"))
				imagem.add(mensagem[i]);
		}

	}

}
