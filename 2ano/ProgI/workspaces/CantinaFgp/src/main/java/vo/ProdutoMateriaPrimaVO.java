package vo;

public class ProdutoMateriaPrimaVO extends GenericVO{
	
	private Long idProdutoMateriaPrima;
	private String codProdutoMateriaPrima;
	private MateriaPrimaVO materiaPrima;
	private ProdutoVendaVO produtoFabricado;
	private Double qtde;
	private UnidadeProdutoVO unidade;
	
	public ProdutoMateriaPrimaVO() {

	}

	public Long getIdReceita() {
		return idProdutoMateriaPrima;
	}

	public void setIdReceita(Long idReceita) {
		this.idProdutoMateriaPrima = idReceita;
	}

	public String getCodReceita() {
		return codProdutoMateriaPrima;
	}

	public void setCodReceita(String codReceita) {
		this.codProdutoMateriaPrima = codReceita;
	}

	public MateriaPrimaVO getMateriaPrima() {
		return materiaPrima;
	}

	public void setMateriaPrima(MateriaPrimaVO materiaPrima) {
		this.materiaPrima = materiaPrima;
	}

	public Double getQtde() {
		return qtde;
	}

	public void setQtde(Double qtde) {
		this.qtde = qtde;
	}

	public UnidadeProdutoVO getUnidade() {
		return unidade;
	}

	public void setUnidade(UnidadeProdutoVO unidade) {
		this.unidade = unidade;
	}

	public ProdutoVendaVO getProdutoFabricado() {
		return produtoFabricado;
	}

	public void setProdutoFabricado(ProdutoVendaVO produtoFabricado) {
		this.produtoFabricado = produtoFabricado;
	}
		
}
