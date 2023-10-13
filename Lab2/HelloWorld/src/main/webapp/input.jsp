<%--
  Created by IntelliJ IDEA.
  User: Gabriel
  Date: 13/10/2023
  Time: 08:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Upload DIMACS Graph</title>
</head>
<body>
<h1>Upload a DIMACS Graph</h1>
<form action="FileUploadServlet" method="post" enctype="multipart/form-data">
    <input type="file" name="file" /><br />
    <input type="submit" value="Upload" />
</form>
</body>
</html>
