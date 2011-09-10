<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=utf-8" language="java"
import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>Alterar Questão</title>
	
</head>
<body>
	<div>
		<%@ include file="../questoes/menu.jsp" %>
	</div>
	
	<div>
		<br/>
		<form id="musicForm" action="<c:url value="/questoes/texto/${questao.id }"/>" method="post" accept-charset="us-ascii">
			<fieldset>
				<legend>Alterar questão de texto</legend>
				
				<label for="enunciado">Enunciado:</label><br/>
					<textarea id="enunciado" rows= "5" cols="80" name="questao.enunciado">${questoesDeTexto.enunciado }</textarea>
				<br/>
				<label for="resposta">Resposta:</label><br/>
					<textarea id="resposta" rows= "5" cols="80" name="questao.resposta">${questoesDeTexto.resposta }</textarea>
				<br/>
				<button type="submit" name="_method" value="put">Alterar</button>
			</fieldset>					
		</form>
		<br/>
	</div>
</body>
</html>