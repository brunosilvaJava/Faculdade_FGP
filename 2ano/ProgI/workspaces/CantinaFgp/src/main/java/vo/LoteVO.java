package vo;

import java.util.Date;

import enumeradores.TipoGeradorLote;
import enumeradores.TipoProduto;

public class LoteVO {
	
	private Long idLote;
	private String codLote;
	private Double qtde;
	private Date dataVencimento;
	private ProdutoCantinaVO estoqueProduto;
	private OrdemProducaoVO ordemProducao;
	private CompraVO compra;
	private TipoGeradorLote tipoGeradorLote;
	

	public LoteVO() {

	}

	public Long getIdLote() {
		return idLote;
	}

	public void setIdLote(Long idLote) {
		this.idLote = idLote;
	}

	public String getCodLote() {
		return codLote;
	}

	public void setCodLote(String codLote) {
		this.codLote = codLote;
	}

	public Double getQtde() {
		return qtde;
	}

	public void setQtde(Double qtde) {
		this.qtde = qtde;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public ProdutoCantinaVO getEstoqueProduto() {
		return estoqueProduto;
	}

	public void setEstoqueProduto(ProdutoCantinaVO estoqueProduto) {
		this.estoqueProduto = estoqueProduto;
	}

	public TipoGeradorLote getTipoGeradorLote() {
		return tipoGeradorLote;
	}

	public void setTipoGeradorLote(TipoGeradorLote tipoGeradorLote) {
		this.tipoGeradorLote = tipoGeradorLote;
	}

	public OrdemProducaoVO getOrdemProducao() {
		return ordemProducao;
	}

	public void setOrdemProducao(OrdemProducaoVO ordemProducao) {
		this.ordemProducao = ordemProducao;
	}

	public CompraVO getCompra() {
		return compra;
	}

	public void setCompra(CompraVO compra) {
		this.compra = compra;
	}

	
	
}
