package vo;

import interfaces.IGeradorCompra;

import java.util.Date;
import java.util.List;

public class FuncionarioCantinaVO extends GenericVO implements IGeradorCompra {

	private Long idFuncionarioCantina;
	private FuncionarioVO funcionario;
	private CantinaVO cantina;
	private String cargo;
	private Date dataContratacao;
	private Date dataSaida;
	private Boolean ativo;
	private List<VendaVO> vendasFuncionario;
	private List<CompraVO> comprasFuncionario;

	public FuncionarioCantinaVO() {

	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Date getDataContratacao() {
		return dataContratacao;
	}

	public void setDataContratacao(Date dataContratacao) {
		this.dataContratacao = dataContratacao;
	}

	public Date getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	public FuncionarioVO getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(FuncionarioVO funcionario) {
		this.funcionario = funcionario;
	}

	public CantinaVO getCantina() {
		return cantina;
	}

	public void setCantina(CantinaVO cantina) {
		this.cantina = cantina;
	}

	public List<VendaVO> getVendasFuncionario() {
		return vendasFuncionario;
	}

	public void setVendasFuncionario(List<VendaVO> vendasFuncionario) {
		this.vendasFuncionario = vendasFuncionario;
	}

	public List<CompraVO> getComprasFuncionario() {
		return comprasFuncionario;
	}

	public void setComprasFuncionario(List<CompraVO> comprasFuncionario) {
		this.comprasFuncionario = comprasFuncionario;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Long getIdFuncionarioCantina() {
		return idFuncionarioCantina;
	}

	public void setIdFuncionarioCantina(Long idFuncionarioCantina) {
		this.idFuncionarioCantina = idFuncionarioCantina;
	}
	
}
