package taubate.fatec.tg.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Municipe {
		@Id
	    private Integer codigo;
	    private String nome;
	    private String telefone;
	    private String nomeSocial;
	    private String cpf;
	    private String nomeMae;
	    private String dataNascimento;
	    private String sexo;
	    private int codigoOcupacao;
	    private String tipoLogradouro;
	    private String logradouro;
	    private int numeroLogradouro;
	    private String complemento;
	    private int codigoBairro;
	    private String cep;
	    private int codigoCidade;
	    private String anoObito;
	    private String nacionalidade;
	    private int cidadeNascimento;
	    private boolean solicitaExclusao;
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
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public String getTelefone() {
			return telefone;
		}
		public void setTelefone(String telefone) {
			this.telefone = telefone;
		}
		public String getNomeSocial() {
			return nomeSocial;
		}
		public void setNomeSocial(String nomeSocial) {
			this.nomeSocial = nomeSocial;
		}
		public String getCpf() {
			return cpf;
		}
		public void setCpf(String cpf) {
			this.cpf = cpf;
		}
		public String getNomeMae() {
			return nomeMae;
		}
		public void setNomeMae(String nomeMae) {
			this.nomeMae = nomeMae;
		}
		public String getDataNascimento() {
			return dataNascimento;
		}
		public void setDataNascimento(String dataNascimento) {
			this.dataNascimento = dataNascimento;
		}
		public String getSexo() {
			return sexo;
		}
		public void setSexo(String sexo) {
			this.sexo = sexo;
		}
		public int getCodigoOcupacao() {
			return codigoOcupacao;
		}
		public void setCodigoOcupacao(int codigoOcupacao) {
			this.codigoOcupacao = codigoOcupacao;
		}
		public String getTipoLogradouro() {
			return tipoLogradouro;
		}
		public void setTipoLogradouro(String tipoLogradouro) {
			this.tipoLogradouro = tipoLogradouro;
		}
		public String getLogradouro() {
			return logradouro;
		}
		public void setLogradouro(String logradouro) {
			this.logradouro = logradouro;
		}
		public int getNumeroLogradouro() {
			return numeroLogradouro;
		}
		public void setNumeroLogradouro(int numeroLogradouro) {
			this.numeroLogradouro = numeroLogradouro;
		}
		public String getComplemento() {
			return complemento;
		}
		public void setComplemento(String complemento) {
			this.complemento = complemento;
		}
		public int getCodigoBairro() {
			return codigoBairro;
		}
		public void setCodigoBairro(int codigoBairro) {
			this.codigoBairro = codigoBairro;
		}
		public String getCep() {
			return cep;
		}
		public void setCep(String cep) {
			this.cep = cep;
		}
		public int getCodigoCidade() {
			return codigoCidade;
		}
		public void setCodigoCidade(int codigoCidade) {
			this.codigoCidade = codigoCidade;
		}
		public String getAnoObito() {
			return anoObito;
		}
		public void setAnoObito(String anoObito) {
			this.anoObito = anoObito;
		}
		public String getNacionalidade() {
			return nacionalidade;
		}
		public void setNacionalidade(String nacionalidade) {
			this.nacionalidade = nacionalidade;
		}
		public int getCidadeNascimento() {
			return cidadeNascimento;
		}
		public void setCidadeNascimento(int cidadeNascimento) {
			this.cidadeNascimento = cidadeNascimento;
		}
		public boolean isSolicitaExclusao() {
			return solicitaExclusao;
		}
		public void setSolicitaExclusao(boolean solicitaExclusao) {
			this.solicitaExclusao = solicitaExclusao;
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
			return "Municipe [codigo=" + codigo + ", nome=" + nome + ", telefone=" + telefone + ", nomeSocial="
					+ nomeSocial + ", cpf=" + cpf + ", nomeMae=" + nomeMae + ", dataNascimento=" + dataNascimento
					+ ", sexo=" + sexo + ", codigoOcupacao=" + codigoOcupacao + ", tipoLogradouro=" + tipoLogradouro
					+ ", logradouro=" + logradouro + ", numeroLogradouro=" + numeroLogradouro + ", complemento="
					+ complemento + ", codigoBairro=" + codigoBairro + ", cep=" + cep + ", codigoCidade=" + codigoCidade
					+ ", anoObito=" + anoObito + ", nacionalidade=" + nacionalidade + ", cidadeNascimento="
					+ cidadeNascimento + ", solicitaExclusao=" + solicitaExclusao + ", dataCadastro=" + dataCadastro
					+ ", usuarioCadastro=" + usuarioCadastro + ", dataAlteracao=" + dataAlteracao
					+ ", usuarioAlteracao=" + usuarioAlteracao + "]";
		}
	    
	    
	    
	}

