package vo;

import java.util.Date;
import java.util.List;

public class CaixaVO {

	private Long idCaixa;
	private Double saldoInicial;
	private Double saldoFinal;
	private Double diferenca;
	private Date dataHoraAbertura;
	private Date dataHoraFechamento;
	private List<MovimentacaoVO> movimentacoes;
	
	public CaixaVO() {

	}

	public Long getIdCaixa() {
		return idCaixa;
	}

	public void setIdCaixa(Long idCaixa) {
		this.idCaixa = idCaixa;
	}

	public Double getSaldoInicial() {
		return saldoInicial;
	}

	public void setSaldoInicial(Double saldoInicial) {
		this.saldoInicial = saldoInicial;
	}

	public Double getSaldoFinal() {
		return saldoFinal;
	}

	public void setSaldoFinal(Double saldoFinal) {
		this.saldoFinal = saldoFinal;
	}

	public Double getDiferenca() {
		return diferenca;
	}

	public void setDiferenca(Double diferenca) {
		this.diferenca = diferenca;
	}

	public Date getDataHoraAbertura() {
		return dataHoraAbertura;
	}

	public void setDataHoraAbertura(Date dataHoraAbertura) {
		this.dataHoraAbertura = dataHoraAbertura;
	}

	public Date getDataHoraFechamento() {
		return dataHoraFechamento;
	}

	public void setDataHoraFechamento(Date dataHoraFechamento) {
		this.dataHoraFechamento = dataHoraFechamento;
	}

	public List<MovimentacaoVO> getMovimentacoes() {
		return movimentacoes;
	}

	public void setMovimentacoes(List<MovimentacaoVO> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}
	
}
