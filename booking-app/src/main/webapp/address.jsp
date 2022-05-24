<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html class="no-js" lang="en">
<head>
<%@ include file="header.jsp"%>
<meta charset="ISO-8859-1">
<script type="text/javascript" charset="utf-8">
</script>
</head>
<body>
	<!-- Single pro tab review Start-->
	<div class="col-md-10 col-md-offset-1 row">
		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="product-payment-inner-st">
					<ul id="myTabedu1" class="tab-review-design">
						<li class="active"><a href="">Address</a></li>
					</ul>
					<!-- <form action="/userlisting" method="get"> -->
					<div class="payment-adress">
						<a
							class="rmk btn btn-primary waves-effect waves-light col-md-offset-10 col-md-2"
							href="/menu" type="submit" name="submit"
							value="adminListing">Back to Login</a>
					</div>
					<!-- </form> -->

					<div id="myTabContent" class="tab-content custom-product-edit">
						<div class="product-tab-list tab-pane fade active in"
							id="description">
							<div class="row">
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
									<div class="review-content-section">
										<form action="/register" method="post">

											<p style="color: red" align="center">${errormsg}</p>

											<input type="hidden" name="id" id="id" value="${member.id}">
											<input type="hidden" name="role" id="role" value="MEMBER">

											<div id="dropzone1" class="pro-ad">

												<p style="color: green" align="center">${successMessage}</p>
												<p style="color: red" align="center">${deletesuccessmessage}</p>

												<div class="well row">
													<div class=" col-lg-6 col-md-5 col-sm-6 col-xs-12">
														<div class="form-group"></div>
														
														<div class="form-group">
															<input name="addressLineOne" id ="addressLineOne" type="text"
																class="form-control" placeholder="Address Line One"
																value="${address.addressLineOne}" readonly>
														</div>
														
														<div class="form-group">
															<input name="addressLineTwo" id ="addressLineTwo" type="text"
																class="form-control" placeholder="Address Line Two"
																value="${address.addressLineTwo}" readonly>
														</div>
														
														<div class="form-group">
															<input name="city" id ="city" type="text"
																class="form-control" placeholder="City"
																value="${address.city}" readonly>
														</div>
														
														<div class="form-group">
															<input name="state" id ="state" type="text"
																class="form-control" placeholder="State"
																value="${address.state}" readonly>
														</div>
														
														<div class="form-group">
															<input name="postalCode" id ="postalCode" type="text"
																class="form-control" placeholder="Postal Code"
																value="${address.postalCode}" readonly>
														</div>
													</div>

													
												</div>
											</div>
											<div class="row">
												<div class="col-lg-12">
													<div class="payment-adress">
														<button class="rmk btn btn-primary waves-effect waves-light"
															type="submit" name="submit" value="register">Submit</button>
														<button class="rmk btn btn-primary waves-effect waves-light"
															type="reset" name="reset" value="reset">Clear</button>

													</div>
												</div>
											</div>

										</form>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>

