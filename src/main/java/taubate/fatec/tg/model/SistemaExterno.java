package taubate.fatec.tg.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SistemaExterno {
	
	@Id
	private Integer codigo;
	private String descricao;
	private Integer empCodigo;
	private String status;
	private String email;
	private String preposto;
	private Integer contagemAcessos;
	private String dataCadastro;
	private Integer usuarioCadastro;
	private String dataAlteracao;
	private Integer usuarioAlteracao;
	
	
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
	public Integer getEmpCodigo() {
		return empCodigo;
	}
	public void setEmpCodigo(Integer empCodigo) {
		this.empCodigo = empCodigo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	
	public Integer getContagemAcessos() {
		return contagemAcessos;
	}
	public void setContagemAcessos(Integer contagemAcessos) {
		this.contagemAcessos = contagemAcessos;
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
		return "SistemaExterno [codigo=" + codigo + ", descricao=" + descricao + ", empCodigo=" + empCodigo
				+ ", status=" + status + ", email=" + email + ", preposto=" + preposto + ", contagemAcessos="
				+ contagemAcessos + ", dataCadastro=" + dataCadastro + ", usuarioCadastro=" + usuarioCadastro
				+ ", dataAlteracao=" + dataAlteracao + ", usuarioAlteracao=" + usuarioAlteracao + "]";
	}
	

	

}
