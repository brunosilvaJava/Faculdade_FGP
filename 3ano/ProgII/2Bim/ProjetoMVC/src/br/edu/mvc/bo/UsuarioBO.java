package br.edu.mvc.bo;

import br.edu.mvc.dao.UsuarioDAO;
import br.edu.mvc.utils.UtilFuncoes;
import br.edu.mvc.vo.UsuarioVO;

/**
 * Arquitetura MVC - Classe BO (Business Object)
 * Classe BO da tabela Usuário.
 * Responsável pelas regras de negócio relacionadas ao Usuario.
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
	 * Método responsável por validar as regras de negócio relacionadas ao login do usuário. <br />
	 * 
	 * Valida se os dados de login e senha não estão vazios. Se estiverem vazios, retorna null, <br />
	 * senão busca no banco um usuário correspondente aos dados de login e senha informados.
	 * 
	 * @param usuarioEntrada - Recebe uma instância de UsuarioVO com os dados de login e senha informados.
	 * @return UsuarioVO - Retorna o usuário correspondente aos dados de usuário e senha informados, caso não encontre retorna null.
	 */
	public UsuarioVO confirmarLogin(UsuarioVO usuarioEntrada){
		
		// variável de Usuario que o método irá retornar na linha 61
		
		UsuarioVO usuarioRetorno = null;

		// Pega os dados login e senha do parâmetro usuarioEntrada passado no método
		
		String login = usuarioEntrada.getLogin();
		String senha = usuarioEntrada.getSenha();
		
		// Verifica se os dados de login e senha não estão vázios, utilizando o método isValorVazio da classe UtilFuncoes
		
		if(!UtilFuncoes.isValorVazio(login) && !UtilFuncoes.isValorVazio(senha)){
			
			// se não estiverem vazios, chama o método confirmarLogin da classe UsuarioDAO passando como parâmetro o usuarioEntrada
			// e coloca o retorno do método na váriavel usuarioRetorno (UsuarioVO). Obs: o retorno pode ser null caso não encontre nada no banco
			
			usuarioRetorno = usuarioDao.confirmarLogin(usuarioEntrada); 
			
		}
		else{
			
			// se estiver vazio irá retornar null
			
			System.out.println("Login e(ou) Senha vazio! - UsuarioBO->confirmarLogin");
		
		}
				
		return usuarioRetorno;
		
	}
	
}
