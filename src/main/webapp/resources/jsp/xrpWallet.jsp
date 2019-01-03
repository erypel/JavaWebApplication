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
		<h1>UID: ${userID} / WID: ${walletID}  / ${balance} XRP</h1>			
		<h1>${destTag}</h1>
	</div>
	<br>
	<a href="generateDestTag.action">
		<input type="button" value="Generate Destination Tag"/>
	</a>
	<br>
	<a href="paymentTransaction.action">Send Test Transaction</a>
</body>
</html>