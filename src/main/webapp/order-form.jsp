<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>User Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="https://www.javaguides.net" class="navbar-brand"> User
					Management App </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/listOrder"
					class="nav-link">ORDERS</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">

				<c:if test="${order != null}">
					<form action="updateOrder" method="put">
		
				</c:if>
				<c:if test="${order == null}">
					<form action="insertOrder" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${order != null}">
            			Editar Order
            		</c:if>
						<c:if test="${order == null}">
            			Crear Order
            		</c:if>
					</h2>
				</caption>

				<c:if test="${order != null}">
					<input type="hidden" name="id_order"
						value="<c:out value='${order.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Item</label> <input type="text"
						value="<c:out value='${order.item}' />" class="form-control"
						name="item">
				</fieldset>
			
				<fieldset class="form-group">
					<label>Precio</label> <input type="text"
						value="<c:out value='${order.precio}' />"
						class="form-control" name="precio">
				</fieldset>
			<fieldset class="form-group">
					<label>Customer Id</label> <input type="text"
						value="<c:out value='${order.customer_id}' />"
						class="form-control" name="customer_id">
				</fieldset>


				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>