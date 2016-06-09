package vo;

public class FornecedorProdutoVO extends GenericVO {

	private Double valor;
	private FornecedorVO fornecedor;
	private ProdutoVO produto;
	
	public FornecedorProdutoVO() {

	}

	protected Double getValor() {
		return valor;
	}

	protected void setValor(Double valor) {
		this.valor = valor;
	}

	protected FornecedorVO getFornecedor() {
		return fornecedor;
	}

	protected void setFornecedor(FornecedorVO fornecedor) {
		this.fornecedor = fornecedor;
	}

	protected ProdutoVO getProduto() {
		return produto;
	}

	protected void setProduto(ProdutoVO produto) {
		this.produto = produto;
	}
	
}

