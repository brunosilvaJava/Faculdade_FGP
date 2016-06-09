package vo;

import java.util.Date;

public class CadernetaVO extends GenericVO {
	
	private Integer idCaderneta;
	private Double valor;
	private Date dataMovimentacao;
	private Boolean status;
	private String observacao;
	private ClienteCantinaVO clienteCantina; 
	
	public CadernetaVO() {
	}

	public Integer getIdCaderneta() {
		return idCaderneta;
	}

	public void setIdCaderneta(Integer idCaderneta) {
		this.idCaderneta = idCaderneta;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public ClienteCantinaVO getClienteCantina() {
		return clienteCantina;
	}

	public void setClienteCantina(ClienteCantinaVO clienteCantina) {
		this.clienteCantina = clienteCantina;
	}

	public Date getDataMovimentacao() {
		return dataMovimentacao;
	}

	public void setDataMovimentacao(Date dataMovimentacao) {
		this.dataMovimentacao = dataMovimentacao;
	}
		
}
