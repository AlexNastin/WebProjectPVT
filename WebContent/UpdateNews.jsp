<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>UpdateNews</title>
</head>
<body>
	<jsp:useBean id="news" class="dto.News" scope="request"></jsp:useBean>
	<form action="AdminController">
		<input type="hidden" name="operation" value="updatewrite">
		<table border="2">
			<tr>
				<td>Id:</td>
				<td><input name="id"
					value="<jsp:getProperty property="id" name="news"/>"
					readonly="readonly"><br></td>
				<td>Can not be changed</td>
			</tr>
			<tr>
				<td>Title:</td>
				<td><input name="title"
					value="<jsp:getProperty property="title" name="news"/>"><br></td>
				<td>Any text</td>
			</tr>
			<tr>
				<td>Category:</td>
				<td><input name="category"
					value="<jsp:getProperty property="category" name="news"/>">
					<br></td>
				<td>Any text</td>
			</tr>
			<tr>
				<td>Annotation:</td>
				<td><input name="annotation"
					value="<jsp:getProperty property="annotation" name="news"/>"><br></td>
				<td>Any text</td>
			</tr>
			<tr>
				<td>Author:</td>
				<td><input name="author"
					value="<jsp:getProperty property="author" name="news"/>"><br></td>
				<td>Email author</td>
			</tr>
			<tr>
				<td>Date:</td>
				<td><input name="date"
					value="<jsp:getProperty property="date" name="news"/>"><br></td>
				<td>Example: 2014-04-24</td>
			</tr>

		</table>
		Text:<br>
		<textarea rows="10" cols="50" name="text"><jsp:getProperty
				property="text" name="news" /></textarea>
		<br> <input type="submit" value="Save">
	</form>
</body>
</html>