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
        <form action='cadastro'>
        	<input type="submit" value="Inserir"/>
        </form>
        <form action='alteracao'>
        	<input type="submit" value="Alterar"/>
        </form>
        <form action='remocao'>
        	<input type="submit" value="Excluir"/>
        </form>
        <form action='home'>
        	<input type="submit" value="Voltar"/>
		</form>
    <p>Lista de Alunos</p>
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
            </tr>
        </c:forEach>
    </tbody>
</table>
	
</body>
</html>