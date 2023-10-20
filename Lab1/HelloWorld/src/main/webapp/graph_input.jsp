<!DOCTYPE html>
<html>
<head>
    <title>Graph Input</title>
</head>
<body>
<h2>Generate Random Tree</h2>
<form action="${pageContext.request.contextPath}/GraphGenerator" method="get">
    Number of Vertices: <input type="text" name="numVertices"><br>
    <input type="submit" value="Generate Tree">
</form>
</body>
</html>
