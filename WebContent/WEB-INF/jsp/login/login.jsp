<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
	<div id="container">
		
		<!-- <c:forEach var="error" items="${errors}">
    		 <li style="color:red"> ${error.category} - ${error.message}</li>
		</c:forEach> -->
		
		<c:choose>
			<c:when test="${empty usuarioSession.usuario.nome}">
				<form id="form_login" action="login" method="post">
	                
	                <fieldset> 
	                    <legend>Login</legend>
	                    <div> 
	                        <label for="usuario.login">Login:</label> 
	                        <input type="text" name="usuario.login" value="${usuario.login}" />
	                    </div>
	                    <div>    
	                        <label for="usuario.senha">Senha:</label> 
	                        <input type="password" name="usuario.senha" value="${usuario.senha}" />
	                    </div>
	                </fieldset>
	                <div> 
	                    <input type="submit" value="Entrar">
	                </div> 
	                
	            </form> 
			</c:when>
			<c:otherwise>
			    Olá, ${usuarioSession.usuario.nome}
			</c:otherwise>
	     </c:choose>
        
	</div>

</body>
</html>