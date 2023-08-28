package taubate.fatec.tg.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Uf {
	
	@Id
	private Integer codigo;	
	private String descricao;
	private String regiao;
	private Date dataCadastro;
	private Integer usuarioCadastro;
	private Date dataAlteracao;
	private Integer usuarioAlteracao;
	@Override
	public String toString() {
		return "Uf [codigo=" + codigo + ", descricao=" + descricao + ", regiao=" + regiao + ", dataCadastro="
				+ dataCadastro + ", usuarioCadastro=" + usuarioCadastro + ", dataAlteracao=" + dataAlteracao
				+ ", usuarioAlteracao=" + usuarioAlteracao + "]";
	}
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
	public String getRegiao() {
		return regiao;
	}
	public void setRegiao(String regiao) {
		this.regiao = regiao;
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
