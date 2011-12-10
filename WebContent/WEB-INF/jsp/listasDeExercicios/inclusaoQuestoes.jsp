<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=utf-8" language="java"
import="java.sql.*" errorPage="" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
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
		
	<h3>Incluir Questões na Lista</h3>
		
	<div>
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
							<form id="formIncluirQuestao" action="<c:url value="/listasDeExercicios/${idDaListaDeExercicios }/questoes/inclui"/>" method="post">
								<fieldset class="fieldsetSemFormatacao">
									<input id="idDaQuestao" type="hidden" name="idDaQuestao" value="${questao.id }" />
									<label id="labelPesoDaQuestao" for="pesoDaQuestao">Peso:</label>
									<input id="pesoDaQuestao" type="text" size="6" maxlength="6" name="pesoDaQuestao" />
									<label id="labelOrdemDaQuestao" for="ordemDaQuestao">Ordem:</label>
									<input id="ordemDaQuestao" type="text" size="6" maxlength="6" name="ordemDaQuestao" />
									<button id="incluiQuestao" type="submit" name="_method" value="put">Incluir</button>
								</fieldset>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div>
			<span>Páginas: </span>
			
			<c:if test ="${pagina > 1}">
				<span>&nbsp;&nbsp;</span><a href="<c:url value="inclusaoQuestoes?proxPagina=${pagina - 1}"/>">&lt;&lt;</a>
			</c:if>
			
			<!--<c:forEach items="${listaDePaginasAnteriores }" var="numeroPagina">
				<a href="<c:url value="${listaDeExercicios.id }/inclusaoQuestoes?proxPagina=${numeroPagina}"/>">&nbsp;&nbsp;${numeroPagina}</a>
			</c:forEach>-->
			
			<span>&nbsp;&nbsp;${pagina}</span>
			
			<!--<c:forEach items="${listaDePaginasPosteriores }" var="numeroPagina">
				<a href="<c:url value="${listaDeExercicios.id }/inclusaoQuestoes?proxPagina=${numeroPagina}"/>">&nbsp;&nbsp;${numeroPagina}</a>
			</c:forEach>-->
						
			<c:if test ="${pagina < ultimaPagina}">
				<span>&nbsp;&nbsp;</span><a href="<c:url value="inclusaoQuestoes?proxPagina=${pagina + 1}"/>">&gt;&gt;</a>
			</c:if>
			
		</div>
	</div>
	<br/>
	<form id="cadastrarQuestao" action="/academic-devoir/questoes/cadastro">
		<fieldset class="fieldsetSemFormatacao">
			<!--input type="submit" value="Cadastrar nova questão"></input-->
			<a href="<c:url value='questoes/cadastro'/>">Cadastrar nova questão</a>
		</fieldset>
	</form>
	<a href="<c:url value='/login'/>">Sair</a>
    <a href="<c:url value='/professores/home'/>">Página Principal</a><br/>
</body>
</html>