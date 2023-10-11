<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ page isErrorPage="true" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<!-- for Bootstrap CSS -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />

<title>Login and Registration</title>
</head>
<body>
	<div class="container mt-5">
		<h1>Register</h1>
		<form:form action="/register" method="POST" modelAttribute="newUser">
			<div class="form-group">
				<form:label path="userName"> User Name: </form:label>
				<form:input path="userName" class="form-control" /> 
				<form:errors path="userName" class="text-danger" /> 
			</div>
			<div class="form-group">
				<form:label path="email"> Email: </form:label>
				<form:input path="email" class="form-control" /> 
				<form:errors path="email" class="text-danger" /> 
			</div>
			<div class="form-group">
				<form:label path="password"> Password: </form:label>
				<form:input path="password" class="form-control" type="password" /> 
				<form:errors path="password" class="text-danger" /> 
			</div>
			<div class="form-group">
				<form:label path="confirm"> Confirm Password: </form:label>
				<form:input path="confirm" class="form-control" type="password" /> 
				<form:errors path="confirm" class="text-danger" /> 
			</div>
			<button type="submit" class="btn btn-primary mt-3"> Submit</button>
		</form:form>
	
		<h1 class="mt-5">Login</h1>
		<form:form action="/login" method="POST" modelAttribute="newLogin">
			<div class="form-group">
				<form:label path="email"> Email: </form:label>
				<form:input path="email" class="form-control" /> 
				<form:errors path="email" class="text-danger" /> 
			</div>
			<div class="form-group">
				<form:label path="password"> Password: </form:label>
				<form:input path="password" class="form-control" type="password" /> 
				<form:errors path="password" class="text-danger" /> 
			</div>
			<button type="submit" class="btn btn-primary mt-3"> Submit</button>
		</form:form>
	</div>
</body>
<!-- For any Bootstrap that uses JS -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</html>