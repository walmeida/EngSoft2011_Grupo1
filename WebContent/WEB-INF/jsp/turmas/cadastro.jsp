<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" language="java"
import="java.sql.*" errorPage="" %>

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
	<form action='cadastra' accept-charset="utf-8">
	<fieldset>
		<input type="hidden" name="nova.professor.id" value="${usuarioSession.usuario.id }"/>
		<p><label>Nome: </label><input type="text" size="30" name="nova.nome"/></p>
		<p><label>Disciplina:</label>
		    <select name="nova.disciplina.id">
    		<c:forEach items="${listaDeDisciplinas}" var="disciplina">
          		<option value="${disciplina.id }">${disciplina.nome }</option> 
    		</c:forEach>
    	</select></p>
		<p><label for="prazoDeMatricula">Prazo de matrícula:</label></p><br/>

					<p><label for="dia">Dia: </label>
						<input id="dia" type="text" size="2" maxlength="2" name="prazoDeMatricula[0]" value="${diaAtual}"/></p>
					<p><label for="mes">Mês: </label>
						<input id="mes" type="text" size="2" maxlength="2" name="prazoDeMatricula[1]" value="${mesAtual}"/></p>
					<p><label for="ano">Ano:<br/></label>
						<input id="ano" type="text" size="2" maxlength="4" name="prazoDeMatricula[2]" value="${anoAtual}"/></p>

	<p class="submit"><input type="submit" value="Enviar"/></p>
	</fieldset>
	</form>
	</div>
	</div>
</html>