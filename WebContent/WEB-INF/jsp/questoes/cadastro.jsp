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
	
	function habilitaMultiplaEscolha(numeroDeAlternativas) {
		$('form').attr('action', '<c:url value="/questoes/mult"/>');				
		$('#respostaMultiplaEscolha0').attr('checked', 'checked');
		liberaAlternativas (numeroDeAlternativas);
		$('#questaoDeMultiplaEscolhaContainer').show();
	}
	
	function habilitaTexto() {
		$('form').attr('action', '<c:url value="/questoes/texto"/>');
		$('#respostaTexto').removeAttr('disabled');
		$('#questaoDeTextoContainer').show();
	}
	
	function habilitaVouF() {
		$('form').attr('action', '<c:url value="/questoes/vouf"/>');
		$('#respostaVouFVerdadeiro').removeAttr('disabled');
		$('#respostaVouFFalso').removeAttr('disabled');
		$('#respostaVouFVerdadeiro').attr('checked', 'checked');
		$('#questaoDeVouFContainer').show();
	}
	
	function desabilitaMultiplaEscolha() {
		$('.respostaDasAlternativas').attr('disabled', 'disabled');
		$('.respostaDasAlternativas').removeAttr('checked', 'checked');
		$('.alternativa').attr('disabled', 'disabled');
		$('#questaoDeMultiplaEscolhaContainer').hide();
	}
	
	function desabilitaVouF() {
		$('.respostaVouF').removeAttr('checked', 'checked');
		$('.respostaVouF').attr('disabled', 'disabled');
		$('#questaoDeVouFContainer').hide();
	}
	
	function desabilitaTexto() {
		$('#respostaTexto').attr('disabled', 'disabled');
		$('#questaoDeTextoContainer').hide();
	}
	
	$(document).ready(function() {		
		var numeroDeAlternativas = 5;
		
		$('#questaoDeMultiplaEscolhaContainer').hide();
		$('#questaoDeVouFContainer').hide();
				
		$('#seletorDeTipoDeQuestao').change(function() {
			var idSeletor = $(this).val();
			if (idSeletor == 'multiplaEscolha') {
				desabilitaTexto();
				desabilitaVouF();
				habilitaMultiplaEscolha(numeroDeAlternativas);
			}
			else if (idSeletor == 'texto') {
				desabilitaMultiplaEscolha();
				desabilitaVouF();
				habilitaTexto();
			}
			else if (idSeletor == 'VouF') {
				desabilitaMultiplaEscolha();
				desabilitaTexto();
				habilitaVouF();
			}
			else {
				desabilitaMultiplaEscolha();
				desabilitaTexto();
				desabilitaVouF();
				$('form').attr('action', '<c:url value="/questoes/submissao"/>');
			}
			$(this).attr('selected', 'selected');
		});
		
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
		<form action="<c:url value="/questoes/texto"/>" method="post" accept-charset="us-ascii">
			<fieldset>
				<legend>Cadastrar Questão</legend>
				<br/>
				<label for="enunciado">Enunciado:</label><br/>
					<textarea id="enunciado" rows= "5" cols="80" name="questao.enunciado">${questao.enunciado }</textarea>
				<br/><br/>
				
				<p>Tipo de Questão: </p>
				<select id="seletorDeTipoDeQuestao" name="tipoDeQuestao">
					<option selected="selected" value="texto">Texto</option>
					<option value="multiplaEscolha">Múltipla Escolha</option>
					<option value="VouF">V ou F</option>
					<option value="submissao">Submissão de Arquivo</option>
				</select>
				
				<div id="questaoDeMultiplaEscolhaContainer">
					<br/><br/>
					<p>Número de Alternativas: </p>
					<select id="seletorDeAlternativas" name="numeroDeAlternativas">
						<c:forEach begin="2" end="10" step="1" varStatus="iteracao">
							<c:choose>
								<c:when test="${iteracao.index eq 5 }">
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
					<c:forEach begin="0" end="9" step="1" varStatus="iteracao">
						<br id="espacoAlternativas${iteracao.index }"/>
						<br id="espacoAlternativas2_${iteracao.index }"/>
						<c:choose>
							<c:when test="${iteracao.index eq 0 }">
								<input id="respostaMultiplaEscolha${iteracao.index }" class="respostaDasAlternativas" type="radio" checked="checked" name="questao.resposta" value="${valorResposta }" disabled="disabled" />
								<c:set var="valorResposta" value="${valorResposta+1 }"/>
							</c:when>
							<c:otherwise>
								<input id="respostaMultiplaEscolha${iteracao.index }" class="respostaDasAlternativas" type="radio" name="questao.resposta" value="${valorResposta }" disabled="disabled" />
								<c:set var="valorResposta" value="${valorResposta*2 }"/>
							</c:otherwise>
						</c:choose>
								<input  id="alternativa${iteracao.index }" class="alternativa" type="text" size="100" name="questao.alternativas[]" disabled="disabled" />
					</c:forEach>
				</div>

				<div id="questaoDeTextoContainer">
					<br/><br/>
					<label for="respostaTexto">Resposta:</label>
					<br/>
					<textarea id="respostaTexto" rows= "5" cols="80" name="questao.resposta"></textarea>
				</div>
				
				<div id="questaoDeVouFContainer">
					<br/>
					<label for="resposta">Resposta:</label>
					<input id="respostaVouFVerdadeiro" class="respostaVouF" type="radio" name="questao.resposta" value="true" disabled="disabled"/><p>Verdadeiro</p>
					<input id="respostaVouFFalso" class="respostaVouF" type="radio" name="questao.resposta" value="false" disabled="disabled"/><p>Falso</p>
				</div>
				<br/><br/>
				<button type="submit">Enviar</button>
			</fieldset>
		</form>
		<br/>
	</div>
	<a href="<c:url value='/login'/>">Sair</a>
	<a href="<c:url value='/questoes'/>">Voltar</a><br/>
</body>
</html>