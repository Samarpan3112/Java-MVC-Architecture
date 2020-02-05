<html>
<head>
<title>Get Statement</title>
</head>
<body>
<%
session=request.getSession();
out.println("CREDITS:"+ session.getAttribute("Credit"));
out.println();
out.println("DEBITS:"+ session.getAttribute("Debit"));
%>

</body>
</html>