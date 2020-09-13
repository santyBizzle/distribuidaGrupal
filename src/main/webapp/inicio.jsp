<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>User Management Application</title>
<link href="webjars/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<script src="webjars/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: red">
			<div class="container text-center">
			<div>
				<h3 class="text-center" style="color: white-space:;" align="center">UNIVERSIDAD
					CENTRAL DEL ECUADOR - PROGRAMACION DISTRIBUIDA-GRUPO3</h3>
			</div>
</div>
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link"></a></li>
			</ul>
		</nav>
	</header>
	<br>

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">Realizado por : Freddy Roman - Byron Cabezas - Stalyn Gunza </h3>
			<hr>
			<div class="container text-center">

				<a href="<%=request.getContextPath()%>/listarCustomers"
					class="btn btn-success">Administrar Customers</a>
					
						<a href="<%=request.getContextPath()%>/listarOrders"
					class="btn btn-success">Administrar Orders</a>
			</div>
		

			<br>

		</div>
	</div>

</body>
</html>
