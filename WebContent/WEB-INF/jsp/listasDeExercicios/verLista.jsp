<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" language="java"
import="java.sql.*" errorPage="" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<script type="text/javascript" charset="utf-8" src="<c:url value="/javascript/jquery-1.7.1.min.js"/>"></script>

<script type="text/javascript" charset="utf-8">	
	$(document).ready(function() {		
		$('#formIncluirTurma').hide();
		$('#formIncluirQuestao').hide();
		$('[id^=formAlterarQuestao]').hide();
		
		$('#ativaIncluirTurma').click(function() {
			$('#formIncluirTurma').show();
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
<%@ include file="/css/form2.css" %>
</style>
<title>Academic Devoir</title>
</head>

<body>
	<div id="wrapper"> 
	<div id="header"> <%@ include file="/css/header.jsp" %></div> <br/>
	<div id="left"><fieldset><%@ include file="/css/menu.jsp" %></fieldset></div>
	<div id="right">
	<div id="menu">Propriedades da Lista</div>
	<br/><br/>
		
	<table>
		<tr>
			<td>Nome:</td>
			<td>${listaDeExercicios.propriedades.nome }</td>
		</tr>
		<tr>
			<td>Enunciado:</td>
			<td>${listaDeExercicios.propriedades.enunciado }</td>
		</tr>
		<tr>
			<td>Turma:</td>
			<td>${listaDeExercicios.turma.disciplina.nome } - ${listaDeExercicios.turma.nome }</td>
		</tr>
		<tr>
			<td>Visível:</td>
			<td>
				<c:choose>
					<c:when test="${listaDeExercicios.propriedades.visivel }">Sim</c:when>
					<c:otherwise>Não</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td>Peso:</td>
			<td>${listaDeExercicios.propriedades.peso }</td>
		</tr>
		<tr>
			<td>Prazo de Entrega:</td>
			<td>${prazo }</td>
		</tr>
		<tr>
			<td>Número Máximo de Envios:</td>
			<td>
				<c:choose>
					<c:when test="${empty (listaDeExercicios.propriedades.numeroMaximoDeEnvios) or (listaDeExercicios.propriedades.numeroMaximoDeEnvios eq 0)}">
						Ilimitado
					</c:when>
					<c:otherwise>
						${listaDeExercicios.propriedades.numeroMaximoDeEnvios }
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td>Auto Correção:</td>
			<td>
				<c:choose>
					<c:when test="${listaDeExercicios.propriedades.autoCorrecao eq 'DESATIVADA' }">
						Desabilitada
					</c:when>
					<c:when test="${listaDeExercicios.propriedades.autoCorrecao eq 'PROFESSOR' }">
						Somente Professor
					</c:when>
					<c:otherwise>
						Professor e Aluno
					</c:otherwise>
				</c:choose>
			</td>
		</tr>		
	</table>
	<form action="<c:url value="/listasDeExercicios/altera/${listaDeExercicios.id }"/>">
		<fieldset class="fieldsetSemFormatacao">
			<button type="submit">Alterar</button>
			<a href="<c:url value='/listasDeExercicios/resolver/${listaDeExercicios.id }'/>">Resolver Lista</a>
			<a href="<c:url value='/listasDeExercicios/autocorrecao/${listaDeExercicios.id }'/>">Correção Automática</a>
		</fieldset>
	</form>
	
	<form action="<c:url value="/listasDeExercicios/${listaDeExercicios.id }"/>" method="post">
		<fieldset class="fieldsetSemFormatacao">
			<button type="submit" name="_method" value="delete">Excluir Lista</button>
		</fieldset>
	</form>
	
	<h3>Questões</h3>
	
	<div>
		<c:forEach items="${listaDeExercicios.questoes }" var="questaoDaLista" varStatus="status">
				<table>
					<tr>
						<td>${status.index+1 }. ${questaoDaLista.questao.enunciado }</td>
						<td>
							<button id="ativaAlterarQuestao${status.index }" class="ativaAlterarQuestao" type="button">Alterar Questão</button>
							<form id="formAlterarQuestao${status.index }" action="<c:url value="/listasDeExercicios/${listaDeExercicios.id }/questoes/${status.index }"/>" method="post" accept-charset="utf-8">							
								<fieldset class="fieldsetSemFormatacao">
									<label for="idDaNovaQuestao">ID da nova Questão:</label>
									<input type="text" value="${questaoDaLista.questao.id}" size="6" maxlength="6" name="idDaNovaQuestao" />
									<input type="text" value="${questaoDaLista.ordem}" size="6" maxlength="6" name="ordemDaQuestao" />
									<button type="submit" name="_method" value="put">alterar</button>
								</fieldset>
							</form>
						</td>
						<td>
							<form action="<c:url value="/listasDeExercicios/${listaDeExercicios.id }/questoes/${status.index }"/>" method="post" accept-charset="utf-8">
								<fieldset class="fieldsetSemFormatacao">
									<button type="submit" name="_method" value="delete">Remover</button>
								</fieldset>
							</form>
						</td>							
					</tr>						
				</table>
		</c:forEach>
		
		<a href="<c:url value='${listaDeExercicios.id }/inclusaoQuestoes?proxPagina=1&filtro='/>">Incluir nova Questão</a>
		
	</div>
	</div>
	</div>
</body>
</html>