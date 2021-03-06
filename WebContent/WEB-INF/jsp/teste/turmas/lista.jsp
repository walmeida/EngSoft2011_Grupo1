<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Academic Devoir</title></head>
<head>
<style type="text/css">
<%@ include file="/css/form.css" %>
</style>
<title>Academic Devoir</title>
</head>

<body>
	<div id="menu">Lista De Turmas</div>
    <a href="<c:url value='/turmas/cadastro'/>">Inserir</a>
	<table>
        <c:forEach items="${listaDeTurmas}" var="turma">
            <tr>
                <td>
                    <a href="<c:url value="/turmas/home/${turma.id }"/>">
                        ${turma.disciplina.nome} - ${turma.nome} </a>
                </td>
                <td><a href="./alteracao?id=${turma.id}">  Alterar</a> <a href="./remove?id=${turma.id}">  Excluir</a></td>
            </tr>
        </c:forEach>
    </table>
    <div id="link"><a href="<c:url value='/login'/>">Sair</a></div>
    <div id="link"><a href="<c:url value='/professores/home'/>">P�gina Principal</a></div><br/>
</body>
</html>