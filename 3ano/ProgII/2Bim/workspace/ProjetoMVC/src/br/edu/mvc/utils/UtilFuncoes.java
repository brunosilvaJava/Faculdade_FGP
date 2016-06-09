package br.edu.mvc.utils;

/**
 * Classe respons�vel por conter constantes e m�todos utilizados em v�rias partes do sistema
 * 
 * @author bruno.silva
 *
 */
public class UtilFuncoes {

	// Constantes
	public static final String LOGIN_JSP = "/login.jsp";
	public static final String HOME_JSP = "/pages/home.jsp";
	public static final String LOGIN = "login";
	public static final String SENHA = "senha";
	public static final String SAIR_SISTEMA = "sairSistema";
	public static final String PARAM_ACAO = "acao";
	public static final String USUARIO_SESSAO = "usuarioSessao";
	public static final String USUARIO_INVALIDO = "usuarioInvalido";
	public static final String FLAG_FALHA = "falha";
	
	/**
	 * Este m�todo � respons�vel por verificar se o valor da String � vazio ou nulo.
	 * 
	 * @param valor - tipo String 
	 * @return boolean - retorna true se o valor for vazio ou nulo e true se houver algum valor. 
	 */
	public static boolean isValorVazio(String valor){
		if(valor == null || valor.trim().equals("")){
			return true;
		}
		return false;		
	}
	
}
