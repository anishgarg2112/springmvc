
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Registration</title>
<style>
.error {
	color: red;
	font-weight: bold;
}
</style>
</head>
<body>
	<div align="center">
		<h2>Enroll Associate Details Here</h2>
		<table>

			<form:form action="registerCustomer" method="post"
				modelAttribute="customer">

				<tr>
					<td>FirstName:</td>
					<td><form:input path="name" size="30" /></td>
					<td><form:errors path="name" cssClass="error" /></td>
				</tr>

				<tr>
					<td>Phone No.:</td>
					<td><form:input path="mobileNo" size="30" /></td>
					<td><form:errors path="mobileNo" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Balance</td>
					<td><form:input path="wallet.balance" size="30" /></td>
					<td><form:errors path="wallet.balance" cssClass="error" /></td>
				</tr>

				<tr>
					<td><input type="submit" value="Submit" /></td>
				</tr>
			</form:form>
		</table>
	</div>
</body>
</html>
