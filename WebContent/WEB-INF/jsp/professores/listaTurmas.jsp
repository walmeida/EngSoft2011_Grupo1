<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Academic Devoir</title></head>
<head>
<style type="text/css">
<%@ include file="../css/formatacao.css" %>
</style>
<title>Academic Devoir</title>
</head>

<body>
    <h1>Academic Devoir</h1>
    <h2>Grupo 1 - Engenharia de Software</h2>
    <h4>Lista de Turmas</h4>
    <table>
        <c:forEach items="${professor.turmas}" var="turma">
            <tr>
                <td>
                    <a href="<c:url value="/turmas/home/${turma.id }"/>">
                        ${turma.disciplina.nome} - ${turma.nome} </a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <form action='../turmas/cadastro'>
            <input type="submit" value="Cadastrar nova turma"/>
	</form>
    
</body>
</html>