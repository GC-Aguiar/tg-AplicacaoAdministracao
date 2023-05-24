package taubate.fatec.tg.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Empresa {
	
	@Id
	private Integer codigo;
	private String descricao;
	private String cnpj;
	private String email;
	private String preposto;
    private String dataCadastro;
    private int usuarioCadastro;
    private String dataAlteracao;
    private int usuarioAlteracao;
    
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPreposto() {
		return preposto;
	}
	public void setPreposto(String preposto) {
		this.preposto = preposto;
	}
	public String getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public int getUsuarioCadastro() {
		return usuarioCadastro;
	}
	public void setUsuarioCadastro(int usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}
	public String getDataAlteracao() {
		return dataAlteracao;
	}
	public void setDataAlteracao(String dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}
	public int getUsuarioAlteracao() {
		return usuarioAlteracao;
	}
	public void setUsuarioAlteracao(int usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}
	@Override
	public String toString() {
		return "Empresa [codigo=" + codigo + ", descricao=" + descricao + ", cnpj=" + cnpj + ", email=" + email
				+ ", preposto=" + preposto + ", dataCadastro=" + dataCadastro + ", usuarioCadastro=" + usuarioCadastro
				+ ", dataAlteracao=" + dataAlteracao + ", usuarioAlteracao=" + usuarioAlteracao + "]";
	}
	
	
	
	
	
	

}
