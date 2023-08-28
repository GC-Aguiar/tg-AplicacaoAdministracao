package taubate.fatec.tg.model;

public class SistemaExternoView {
	
	private Integer codigo;
	private String descricao;
	private Integer empCodigo;
	private String empDescricao;
	private String status;
	private String email;
	private String preposto;
	private Integer contagemAcessos;
	
	public SistemaExternoView() {
		super();
	}

	public SistemaExternoView(Integer codigo, String descricao, Integer empCodigo, String empDescricao, String status,
			String email, String preposto, Integer contagemAcessos) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.empCodigo = empCodigo;
		this.empDescricao = empDescricao;
		this.status = status;
		this.email = email;
		this.preposto = preposto;
		this.contagemAcessos = contagemAcessos;
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

	public Integer getEmpCodigo() {
		return empCodigo;
	}

	public void setEmpCodigo(Integer empCodigo) {
		this.empCodigo = empCodigo;
	}

	public String getEmpDescricao() {
		return empDescricao;
	}

	public void setEmpDescricao(String empDescricao) {
		this.empDescricao = empDescricao;
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



	@Override
	public String toString() {
		return "SistemaExternoView [codigo=" + codigo + ", descricao=" + descricao + ", empCodigo=" + empCodigo
				+ ", empDescricao=" + empDescricao + ", status=" + status + ", email=" + email + ", preposto="
				+ preposto + ", contagemAcessos=" + contagemAcessos + "]";
	}



	
	


}
