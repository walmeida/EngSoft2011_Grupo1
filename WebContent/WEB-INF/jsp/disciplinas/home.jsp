<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
<style type="text/css">
<%@ include file="/css/form2.css" %>
</style>
<title>Academic Devoir</title>
</head>

<body>	
	<div id="wrapper"> 
	<div id="header"> <%@ include file="/css/header.jsp" %></div> <br/>
	<div id="left"><fieldset><%@ include file="/css/menu.jsp" %></fieldset></div>
	<div id="right">
	<div id="menu">Home da Disciplinas</div>
	<br/><br/>
		
		<!-- <form action="/academic-devoir/turmas/cadastro"> -->
		<a href="<c:url value='/turmas/cadastro'/>">Cadastrar nova turma</a><br/><br/>
		<!-- </form> -->
		<!-- <form action="/academic-devoir/disciplinas/alteracao"> -->
		<a href="<c:url value='/disciplinas/alteracao/${disciplina.id}'/>">Alterar dados desta disciplina</a><br/><br/>
		<!-- </form> -->
		<a href="<c:url value='/disciplinas/remove?id=${disciplina.id}'/>">Remover esta disciplina</a><br/>
		
		<br/><br/>
		<a href="<c:url value='/disciplinas/lista'/>">Listar disciplinas</a><br/>
		<a href="<c:url value='/disciplinas/cadastro'/>">Cadastrar nova disciplina</a><br/>
	</div>
	</div>
</body>
</html>