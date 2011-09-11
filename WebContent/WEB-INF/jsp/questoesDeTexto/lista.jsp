<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=utf-8" language="java"
import="java.sql.*" errorPage="" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<h1>Academic Devoir</h1>
<h2>Grupo 1 - Engenharia de Software</h2>
</head>
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
</style>

<body>
	<div id="menu">
		<%@ include file="../questoes/menu.jsp" %><br/>
	</div>
		
	<div>
		<table>
			<thead>
				<tr>
					<th>Questão</th>
					<th>Enunciado</th>
					<th>Resposta</th>
					<th>Alterar</th>
					<th>Remover</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${questaoDeTextoList }" var="questao">
					<tr>
						<td>${questao.id }</td>
						<td>${questao.enunciado }</td>
						<td>${questao.resposta }</td>
						<td><a href="<c:url value="/questoes/texto/${questao.id }"/>">Alterar</a></td>
						<td>
							<form action="<c:url value="/questoes/${questao.id }"/>" method="post">
									<button name="_method" value="delete">Remover</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div><br/>
		<a href="<c:url value="/questoes/texto/cadastro"/>">Cadastrar Nova Questão</a>
	</div>
</body>
</html>