package enumeradores;

public enum TipoProduto {

	REVENDA("RV", "Revenda"), PRODUCAO("PR",  "Produção"), MATERIA_PRIMA("MP", "Matéria-Prima");

	private String tipoProduto;
	private String descricao;
	
	TipoProduto(){
		
	}

	TipoProduto(String tipoProduto, String descricao){
		this.tipoProduto = tipoProduto;
		this.descricao = descricao;
	}

	public String getTipoProduto() {
		return tipoProduto;
	}
	
	public String getDescricao(){
		
		return descricao;
		
	}
	
}
