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
		<form action="<c:url value="/listasDeExercicios" />" method="post" accept-charset="us-ascii">
			<fieldset>
				<legend>Cadastrar lista de exercícios</legend>				
				<label for="nome">Nome:</label>
					<input id="nome" type="text" name="listaDeExercicios.nome" />
					<br/>
				<label for="enunciado">Enunciado:</label>
					<input id="enunciado" type="text" name="listaDeExercicios.enunciado" />
					<br/>
				<label for="visivel">Visível:</label>
					<input id="visivel" type="checkbox" name="listaDeExercicios.visivel" value="true" />
					<br/>
				<label for="Peso">Peso:</label>
					<input id="peso" type="text" size="5" maxlength="3" name="listaDeExercicios.peso" />
					<br/>
				<label for="prazoDeEntrega">Prazo de entrega:</label>
				<br/>
				<label for="dia">Dia:</label>
					<input id="dia" type="text" size="2" maxlength="2" name="prazoDeEntrega[0]" />
				<label for="mes">Mês:</label>
					<input id="mes" type="text" size="2" maxlength="2" name="prazoDeEntrega[1]" />
				<label for="ano">Ano:</label>
					<input id="ano" type="text" size="2" maxlength="4" name="prazoDeEntrega[2]" />
				<label for="hora">Hora:</label>
					<input id="hora" type="text" size="2" maxlength="2" name="prazoDeEntrega[3]" />
					<p>:</p>
					<input id="minuto" type="text" size="2" maxlength="2" name="prazoDeEntrega[4]" />
					<br/>
					
				<label for="turmas">Turmas:</label>
				<br />
				<c:forEach items="${usuarioSession.usuario.turmas }" var="turma">
					<input type="checkbox" name="idDasTurmas[]" value="${turma.id }" />
					<p> ${turma.nome }</p>
					<br />
				</c:forEach>
				
				<br />
				<button type="submit">Enviar</button>
			</fieldset>
		</form>
		<br/>
	</div>
	<a href="<c:url value='/login'/>">Sair</a>
	<a href="<c:url value='../listasDeExercicios'/>">Voltar</a>
	<a href="<c:url value='../professores/home'/>">Pagina principal</a><br/>
</body>
</html>