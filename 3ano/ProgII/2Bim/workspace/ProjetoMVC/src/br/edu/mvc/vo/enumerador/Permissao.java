package br.edu.mvc.vo.enumerador;

/**
 * Este enum � respons�vel por armazenar os tipos de permiss�es poss�veis no sistema, 
 * al�m dos m�todos referentes �s estas permiss�es. 
 * 
 * @author bruno.silva *
 */
public enum Permissao {
	
	/** Permiss�o de Administrador do sistema, valor = 'admin' */
	ADMIN(Permissao.ADMINISTRADOR);
	
	private static final String ADMINISTRADOR = "admin";
	private String permissao;
	
	private Permissao(String permissao) {		
		this.permissao = permissao;		
	}
	
	/**
	 * M�todo utilizado para receber uma String com o valor da permiss�o e retornar o enum relacionado a ela.
	 * @param permissao - tipo String
	 * @return Permissao - retorna o enum referente � permiss�o informada, se n�o houver retorna null
	 */
	public static Permissao verificarPermissao(String permissao){
				
		switch (permissao) {
		
		case Permissao.ADMINISTRADOR:
			return ADMIN;
				
		default:
			return null;
		}
		
	}
	
	/**
	 * M�todo respons�vel por retornar o valor do enum.
	 * @return String - retorna o valor do enum em String
	 */
	public String getPermissao() {
		return permissao;
	}

}
