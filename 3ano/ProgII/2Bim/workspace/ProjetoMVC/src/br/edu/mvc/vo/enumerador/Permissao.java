package br.edu.mvc.vo.enumerador;

/**
 * Este enum é responsável por armazenar os tipos de permissões possíveis no sistema, 
 * além dos métodos referentes às estas permissões. 
 * 
 * @author bruno.silva *
 */
public enum Permissao {
	
	/** Permissão de Administrador do sistema, valor = 'admin' */
	ADMIN(Perm.ADMINISTRADOR);
	
	private String permissao;
	
	private Permissao(String permissao) {		
		this.permissao = permissao;		
	}
	
	/**
	 * Método utilizado para receber uma String com o valor da permissão e retornar o enum relacionado a ela.
	 * @param permissao - tipo String
	 * @return Permissao - retorna o enum referente à permissão informada, se não houver retorna null
	 */
	public static Permissao verificarPermissao(String permissao){
				
		switch (permissao) {
		
		case Perm.ADMINISTRADOR:
			return ADMIN;
				
		default:
			return null;
		}
		
	}
	
	/**
	 * Método responsável por retornar o valor do enum.
	 * @return String - retorna o valor do enum em String
	 */
	public String getPermissao() {
		return permissao;
	}
	
	/**
	 * Classe interna Perm - feita exclusivamente para conter cosntantes que representem os valores dos enums de Permissao
	 * @author bruno.silva
	 *
	 */
	private static class Perm{
		/** Constante que representa o valor do enum ADMIN - valor = 'admin' */
		private static final String ADMINISTRADOR = "admin";
	}

}
