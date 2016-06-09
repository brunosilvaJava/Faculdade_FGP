package br.edu.mvc.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.mvc.bo.UsuarioBO;
import br.edu.mvc.vo.UsuarioVO;
import static br.edu.mvc.utils.UtilFuncoes.*; // importação estática, mais interessante para o uso de constantes

/**
 * Servlet responsável por receber as requisições enviadas para a URN '/login'. (Ver web.xml)
 * 
 * Caso os dados de login estejam corretos, a servlet envia a requisição para a página home.jsp,
 * se estiverem incorretos, responde ao cliente(browser) a página login.jsp
 * 
 * @author bruno.silva
 *
 */
public class LoginController extends HttpServlet {

	// Classe responsável pelas regras de negócio relacionadas a Usuario
	private UsuarioBO usuarioBo;

	{
		usuarioBo = new UsuarioBO();
	}
	
	/*
	 * XXX - *** CICLO DE VIDA SERVLET - EXPLICAÇÃO GERAL
	 * 
	 * FASE 1 - INICIAÇÃO - executa método 'init'
	 * 		- Instância o Servlet.
	 * 			- O momento da criação do Servlet é definida através do parâmtro load-on-startup no Deployment Descriptor (web.xml).
	 * 		- É executado apenas uma vez, no momento da criação da Servlet.
	 * 
	 * FASE 2 - ATENDIMENTO DE REQUISIÇÕES - executa método 'service'
	 * 		- Atende as requisições.
	 * 			- Quando a Servlet recebe uma requisição, este método é executado.
	 * 		- Permanece nesta fase enquanto o Servidor (Tomcat) estiver ativo e a Aplicação (Sistema) do Servlet estiver carregada.
	 * 		- Na classe HttpServlet, o método service verifica se a requisição HTTP é do tipo GET ou POST, para chamar o método 'doGet' ou 'doPost'
	 * 
	 * FASE 3 - FINALIZAÇÃO - executa o método destroy
	 * 		- Finaliza o Servlet.
	 * 			- O Servlet é finalizado quando o Servidor(Tomcat) é finalizado ou quando a Aplicação(Sistema) torna-se inativa pelo ServletContainer.
	 * 
	 */
	
	/**
	 * MÉTODO INIT
	 * 
	 * <n>OBS:</n> 
	 * Caso não desejar alterar o comportamento deste método, não é necessário colocá-lo na Servlet.
	 * Está aqui apenas para explicação.
	 *  
	 * XXX - *** CICLO DE VIDA SERVLET - FASE 1 - INICIALIZAÇÃO - MÉTODO 'INIT'
	 * 
	 * FASE 1 - INICIALIZAÇÃO - executa o método 'init'
	 * 
	 * Servlet é carregada(instânciada) pelo 'Servlet Container' de acordo com o valor 
	 * passado no parâmetro 'load-on-statup', no web.xml (Deployment Descriptor) -> ver web.xml - linha 21.
	 * Se o valor for positivo, é instânciada junto da aplicação, na ordem crescente (cada servlet tem seu parâmetro 'load-on-statup')
	 * Se o valor for 0, é instânciada na primeira requisição recebida por este Servlet.
	 */
	
	@Override
	public void init() throws ServletException {
		
		System.out.println(" *** CICLO DE VIDA SERVLET - FASE 1 - INICIALIZAÇÃO - MÉTODO 'INIT'");
		
		// chamando o método init da classe pai (HttpServlet) para que a funcionalidade do método continue a mesma.
		super.init();
	}

	/**
	 * MÉTODO SERVICE
	 * 
	 * Em toda requisição realizada, é chamado o método service, por padrão, o método service da classe HttpServlet verifica
	 * se a requisição foi via GET ou por via POST e então chama o método doGet ou o método doPost. <br/>
	 * 
	 * <n>OBS:</n> 
	 * Caso não desejar alterar o comportamento deste método, não é necessário colocá-lo na Servlet.
	 * Está aqui apenas para explicação.
	 * 
	 * 
	 * XXX - *** CICLO DE VIDA SERVLET - FASE 2 - ATENDIMENTO DE REQUISIÇÕES - MÉTODO 'SERVICE'
	 * 
	 * FASE 2 - ATENDIMENTO DE REQUISIÇÕES - Executa o método 'service'
	 * 
	 * É executada sempre que é feita uma requisição para esta Servlet
	 * Enquanto o Servidor (Tomcat) estiver ativo e a Aplicação (Sistema) estiver carregado, este permanecerá na fase 2. 
	 * 
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println(" *** CICLO DE VIDA SERVLET - FASE 2 - ATENDIMENTO DE REQUISIÇÕES - MÉTODO 'SERVICE'");
		
		// chamando o método service da classe pai (HttpServlet) para que a funcionalidade do método continue a mesma.
		super.service(request, response);
	}
	
	/**
	 * MÉTODO DESTROY
	 * 
	 * Método utilizado para destruir a Servlet
	 * 
	 * <n>OBS:</n> 
	 * Caso não desejar alterar o comportamento deste método, não é necessário colocá-lo na Servlet.
	 * Está aqui apenas para explicação.
	 * 
	 * 
	 * XXX - *** CICLO DE VIDA SERVLET - FASE 3 - FINALIZAÇÃO - MÉTODO 'DESTROY'
	 * 
	 * FASE 3 - FINALIZAÇÃO - Executa o método destroy
	 * 
	 * É executado quando o Servidor (Tomcat) é finalizado ou quando a Aplicação (Sistema) torna-se inativa pelo ServletContainer
	 * 
	 */
	@Override
	public void destroy() {		
		
		System.out.println(" *** CICLO DE VIDA SERVLET - FASE 3 - FINALIZAÇÃO - MÉTODO 'DESTROY'");		
		
		// chamando o método destroy da classe pai (HttpServlet) para que a funcionalidade do método continue a mesma.
		super.destroy();
	}
	
