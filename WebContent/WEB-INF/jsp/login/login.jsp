<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	                    <div> 
	                    <p><label for="usuario.login">Login:<br/></label> 
					<input type="text" size="30" name="usuario.login" value="${usuario.login}" /></p>
		                </div>
	        	        <div>    
	                        <p><label for="usuario.senha">Senha:<br/></label> 
							<input type="password" size="30" name="usuario.senha" value="${usuario.senha}" /></p>
						</div>
		                <div>
		                    <p class="submit"><input type="submit" value="Entrar"></p>
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
