package vo;

import enumeradores.TipoStatus;

public class StatusVO extends GenericVO{
	
	private Long idStatus;
	private String descricao;
	private TipoStatus tipoStatus;
	
	public StatusVO() {

	}

	@Override
	public String toString() {
		return descricao;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(obj instanceof StatusVO){
		
			StatusVO status = (StatusVO) obj;
			
			if(descricao == null){
				return false;
			}
			
			return status.getDescricao().equals(descricao);
			
		}
			
		return false;
	}
	
	@Override
	public int hashCode() {
		
		return this.descricao.hashCode();
		
	};
	
	public Long getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(Long idStatus) {
		this.idStatus = idStatus;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoStatus getTipoStatus() {
		return tipoStatus;
	}

	public void setTipoStatus(TipoStatus tipoStatus) {
		this.tipoStatus = tipoStatus;
	}
	
}
