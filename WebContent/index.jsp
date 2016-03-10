<%@ page import="com.ynov.transpotter.helpers.GeoHelper" %>
<%@ page import="com.ynov.transpotter.models.Coordinates" %>
<%


System.out.println("raliement : "+GeoHelper.getNearestOnSegment(44.3591281, 2.5523449, 43.181034, 3.020214, 43.595265, 1.434293 ));
System.out.println("raliement : "+GeoHelper.getDistanceTest(43.628358, 1.465174, 43.584346, 1.451786));
%>
<%@ page language="java" isELIgnored="false"
	contentType="text/html;charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;
charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<link type="text/css" rel="stylesheet" href="css/styles.css" />
<title>Transp'Otter</title>
</head>
<body>
	<c:import url="/WEB-INF/menu/menu.jsp" />
	<div class="container">
		<p>Bienvenue dans notre exemple de login avec Spring MVC</p>
	</div>
</body>
</html>
>>>>>>> c99d889909aa6e7246de0f30cef6df325bf47501
