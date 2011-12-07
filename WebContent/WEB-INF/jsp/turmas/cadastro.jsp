<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=utf-8" language="java"
import="java.sql.*" errorPage="" %>
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
	<form action='cadastra' accept-charset="us-ascii">
	<fieldset><legend>Cadastrar nova turma:</legend><br/>
		<input type="hidden" name="nova.professor.id", value="${usuarioSession.usuario.id }"/>
		Nome: <br/><input type="text" size="30" name="nova.nome"/><br/>
		Disciplina:<br />
    <select name="nova.disciplina.id">
    <c:forEach items="${listaDeDisciplinas}" var="disciplina">
          <option value="${disciplina.id }">${disciplina.nome }</option> 
    </c:forEach>
    </select>
        <br /><br />		
	<input type="submit" value="Enviar"/>
	</fieldset>
	</form>
	<a href="<c:url value='/login'/>">Sair</a>
	<a href="<c:url value='/professores/home'/>">Voltar</a><br/>
</body>
</html>