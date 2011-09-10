<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
		
		<form id="form_login" action="/login" method="post">
			
			<fieldset> 
		    	<legend>Login</legend>
				<div> 
		      		<label for="instancia.nome">Clube:</label> 
		      		<input type="text" name="instancia.nome" value="${clube.nome}" />
		      	</div>
		      	<div>	 
		      		<label for="instancia.estado">Estado:</label> 
		      		<input type="text" name="instancia.estado" value="${clube.estado}" />
		      	</div>
		    </fieldset>
		    <div> 
	      		<input type="submit" value="Entrar">
	    	</div> 
		    
	   	</form> 
	</div>

</body>
</html>