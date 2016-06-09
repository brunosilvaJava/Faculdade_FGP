package vo;

import java.util.List;

public class TurmaVO {
	
	private String codTurma;
	private String curso;
	private Integer ano;
	private Boolean ativo;
	private List<ClienteCantinaVO> clientesCantina; 
	
	public TurmaVO() {

	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public List<ClienteCantinaVO> getClientesCantina() {
		return clientesCantina;
	}

	public void setClientesCantina(List<ClienteCantinaVO> clientesCantina) {
		this.clientesCantina = clientesCantina;
	}

	public String getCodTurma() {
		return codTurma;
	}

	public void setCodTurma(String codTurma) {
		this.codTurma = codTurma;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
}
