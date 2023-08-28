package taubate.fatec.tg.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Bairro {

	
	@Id
	private Integer codigo;
	private String descricao;	
	private String regiao;
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
	public String getRegiao() {
		return regiao;
	}
	public void setRegiao(String regiao) {
		this.regiao = regiao;
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
		return "Bairro [codigo=" + codigo + ", descricao=" + descricao + ", regiao=" + regiao + ", dataCadastro="
				+ dataCadastro + ", usuarioCadastro=" + usuarioCadastro + ", dataAlteracao=" + dataAlteracao
				+ ", usuarioAlteracao=" + usuarioAlteracao + "]";
	}
	
}
