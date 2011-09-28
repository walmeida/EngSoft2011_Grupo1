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
	$(document).ready(function() {		
		$('#formIncluirTurma').hide();
		$('#formIncluirQuestao').hide();
		$('[id^=formAlterarQuestao]').hide();
		
		$('#ativaIncluirTurma').click(function() {
			$('#formIncluirTurma').show();
			$(this).hide();
	  	});
		
		$('#ativaIncluirQuestao').click(function() {
			$('#formIncluirQuestao').show();
			$(this).hide();
		});
		
		$('.ativaAlterarQuestao').click(function() {
			var idBotaoClicado = $(this).attr('id');
			var numero = parseInt(idBotaoClicado.replace(/\D/g, ''), 10);
			$('#formAlterarQuestao'+numero).show();
			$(this).hide();
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
.fieldsetSemFormatacao{
	border:none;
	padding: 0px;
}
</style>
</head>
<body>
	<h1>Academic Devoir</h1>
	<h2>Grupo 1 - Engenharia de Software</h2>
	<div id="menu">
		<%@ include file="../questoes/menu.jsp" %><br/>
	</div>
	
	<h3>Propriedades da Lista</h3>
	<table>
		<tr>
			<td>Nome:</td>
			<td>${listaDeExercicios.nome }</td>
		</tr>
		<tr>
			<td>Enunciado:</td>
			<td>${listaDeExercicios.enunciado }</td>
		</tr>
		<tr>
			<td>Visível:</td>
			<td>
				<c:choose>
					<c:when test="${listaDeExercicios.visivel }">Sim</c:when>
					<c:otherwise>Não</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td>Peso:</td>
			<td>${listaDeExercicios.peso }</td>
		</tr>
		<tr>
			<td>Prazo de Entrega:</td>
			<td>${prazo }</td>
		</tr>
	</table>
	<form action="<c:url value="/listasDeExercicios/altera/${listaDeExercicios.id }"/>">
		<fieldset class="fieldsetSemFormatacao">
			<button type="submit">Alterar</button>
		</fieldset>
	</form>
	
	<h3>Turmas</h3>
	
	<div>
		<table>
			<c:forEach items="${listaDeExercicios.turmas }" var="turma" varStatus="status">
				<tr>
					<td>${turma.nome }</td>
					<td>
						<form action="<c:url value="/listasDeExercicios/${listaDeExercicios.id }/turmas/${status.index }" />" method="post" accept-charset="us-ascii">
							<fieldset class="fieldsetSemFormatacao">
								<button type="submit" name="_method" value="delete">Remover</button>
							</fieldset>
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
		
	<div>
		<button id="ativaIncluirTurma" type="button">Incluir nova Turma</button>
		<form id="formIncluirTurma" action="<c:url value="/listasDeExercicios/${listaDeExercicios.id }/turmas/inclui"/>" method="post">
			<fieldset class="fieldsetSemFormatacao">
				<label for="idDaTurma">ID da Turma:</label>
				<input type="text" size="6" maxlength="6" name="idDaTurma" />
				<button type="submit" name="_method" value="put">Incluir</button>
			</fieldset>
		</form>	
	</div>
	
	<h3>Questões</h3>
	
	<div>
		<c:forEach items="${listaDeExercicios.questoes }" var="questaoDaLista" varStatus="status">
				<table>
					<tr>
						<td>${status.index+1 }. ${questaoDaLista.questao.enunciado }</td>
						<td>
							<button id="ativaAlterarQuestao${status.index }" class="ativaAlterarQuestao" type="button">Alterar Questão</button>
							<form id="formAlterarQuestao${status.index }" action="<c:url value="/listasDeExercicios/${listaDeExercicios.id }/questoes/${status.index }"/>" method="post" accept-charset="us-ascii">							
								<fieldset class="fieldsetSemFormatacao">
									<label for="idDaNovaQuestao">ID da nova Questão:</label>
									<input type="text" size="6" maxlength="6" name="idDaNovaQuestao" />
									<button type="submit" name="_method" value="put">alterar</button>
								</fieldset>
							</form>
						</td>
						<td>
							<form action="<c:url value="/listasDeExercicios/${listaDeExercicios.id }/questoes/${status.index }"/>" method="post" accept-charset="us-ascii">
								<fieldset class="fieldsetSemFormatacao">
									<button type="submit" name="_method" value="delete">Remover</button>
								</fieldset>
							</form>
						</td>							
					</tr>						
				</table>
		</c:forEach>
		
		<button id="ativaIncluirQuestao" type="button">Incluir nova Questao</button>
		
		<form id="formIncluirQuestao" action="<c:url value="/listasDeExercicios/${listaDeExercicios.id }/questoes/inclui"/>" method="post">
			<fieldset class="fieldsetSemFormatacao">
				
				<label id="labelIdDaQuestao" for="idDaQuestao">ID da Questao:</label>
				<input id="idDaQuestao" type="text" size="6" maxlength="6" name="idDaQuestao" />
				<button id="incluiQuestao" type="submit" name="_method" value="put">Incluir</button>
			</fieldset>
		</form>
	</div>
	
</body>
</html>