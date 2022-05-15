<%@page import="com.Customer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/customers.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>Customer Management V10.1</h1>
				<form id="formCustomer" name="formCustomer" method="post" action="customers.jsp">
					    User name: <input id="username" name="username" type="text"
						class="form-control form-control-sm"> <br>
						Account Number: <input id="accountno" name="accountno" type="text"
						class="form-control form-control-sm"> <br> 
						Phone Number: <input id="phonenumber" name="phonenumber" type="text"
						class="form-control form-control-sm"> <br> 
						Address: <input id="address" name="address" type="text"
						class="form-control form-control-sm"> <br> 
						<input id="btnSave" name="btnSave" type="button" value="Save"
						class="btn btn-primary">
						 <input type="hidden"
						id="hidCustomerIDSave" name="hidCustomerIDSave" value="">
				</form>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divCustomerGrid">
					<%
					Customer CustomerObj = new Customer();
					out.print(CustomerObj.readCustomers());
					%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>