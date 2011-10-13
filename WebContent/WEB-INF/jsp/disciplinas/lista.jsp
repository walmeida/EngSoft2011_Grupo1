<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
<style type="text/css">
<%@ include file="../css/formatacao.css" %>
</style>
<title>Academic Devoir</title>
</head>

<body>
	<h1>Academic Devoir</h1>
	<h2>Grupo 1 - Engenharia de Software</h2>
	<h4>Lista de Disciplinas</h4>
	<table>
    <thead>
        <tr>
        <th>id</th>
        <th>Nome</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${lista }" var="disciplina">
            <tr>
                <td>${disciplina.id}</td>
                <td>${disciplina.nome}</td>
            </tr>
        </c:forEach>             
    </tbody>
</table>
<a href="<c:url value='/login'/>">Sair</a>
<a href="<c:url value='cadastro'/>">Inserir</a>
<a href="<c:url value='alteracao'/>">Alterar</a>
<a href="<c:url value='remocao'/>">Excluir</a>
<a href="<c:url value='home'/>">Voltar</a>
</body>
</html>