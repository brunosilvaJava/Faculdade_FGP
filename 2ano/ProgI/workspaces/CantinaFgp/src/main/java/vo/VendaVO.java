package vo;

import java.util.Date;
import java.util.List;

public class VendaVO extends GenericVO{
	
	private Long idVenda;
	private String codVenda;
	private Date data;
	private Double desconto;
	private FormaPgtoVO formaPgto;
	private ClienteCantinaVO clienteCantina;
	private FuncionarioCantinaVO funcionarioCantina;
	private List<ItemVendaVO> itensVenda;

	public VendaVO() {

	}

	public Long getIdVenda() {
		return idVenda;
	}

	public void setIdVenda(Long idVenda) {
		this.idVenda = idVenda;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public FormaPgtoVO getFormaPgto() {
		return formaPgto;
	}

	public void setFormaPgto(FormaPgtoVO formaPgto) {
		this.formaPgto = formaPgto;
	}

	public ClienteCantinaVO getClienteCantina() {
		return clienteCantina;
	}

	public void setClienteCantina(ClienteCantinaVO clienteCantina) {
		this.clienteCantina = clienteCantina;
	}

	public List<ItemVendaVO> getItensVenda() {
		return itensVenda;
	}

	public void setItensVenda(List<ItemVendaVO> itensVenda) {
		this.itensVenda = itensVenda;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public String getCodVenda() {
		return codVenda;
	}

	public void setCodVenda(String codVenda) {
		this.codVenda = codVenda;
	}

	public FuncionarioCantinaVO getFuncionarioCantina() {
		return funcionarioCantina;
	}

	public void setFuncionarioCantina(FuncionarioCantinaVO funcionarioCantina) {
		this.funcionarioCantina = funcionarioCantina;
	}
	
}
