package vo;

import java.util.List;

public class CantinaVO extends GenericVO {
	
	private Long idCantina;
	private String nome;
	private Boolean ativo;
	private List<ClienteCantinaVO> clientesCantina;
	private List<FuncionarioCantinaVO> funcionariosCantina;
	private List<ProdutoCantinaVO> estoqueProdutos;
	
	public CantinaVO() {

	}

	public Long getIdCantina() {
		return idCantina;
	}

	public void setIdCantina(Long idCantina) {
		this.idCantina = idCantina;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<ProdutoCantinaVO> getEstoqueProdutos() {
		return estoqueProdutos;
	}

	public void setEstoqueProdutos(List<ProdutoCantinaVO> estoqueProdutos) {
		this.estoqueProdutos = estoqueProdutos;
	}

	public List<ClienteCantinaVO> getClientesCantina() {
		return clientesCantina;
	}

	public void setClientesCantina(List<ClienteCantinaVO> clientesCantina) {
		this.clientesCantina = clientesCantina;
	}

	public List<FuncionarioCantinaVO> getFuncionariosCantina() {
		return funcionariosCantina;
	}

	public void setFuncionariosCantina(List<FuncionarioCantinaVO> funcionariosCantina) {
		this.funcionariosCantina = funcionariosCantina;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
}
