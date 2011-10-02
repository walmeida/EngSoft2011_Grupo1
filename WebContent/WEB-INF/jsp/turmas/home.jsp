<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Academic Devoir</title></head>
<style type="text/css">
<%@ include file="../css/formatacao.css" %>
</style> 
<body>
    <h1>Academic Devoir</h1>
    <h2>Grupo 1 - Engenharia de Software</h2>
    <br/><br/>
    <form action='../alteracao' method="get">
        <input type="hidden" value="${turma.id}" name="id">
        <input type="submit" value="Alterar informações da turma"/>
    </form>
    <form action='../remove' method="get">
        <input type="hidden" value="${turma.id}" name="id">
        <input type="submit" value="Excluir Turma"/>
    </form>
    <form action='../../listasDeExercicios/cadastro' method="get">
        <input type="submit" value="Criar lista de exercícios"/>
    </form>
    <form action='../../alunos/listaAlunosNaTurma' method="get">
        <input type="hidden" value="${turma.id}" name="turma.id">
        <input type="submit" value="Gerenciar alunos"/>
    </form>
</body>
</html>