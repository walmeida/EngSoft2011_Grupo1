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
	<p>Lista de Produtos</p>
	<table>
    <thead>
        <tr>
        <th>id</th>
        <th>Nome</th>
        <th>Descrição</th>
        <th>preço</th>
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
        <form action='form'>
        	<input type="submit" value="Inserir"/>
        </form>
        <form action='formalter'>
        	<input type="submit" value="Alterar"/>
        </form>
        <form action='formremover'>
        	<input type="submit" value="Excluir"/>
        </form>
        <form action='index'>
        	<input type="submit" value="Voltar"/>
        </form>
    </tbody>
</table>
	
</body>
</html>