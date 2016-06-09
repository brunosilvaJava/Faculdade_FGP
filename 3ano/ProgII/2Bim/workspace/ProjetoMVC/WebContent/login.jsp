<!-- 
	*** DIRETIVAS 
	-- Uma diretiva define atributos específicos a serem incluídos dentro de páginas JSP
	-- Estrutura: <'%@ diretiva nomeAtributo1="valorAtributo1" nomeAtributo1="valorAtributo2" %> 
-->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<!-- 
	*** JSTL    
	-- Suporta tarefas estruturais como iteração e condições, tags para manipulação de documentos XML, internacionalização e SQL;
	-- Tags
		-- core: Core - funcionalidades de looping, expressões e input/output básicos
		-- fmt: Formatting - formatação de dados
-->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login - Trabalho MVC</title>
</head>
<body>

	<!-- 
			*** O atributo 'action' do form serve para definir a para onde os parâmetros serão enviados 
			*** O atributo 'method' do form serve para definir o tipo da requisição HTTP enviada (GET/POST). 
	-->
	<form action="login" method="post">
		
		<!-- *** Os names dos campos servem para identificar o parâmetro no envio da requisição ao servidor -->
		
		<c:out value="Login: " /> <input type="text" name="login" /> <br />
		<c:out value="Senha: " />  <input type="password" name="senha" />
		<p><input type="submit" value="Entrar" /></p>
		
		<!-- Verifica se o parâmetro usuarioInvalido não é vazio - ver LoginController / linha 213 -->
		<c:if test="${not empty usuarioInvalido}">
			<p style="color: red">Usuário e(ou) senha inválido(s).</p>		
		</c:if>
		
	</form>
	
	<div style="position: absolute; bottom: 0; color: blue;">	
	
		<!-- c:out imprimi o value na tela -->
		<c:out value="Todos os Direitos Reservados - " />
		
		<!-- fmt:formatDate - formata a data no padrão dd/MM/aaaa -->
		<!-- scriptlet - <'%= new Date() %> -->
		<fmt:formatDate value="<%= new Date() %>"/>
		
	</div>

</body>
</html>