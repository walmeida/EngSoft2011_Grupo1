<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<style type="text/css">
<%@ include file="../css/formatacao.css" %>
</style>
<title>Academic Devoir</title>
</head>

<body>
	<h1>Academic Devoir</h1>
	<h2>Grupo 1 - Engenharia de Software</h2>
	<h4>Cadastro de Aluno</h4>
	<form action='cadastra'>
	<fieldset>
	<legend>N&atilde;o &eacute; um aluno cadastrado? </legend><br/>
		Preencha o formul&aacute;rio abaixo e clique no bot&atilde;o "Enviar".<br/><br/>
		Nome: <br/><input type="text" size="30" name="novo.nome"/><br/>
		Login : <br/><input type="text" size="30" name="novo.login"/><br/>
		Senha : <br/><input type="password" size="30" name="novo.senha"/><br/>
		E-mail : <br/><input type="text" size="30" name="novo.email"/><br/><br/>
		<input type="hidden" name="novo.privilegio" value="0"/>
	<input type="submit" value="Enviar"/>
	</fieldset>
	</form>
	<a href="<c:url value='/login'/>">Sair</a>
	
	<c:if test ="${usuarioSession.usuario.id != null}">
        <a href="<c:url value='/alunos/lista'/>">Voltar</a><br/>              
    </c:if>
</body>
</html>