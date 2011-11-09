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
	<form action='cadastra'>
	<fieldset><legend>Cadastrar nova turma:</legend><br/>
		<input type="hidden" name="nova.professor.id", value="${usuarioSession.usuario.id }"/>
		<p><label>Nome: </label><input type="text" size="30" name="nova.nome"/></p>
		<p><label>Disciplina:</label>
    <select name="nova.disciplina.id">
    <c:forEach items="${listaDeDisciplinas}" var="disciplina">
          <option value="${disciplina.id }">${disciplina.nome }</option> 
    </c:forEach>
    </select>
        </p>	
	<p class="submit"><input type="submit" value="Enviar"/></p>
	</fieldset>
	</form>
	<div id="link"><a href="<c:url value='/login'/>">Sair</a></div>
	<div id="link"><a href="<c:url value='/professores/home'/>">Voltar</a></div><br/>
</body>
</html>