package br.edu.mvc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Fábrica de Conexão com o banco de dados.
 * Padrão de projeto Factory.
 * 
 * @author bruno.silva
 *
 */
public class ConnectionFactory {

	private static ConnectionFactory fabricaConexao;
	private Connection conexao;
	
	// Construtor privado para que seja possível instância apenas pelo método getInstancia()
	private ConnectionFactory() {
	}
	
	/**
	 * Este método é responsável por retornar uma instância de ConnectionFactory. <br/>
	 * Padrão de projeto Singleton - uma instância apenas da classe, só é possível obter uma instância da classe através deste método.
	 * @return ConnectionFactory - retorna uma instância da classe ConnectionFactory.
	 */
	public static ConnectionFactory getInstancia(){
		if(fabricaConexao == null){
			return new ConnectionFactory();
		}
		return fabricaConexao;
	}
	
	/**
	 * Este método é responsável por retornar a conexão com o banco de dados, através do método getConnection da classe Driver Manager <br/>
	 * 
	 * @return Connection - Retorna uma instância de Connection (conexão com o banco de dados)
	 * @throws ClassNotFoundException - Lança uma ClassNotFoundException caso não encontrar o Driver de conexão com o banco
	 * @throws SQLException - Lança uma SQL Exception caso ocorra um erro de SQL no banco
	 */
	public Connection getConexao() throws ClassNotFoundException, SQLException{
	
		// Driver de conexão com o banco
		Class.forName("com.mysql.jdbc.Driver"); 
		
		// DriverManager.getConnection - Estabele e retorna a conexão com o banco de dados.
		conexao = DriverManager.getConnection("jdbc:mysql://localhost/loja", "root", ""); 
		 
		return conexao;
		 
	}
	
	
}
