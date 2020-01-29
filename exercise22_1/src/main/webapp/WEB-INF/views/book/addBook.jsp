<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml11.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add a Book</title>
</head>
<body>
	<spring:url value="/books/" var="action" />
	<form:form modelAttribute="book" action="${action}" method="post">
	<table>
		<tr>
			<td>Title:</td>
			<td><form:input path="title" /></td>
			<td><form:errors path="title" cssClass="error"/></td>
		</tr>
		<tr>
			<td>Author:</td>
			<td><form:input path="author" /> </td>
			<td><form:errors path="author" cssClass="error"/></td>
		</tr>
		<tr>
			<td>ISBN:</td>
			<td><form:input path="ISBN" /> </td>
			<td><form:errors path="ISBN" cssClass="error"/></td>
		</tr>
		<tr>
			<td>Price:</td>
			<td><form:input path="price" /> </td>
			<td><form:errors path="price" cssClass="error"/></td>
		</tr>
		<tr>
			<td>Published Date:</td>
			<td><form:input path="publishedDate" /> </td>
			<td><form:errors path="publishedDate" cssClass="error"/></td>
		</tr>
	</table>
	<input type="submit"/>
	
	</form:form>
</body>
</html>