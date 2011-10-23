<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=utf-8" language="java"
import="java.sql.*" errorPage="" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<style type="text/css">
body
{
background-color:#f0ecc6;
}
h1 
{
color: black;
text-align: center;
font-size: 40px;
font-family:"Times New Roman";
font-style: italic;
font-variant: small-caps;
}
h2
{
color: black;
text-align: center;
font-size: 20px;
font-family:"Times New Roman";
}
form {
margin: 3em auto;
width: 62%;
}
p{
display: inline;
}
.fieldsetSemFormatacao{
	border:none;
	padding: 0px;
}
</style>
</head>

<body>
	<h1>Academic Devoir</h1>
	<h2>Grupo 1 - Engenharia de Software</h2>
	<div>
		<%@ include file="../questoes/menu.jsp" %>
	</div>
	
	<div>
		<br/>
		<form action="<c:url value="/listasDeExercicios/${listaDeExercicios.id }"/>" method="post" accept-charset="us-ascii">
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
					<input id="mes" type="text" size="2" maxlength="2" name="prazoDeEntrega[1]" value="${prazo[1] }"/>
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
	<a href="<c:url value='/login'/>">Sair</a>
    <a href="<c:url value='/professor/home'/>">Página Principal</a><br/>
</body>
</html>