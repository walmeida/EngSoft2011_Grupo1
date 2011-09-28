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
	<div id="container">
		
		<c:forEach var="error" items="${errors}">
    		 <li style="color:red"> ${error.category} - ${error.message}</li>
		</c:forEach>
		
		<c:choose>
			<c:when test="${empty usuarioSession.usuario.nome}">
				<form id="form_login" action="autenticar" method="post">
	                
	                <fieldset> 
	                    <legend>Fa&ccedil;a o login:</legend><br/>
	                    <div> 
	                        <label for="usuario.login">Login:<br/></label> 
	                        <input type="text" size="30" name="usuario.login" value="${usuario.login}" />
	                    </div>
	                    <div>    
	                        <label for="usuario.senha">Senha:<br/></label> 
	                        <input type="password" size="30" name="usuario.senha" value="${usuario.senha}" />
	                    </div>
		                <div>
		                    <p><input type="submit" value="Entrar"></p>
		                </div>
		                <div>    
	                        <p><a href="alunos/cadastro">Criar Conta</a></p>
	                        <p><a href="#">Esqueci minha senha xD</a></p>
	                    </div> 
	                </fieldset>
	                
	            </form> 
			</c:when>
			<c:otherwise>
			    Olá, ${usuarioSession.usuario.nome}
			    <div>    
                    <p><a href="logout">Logout</a></p>
                </div> 
			</c:otherwise>
	     </c:choose>
        
	</div>

</body>
</html>