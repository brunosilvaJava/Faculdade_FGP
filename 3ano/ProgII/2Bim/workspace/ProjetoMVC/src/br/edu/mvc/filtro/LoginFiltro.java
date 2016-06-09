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

	Filtro intercepta requisições 
	Filtro não é Servlet 
	Filtro analisa url 

	Tem 3 ciclos de vida 
	
	INIT - É chamado na primeira vez que o filtro é chamado 
	DESTROY - É chamado quando dá erro de acesso 	
	CHAIN - É chamado todaz vez que é interceptado uma página, que é feita uma requisição

*/

/**
 * Filtro responsável por filtrar todas as requisições feitas às paginas dentro da pasta 'pages'.
 * Verifica se o usuário está logado, se estiver encaminha a requisição para a Servlet responsável,
 * caso contrário retorna a página login.jsp para o cliente (browser).
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
	 * As requisições feitas à URN '\pages\*' são interceptadas pelo LoginFiltro e caem neste método.
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
