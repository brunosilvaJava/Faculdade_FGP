package br.edu.mvc.filtro;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static br.edu.mvc.utils.UtilFuncoes.*;

/* 	// *** FILTRO

	Filtro intercepta requisi��es 
	Filtro n�o � Servlet 
	Filtro analisa url 

	Tem 3 ciclos de vida 
	
	INIT - � chamado na primeira vez que o filtro � chamado 
	DESTROY - � chamado quando d� erro de acesso 	
	CHAIN - � chamado todaz vez que � interceptado uma p�gina, que � feita uma requisi��o

*/

/**
 * Filtro respons�vel por filtrar todas as requisi��es feitas �s paginas dentro da pasta 'pages'.
 * Verifica se o usu�rio est� logado, se estiver encaminha a requisi��o para a Servlet respons�vel,
 * caso contr�rio retorna a p�gina login.jsp para o cliente (browser).
 * 
 * @author bruno.silva
 *
 */
public class LoginFiltro implements Filter{

	@Override
	public void init(FilterConfig config) throws ServletException {}
	@Override
	public void destroy() {}

	/**
	 * As requisi��es feitas � URN '\pages\*' s�o interceptadas pelo LoginFiltro e caem neste m�todo.
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpSession sessao = request.getSession();
		
		if(sessao.getAttribute(USUARIO_SESSAO) != null){
			chain.doFilter(request, response);
		}
		else{
			response.sendRedirect(request.getContextPath() + LOGIN_JSP);
		}
		
	}

}
