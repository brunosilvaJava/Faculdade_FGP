package vo;

import java.util.List;

import enumeradores.TipoProduto;

public abstract class ProdutoVO extends GenericVO {
	
	private Long idProduto;
	private String codProduto;
	private String descricao;
	private Double qtdeEstoque;
	private Double precoCusto;
	private UnidadeProdutoVO unidade;
	private Boolean ativo;
	private Boolean lote;
	private ProdutoCantinaVO produtoCantina;
	private List<ItemCompraVO> itemCompras;
	private List<FornecedorProdutoVO> fornecedores;
	private TipoProduto tipo;
	
	public ProdutoVO() {
	
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPrecoCusto() {
		return precoCusto;
	}

	public void setPrecoCusto(Double precoCusto) {
		this.precoCusto = precoCusto;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getCodProduto() {
		return codProduto;
	}

	public void setCodProduto(String codProduto) {
		this.codProduto = codProduto;
	}

	public Boolean getLote() {
		return lote;
	}

	public void setLote(Boolean lote) {
		this.lote = lote;
	}

	public UnidadeProdutoVO getUnidade() {
		return unidade;
	}

	public void setUnidade(UnidadeProdutoVO unidade) {
		this.unidade = unidade;
	}

	public List<ItemCompraVO> getItemCompras() {
		return itemCompras;
	}

	public void setItemCompras(List<ItemCompraVO> itemCompras) {
		this.itemCompras = itemCompras;
	}

	public List<FornecedorProdutoVO> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(List<FornecedorProdutoVO> fornecedores) {
		this.fornecedores = fornecedores;
	}

	public TipoProduto getTipo() {
		return tipo;
	}

	public void setTipo(TipoProduto tipo) {
		this.tipo = tipo;
	}

	public ProdutoCantinaVO getEstoque() {
		return produtoCantina;
	}

	public void setEstoque(ProdutoCantinaVO produtoCantina) {
		this.produtoCantina = produtoCantina;
	}

	public Double getQtdeEstoque() {
		return qtdeEstoque;
	}

	public void setQtdeEstoque(Double qtdeEstoque) {
		this.qtdeEstoque = qtdeEstoque;
	}
	
}
