package enumeradores;

public enum TipoGeradorCompra {
	
	FUNCIONARIO_CANTINA("FC"), ORDEM_PRODUCAO("OP");
	
	private String tipo;
	
	TipoGeradorCompra(){
		
	}
	
	TipoGeradorCompra(String tipo){
		this.setTipo(tipo);
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
