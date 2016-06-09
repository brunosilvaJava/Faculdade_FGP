package vo;

public class UnidadeProdutoVO extends GenericVO {

	private Long idUnidadeProduto;
	private String abreviatura;
	private String descricao;
	private Boolean status;
	
	public UnidadeProdutoVO() {
	 
	}

	public Long getIdUnidadeProduto() {
		return idUnidadeProduto;
	}

	public void setIdUnidadeProduto(Long idUnidadeProduto) {
		this.idUnidadeProduto = idUnidadeProduto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	
}
