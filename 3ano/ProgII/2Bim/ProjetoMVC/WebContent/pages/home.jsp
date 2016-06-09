<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home - Trabalho MVC</title>
</head>
<body>

	<!-- ${exemplo} - Expression Language -->
	<h3><c:out value="Seja bem vindo ${usuarioLogado.login}" /></h3>
	
	<a href="${pageContext.request.contextPath}/login?acao=sairSistema">Sair do Sistema</a>
	
	<div style="position: absolute; bottom: 0; color: blue;">	
		<c:out value="Todos os Direitos Reservados - " />
		<fmt:formatDate value="<%= new Date() %>" />
	</div>

</body>
</html>