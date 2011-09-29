<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=utf-8" language="java"
import="java.sql.*" errorPage="" %>
<html>
<head>
<script type="text/javascript" charset="utf-8" src="<c:url value="/javascript/jquery-1.6.2.min.js"/>"></script>
<script type="text/javascript" charset="utf-8">
	function liberaAlternativas(numeroDeAlternativas) {
		var i = 0;
		while (i < numeroDeAlternativas) {
			$('#respostaMultiplaEscolha'+i).removeAttr('disabled');
			$('#alternativa'+i).removeAttr('disabled');
			$('#respostaMultiplaEscolha'+i).show();
			$('#alternativa'+i).show();
			$('#espacoAlternativas'+i).show();
			$('#espacoAlternativas2_'+i).show();
			i++;
		}
		while (i < 10) {
			$('#respostaMultiplaEscolha'+i).attr('disabled', 'disabled');
			$('#alternativa'+i).attr('disabled', 'disabled');
			$('#respostaMultiplaEscolha'+i).hide();
			$('#alternativa'+i).hide();
			$('#espacoAlternativas'+i).hide();
			$('#espacoAlternativas2_'+i).hide();
			i++;
		}
	}

	$(document).ready(function(){
		var numeroDeAlternativas = ${numeroDeAlternativas};
		liberaAlternativas(numeroDeAlternativas);
		
		$('#seletorDeAlternativas').change(function() {
	 		var valor = $(this).val();	 		
	 		numeroDeAlternativas = parseInt(valor);
	 		liberaAlternativas(numeroDeAlternativas);
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

legend 
{
color: #8c550e;
}
form {
margin: 3em auto;
width: 62%;
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
		
		<form action="<c:url value="/questoes/mult/${questao.id }"/>" method="post" accept-charset="us-ascii">
			<fieldset>
				<legend>Alterar questão de múltipla escolha</legend>
				<br/>
				<label for="enunciado">Enunciado:</label><br/>
					<textarea id="enunciado" rows= "5" cols="80" name="questao.enunciado">${questao.enunciado }</textarea>
				<br/>
				<p>Número de Alternativas: </p>
					<select id="seletorDeAlternativas" name="numeroDeAlternativas">
						<c:forEach begin="2" end="10" step="1" varStatus="iteracao">
							<c:choose>
								<c:when test="${iteracao.index eq numeroDeAlternativas }">
									<option selected="selected" value="${iteracao.index }">${iteracao.index }</option>
								</c:when>
								<c:otherwise>
									<option value="${iteracao.index }">${iteracao.index }</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
					<br/><br/>
					<label>Alternativas:</label>
					<c:set var="valorResposta" value="1" />
					<c:forEach items="${questao.alternativas }" var="alternativa" varStatus="iteracao">
						<br id="espacoAlternativas${iteracao.index }"/>
						<br id="espacoAlternativas2_${iteracao.index }"/>
						<c:choose>
							<c:when test="${valorResposta eq questao.resposta }">
								<input id="respostaMultiplaEscolha${iteracao.index }" class="respostaDasAlternativas" type="radio" checked="checked" name="questao.resposta" value="${valorResposta }" />
							</c:when>
							<c:otherwise>
								<input id="respostaMultiplaEscolha${iteracao.index }" class="respostaDasAlternativas" type="radio" name="questao.resposta" value="${valorResposta }" />
							</c:otherwise>
						</c:choose>
							<input  id="alternativa${iteracao.index }" class="alternativa" type="text" size="100" name="questao.alternativas[${iteracao.index }]" value="${alternativa }" />
							<c:set var="valorResposta" value="${valorResposta*2 }"/>
					</c:forEach>
					
					<c:forEach begin="${numeroDeAlternativas }" end="9" step="1" varStatus="iteracao">
						<br id="espacoAlternativas${iteracao.index }"/>
						<br id="espacoAlternativas2_${iteracao.index }"/>
						
						<input id="respostaMultiplaEscolha${iteracao.index }" class="respostaDasAlternativas" type="radio" name="questao.resposta" value="${valorResposta }" disabled="disabled" />
						<input  id="alternativa${iteracao.index }" class="alternativa" type="text" size="100" name="questao.alternativas[${iteracao.index }]" disabled="disabled" />
						
						<c:set var="valorResposta" value="${valorResposta*2 }"/>
					</c:forEach>
					
				<br/>
				<button type="submit" name="_method" value="put">Salvar Alterações</button>
			</fieldset>
		</form>
		<br/>
	</div>
</body>
</html>