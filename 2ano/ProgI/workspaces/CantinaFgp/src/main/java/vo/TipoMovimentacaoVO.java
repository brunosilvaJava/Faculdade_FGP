package vo;

import java.util.List;

public class TipoMovimentacaoVO {

	private Long idTipoMovimentacao;
	private String descricao;
	private List<MovimentacaoVO> movimentacoes;
	
	public TipoMovimentacaoVO() {

	}

	public Long getIdTipoMovimentacao() {
		return idTipoMovimentacao;
	}

	public void setIdTipoMovimentacao(Long idTipoMovimentacao) {
		this.idTipoMovimentacao = idTipoMovimentacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<MovimentacaoVO> getMovimentacoes() {
		return movimentacoes;
	}

	public void setMovimentacoes(List<MovimentacaoVO> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}
		
}
