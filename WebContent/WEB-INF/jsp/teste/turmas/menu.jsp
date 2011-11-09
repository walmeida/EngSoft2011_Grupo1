<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=utf-8" language="java"
import="java.sql.*" errorPage="" %>
<html>
<head>
<style type="text/css">
<%@ include file="/css/form.css" %>
</style>
<title>Academic Devoir</title>
</head>

<body>
		<div>
		<br/><br/>
		<form action="/academic-devoir/turmas/lista">
		<a href="<c:url value='/turmas/lista'/>">Listar turmas</a><br/>
		</form>
		<form action="/academic-devoir/turmas/cadastro">
		<a href="<c:url value='/turmas/cadastro'/>">Cadastrar nova turma</a><br/>
		</form>
		<!-- <form action="/academic-devoir/turmas/remocao">
		<a href="<c:url value='/turmas/remocao'/>">Remover turma</a><br/>
		</form>
		<form action="/academic-devoir/turmas/alteracao">
		<a href="<c:url value='/turmas/alteracao'/>">Alterar dados da turma</a><br/>
		</form> -->
	</div>
	<div id="link"><a href="<c:url value='/login'/>">Sair</a></div>
	<div id="link"><a href="<c:url value='/turmas/lista'/>">Pagina Principal</a></div><br/>
</body>

</html>