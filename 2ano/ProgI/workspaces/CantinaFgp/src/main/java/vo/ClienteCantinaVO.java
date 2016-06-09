package vo;

import java.util.List;

public class ClienteCantinaVO extends GenericVO {

	private Long idClienteCantina;
	private ClienteVO cliente;
	private CantinaVO cantina;
	private Double limiteDebito;
	private Boolean ativo;
	private List<CadernetaVO> cadernetas;
	private List<VendaVO> vendas;
	private TurmaVO turma;

	public ClienteCantinaVO() {

	}
	
	public List<CadernetaVO> getCadernetas() {
		return cadernetas;
	}

	public void setCadernetas(List<CadernetaVO> cadernetas) {
		this.cadernetas = cadernetas;
	}

	public List<VendaVO> getVendas() {
		return vendas;
	}

	public void setVendas(List<VendaVO> vendas) {
		this.vendas = vendas;
	}

	public List<CadernetaVO> getCardenetas() {
		return cadernetas;
	}

	public void setCardenetas(List<CadernetaVO> cadernetas) {
		this.cadernetas = cadernetas;
	}

	public Double getLimiteDebito() {
		return limiteDebito;
	}

	public void setLimiteDebito(Double limiteDebito) {
		this.limiteDebito = limiteDebito;
	}

	public TurmaVO getTurma() {
		return turma;
	}

	public void setTurma(TurmaVO turma) {
		this.turma = turma;
	}

	public Long getIdClienteCantina() {
		return idClienteCantina;
	}

	public void setIdClienteCantina(Long idClienteCantina) {
		this.idClienteCantina = idClienteCantina;
	}

	public ClienteVO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteVO cliente) {
		this.cliente = cliente;
	}

	public CantinaVO getCantina() {
		return cantina;
	}

	public void setCantina(CantinaVO cantina) {
		this.cantina = cantina;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
}
