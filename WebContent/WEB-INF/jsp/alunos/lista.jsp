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
	<p>Lista de Alunos</p>
	
        <form action='cadastro'>
        	<!-- input type="submit" value="Inserir"/-->
        	<a href="<c:url value='/alunos/cadastro'/>">Inserir</a><br/>
        </form>
<!--        <form action='alteracao'>
        	<input type="submit" value="Alterar"/>
        </form>
        <form action='remocao'>
        	
        	<a href="<c:url value='/alunos/remocao'/>">Excluir</a><br/>
        </form>
        <form action='home'>
        	
		</form>
-->
    
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
        <c:forEach items="${listaDeAlunos}" var="aluno">
            <tr>
                <td>${aluno.id}</td>
                <td>${aluno.nome}</td>
                <td>${aluno.email}</td>
                <td>${aluno.login}</td>
                <td>${aluno.senha}</td>
                <td><a href="./alteracao?id=${aluno.id}">  Alterar</a> <a href="./remove?id=${aluno.id}">  Excluir</a></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<a href="<c:url value='/login'/>">Sair</a>
<a href="<c:url value='/professores/home'/>">Voltar</a><br/>
</body>
</html>