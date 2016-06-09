package br.edu.mvc.bo;

import br.edu.mvc.dao.UsuarioDAO;
import br.edu.mvc.utils.UtilFuncoes;
import br.edu.mvc.vo.UsuarioVO;

/**
 * Arquitetura MVC - Classe BO (Business Object)
 * Classe BO da tabela Usu�rio.
 * Respons�vel pelas regras de neg�cio relacionadas ao Usuario.
 * 
 * @author bruno.silva
 *
 */
public class UsuarioBO {

	private UsuarioDAO usuarioDao;
	
	{
		usuarioDao = new UsuarioDAO();
	}
	
	/**
	 * M�todo respons�vel por validar as regras de neg�cio relacionadas ao login do usu�rio. <br />
	 * 
	 * Valida se os dados de login e senha n�o est�o vazios. Se estiverem vazios, retorna null, <br />
	 * sen�o busca no banco um usu�rio correspondente aos dados de login e senha informados.
	 * 
	 * @param usuarioEntrada - Recebe uma inst�ncia de UsuarioVO com os dados de login e senha informados.
	 * @return UsuarioVO - Retorna o usu�rio correspondente aos dados de usu�rio e senha informados, caso n�o encontre retorna null.
	 */
	public UsuarioVO confirmarLogin(UsuarioVO usuarioEntrada){
		
		// vari�vel de Usuario que o m�todo ir� retornar na linha 61
		
		UsuarioVO usuarioRetorno = null;

		// Pega os dados login e senha do par�metro usuarioEntrada passado no m�todo
		
		String login = usuarioEntrada.getLogin();
		String senha = usuarioEntrada.getSenha();
		
		// Verifica se os dados de login e senha n�o est�o v�zios, utilizando o m�todo isValorVazio da classe UtilFuncoes
		
		if(!UtilFuncoes.isValorVazio(login) && !UtilFuncoes.isValorVazio(senha)){
			
			// se n�o estiverem vazios, chama o m�todo confirmarLogin da classe UsuarioDAO passando como par�metro o usuarioEntrada
			// e coloca o retorno do m�todo na v�riavel usuarioRetorno (UsuarioVO). Obs: o retorno pode ser null caso n�o encontre nada no banco
			
			usuarioRetorno = usuarioDao.confirmarLogin(usuarioEntrada); 
			
		}
		else{
			
			// se estiver vazio ir� retornar null
			
			System.out.println("Login e(ou) Senha vazio! - UsuarioBO->confirmarLogin");
		
		}
				
		return usuarioRetorno;
		
	}
	
}
