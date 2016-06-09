package vo;


public class ItemVendaVO {
	
	private ProdutoVendaVO produto;
	private VendaVO venda;
	private Double qtde;
	private Double valor;
		
	public ItemVendaVO() {

	}

	public VendaVO getVenda() {
		return venda;
	}

	public void setVenda(VendaVO venda) {
		this.venda = venda;
	}

	public Double getQtde() {
		return qtde;
	}

	public void setQtde(Double qtde) {
		this.qtde = qtde;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}



	public ProdutoVendaVO getProduto() {
		return produto;
	}



	public void setProduto(ProdutoVendaVO produto) {
		this.produto = produto;
	}
	
}
