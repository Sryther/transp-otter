<%@ page language="java" isELIgnored="false"
	contentType="text/html;charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<!--importation de l'API google MAP Version 3-->
<script type="text/javascript"
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCDD1OG9WoJ401qEKMIRXNlYCKYINQQnkg"></script>
<script type="text/javascript"><c:import url="/WEB-INF/lib/maps.jsp"/></script>
</head>
<body onload="init()">
    <c:import url="/WEB-INF/menu/menu.jsp" />
	<div class="container">
		<c:import url="/WEB-INF/trip/search.jsp" />
	</div>
</body>
</html>