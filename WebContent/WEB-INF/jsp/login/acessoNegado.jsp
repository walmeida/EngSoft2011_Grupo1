<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Academic Devoir</title>
<link rel="stylesheet" type="text/css" charset="utf-8" media="screen" href="<c:url value="/css/form2.css"/>"/>
</head>

<body>
<div id="wrapper"> 
	<div id="header"> <%@ include file="/css/header.jsp" %></div> <br/>
	<div id="container">
	
	<p>Erro 403 - Acesso negado</p>
	<p>Você não tem permissão. Pare de querer zuar o bang =/</p>
	<c:if test ="${usuarioSession.usuario.privilegio == 'ALUNO' || usuarioSession.usuario.privilegio == 'MONITOR'}">
		<a href="<c:url value='/alunos/home'/>">Página Principal</a><br/>
	</c:if>
	<c:if test ="${usuarioSession.usuario.privilegio == 'PROFESSOR' || usuarioSession.usuario.privilegio == 'ADMINISTRADOR'}">
		<a href="<c:url value='/professores/home'/>">Página Principal</a><br/>    		         
	</c:if>
	</div>
</div>
</body>
</html>
