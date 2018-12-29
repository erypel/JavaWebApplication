<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>XRP Wallet</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div id="balance">	
		<h1>{{ ripple_wallet.usd }} USD / {{ ripple_wallet.xrp }} XRP</h1>			
	</div>
</body>
</html>