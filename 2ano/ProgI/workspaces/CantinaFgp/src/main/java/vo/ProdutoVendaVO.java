package vo;

import java.util.List;

public class ProdutoVendaVO extends ProdutoVO{

	private Double precoVenda;
 	private List<OrdemProducaoVO> ordensProducao;
 	private List<ProdutoMateriaPrimaVO> receita;
	
	public ProdutoVendaVO() {
	 
	}

	public Double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(Double precoVenda) {
		this.precoVenda = precoVenda;
	}

	public List<OrdemProducaoVO> getOrdensProducao() {
		return ordensProducao;
	}

	public void setOrdensProducao(List<OrdemProducaoVO> ordensProducao) {
		this.ordensProducao = ordensProducao;
	}
	public List<ProdutoMateriaPrimaVO> getReceita() {
		return receita;
	}

	public void setReceita(List<ProdutoMateriaPrimaVO> receita) {
		this.receita = receita;
	}

}
