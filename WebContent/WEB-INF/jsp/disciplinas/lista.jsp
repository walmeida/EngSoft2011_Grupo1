<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
<style type="text/css">
<%@ include file="/css/form2.css" %>
</style>
<title>Academic Devoir</title>
</head>

<body>
	<div id="wrapper"> 
	<div id="header"> <%@ include file="/css/header.jsp" %></div> <br/>
	<div id="left"><fieldset><%@ include file="/css/menu.jsp" %></fieldset></div>
	<div id="right">
	<div id="menu">Lista de Disciplinas</div>
	<br/><br/>
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
                <td><a href="<c:url value='/disciplinas/alteracao/${disciplina.id}'/>"> Alterar</a> <a href="./remove?id=${disciplina.id}">  Excluir</a></td>
            </tr>
        </c:forEach>             
    </tbody>
	</table>
	<br/><br/>
	<a href="<c:url value='/turmas/cadastro'/>">Cadastrar nova turma</a>  
    <a href="<c:url value='/disciplinas/cadastro'/>">Cadastrar nova disciplina</a>
	
	</div>
	</div>
</body>
</html>