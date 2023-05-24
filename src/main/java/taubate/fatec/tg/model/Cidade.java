package taubate.fatec.tg.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cidade {
	
	@Id
	private Integer codigo;
	private String descricao;
	private Integer ufCodigo;
	private Date dataCadastro;
	private Integer usuarioCadastro;
	private Date dataAlteracao;
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
	public Integer getUfCodigo() {
		return ufCodigo;
	}
	public void setUfCodigo(Integer ufCodigo) {
		this.ufCodigo = ufCodigo;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public Integer getUsuarioCadastro() {
		return usuarioCadastro;
	}
	public void setUsuarioCadastro(Integer usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}
	public Date getDataAlteracao() {
		return dataAlteracao;
	}
	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}
	public Integer getUsuarioAlteracao() {
		return usuarioAlteracao;
	}
	public void setUsuarioAlteracao(Integer usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}
	
	

}
