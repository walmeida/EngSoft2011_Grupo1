<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" language="java"
import="java.sql.*" errorPage="" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

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
	<div id="menu">Ver corre&ccedil;&atilde;o</div>
	<br/><br/>
    
    <table>
        <tr>
            <td>${listaDeExercicios.propriedades.nome}</td>
        </tr>
        <tr>
            <td>${listaDeExercicios.propriedades.enunciado}</td>
        </tr>
    </table>
    
    <h3>Nota: ${listaDeRespostas.notaFinal}</h3>    
    <div>
        <c:forEach items="${listaDeExercicios.questoes}" var="questaoDaLista" varStatus="iteracao">
                <fieldset>
                        <p>${questaoDaLista.ordem} )
                                ${questaoDaLista.questao.enunciado}</p>
                        ${renderizacao[iteracao.index]}
                </fieldset>
        </c:forEach>
    </div>
    </div>
    </div>   
</body>
</html>