	/**
	 * Método responsável por receber as requisições HTTP do tipo GET
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		// Buscando no request o parâmetro 'acao' (se houver) enviado pelo cliente (browser)
		// Ver a linha 16 da página home: link 'Sair do Sistema'
		// <a href="${pageContext.request.contextPath}/login?acao=sairSistema">Sair do Sistema</a>
		
		String acao = request.getParameter(PARAM_ACAO);
		
		// Pegando a sessao
		
		HttpSession sessao = request.getSession();
		
		// Por padrão a página de resposta é a home.jsp
		
		String paginaResposta = HOME_JSP;
		
		// Se a sessão for nula, ou a variável acao for igual a 'sairSistema', a sessão é encerrada e a página de retorna será a login.jsp
		
		if(sessao.getAttribute(USUARIO_SESSAO) == null || (acao != null && acao.equals(SAIR_SISTEMA))){
			sessao = request.getSession();
			sessao.invalidate();
			paginaResposta = LOGIN_JSP;
		}
		
		// Redireciona para a página de resposta definida acima
		
		response.sendRedirect(request.getContextPath() + paginaResposta);

	} // fim do método doGet
	

	/**
	 * Método responsável por receber as requisições HTTP do tipo POST
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Pegando a sessao
		
		HttpSession sessao = request.getSession();
		
		// Por padrão a página de resposta é a home.jsp
		
		String paginaRetorno = HOME_JSP;
		
		// Objeto para incluir os dados de login e senha recebidos na requisição
		
		UsuarioVO usuarioRequest = null;
		
		// Objeto para incluir o usuário retornado do banco de dados, referente ao login e senha recebidos na requisição 
		
		UsuarioVO usuarioResponse = null;
		
		// Classe responsável por dispachar a requisição
		
		RequestDispatcher requestDispatcher = null;

		// criando o usuário para incluir os dados de login e senha recebidos na requisição
		
		usuarioRequest = new UsuarioVO();
		usuarioRequest.setLogin(request.getParameter(LOGIN));
		usuarioRequest.setSenha(request.getParameter(SENHA));

		// Através do método confirmarLogin da classe UsuarioBO, valida os dados de login e senha informados, 
		// retorna e atribui ao usuarioResponse o usuario buscado no banco
		// se os dados não foram informados ou foram informados incorretamente, retorna null
		
		usuarioResponse = usuarioBo.confirmarLogin(usuarioRequest);

		// se o usuarioResponse for null, significa se os dados informados não correspondem ao usuário no banco de dados
		// então atribui à paginaRetorno a página de login.jsp e insere na requisição o atributo 'usuarioInvalido' com o valor 'falha' 
		// se não for null, segnifica que o usuário foi encontrado no banco, e insere na sessão o atributo 'usuarioSessao', com o 'usuarioResponse' (usuario encontrado no banco)
		
		if (usuarioResponse == null) {
			paginaRetorno = LOGIN_JSP;
			request.setAttribute(USUARIO_INVALIDO, FLAG_FALHA); // inserindo na requisição atributo de usuarioInvalido
		} else {
			sessao.setAttribute(USUARIO_SESSAO, usuarioResponse); // inserindo usuario na sessao com o nome de usuarioSessao e o valor sendo o objeto UsuarioResponse
		}

		// Através do método getRequestDispatcher retorna uma instância da classe RequestDispatcher com a página que será retornada ao cliente (browser) -> linha 170
		// e 'dispacha' a requisição para a página informada -> linha 171
		requestDispatcher = request.getRequestDispatcher(paginaRetorno);
		requestDispatcher.forward(request, response);

	} // fim do método doPost

}
