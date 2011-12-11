<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" language="java"
import="java.sql.*" errorPage="" %>

<html>
<head><title>Academic Devoir</title>
<link rel="stylesheet" type="text/css" charset="utf-8" media="screen" href="<c:url value="/css/form2.css"/>"/>
<style>
fieldset
{
border: 1px solid #00aa22;
width: 20em;
}
</style>
</head>

<body>
<div id="wrapper"> 
	<div id="header"> <%@ include file="/css/header.jsp" %></div> <br/>
	<div id="container">
		<c:forEach var="error" items="${errors}">
			<li style="color:red"> ${error.category} - ${error.message}</li>
		</c:forEach>	
		<c:choose>
			<c:when test="${empty usuarioSession.usuario.nome}">
				<form id="form_login" action="autenticar" method="post">   
		                <fieldset> 
				<legend>Fa&ccedil;a o login:</legend><br/>
						<table cellspacing="5" cellpadding="2">
						<tr>
		                    <td><label for="usuario.login">Login:</label></td> 
							<td><input type="text" size="20" name="usuario.login" value="${usuario.login}" /></td>
		                </tr>
	        	        <tr>    
	                        <td><label for="usuario.senha">Senha:</label></td> 
							<td><input type="password" size="20" name="usuario.senha" value="${usuario.senha}" /></td>
						</tr>
						</table>
		                <div>
		                    <p class="submit"><input type="submit" value="Entrar"/></p>
		                </div>
		                <div>    
	                        <div id="link"><a href="alunos/cadastro">Criar Conta</a></div>
	                        <div id="link"><a href="#">Esqueci minha senha xD</a></div>
	                    </div> 
	                	</fieldset>
		                <c:out value="${error}"></c:out>
				</form> 
				<div id="grupo">Academic Devoir</div>
			</c:when>

			<c:otherwise>
				Tem certeza ${usuarioSession.usuario.nome}?
				<div>    
				<a href="logout">Sair</a>
				<c:if test ="${usuarioSession.usuario.privilegio == 'PROFESSOR' || usuarioSession.usuario.privilegio == 'ADMINISTRADOR'}">
        				<a href="<c:url value='/professores/home'/>">Página Principal</a><br/>              
				</c:if>
				<c:if test ="${usuarioSession.usuario.privilegio == 'ALUNO' || usuarioSession.usuario.privilegio == 'MONITOR'}">
        				<a href="<c:url value='/alunos/home'/>">Página Principal</a><br/>              
				</c:if>
				</div> 
			</c:otherwise>
	     </c:choose>
	</div>    
</div>
</body>
</html>
