<link rel="stylesheet" type="text/css" href="style.css">
<link href="https://fonts.googleapis.com/css?family=Lilita+One&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Raleway&display=swap" rel="stylesheet">
<html>
<head>
<title>Balance</title>
</head>
<body>
<%
session=request.getSession();
out.println("YOUR ACCOUNT BALANCE IS:"+ session.getAttribute("balance"));
%>

</body>
</html>