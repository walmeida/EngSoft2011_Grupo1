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
	<div id="menu">Cadastro de lista de exerc&iacute;cio</div>
	<br/><br/>
		<form style="width: 25em;" action="<c:url value="/listasDeExercicios" />" method="post" accept-charset="utf-8">
			<fieldset>
				<legend>Cadastrar lista de exerc&iacute;cios</legend>				
				<label for="nome">Nome:</label>
					<input id="nome" type="text" name="propriedades.nome" />
					<br/>
				<label for="enunciado">Enunciado:</label>
					<input id="enunciado" type="text" name="propriedades.enunciado" />
					<br/>
				<label for="visivel">Vis&iacute;vel:</label>
					<input id="visivel" type="checkbox" name="propriedades.visivel" value="true" />
					<br/>
				<label for="Peso">Peso:</label>
					<input id="peso" type="text" size="5" maxlength="3" name="propriedades.peso" />
					<br/>
				<label for="prazoDeEntrega">Prazo de entrega:</label>
				<br/>
				<label for="dia">Dia:</label>
					<input id="dia" type="text" size="2" maxlength="2" name="prazoDeEntrega[0]" />
				<label for="mes">Mês:</label>
					<input id="mes" type="text" size="2" maxlength="2" name="prazoDeEntrega[1]" />
				<label for="ano">Ano:</label>
					<input id="ano" type="text" size="2" maxlength="4" name="prazoDeEntrega[2]" />
				<br/>
				<label for="hora">Hora:</label>
					<input id="hora" type="text" size="2" maxlength="2" name="prazoDeEntrega[3]" />:
					<input id="minuto" type="text" size="2" maxlength="2" name="prazoDeEntrega[4]" />
					<br/>
				<label for="numeroMaximoDeEnvios">N&uacute;mero M&aacute;ximo de Envios:</label>
					<input id="numeroMaximoDeEnvios" type="text" size="2" maxlength="2" name="propriedades.numeroMaximoDeEnvios" />
					<br/>
				<label for="autoCorrecao">Auto Corre&ccedil;&atilde;o:</label>
					<select id="autoCorrecao" name="propriedades.autoCorrecao">
						<option value="0">Desativada</option>
						<option value="1">Professor</option>
						<option value="2">Aluno e Professor</option>
					</select>
				<br/><br/>
					
				<!-- <label for="turmas">Turmas:</label>
				<br />
				<c:forEach items="${turmasDoProfessor }" var="turma">
					<input type="checkbox" name="idDasTurmas[]" value="${turma.id }" />
					<p> ${turma.disciplina.nome } - ${turma.nome }</p>
					<br />
				</c:forEach>
				-->
				
				<label for="turmas">Turmas:</label>
				<br />
				<c:forEach items="${turmasDoProfessor }" var="turma">
					<input type="radio" name="idDaTurma" value="${turma.id }" />
					 ${turma.disciplina.nome } - ${turma.nome }
					<br />
				</c:forEach>
								
				<br />
				<button type="submit">Enviar</button>
			</fieldset>
		</form>
		<br/>
	</div>
</body>
</html>