<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
<style type="text/css">
<%@ include file="/css/form.css" %>
</style>
<title>Academic Devoir</title>
</head>

<body>
	<div id="wrapper">
	<div id="left"><%@ include file="home.jsp" %></div>
	<div id="right">
	<div id="menu">Lista de Professores</div>

    <form action='cadastro'>
       	<a href="<c:url value='/professores/cadastro'/>">Inserir</a><br/>
    </form>

	
	<table>
    <thead>
        <tr>
        <th>id</th>
        <th>Nome</th>
        <th>E-mail</th>
        <th>Login</th>
        <th>Senha</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${listaDeProfessores}" var="professor">
            <tr>
                <td>${professor.id}</td>
                <td>${professor.nome}</td>
                <td>${professor.email}</td>
                <td>${professor.login}</td>
                <td>${professor.senha}</td>
                <td><a href="./alteracao?id=${professor.id}">  Alterar</a><a href="./remove?id=${professor.id}">  Excluir</a></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<div id="link"><a href="<c:url value='/login'/>">Sair</a></div><br/>
<div id="link"><a href="<c:url value='/professores/home'/>">Voltar</a></div><br/>
</div>
</div>
	
</body>
</html>