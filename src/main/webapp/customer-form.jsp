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
					class="nav-link">Customers</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">

				<c:if test="${customer != null}">
					<form action="update" method="put">
				</c:if>
				<c:if test="${customer == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${customer != null}">
            			Editar Customer
            		</c:if>
						<c:if test="${customer == null}">
            			Crear Customer
            		</c:if>
					</h2>
				</caption>

				<c:if test="${customer != null}">
					<input type="hidden" name="id_customer"
						value="<c:out value='${customer.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Nombre </label> <input type="text"
						value="<c:out value='${customer.name}' />" class="form-control"
						name="name">
				</fieldset>

				<fieldset class="form-group">
					<label>Surname</label> <input type="text"
						value="<c:out value='${customer.surname}' />" class="form-control"
						name="surname">
				</fieldset>



				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>