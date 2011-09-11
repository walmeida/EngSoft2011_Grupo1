<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<h1>Academic Devoir</h1>
<h2>Grupo 1 - Engenharia de Software</h2>
</head>
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

legend 
{
color: #8c550e;
}
form {
margin: 3em auto;
width: 25%;
}

</style> 

<body>
	<div id="container">
		
		<!-- <c:forEach var="error" items="${errors}">
    		 <li style="color:red"> ${error.category} - ${error.message}</li>
		</c:forEach> -->
		
		<c:choose>
			<c:when test="${empty usuarioSession.usuario.nome}">
				<form id="form_login" action="login" method="post">
	                
	                <fieldset> 
	                    <legend>Fa&ccedila o login:</legend><br/>
	                    <div> 
	                        <label for="usuario.login">Login:<br/></label> 
	                        <input type="text" size="30" name="usuario.login" value="${usuario.login}" />
	                    </div>
	                    <div>    
	                        <label for="usuario.senha">Senha:<br/></label> 
	                        <input type="password" size="30" name="usuario.senha" value="${usuario.senha}" />
	                    </div>
		                <div><br/>
		                    <input type="submit" value="Entrar">
		                </div> 
	                </fieldset>
	                
	            </form> 
			</c:when>
			<c:otherwise>
			    Olá, ${usuarioSession.usuario.nome}
			</c:otherwise>
	     </c:choose>
        
	</div>

</body>
</html>