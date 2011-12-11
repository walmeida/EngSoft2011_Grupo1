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
		<input type="hidden" name="nova.disciplina.id" value="${idDisciplina }"/>
		<p><label>Nome: </label><input type="text" size="30" name="nova.nome"/></p>
		<!-- <p><label>Disciplina:</label>
		    <select name="nova.disciplina.id">
    		<c:forEach items="${listaDeDisciplinas}" var="disciplina">
          		<option value="${disciplina.id}" >${disciplina.nome}</option>
          	</c:forEach>
    	</select>
    	</p> -->
		<p><label for="prazoDeMatricula">Prazo de matr&iacute;cula:</label></p><br/>

					<p><label for="dia">Dia:<br/></label>
						<select name="prazoDeMatricula[0]">
						<c:forEach var="i" begin="1" end="31" step="1" varStatus="status">
							<c:if test="${ diaAtual == i}">
								<option value="${i}" selected>${i}</option>
							</c:if>
							<c:if test="${ diaAtual != i}">
								<option value="${i}">${i}</option>
							</c:if>
						</c:forEach>
						</select> 
					</p>
					
					<p><label for="mes">M&ecirc;s:<br/></label>
						<select name="prazoDeMatricula[1]">
						<c:forEach var="i" begin="1" end="12" step="1" varStatus="status">
							<c:if test="${ mesAtual == i}">
								<option value="${i}" selected>${i}</option>
							</c:if>
							<c:if test="${ mesAtual != i}">
								<option value="${i}">${i}</option>
							</c:if>
						</c:forEach>
						</select> 
					</p>
					
					<p><label for="ano">Ano:<br/></label>
						<select name="prazoDeMatricula[2]">
							<option value="${anoAtual}" selected>${anoAtual}</option>
						<c:forEach var="i" begin="${anoAtual + 1}" end="${anoAtual + 5}" step="1" varStatus="status">
							<option value="${i}">${i}</option>
						</c:forEach>
						</select> 
					</p>	
					
	<p class="submit"><input type="submit" value="Enviar"/></p>
	</fieldset>
	</form>
	</div>
	</div>
</body>
</html>