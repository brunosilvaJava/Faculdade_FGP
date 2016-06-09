package br.edu.mvc.vo;

import br.edu.mvc.vo.enumerador.Permissao;

/**
 * Arquitetura MVC - Classe VO (View Object)
 * Classe VO da tabela Usu�rio.
 * Respons�vel pelos membros (atributos e m�todos) relacionados ao Usuario. 
 * � uma 'c�pia' da tabela Usuario no banco de dados.
 * 
 * @author bruno.silva
 *
 */
public class UsuarioVO {

	// Atributos
	
	private Integer codigo;
	private String login;
	private String senha;
	private Permissao permissao;
	
	// Construtor
	
	public UsuarioVO() {
	}

	public Integer getCodigo() {
		return codigo;
	}
	
	// M�todos

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Permissao getPermissao() {
		return permissao;
	}

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}
		
}
