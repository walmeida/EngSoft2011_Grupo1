<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" language="java"
import="java.sql.*" errorPage="" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

<style type="text/css">
body
{
background-color:#f0ecc6;
}
h1 
{
color: black;
text-align: center;
font-size: 40px;
font-family:"Times New Roman";
font-style: italic;
font-variant: small-caps;
}
h2
{
color: black;
text-align: center;
font-size: 20px;
font-family:"Times New Roman";
}
.fieldsetSemFormatacao{
    border:none;
    padding: 0px;
}
</style>
</head>
<body>
    <h1>Academic Devoir</h1>
    <h2>Grupo 1 - Engenharia de Software</h2>
    
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
    
    <a href="<c:url value='/login'/>">Sair</a>
    <!-- TODO a href="<c:url value='/alunos/home'/>">Página Principal</a><br/ -->   
</body>
</html>