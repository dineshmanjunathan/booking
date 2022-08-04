<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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

.wrapper {
	min-height: 65vh;
	display: flex;
	align-items: center;
	background-size: cover;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
</head>

<body>
	<form method="post">
		<div class="wrapper">
			<div class="inner">
				<div style="width: 365px;">
					<img src="../../img/product/parcel.jpg" alt="">
				</div>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<div class="blog-details-area mg-b-15">
					<div class="container-fluid" style="width: 100%;">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
								<div class="blog-details-inner">
									<br>
									<br>
									<br>

									<div class="row control-margin">
										<div class="col-md-12 control-margin">
											<a href="/bookinginventory"><button style="height: 80px;"
													type="button" class="btn btn-primary button-margin" id="BB">Booking
													Inventory</button></a>
											<c:if test="${sessionScope.ROLE ne 'BOOKING USER'}">
												<a href="/deliveryinventory"><button
														style="height: 80px;" type="button"
														class="btn btn-primary button-margin" id="BB">Delivery
														Inventory</button></a>
											</c:if>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>