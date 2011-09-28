<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=utf-8" language="java"
import="java.sql.*" errorPage="" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<script type="text/javascript" charset="utf-8" src="<c:url value="/javascript/jquery-1.6.2.min.js"/>"></script>
<script type="text/javascript" charset="utf-8">
	$(document).ready(function(){
	  $('.seletorDeAlternativas').click(function(){
		  $('form').attr('action', '<c:url value="/questoes/mult/cadastro"/>');
		  $('form').attr('method', 'get');
		  $('form').submit();
	  });
	});
</script>
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
form {
margin: 3em auto;
width: 62%;
}
p{
display: inline;
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
	<div>
		<%@ include file="../questoes/menu.jsp" %>
	</div>
	
	<div>
		<br/>
		<form action="<c:url value="/questoes/mult"/>" method="post" accept-charset="us-ascii">
			<fieldset>
				<legend>Cadastrar questão de múltipla escolha</legend>
				<br/>
				<label for="enunciado">Enunciado:</label><br/>
					<textarea id="enunciado" rows= "5" cols="80" name="questao.enunciado">${questao.enunciado }</textarea>
				<br/>
				<c:set var="iteracao" value="2"/>
				<br/>
				<p>Número de Alternativas: </p>
				<select name="numeroDeAlternativas">
					<c:forEach begin="2" end="10" step="1">
						<c:choose>
							<c:when test="${iteracao eq numeroDeAlternativas}">
								<option class="seletorDeAlternativas" selected="selected" value="${iteracao }">${iteracao }</option>
							</c:when>
							<c:otherwise>
								<option class="seletorDeAlternativas" value="${iteracao }">${iteracao }</option>
							</c:otherwise>
						</c:choose>						
						<c:set var="iteracao" value="${iteracao+1 }"/>
					</c:forEach>
				</select>
				<br/>
				<label for="alternativa[0]"></label><br/>
					<input id="resposta0" type="radio" checked="checked" name="questao.resposta" value="1"></input>
					<input id="alternativa0" type="text" size="100" name="alternativasEnviadas[]" value="${alternativas[0] }"></input>			
					<c:set var="valorResposta" value="2"/>
					<br/>
				<c:forEach begin="1" end="${numeroDeAlternativas-1 }" step="1" varStatus="iteracao">
					<label for="alternativa[${iteracao.index }]"></label><br/>
						<input id="resposta${iteracao.index }" type="radio" name="questao.resposta" value="${valorResposta }"></input>
						<input  id="alternativa${iteracao.index }" type="text" size="100" name="alternativasEnviadas[]" value="${alternativas[iteracao.index] }"></input>											
						<c:set var="valorResposta" value="${valorResposta*2 }"/>
						<br/>
				</c:forEach>
				<button type="submit">Enviar</button>
			</fieldset>
		</form>
		<br/>
	</div>
</body>
</html>