<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CRUD</title>
<style type="text/css">
body
{
background-color:#ffcfeb;
}
h1
{
background-color:#fea8ce;
font-family: "Times New Roman";
color:black;
}
p
{
font-family:"Times New Roman";
font-size:18px;
}
div
{
background-color:#fec2e5;
}
</style> 
</head>
<body>
	<h1>CRUD</h1>
	<p>Lista de Produtos</p>
	<table>
    <thead>
        <tr>
        <th>Id</th>
        <th>Nome</th>
        <th>Descricao</th>
        <th>Preco</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${produtodao.lista}" var="produto">
            <tr>
                <td>${produto.id}</td>
                <td>${produto.nome}</td>
                <td>${produto.descricao}</td>
                <td>${produto.preco}</td>
            </tr>
        </c:forEach>
        <form action='formulario'>
        	<input type="submit" value="Adicionar"/>
        </form>
        <form action='alteracao'>
        	<input type="submit" value="Alterar"/>
        </form>
        <form action='remocao'>
        	<input type="submit" value="Remover"/>
        </form>
        <form action='home'>
        	<input type="submit" value="Voltar"/>
        </form>
    </tbody>
</table>
	
</body>
</html>