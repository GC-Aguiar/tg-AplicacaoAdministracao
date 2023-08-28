package taubate.fatec.tg.model;

import java.sql.Date;

public class CidadeView {
	
	private Integer codigo;
	private String descricao;
	private Integer ufCodigo;
	private String ufDescricao;
	


	public CidadeView() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public CidadeView(Integer codigo, String descricao, Integer ufCodigo, String ufDescricao) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.ufCodigo = ufCodigo;
		this.ufDescricao = ufDescricao;
	}



	@Override
	public String toString() {
		return "CidadeView [codigo=" + codigo + ", descricao=" + descricao + ", ufCodigo=" + ufCodigo + ", ufDescricao="
				+ ufDescricao + "]";
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

	public Integer getUfCodigo() {
		return ufCodigo;
	}

	public void setUfCodigo(Integer ufCodigo) {
		this.ufCodigo = ufCodigo;
	}

	public String getUfDescricao() {
		return ufDescricao;
	}

	public void setUfDescricao(String ufDescricao) {
		this.ufDescricao = ufDescricao;
	}
	
	

}
