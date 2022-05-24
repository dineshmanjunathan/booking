<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@ include file="header.jsp"%>
<title>Insert title here</title>
<style type="text/css">
@import url(//fonts.googleapis.com/css?family=Montserrat:300,400,500);

.contact4 {
	font-family: "Montserrat", sans-serif;
	color: #8d97ad;
	font-weight: 300;
}

.contact4 h1, .contact4 h2, .contact4 h3, .contact4 h4, .contact4 h5,
	.contact4 h6 {
	color: #3e4555;
}

.contact4 .font-weight-medium {
	font-weight: 500;
}

.contact4 .form-control {
	background: transparent;
	border: 2px solid rgba(255, 255, 255, 0.5);
}

.contact4 .form-control:focus {
	border-color: #ffffff;
}

.contact4 input::-webkit-input-placeholder, .contact4 textarea::-webkit-input-placeholder
	{
	color: rgba(255, 255, 255, 0.7);
}

.contact4 input:-ms-input-placeholder, .contact4 textarea:-ms-input-placeholder
	{
	color: rgba(255, 255, 255, 0.7);
}

.contact4 input::placeholder, .contact4 textarea::placeholder {
	color: rgba(255, 255, 255, 0.7);
}

.contact4 .right-image {
	position: absolute;
	right: 0;
	bottom: 0;
	top: 0;
}

.contact4.bg-info {
	background-color: #188ef4 !important;
}

.contact4 .text-inverse {
	color: #3e4555 !important;
}

@media ( min-width : 1024px) {
	.contact4 .contact-box {
		padding: 80px 105px 80px 0px;
	}
}

@media ( max-width : 767px) {
	.contact4 .contact-box {
		padding-left: 15px;
		padding-right: 15px;
	}
}

@media ( max-width : 1023px) {
	.contact4 .right-image {
		position: relative;
		bottom: -95px;
	}
}
</style>
</head>
<body>

	<div class="col-md-10 col-md-offset-2 row">
		<div class="row">
			<div class="row">
			<c:set var="url" value="#"></c:set>
				<c:choose>
					<c:when test="${sessionScope.ROLE == 'MEMBER' }">
						<c:set var="url" value="/menu"></c:set>
					</c:when>
					<c:otherwise>
						<c:set var="url" value="/stock/point/menu"></c:set>
					</c:otherwise>
				</c:choose>
				<a href="${url}"
					class="rmk btn btn-primary m-btn m-btn--custom m-btn--icon col-md-offset-1 col-md-2">
					<span><i class="fa fa-arrow-left"></i> <span>Back to
							Main</span> </span>
				</a>
			</div>
			<div class="bg-info contact4 overflow-hiddedn position-relative">
				<!-- Row  -->
				<div class="row no-gutters">
					<div class="container">
						<div class="col-lg-6 contact-box mb-4 mb-md-0">
							<div class="">
								<h1 class="title font-weight-light text-white mt-2">Contact	Us</h1><br><br>
								<div class="row" style="font-style: oblique;">
								<h5 class=" font-weight-light text-white mt-2">support@ssmarket.com</h5>
								<h5 class=" font-weight-light text-white mt-2">0422-2442525</h5>
								<h5 class=" font-weight-light text-white mt-2">KCP-Nagar , Pn Palayam,</h5>
								<h5 class=" font-weight-light text-white mt-2">Cbe - 640 020</h5><br>
								</div>
<!-- 								<form class="mt-3"> -->
<!-- 									<div class="row"> -->
<!-- 										<div class="col-lg-12"> -->
<!-- 											<div class="form-group mt-2"> -->
<!-- 												<input class="form-control text-white" type="text" -->
<!-- 													placeholder="name"> -->
<!-- 											</div> -->
<!-- 										</div> -->
<!-- 										<div class="col-lg-12"> -->
<!-- 											<div class="form-group mt-2"> -->
<!-- 												<input class="form-control text-white" type="email" -->
<!-- 													placeholder="email address"> -->
<!-- 											</div> -->
<!-- 										</div> -->
<!-- 										<div class="col-lg-12"> -->
<!-- 											<div class="form-group mt-2"> -->
<!-- 												<textarea class="form-control text-white" rows="3" -->
<!-- 													placeholder="message"></textarea> -->
<!-- 											</div> -->
<!-- 										</div> -->
<!-- 										<div class="col-lg-12 d-flex align-items-center mt-2"> -->
<!-- 											<button type="submit" -->
<!-- 												class="btn bg-white text-inverse px-3 py-2"> -->
<!-- 												<span> Submit</span> -->
<!-- 											</button> -->
<!-- 											<span class="ml-auto text-white align-self-center"><i -->
<!-- 												class="icon-phone mr-2"></i>251 546 9442</span> -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 								</form> -->
							</div>
						</div>
					</div>
					<div class="col-lg-6 right-image p-r-0">
						<iframe
							src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d1619902.0054433027!2d-122.68851282163044!3d37.534535608111824!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x80859a6d00690021%3A0x4a501367f076adff!2sSan+Francisco%2C+CA%2C+USA!5e0!3m2!1sen!2sin!4v1507725785789"
							width="100%" height="538" frameborder="0" style="border: 0"
							allowfullscreen data-aos="fade-left" data-aos-duration="3000"></iframe>
					</div>
				</div>
			</div>
		</div>
	</div>


</body>
</html>