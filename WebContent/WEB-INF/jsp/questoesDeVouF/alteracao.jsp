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
legend 
{
color: #8c550e;
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
		<form action="<c:url value="/questoes/vouf/${questao.id }" />" method="post" accept-charset="us-ascii">
			<fieldset>
				<legend>Alterar questão de V ou F</legend>
				<br/>
				<label for="enunciado">Enunciado:</label><br/>
					<textarea id="enunciado" rows= "5" cols="80" name="questao.enunciado">${questao.enunciado }</textarea>
				<br/>
				<label for="resposta">Resposta:</label>
					<c:if test="${questao.resposta }">
						<input id="verdadeiro" type="radio" checked="checked" name="questao.resposta" value="true"/><p>Verdadeiro</p>
						<input id="falso" type="radio" name="questao.resposta" value="false"/><p>Falso</p>
					</c:if>
					<c:if test="${not questao.resposta or empty questao.resposta}">
						<input id="verdadeiro" type="radio" name="questao.resposta" value="true"/><p>Verdadeiro</p>
						<input id="falso" type="radio" checked="checked" name="questao.resposta" value="false"/><p>Falso</p>
					</c:if>
				<br/><br/>
				<button type="submit" name="_method" value="put">Salvar Alterações</button>
			</fieldset>					
		</form>
		<br/>
	</div>
</body>
</html>