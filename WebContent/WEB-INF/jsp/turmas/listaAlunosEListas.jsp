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
    <div id="menu">Lista de Alunos</div>
    <table border="1">
    <thead>
        <tr>
        <th>Nome</th>
        <c:forEach items="${turma.listas}" var="lista">
            <th>${lista.propriedades.nome}</th>
        </c:forEach>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${turma.alunos}" var="aluno">
            <tr>
                <td>${aluno.nome}</td>
                <c:forEach items="${turma.listas}" var="lista">
                    <td>
                    <c:forEach items="${lista.respostas}" var="resposta">    
                        <c:if test ="${resposta.aluno.id == aluno.id}">
                            ${lista.propriedades.nome}
                        </c:if>
                    </c:forEach>
                    </td>
                </c:forEach>
            </tr>
        </c:forEach>
    </tbody>
</table> 
</div>
</div>
</body>
</html>