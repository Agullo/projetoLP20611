package usuario;

public interface Usuario extends Comparable<Usuario> {

	public int getPopularidade();

	public void setPopularidade();

	public void curtePost();

	public void DescurtePost();

}
