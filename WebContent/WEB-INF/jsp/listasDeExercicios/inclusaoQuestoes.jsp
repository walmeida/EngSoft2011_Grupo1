<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" language="java"
import="java.sql.*" errorPage="" %>

<html>

<head>
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
	<div id="menu">Incluir Questões na Lista</div>
	<br/><br/>
	
		
		<form style="width: 25em;" id="formIncluirQuestao" action="<c:url value="/listasDeExercicios/${idDaListaDeExercicios }/inclusaoQuestoes"/>" method="get">
			<!--  <fieldset>
				<legend>Filtrar por Tags</legend> -->
				<span>Filtra por Tags: </span>
				<input type="hidden" id="proxPag" name="proxPagina" value="1" />
				<input type="text" id="filtro" name="filtro" />
				<input type="submit" value="Filtrar"  />
			<!--  </fieldset> -->
		</form>
		
	
	<br/>
		
	
		<table>
			<thead>
				<tr>
					<th>ID</th>
					<th>Enunciado</th>
					<th>Inclusão</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${listaDeQuestoes }" var="questao">
					<tr>
						<td>${questao.id }</td>
						<td>${questao.enunciado }</td>
						<td>
							<form style="width: 25em;" id="formIncluirQuestao" action="<c:url value="/listasDeExercicios/${idDaListaDeExercicios }/questoes/inclui"/>" method="post">
								<fieldset class="fieldsetSemFormatacao">
									<input id="idDaQuestao" type="hidden" name="idDaQuestao" value="${questao.id }" />
									<label id="labelPesoDaQuestao" for="pesoDaQuestao">Peso:</label>
									<input id="pesoDaQuestao" type="text" size="6" maxlength="6" name="pesoDaQuestao" />
									<button id="incluiQuestao" type="submit" name="_method" value="put">Incluir</button>
								</fieldset>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

			<span>Páginas: </span>
			
			<c:if test ="${pagina > 1}">
				<span>&nbsp;&nbsp;</span><a href="<c:url value="inclusaoQuestoes?proxPagina=${pagina - 1}&filtro=${filtroAtual}"/>">&lt;&lt;</a>
			</c:if>
			
			<!--<c:forEach items="${listaDePaginasAnteriores }" var="numeroPagina">
				<a href="<c:url value="${listaDeExercicios.id }/inclusaoQuestoes?proxPagina=${numeroPagina}"/>">&nbsp;&nbsp;${numeroPagina}</a>
			</c:forEach>-->
			
			<span>&nbsp;&nbsp;${pagina}</span>
			
			<!--<c:forEach items="${listaDePaginasPosteriores }" var="numeroPagina">
				<a href="<c:url value="${listaDeExercicios.id }/inclusaoQuestoes?proxPagina=${numeroPagina}"/>">&nbsp;&nbsp;${numeroPagina}</a>
			</c:forEach>-->
						
			<c:if test ="${pagina < ultimaPagina}">
				<span>&nbsp;&nbsp;</span><a href="<c:url value="inclusaoQuestoes?proxPagina=${pagina + 1}&filtro=${filtroAtual}"/>">&gt;&gt;</a>
			</c:if>

	<br/>
	<form id="cadastrarQuestao" action="/academic-devoir/questoes/cadastro">
		<fieldset class="fieldsetSemFormatacao">
			<!--input type="submit" value="Cadastrar nova questão"></input-->
			<a href="<c:url value='/questoes/cadastro'/>">Cadastrar nova questão</a>
		</fieldset>
	</form>
	</div>
	</div>
</body>
</html>