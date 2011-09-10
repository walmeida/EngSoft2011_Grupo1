<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CRUD</title>
<style type="text/css">
body
{
background-color:#dfdfdf;
}
h1
{
color:black;
text-align:center;
}
p
{
font-family:"Verdana";
font-size:18px;
}
</style> 
</head>
<body>
	<h1>CRUD</h1>
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
        <c:forEach items="${alunodao.lista}" var="aluno">
            <tr>
                <td>${aluno.id}</td>
                <td>${aluno.nome}</td>
                <td>${aluno.email}</td>
                <td>${aluno.login}</td>
                <td>${aluno.senha}</td>
            </tr>
        </c:forEach>
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
    </tbody>
</table>
	
</body>
</html>