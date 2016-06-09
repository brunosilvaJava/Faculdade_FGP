package br.edu.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.mvc.vo.UsuarioVO;
import br.edu.mvc.vo.enumerador.Permissao;

/**
 * Arquitetura MVC - Classe DAO (Data Access Object)
 * Classe DAO da tabela Usuário.
 * Responsável pelo acesso à tabela Usuario do banco de dados.
 * 
 * @author bruno.silva
 *
 */
public class UsuarioDAO {

	/** Classe responsável por estabelecer conexão com o banco de dados e retornar a conexão através do método getConexao() */
	private ConnectionFactory fabricaConexao;
	
	/** Classe responsável por guardar a conexão com o banco de dados */
	private Connection conexao;
	
	/** Classe responsável por executar as querys no banco de dados */
	private PreparedStatement pstm;
	
	/** Classe responsável por guardar o resultado da busca no banco de dados */
	private ResultSet rs;

	// Bloco de Inicialização -> é executado no momento em que a classe é instânciada
	{
		fabricaConexao = ConnectionFactory.getInstancia();
	}
	
	/**
	 * Método responsável por confirmar se os dados de usuario e senha estão corretos.
	 * @param usuario - recebe uma instância de usuario com os dados de login e senha.
	 * @return UsuarioVO - caso os dados estejam corretos, retorna uma instância com os dados do usuário no banco.
	 */
	public UsuarioVO confirmarLogin(UsuarioVO usuario){
		
		// Usuário que o método irá retornar na linha 90
		UsuarioVO u = null;
		
		try {		
			
			// fabricaConexao - retorna conexão com o banco
			
				conexao = fabricaConexao.getConexao(); 
			
			// conexao.prepareStatement - retorna uma instância de PreparedStatement com a query desejada, neste caso um select
		
				pstm = conexao.prepareStatement("select * from usuario where login=? and senha=?");  

			// através da instância de PreparedStatement, informamos o parâmetros(as 'Strings' login e senha) para o select, na sequência informada na query
			
				pstm.setString(1, usuario.getLogin());
				pstm.setString(2, usuario.getSenha());
			
			// pstm.executeQuery - executa a query (select) desejada e retorna uma instância de ResultSet, o ResultSet guarda a lista com os dados obtidos no banco
			// se a query estiver incorreta, lança a SQLException, tratada no catch da linha 85
			
				rs = pstm.executeQuery();
			
			// rs.next - verifica se tem um próximo item na lista, se tiver retorna true
				
				if(rs.next()){
					
					// Caso tenha um próximo, é realizada a criação de uma instância de UsuarioVO e os dados obtidos no banco são inseridos nos atributos da instância
						
						u = new UsuarioVO();
						u.setCodigo(rs.getInt("codigo")); // rs.getInt("codigo") retorna o códido(int) obtido no banco e insere no atributo código
						u.setLogin(rs.getString("login"));
						u.setSenha(rs.getString("senha"));
						u.setPermissao(Permissao.verificarPermissao(rs.getString("permissao")));
					
				}
		
		} catch (ClassNotFoundException e) {
			System.out.println("Driver do banco não encontrado."); //
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Ocorreu uma excessão de SQL"); // query é executada na linha 66. através do método 'pstm.executeQuery()'
			e.printStackTrace();
		}
	
		return u;
		
	}
	
}
