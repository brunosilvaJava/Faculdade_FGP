package vo;

import java.util.Date;

public class MovimentacaoVO {
	
	private Long idMovimentacao;
	private Double valor;
	private Date data;
	private String observacao;
	private TipoMovimentacaoVO tipoMovimentacao;
	private CaixaVO caixa;
	private FuncionarioCantinaVO funcionario;
	private VendaVO venda;
	
	public MovimentacaoVO() {
	
	}

	public Long getIdMovimentacao() {
		return idMovimentacao;
	}

	public void setIdMovimentacao(Long idMovimentacao) {
		this.idMovimentacao = idMovimentacao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public TipoMovimentacaoVO getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	public void setTipoMovimentacao(TipoMovimentacaoVO tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}

	public CaixaVO getCaixa() {
		return caixa;
	}

	public void setCaixa(CaixaVO caixa) {
		this.caixa = caixa;
	}

	public FuncionarioCantinaVO getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(FuncionarioCantinaVO funcionario) {
		this.funcionario = funcionario;
	}

	public VendaVO getVenda() {
		return venda;
	}

	public void setVenda(VendaVO venda) {
		this.venda = venda;
	}
	
}
