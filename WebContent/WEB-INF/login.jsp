<%@ page language="java" isELIgnored="false" contentType="text/html;charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<meta http-equiv="Content-Type" content="text/html;
	charset=ISO-8859-1">
	<title>Login</title>
	<link type="text/css" rel="stylesheet" href="styles.css" />
</head>
<body>
    <c:import url="/WEB-INF/menu/menu.jsp" />
    <div class="container">
	   <c:import url="/WEB-INF/login/form.jsp" />
   </div>
</body>
</html>