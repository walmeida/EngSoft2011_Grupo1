<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=utf-8" language="java"
import="java.sql.*" errorPage="" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>Lista de Questões de Submissão de Arquivo</title>
</head>
<body>
	<div id="menu">
		<%@ include file="../questoes/menu.jsp" %>
	</div>
		
	<div>
		<table>
			<thead>
				<tr>
					<th>Questão</th>
					<th>Enunciado</th>
					<th>Alterar</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${questaoDeSubmissaoDeArquivoList }" var="questao">
					<tr>
						<td>${questao.id }</td>
						<td>${questao.enunciado }</td>
						<td><a href="<c:url value="/questoes/submissao/${questao.id }"/>">Alterar</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div>
		<a href="<c:url value="/questoes/submissao/cadastro"/>">Cadastrar nova Questão</a>
	</div>
</body>
</html>