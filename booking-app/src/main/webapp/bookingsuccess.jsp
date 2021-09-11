<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="header.jsp"%>
<style>
.element-margin {
	margin-bottom: 5px;
}

.button-margin {
	margin-right: 5px;
}
</style>
</head>

<body >
<div class="wrapper">
		<div class="inner">
			<div style="width: 365px;">
				<img src="../../img/product/parcel.jpg" alt="">
			</div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<div class="blog-details-area mg-b-15">
		<div class="container-fluid" style="width: 100%;">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="blog-details-inner">
						<br><br>
						<p style="color: green" align="center">${bookingsuccessmessage}</p>
						<p style="color: green;font-weight: bold; " align="center">${LRNumber}</p>
						<br>
						<a href="/booking"><button type="button" class="btn btn-primary button-margin col-md-12" id="btnClear">Back to Booking</button></a>
				</div>
			</div>
		</div>
	</div>
	</div>
	</div>
	</div>

</body>
</html>
