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
	<div id="menu">
		<%@ include file="../questoes/menu.jsp" %><br/>
	</div>
		
	<h3>Listas de Exercícios</h3>
	
	<div>
		<ul>
			<c:forEach items="${listaDeListas }" var="lista">
				<c:if test ="${usuarioSession.usuario.privilegio == 'PROFESSOR' || usuarioSession.usuario.privilegio == 'ADMINISTRADOR' || usuarioSession.usuario.privilegio == 'MONITOR'}">
        			<li><a href="<c:url value="/listasDeExercicios/${lista.id }"/>">${lista.propriedades.nome }</a></li>              
    			</c:if>
                	
                <c:if test ="${usuarioSession.usuario.privilegio == 'ALUNO'}">
        			<li><a href="<c:url value="/listasDeExercicios/resolver/${lista.id }"/>">${lista.propriedades.nome }</a></li>
 				</c:if>
				
			</c:forEach>
		</ul>
	</div>	
	<form action="<c:url value="/listasDeExercicios/cadastro"/>">
		<fieldset class="fieldsetSemFormatacao">
			<!--input type="submit" value="Cadastrar nova lista"></input-->
			<a href="<c:url value='/listasDeExercicios/cadastro'/>">Cadastrar nova lista</a><br/>	
		</fieldset>
	</form>
	<a href="<c:url value='/login'/>">Sair</a>
	<!-- a href="<c:url value='questoes'/>">Voltar</a><br/ -->	
    <c:if test ="${usuarioSession.usuario.privilegio == 'PROFESSOR' || usuarioSession.usuario.privilegio == 'ADMINISTRADOR'}">
    	<a href="<c:url value='/professores/home'/>">Página Principal</a><br/>              
    </c:if>
    <c:if test ="${usuarioSession.usuario.privilegio == 'ALUNO' || usuarioSession.usuario.privilegio == 'MONITOR'}">
    	<a href="<c:url value='/alunos/home'/>">Página Principal</a><br/>              
 	</c:if>
</body>
</html>