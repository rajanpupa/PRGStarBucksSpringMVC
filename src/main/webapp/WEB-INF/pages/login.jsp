<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<c:set var="disabled" scope="request" value="disabled=\"disabled\"" />
	<c:if test="${username != null }">
		<c:set var="disabled" scope="request" value="" />
		<p>Logged in as : ${username } | <a href="logout">Logout</a></p>
	</c:if>

	<form method="get" action="select" ${disabled } >
		<select name="type" ${disabled }>
			<option value="dark">Dark</option>
			<option value="medium">Medium</option>
			<option value="light">Light</option>
		</select><br /> <input type="submit" value="Select" ${disabled } />
	</form>
	<c:if test="${errors != null && errors.size()>0}">

		<p id="errors">Error(s)!
		<ul>
			<c:forEach var="error" items="${errors}">
				<li>${error}</li>
			</c:forEach>
		</ul>


	</c:if>
	<c:if test="${username == null }">
		<form action="login" method="post">
			Name: <input name="username" /><br /> Password: <input
				name="password" /><br /> <input type="submit" value="submit">
		</form>
	</c:if>
</body>
</html>