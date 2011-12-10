<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	<div id="menu">Cadastro de Turma</div>
	<br/><br/>
	<form action='cadastra'>
	<fieldset>
		<input type="hidden" name="nova.professor.id" value="${usuarioSession.usuario.id }"/>
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
	</div>
	</div>
</html>