package enumeradores;

public enum TipoGeradorLote {

	ORDEM_COMPRA("OC"), ORDEM_PRODUCAO("OP");
	
	private String tipo;
	
	TipoGeradorLote(){
		
	}
	
	TipoGeradorLote(String tipo){
		this.setTipo(tipo);
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
}
