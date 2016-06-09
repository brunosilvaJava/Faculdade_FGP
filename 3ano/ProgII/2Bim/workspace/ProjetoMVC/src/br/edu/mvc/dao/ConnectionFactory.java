package br.edu.mvc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * F�brica de Conex�o com o banco de dados.
 * Padr�o de projeto Factory.
 * 
 * @author bruno.silva
 *
 */
public class ConnectionFactory {

	private static ConnectionFactory fabricaConexao;
	private Connection conexao;
	
	// Construtor privado para que seja poss�vel inst�ncia apenas pelo m�todo getInstancia()
	private ConnectionFactory() {
	}
	
	/**
	 * Este m�todo � respons�vel por retornar uma inst�ncia de ConnectionFactory. <br/>
	 * Padr�o de projeto Singleton - uma inst�ncia apenas da classe, s� � poss�vel obter uma inst�ncia da classe atrav�s deste m�todo.
	 * @return ConnectionFactory - retorna uma inst�ncia da classe ConnectionFactory.
	 */
	public static ConnectionFactory getInstancia(){
		if(fabricaConexao == null){
			return new ConnectionFactory();
		}
		return fabricaConexao;
	}
	
	/**
	 * Este m�todo � respons�vel por retornar a conex�o com o banco de dados, atrav�s do m�todo getConnection da classe Driver Manager <br/>
	 * 
	 * @return Connection - Retorna uma inst�ncia de Connection (conex�o com o banco de dados)
	 * @throws ClassNotFoundException - Lan�a uma ClassNotFoundException caso n�o encontrar o Driver de conex�o com o banco
	 * @throws SQLException - Lan�a uma SQL Exception caso ocorra um erro de SQL no banco
	 */
	public Connection getConexao() throws ClassNotFoundException, SQLException{
	
		// Driver de conex�o com o banco
		Class.forName("com.mysql.jdbc.Driver"); 
		
		// DriverManager.getConnection - Estabele e retorna a conex�o com o banco de dados.
		conexao = DriverManager.getConnection("jdbc:mysql://localhost/loja", "root", ""); 
		 
		return conexao;
		 
	}
	
	
}
