package vo;

import java.util.List;

public class FormaPgtoVO extends GenericVO {
	
	private Long idFormaPgtoVenda;
	private String descricao;
	private List<VendaVO> vendas;
	private List<CompraVO> compras;
	
	public FormaPgtoVO() {

	}

	public Long getIdFormaPgtoVenda() {
		return idFormaPgtoVenda;
	}

	public void setIdFormaPgtoVenda(Long idFormaPgtoVenda) {
		this.idFormaPgtoVenda = idFormaPgtoVenda;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<VendaVO> getVendas() {
		return vendas;
	}

	public void setVendas(List<VendaVO> vendas) {
		this.vendas = vendas;
	}

	public List<CompraVO> getCompras() {
		return compras;
	}

	public void setCompras(List<CompraVO> compras) {
		this.compras = compras;
	}
	
}
