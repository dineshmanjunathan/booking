<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="header.jsp"%>

</head>

<body>

	<div class="wrapper">
		<div class="inner">
		<div class="image-holder">
				<img src="../../img/product/parcel.jpg" alt="">
			</div>
			
			      <form action = "/uploadUserData" method = "post"
         enctype = "multipart/form-data">
         <h3>Upload User
         </h3>
			<div class="row element-margin">
			
			
			
													<div class="col-sm-4">
														<label class="form-label" for="weight">Select File</label>
													</div>
													<div class="col-sm-8">
														         <input type = "file" name = "file" readonly/>
														<br />
         														<input type = "submit" class="btn btn-primary button-margin"  value="submit" />
																	</div>
												</div>
			
			
			</form>
	</div>

</div>
	<script src="js/main.js"></script>
	

</body>
<!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>