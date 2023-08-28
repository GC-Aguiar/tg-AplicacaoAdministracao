package taubate.fatec.tg.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Usuario {

	@Id
	private Integer codigo;
	private String nome;
	private String login;
	private String senha;
	private String status;
	private String codigoPerfil;
	
	private Integer codigoSistema;
	private String dataCadastro;
	private Integer usuarioCadastro;
	private String dataAlteracao;
	private Integer usuarioAlteracao;

	public Usuario() {
		super();
	}

	public Usuario(Integer codigo, String nome, String login, String senha, String status, String codigoPerfil,
			Integer codigoSistema) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.status = status;
		this.codigoPerfil = codigoPerfil;
		this.codigoSistema = codigoSistema;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
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

	public Integer getCodigoSistema() {
		return codigoSistema;
	}

	public void setCodigoSistema(Integer codigoSistema) {
		this.codigoSistema = codigoSistema;
	}


	public String getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Integer getUsuarioCadastro() {
		return usuarioCadastro;
	}

	public void setUsuarioCadastro(Integer usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}

	public String getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(String dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public Integer getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Integer usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	@Override
	public String toString() {
		return "Usuario [codigo=" + codigo + ", nome=" + nome + ", login=" + login + ", senha=" + senha + ", status="
				+ status + ", codigoPerfil=" + codigoPerfil + ", codigoSistema=" + codigoSistema + ", dataCadastro="
				+ dataCadastro + ", usuarioCadastro=" + usuarioCadastro + ", dataAlteracao=" + dataAlteracao
				+ ", usuarioAlteracao=" + usuarioAlteracao + "]";
	}




}
