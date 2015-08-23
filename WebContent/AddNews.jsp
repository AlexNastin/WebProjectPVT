<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AddNews</title>
</head>
<body>
	<form action="AdminController">
		<input type="hidden" name="operation" value="addwrite">
		<table border="1">
			<tr>
				<td>Id:</td>
				<td><input name="id"><br></td>
				<td>Example: 1,4,14,543</td>
			</tr>
			<tr>
				<td>Title:</td>
				<td><input name="title" ><br></td>
				<td>Any text</td>
			</tr>
			<tr>
				<td>Category:</td>
				<td><input name="category"> <br></td>
				<td>Any text</td>
			</tr>
			<tr>
				<td>Annotation:</td>
				<td><input name="annotation"><br></td>
				<td>Any text</td>
			</tr>
			<tr>
				<td>Author:</td>
				<td><input name="author" value="<%= session.getAttribute("email") %>" readonly="readonly"><br></td>
				<td>Email author</td>
			</tr>
			<tr>
				<td>Date:</td>
				<td><input name="date"><br></td>
				<td>Example: 2014-04-24</td>
			</tr>

		</table>
		Text:<br>
		<textarea rows="10" cols="50" name="text"></textarea>
		<br> <input type="submit" value="Save">
	</form>
</body>
</html>