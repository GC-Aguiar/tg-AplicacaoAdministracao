package taubate.fatec.tg.model;

public class UsuarioView {
	
	private Integer codigoUsuario;
	private String nome;
	private String login;
	private String senha;
	private String status;
	private String codigoPerfil;
	private String descricaoPerfil;
	private Integer codigoSistema;
	private String descricaoSistema;
	
	
	public UsuarioView() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	


	public UsuarioView(Integer codigoUsuario, String nome, String login, String senha, String status,
			String codigoPerfil, String descricaoPerfil, Integer codigoSistema, String descricaoSistema) {
		super();
		this.codigoUsuario = codigoUsuario;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.status = status;
		this.codigoPerfil = codigoPerfil;
		this.descricaoPerfil = descricaoPerfil;
		this.codigoSistema = codigoSistema;
		this.descricaoSistema = descricaoSistema;
	}




	@Override
	public String toString() {
		return "UsuarioView [codigoUsuario=" + codigoUsuario + ", nome=" + nome + ", login=" + login + ", senha="
				+ senha + ", status=" + status + ", codigoPerfil=" + codigoPerfil + ", descricaoPerfil="
				+ descricaoPerfil + ", codigoSistema=" + codigoSistema + ", descricaoSistema=" + descricaoSistema + "]";
	}


	public Integer getCodigoUsuario() {
		return codigoUsuario;
	}


	public void setCodigoUsuario(Integer codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getCodigoPerfil() {
		return codigoPerfil;
	}


	public void setCodigoPerfil(String codigoPerfil) {
		this.codigoPerfil = codigoPerfil;
	}


	public String getDescricaoPerfil() {
		return descricaoPerfil;
	}


	public void setDescricaoPerfil(String descricaoPerfil) {
		this.descricaoPerfil = descricaoPerfil;
	}


	public Integer getCodigoSistema() {
		return codigoSistema;
	}


	public void setCodigoSistema(Integer codigoSistema) {
		this.codigoSistema = codigoSistema;
	}


	public String getDescricaoSistema() {
		return descricaoSistema;
	}


	public void setDescricaoSistema(String descricaoSistema) {
		this.descricaoSistema = descricaoSistema;
	}
	
	
	
	

}
