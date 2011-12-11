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
	<div id="menu">Listas de exerc&iacute;cios</div>
	<br/><br/>
		
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
	</div>
	</div>
</body>
</html>