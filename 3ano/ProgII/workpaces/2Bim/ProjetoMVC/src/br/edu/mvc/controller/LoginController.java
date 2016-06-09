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
import static br.edu.mvc.utils.UtilFuncoes.*; // importa��o est�tica, mais interessante para o uso de constantes

/**
 * Servlet respons�vel por receber as requisi��es enviadas para a URN '/login'. (Ver web.xml)
 * 
 * Caso os dados de login estejam corretos, a servlet envia a requisi��o para a p�gina home.jsp,
 * se estiverem incorretos, responde ao cliente(browser) a p�gina login.jsp
 * 
 * @author bruno.silva
 *
 */
public class LoginController extends HttpServlet {

	// Classe respons�vel pelas regras de neg�cio relacionadas a Usuario
	private UsuarioBO usuarioBo;

	{
		usuarioBo = new UsuarioBO();
	}
	
	/*
	 * XXX - *** CICLO DE VIDA SERVLET - EXPLICA��O GERAL
	 * 
	 * FASE 1 - INICIA��O - executa m�todo 'init'
	 * 		- Inst�ncia o Servlet.
	 * 			- O momento da cria��o do Servlet � definida atrav�s do par�mtro load-on-startup no Deployment Descriptor (web.xml).
	 * 		- � executado apenas uma vez, no momento da cria��o da Servlet.
	 * 
	 * FASE 2 - ATENDIMENTO DE REQUISI��ES - executa m�todo 'service'
	 * 		- Atende as requisi��es.
	 * 			- Quando a Servlet recebe uma requisi��o, este m�todo � executado.
	 * 		- Permanece nesta fase enquanto o Servidor (Tomcat) estiver ativo e a Aplica��o (Sistema) do Servlet estiver carregada.
	 * 		- Na classe HttpServlet, o m�todo service verifica se a requisi��o HTTP � do tipo GET ou POST, para chamar o m�todo 'doGet' ou 'doPost'
	 * 
	 * FASE 3 - FINALIZA��O - executa o m�todo destroy
	 * 		- Finaliza o Servlet.
	 * 			- O Servlet � finalizado quando o Servidor(Tomcat) � finalizado ou quando a Aplica��o(Sistema) torna-se inativa pelo ServletContainer.
	 * 
	 */
	
	/**
	 * M�TODO INIT
	 * 
	 * <n>OBS:</n> 
	 * Caso n�o desejar alterar o comportamento deste m�todo, n�o � necess�rio coloc�-lo na Servlet.
	 * Est� aqui apenas para explica��o.
	 *  
	 * XXX - *** CICLO DE VIDA SERVLET - FASE 1 - INICIALIZA��O - M�TODO 'INIT'
	 * 
	 * FASE 1 - INICIALIZA��O - executa o m�todo 'init'
	 * 
	 * Servlet � carregada(inst�nciada) pelo 'Servlet Container' de acordo com o valor 
	 * passado no par�metro 'load-on-statup', no web.xml (Deployment Descriptor) -> ver web.xml - linha 21.
	 * Se o valor for positivo, � inst�nciada junto da aplica��o, na ordem crescente (cada servlet tem seu par�metro 'load-on-statup')
	 * Se o valor for 0, � inst�nciada na primeira requisi��o recebida por este Servlet.
	 */
	
	@Override
	public void init() throws ServletException {
		
		System.out.println(" *** CICLO DE VIDA SERVLET - FASE 1 - INICIALIZA��O - M�TODO 'INIT'");
		
		// chamando o m�todo init da classe pai (HttpServlet) para que a funcionalidade do m�todo continue a mesma.
		super.init();
	}

	/**
	 * M�TODO SERVICE
	 * 
	 * Em toda requisi��o realizada, � chamado o m�todo service, por padr�o, o m�todo service da classe HttpServlet verifica
	 * se a requisi��o foi via GET ou por via POST e ent�o chama o m�todo doGet ou o m�todo doPost. <br/>
	 * 
	 * <n>OBS:</n> 
	 * Caso n�o desejar alterar o comportamento deste m�todo, n�o � necess�rio coloc�-lo na Servlet.
	 * Est� aqui apenas para explica��o.
	 * 
	 * 
	 * XXX - *** CICLO DE VIDA SERVLET - FASE 2 - ATENDIMENTO DE REQUISI��ES - M�TODO 'SERVICE'
	 * 
	 * FASE 2 - ATENDIMENTO DE REQUISI��ES - Executa o m�todo 'service'
	 * 
	 * � executada sempre que � feita uma requisi��o para esta Servlet
	 * Enquanto o Servidor (Tomcat) estiver ativo e a Aplica��o (Sistema) estiver carregado, este permanecer� na fase 2. 
	 * 
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println(" *** CICLO DE VIDA SERVLET - FASE 2 - ATENDIMENTO DE REQUISI��ES - M�TODO 'SERVICE'");
		
		// chamando o m�todo service da classe pai (HttpServlet) para que a funcionalidade do m�todo continue a mesma.
		super.service(request, response);
	}
	
	/**
	 * M�TODO DESTROY
	 * 
	 * M�todo utilizado para destruir a Servlet
	 * 
	 * <n>OBS:</n> 
	 * Caso n�o desejar alterar o comportamento deste m�todo, n�o � necess�rio coloc�-lo na Servlet.
	 * Est� aqui apenas para explica��o.
	 * 
	 * 
	 * XXX - *** CICLO DE VIDA SERVLET - FASE 3 - FINALIZA��O - M�TODO 'DESTROY'
	 * 
	 * FASE 3 - FINALIZA��O - Executa o m�todo destroy
	 * 
	 * � executado quando o Servidor (Tomcat) � finalizado ou quando a Aplica��o (Sistema) torna-se inativa pelo ServletContainer
	 * 
	 */
	@Override
	public void destroy() {		
		
		System.out.println(" *** CICLO DE VIDA SERVLET - FASE 3 - FINALIZA��O - M�TODO 'DESTROY'");		
		
		// chamando o m�todo destroy da classe pai (HttpServlet) para que a funcionalidade do m�todo continue a mesma.
		super.destroy();
	}
	
