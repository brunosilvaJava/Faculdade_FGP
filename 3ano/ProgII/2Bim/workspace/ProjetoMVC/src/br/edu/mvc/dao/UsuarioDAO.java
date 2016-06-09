package br.edu.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.mvc.vo.UsuarioVO;
import br.edu.mvc.vo.enumerador.Permissao;

/**
 * Arquitetura MVC - Classe DAO (Data Access Object)
 * Classe DAO da tabela Usu�rio.
 * Respons�vel pelo acesso � tabela Usuario do banco de dados.
 * 
 * @author bruno.silva
 *
 */
public class UsuarioDAO {

	/** Classe respons�vel por estabelecer conex�o com o banco de dados e retornar a conex�o atrav�s do m�todo getConexao() */
	private ConnectionFactory fabricaConexao;
	
	/** Classe respons�vel por guardar a conex�o com o banco de dados */
	private Connection conexao;
	
	/** Classe respons�vel por executar as querys no banco de dados */
	private PreparedStatement pstm;
	
	/** Classe respons�vel por guardar o resultado da busca no banco de dados */
	private ResultSet rs;

	// Bloco de Inicializa��o -> � executado no momento em que a classe � inst�nciada
	{
		fabricaConexao = ConnectionFactory.getInstancia();
	}
	
	/**
	 * M�todo respons�vel por confirmar se os dados de usuario e senha est�o corretos.
	 * @param usuario - recebe uma inst�ncia de usuario com os dados de login e senha.
	 * @return UsuarioVO - caso os dados estejam corretos, retorna uma inst�ncia com os dados do usu�rio no banco.
	 */
	public UsuarioVO confirmarLogin(UsuarioVO usuario){
		
		// Usu�rio que o m�todo ir� retornar na linha 90
		UsuarioVO u = null;
		
		try {		
			
			// fabricaConexao - retorna conex�o com o banco
			
				conexao = fabricaConexao.getConexao(); 
			
			// conexao.prepareStatement - retorna uma inst�ncia de PreparedStatement com a query desejada, neste caso um select
		
				pstm = conexao.prepareStatement("select * from usuario where login=? and senha=?");  

			// atrav�s da inst�ncia de PreparedStatement, informamos o par�metros(as 'Strings' login e senha) para o select, na sequ�ncia informada na query
			
				pstm.setString(1, usuario.getLogin());
				pstm.setString(2, usuario.getSenha());
			
			// pstm.executeQuery - executa a query (select) desejada e retorna uma inst�ncia de ResultSet, o ResultSet guarda a lista com os dados obtidos no banco
			// se a query estiver incorreta, lan�a a SQLException, tratada no catch da linha 85
			
				rs = pstm.executeQuery();
			
			// rs.next - verifica se tem um pr�ximo item na lista, se tiver retorna true
				
				if(rs.next()){
					
					// Caso tenha um pr�ximo, � realizada a cria��o de uma inst�ncia de UsuarioVO e os dados obtidos no banco s�o inseridos nos atributos da inst�ncia
						
						u = new UsuarioVO();
						u.setCodigo(rs.getInt("codigo")); // rs.getInt("codigo") retorna o c�dido(int) obtido no banco e insere no atributo c�digo
						u.setLogin(rs.getString("login"));
						u.setSenha(rs.getString("senha"));
						u.setPermissao(Permissao.verificarPermissao(rs.getString("permissao")));
					
				}
		
		} catch (ClassNotFoundException e) {
			System.out.println("Driver do banco n�o encontrado."); //
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Ocorreu uma excess�o de SQL"); // query � executada na linha 66. atrav�s do m�todo 'pstm.executeQuery()'
			e.printStackTrace();
		}
	
		return u;
		
	}
	
}
