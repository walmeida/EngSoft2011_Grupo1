<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" language="java"
import="java.sql.*" errorPage="" %>

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
	<div id="menu">Altera&ccedil;&atilde;o da Disciplina</div>
	<br/><br/>
	<div>
		<%@ include file="../questoes/menu.jsp" %>
	</div>
	
	<div>
		<br/>
		<form action="<c:url value="/listasDeExercicios/${listaDeExercicios.id }"/>" method="post" accept-charset="utf-8">
			<fieldset>
				<legend>Alterar lista de exercícios</legend>
				<label for="nome">Nome:</label>
					<input id="nome" type="text" name="propriedades.nome" value="${listaDeExercicios.propriedades.nome }"/>
					<br/>
				<label for="enunciado">Enunciado:</label>
					<input id="enunciado" type="text" name="propriedades.enunciado" value="${listaDeExercicios.propriedades.enunciado }"/>
					<br/>
				<label for="visivel">Visível:</label>
					<c:choose>
						<c:when test="${listaDeExercicios.propriedades.visivel }">
							<input id="visivel" type="checkbox" checked="checked" name="propriedades.visivel" value="true"/>
						</c:when>
						<c:otherwise>
							<input id="visivel" type="checkbox" name="propriedades.visivel" value="true"/>
						</c:otherwise>
					</c:choose>
					<br/>
				<label for="Peso">Peso:</label>
					<input id="peso" type="text" size="5" maxlength="3" name="propriedades.peso" value="${listaDeExercicios.propriedades.peso }"/>
					<br/>
				<label for="prazoDeEntrega">Prazo de entrega:</label>
				<br/>
				<label for="dia">Dia:</label>
					<input id="dia" type="text" size="2" maxlength="2" name="prazoDeEntrega[0]" value="${prazo[0] }"/>
				<label for="mes">Mês:</label>
					<input id="mes" type="text" size="2" maxlength="2" name="prazoDeEntrega[1]" value="${prazo[1] + 1}"/>
				<label for="ano">Ano:</label>
					<input id="ano" type="text" size="2" maxlength="4" name="prazoDeEntrega[2]" value="${prazo[2] }"/>
				<label for="hora">Hora:</label>
					<input id="hora" type="text" size="2" maxlength="2" name="prazoDeEntrega[3]" value="${prazo[3] }"/>
					<p>:</p>
					<input id="minuto" type="text" size="2" maxlength="2" name="prazoDeEntrega[4]" value="${prazo[4] }"/>
					<br/>
				<label for="numeroMaximoDeEnvios">Número Máximo de Envios:</label>
					<input id="numeroMaximoDeEnvios" type="text" size="2" maxlength="2" name="propriedades.numeroMaximoDeEnvios" value="${listaDeExercicios.propriedades.numeroMaximoDeEnvios }"/>
					<br/>
				<label for="autoCorrecao">Auto Correção:</label>
					<select id="autoCorrecao" name="propriedades.autoCorrecao">
						<c:choose>
							<c:when test="${listaDeExercicios.propriedades.autoCorrecao eq 'DESATIVADA' }">
								<option selected="selected" value="0">Desativada</option>
								<option value="1">Professor</option>
								<option value="2">Aluno e Professor</option>
							</c:when>
							<c:when test="${listaDeExercicios.propriedades.autoCorrecao eq 'PROFESSOR' }">
								<option value="0">Desativada</option>
								<option selected="selected" value="1">Professor</option>
								<option value="2">Aluno e Professor</option>
							</c:when>
							<c:otherwise>
								<option value="0">Desativada</option>
								<option value="1">Professor</option>
								<option selected="selected" value="2">Aluno e Professor</option>
							</c:otherwise>
						</c:choose>						
					</select>
					<br/><br/>
				<button type="submit" name="_method" value="put">Salvar Alterações</button>
			</fieldset>
		</form>
		<br/>
	</div>
	</div>
	</div>
</body>
</html>