	/**
	 * M�todo respons�vel por receber as requisi��es HTTP do tipo GET
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		// Buscando no request o par�metro 'acao' (se houver) enviado pelo cliente (browser)
		// Ver a linha 16 da p�gina home: link 'Sair do Sistema'
		// <a href="${pageContext.request.contextPath}/login?acao=sairSistema">Sair do Sistema</a>
		
		String acao = request.getParameter(PARAM_ACAO);
		
		// Pegando a sessao
		
		HttpSession sessao = request.getSession();
		
		// Por padr�o a p�gina de resposta � a home.jsp
		
		String paginaResposta = HOME_JSP;
		
		// Se a sess�o for nula, ou a vari�vel acao for igual a 'sairSistema', a sess�o � encerrada e a p�gina de retorna ser� a login.jsp
		
		if(sessao.getAttribute(USUARIO_SESSAO) == null || (acao != null && acao.equals(SAIR_SISTEMA))){
			sessao = request.getSession();
			sessao.invalidate();
			paginaResposta = LOGIN_JSP;
		}
		
		// Redireciona para a p�gina de resposta definida acima
		
		response.sendRedirect(request.getContextPath() + paginaResposta);

	} // fim do m�todo doGet
	

	/**
	 * M�todo respons�vel por receber as requisi��es HTTP do tipo POST
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Pegando a sessao
		
		HttpSession sessao = request.getSession();
		
		// Por padr�o a p�gina de resposta � a home.jsp
		
		String paginaRetorno = HOME_JSP;
		
		// Objeto para incluir os dados de login e senha recebidos na requisi��o
		
		UsuarioVO usuarioRequest = null;
		
		// Objeto para incluir o usu�rio retornado do banco de dados, referente ao login e senha recebidos na requisi��o 
		
		UsuarioVO usuarioResponse = null;
		
		// Classe respons�vel por dispachar a requisi��o
		
		RequestDispatcher requestDispatcher = null;

		// criando o usu�rio para incluir os dados de login e senha recebidos na requisi��o
		
		usuarioRequest = new UsuarioVO();
		usuarioRequest.setLogin(request.getParameter(LOGIN));
		usuarioRequest.setSenha(request.getParameter(SENHA));

		// Atrav�s do m�todo confirmarLogin da classe UsuarioBO, valida os dados de login e senha informados, 
		// retorna e atribui ao usuarioResponse o usuario buscado no banco
		// se os dados n�o foram informados ou foram informados incorretamente, retorna null
		
		usuarioResponse = usuarioBo.confirmarLogin(usuarioRequest);

		// se o usuarioResponse for null, significa se os dados informados n�o correspondem ao usu�rio no banco de dados
		// ent�o atribui � paginaRetorno a p�gina de login.jsp e insere na requisi��o o atributo 'usuarioInvalido' com o valor 'falha' 
		// se n�o for null, segnifica que o usu�rio foi encontrado no banco, e insere na sess�o o atributo 'usuarioSessao', com o 'usuarioResponse' (usuario encontrado no banco)
		
		if (usuarioResponse == null) {
			paginaRetorno = LOGIN_JSP;
			request.setAttribute(USUARIO_INVALIDO, FLAG_FALHA); // inserindo na requisi��o atributo de usuarioInvalido
		} else {
			sessao.setAttribute(USUARIO_SESSAO, usuarioResponse); // inserindo usuario na sessao com o nome de usuarioSessao e o valor sendo o objeto UsuarioResponse
		}

		// Atrav�s do m�todo getRequestDispatcher retorna uma inst�ncia da classe RequestDispatcher com a p�gina que ser� retornada ao cliente (browser) -> linha 170
		// e 'dispacha' a requisi��o para a p�gina informada -> linha 171
		requestDispatcher = request.getRequestDispatcher(paginaRetorno);
		requestDispatcher.forward(request, response);

	} // fim do m�todo doPost

}
