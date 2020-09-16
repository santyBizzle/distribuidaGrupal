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
			<div>
				<h3 class="text-center" style="color: white-space:;" align="center">UNIVERSIDAD
					CENTRAL DEL ECUADOR - PROGRAMACION DISTRIBUIDA - GRUPO 3</h3>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link"></a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="row">
		<div class="container">
			<h3 class="text-center">Lista de Orders</h3>
			<hr>
			<a href="<%=request.getContextPath()%>/newOrder"
				class="btn btn-success">Crear Orders</a> <br /> <br />
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>Id</th>
						<th>Item</th>
						<th>Precio</th>
						<th>Customer</th>
						<th>Administracion</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="order" items="${listorder}">
						<tr>
							<td><c:out value="${order.id}" /></td>
							<td><c:out value="${order.item}" /></td>
							<td><c:out value="${order.price}" /></td>

							<td><c:out value="${order.datos}" /></td>
							<td><a
								href="editOrder?id_order=<c:out value='${order.id}' />">Editar</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="deleteOrder?id_order=<c:out value='${order.id}' />">Eliminar</a></td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
			<a href="<%=request.getContextPath()%>/" class="btn btn-success">Regresar</a>
		</div>
	</div>
</body>
</html>
