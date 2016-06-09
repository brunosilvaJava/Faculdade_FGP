package vo;

import interfaces.IGeradorCompra;

import java.util.Date;

public class OrdemProducaoVO extends GenericVO implements IGeradorCompra {

	private Long idOrdemProducao;
	private String codOrdemProducao;
	private Date data;
	private Integer qtde;
	private FuncionarioCantinaVO funcionarioCantina;
	private ProdutoVendaVO produtoVenda;
	private LoteVO lote;
	private StatusVO status;
	
	public OrdemProducaoVO() {
		
	}

	public Long getIdOrdemProducao() {
		return idOrdemProducao;
	}

	public void setIdOrdemProducao(Long idOrdemProducao) {
		this.idOrdemProducao = idOrdemProducao;
	}

	public String getCodOrdemProducao() {
		return codOrdemProducao;
	}

	public void setCodOrdemProducao(String codOrdemProducao) {
		this.codOrdemProducao = codOrdemProducao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public FuncionarioCantinaVO getFuncionarioCantina() {
		return funcionarioCantina;
	}

	public void setFuncionarioCantina(FuncionarioCantinaVO funcionarioCantina) {
		this.funcionarioCantina = funcionarioCantina;
	}

	public ProdutoVendaVO getProdutoVenda() {
		return produtoVenda;
	}

	public void setProdutoVenda(ProdutoVendaVO produtoVenda) {
		this.produtoVenda = produtoVenda;
	}

	public LoteVO getLote() {
		return lote;
	}

	public void setLote(LoteVO lote) {
		this.lote = lote;
	}

	public Integer getQtde() {
		return qtde;
	}

	public void setQtde(Integer qtde) {
		this.qtde = qtde;
	}

	public StatusVO getStatus() {
		return status;
	}

	public void setStatus(StatusVO status) {
		this.status = status;
	}
	
}
