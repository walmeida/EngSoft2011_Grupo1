<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" language="java"
import="java.sql.*" errorPage="" %>

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
                <td>
                	<a href="<c:url value="/disciplinas/home/${disciplina.id}"/>">
                	${disciplina.nome}</a>
                </td>
                <td><a href="<c:url value='/disciplinas/alteracao/${disciplina.id}'/>"> Alterar</a> <a href="./remove?id=${disciplina.id}">  Excluir</a></td>
            </tr>
        </c:forEach>             
    </tbody>
	</table>
	<br/><br/>  
    <a href="<c:url value='/disciplinas/cadastro'/>">Cadastrar nova disciplina</a>
	
	</div>
	</div>
</body>
</html>