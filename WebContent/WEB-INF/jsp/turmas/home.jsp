<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=utf-8" language="java"
import="java.sql.*" errorPage="" %>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>

<body>

	<div>
		<a href="<c:url value="/turmas/lista"/>">Lista Tudo</a>
		<a href="<c:url value="/turmas/cadastro"/>">Cadastra nova Disciplina</a>
		<a href="<c:url value="/turmas/remocao"/>">Remove Disciplina</a>
		<a href="<c:url value="/turmas/alteracao"/>">Altera Dados</a>
		<br/>
	</div>
</body>

</html>