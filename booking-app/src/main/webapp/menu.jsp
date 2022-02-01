<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author"
	content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Hugo 0.84.0">
<title>BookingApp</title>



<!-- Bootstrap core CSS -->
<link href="../../dist/css/bootstrap.min.css" rel="stylesheet">

<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}
</style>


<!-- Custom styles for this template -->
<link rel="stylesheet" href="../../dist/css/font-awesome.min.css">
<link href="../../dist/css/features.css" rel="stylesheet">
<%@ include file="header.jsp"%>
<meta charset="ISO-8859-1">
</head>
<!--   <body style="
    background-image: URL('../../img/bg/Bg2.jpg');
"> -->
<body>

	<svg style="display: none;">
  <symbol id="collection" viewBox="0 0 16 16">
    <path
			d="M2.5 3.5a.5.5 0 0 1 0-1h11a.5.5 0 0 1 0 1h-11zm2-2a.5.5 0 0 1 0-1h7a.5.5 0 0 1 0 1h-7zM0 13a1.5 1.5 0 0 0 1.5 1.5h13A1.5 1.5 0 0 0 16 13V6a1.5 1.5 0 0 0-1.5-1.5h-13A1.5 1.5 0 0 0 0 6v7zm1.5.5A.5.5 0 0 1 1 13V6a.5.5 0 0 1 .5-.5h13a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-.5.5h-13z" />
  </symbol>
   <symbol id="chevron-right" viewBox="0 0 16 16">
    <path fill-rule="evenodd"
			d="M4.646 1.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1 0 .708l-6 6a.5.5 0 0 1-.708-.708L10.293 8 4.646 2.354a.5.5 0 0 1 0-.708z" />
  </symbol>
</svg>
	<main>
		<br>
		<div class="container px-4 py-1" style="background-color: whitesmoke;"
			id="featured-3">
			<h3 class="pb-2 border-bottom">Parcel Management</h3>
			<div class="row g-5 py-2 row-cols-1 row-cols-lg-5">
				<div class="feature col">
					<div class="feature-icon bg-primary bg-gradient">
						<svg width="40" height="50" fill="currentColor" class="bi bi-book" viewBox="0 0 16 16">
  						<path d="M1 2.828c.885-.37 2.154-.769 3.388-.893 1.33-.134 2.458.063 3.112.752v9.746c-.935-.53-2.12-.603-3.213-.493-1.18.12-2.37.461-3.287.811V2.828zm7.5-.141c.654-.689 1.782-.886 3.112-.752 1.234.124 2.503.523 3.388.893v9.923c-.918-.35-2.107-.692-3.287-.81-1.094-.111-2.278-.039-3.213.492V2.687zM8 1.783C7.015.936 5.587.81 4.287.94c-1.514.153-3.042.672-3.994 1.105A.5.5 0 0 0 0 2.5v11a.5.5 0 0 0 .707.455c.882-.4 2.303-.881 3.68-1.02 1.409-.142 2.59.087 3.223.877a.5.5 0 0 0 .78 0c.633-.79 1.814-1.019 3.222-.877 1.378.139 2.8.62 3.681 1.02A.5.5 0 0 0 16 13.5v-11a.5.5 0 0 0-.293-.455c-.952-.433-2.48-.952-3.994-1.105C10.413.809 8.985.936 8 1.783z"></path>
						</svg>
					</div>
					<h5>Booking</h5>
					<p></p>
					<a href="/booking" class="icon-link"> Click here <svg
							class="bi" width="1em" height="1em">
							<use xlink:href="#chevron-right" /></svg>
					</a>
				</div>
				<div class="feature col">
					<div class="feature-icon bg-primary bg-gradient">
						<svg width="40" height="50" fill="currentColor" class="bi bi-arrow-right-square" viewBox="0 0 16 16">
  <path fill-rule="evenodd" d="M15 2a1 1 0 0 0-1-1H2a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V2zM0 2a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V2zm4.5 5.5a.5.5 0 0 0 0 1h5.793l-2.147 2.146a.5.5 0 0 0 .708.708l3-3a.5.5 0 0 0 0-.708l-3-3a.5.5 0 1 0-.708.708L10.293 7.5H4.5z"/>
</svg>
					</div>
					<h5>Outgoing Parcels</h5>
					<p></p>
					<a href="/outgoingParcel" class="icon-link"> Click here <svg
							class="bi" width="1em" height="1em">
							<use xlink:href="#chevron-right" /></svg>
					</a>
				</div>
				<div class="feature col">
					<div class="feature-icon bg-primary bg-gradient">
						<svg width="40" height="50"  fill="currentColor" class="bi bi-arrow-left-square" viewBox="0 0 16 16">
  <path fill-rule="evenodd" d="M15 2a1 1 0 0 0-1-1H2a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V2zM0 2a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V2zm11.5 5.5a.5.5 0 0 1 0 1H5.707l2.147 2.146a.5.5 0 0 1-.708.708l-3-3a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L5.707 7.5H11.5z"/>
</svg>
					</div>
					<h5>Incoming Parcels</h5>
					<p></p>
					<a href="/incomingParcel" class="icon-link"> Click here <svg
							class="bi" width="1em" height="1em">
							<use xlink:href="#chevron-right" /></svg>
					</a>
				</div>

				<div class="feature col">
					<div class="feature-icon bg-primary bg-gradient">
						<svg width="40" height="50" fill="currentColor" class="bi bi-bag-check" viewBox="0 0 16 16">
  <path fill-rule="evenodd" d="M10.854 8.146a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708 0l-1.5-1.5a.5.5 0 0 1 .708-.708L7.5 10.793l2.646-2.647a.5.5 0 0 1 .708 0z"/>
  <path d="M8 1a2.5 2.5 0 0 1 2.5 2.5V4h-5v-.5A2.5 2.5 0 0 1 8 1zm3.5 3v-.5a3.5 3.5 0 1 0-7 0V4H1v10a2 2 0 0 0 2 2h10a2 2 0 0 0 2-2V4h-3.5zM2 5h12v9a1 1 0 0 1-1 1H3a1 1 0 0 1-1-1V5z"/>
</svg>
					</div>
					<h5>Inventory</h5>
					<p></p>
					<a href="/inventoryMenu" class="icon-link"> Click here <svg
							class="bi" width="1em" height="1em">
							<use xlink:href="#chevron-right" /></svg>
					</a>
				</div>

				<div class="feature col">
					<div class="feature-icon bg-primary bg-gradient">
						<svg width="40" height="50" fill="currentColor" class="bi bi-cart-check" viewBox="0 0 16 16">
  <path d="M11.354 6.354a.5.5 0 0 0-.708-.708L8 8.293 6.854 7.146a.5.5 0 1 0-.708.708l1.5 1.5a.5.5 0 0 0 .708 0l3-3z"/>
  <path d="M.5 1a.5.5 0 0 0 0 1h1.11l.401 1.607 1.498 7.985A.5.5 0 0 0 4 12h1a2 2 0 1 0 0 4 2 2 0 0 0 0-4h7a2 2 0 1 0 0 4 2 2 0 0 0 0-4h1a.5.5 0 0 0 .491-.408l1.5-8A.5.5 0 0 0 14.5 3H2.89l-.405-1.621A.5.5 0 0 0 2 1H.5zm3.915 10L3.102 4h10.796l-1.313 7h-8.17zM6 14a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm7 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0z"/>
</svg>
					</div>
					<h5>Delivery</h5>
					<p></p>
					<a href="/delivery" class="icon-link"> Click here <svg
							class="bi" width="1em" height="1em">
							<use xlink:href="#chevron-right" /></svg>
					</a>
				</div>
			</div>

		</div>
		<br>
		<c:if test="${fn:contains(sessionScope.ROLE, 'ADMIN')}">
			<div class="container px-4 py-1"
				style="background-color: whitesmoke;" id="featured-3">
				<h3 class="pb-2 border-bottom">Admin Management</h3>
				<div class="row g-5 py-2 row-cols-1 row-cols-lg-5">
					<div class="feature col">
						<div class="feature-icon bg-primary bg-gradient">
							<svg class="bi" width="1em" height="1em">
								<use xlink:href="#collection" /></svg>
						</div>
						<h5>Location</h5>
						<p></p>
						<a href="/locationListing" class="icon-link"> Click here <svg
								class="bi" width="1em" height="1em">
								<use xlink:href="#chevron-right" /></svg>
						</a>
					</div>

					<div class="feature col">
						<div class="feature-icon bg-primary bg-gradient">
							<svg class="bi" width="1em" height="1em">
								<use xlink:href="#collection" /></svg>
						</div>
						<h5>Pay Type</h5>
						<p></p>
						<a href="/payOptionListing" class="icon-link"> Click here <svg
								class="bi" width="1em" height="1em">
								<use xlink:href="#chevron-right" /></svg>
						</a>
					</div>

					<div class="feature col">
						<div class="feature-icon bg-primary bg-gradient">
							<svg class="bi" width="1em" height="1em">
								<use xlink:href="#collection" /></svg>
						</div>
						<h5>Vehicle Details</h5>
						<p></p>
						<a href="/vehicleListing" class="icon-link"> Click here <svg
								class="bi" width="1em" height="1em">
								<use xlink:href="#chevron-right" /></svg>
						</a>
					</div>

					<div class="feature col">
						<div class="feature-icon bg-primary bg-gradient">
							<svg class="bi" width="1em" height="1em">
								<use xlink:href="#collection" /></svg>
						</div>
						<h5>Manage User</h5>
						<p></p>
						<a href="/userlisting" class="icon-link"> Click here <svg
								class="bi" width="1em" height="1em">
								<use xlink:href="#chevron-right" /></svg>
						</a>
					</div>
					<div class="feature col">
						<div class="feature-icon bg-primary bg-gradient">
							<svg class="bi" width="1em" height="1em">
								<use xlink:href="#collection" /></svg>
						</div>
						<h5>Manage Driver</h5>
						<p></p>
						<a href="/driverListing" class="icon-link"> Click here <svg
								class="bi" width="1em" height="1em">
								<use xlink:href="#chevron-right" /></svg>
						</a>
					</div>
				</div>

				<div class="row g-5 py-2 row-cols-1 row-cols-lg-5">
					<div class="feature col">
						<div class="feature-icon bg-primary bg-gradient">
							<svg class="bi" width="1em" height="1em">
								<use xlink:href="#collection" /></svg>
						</div>
						<h5>Manage Conductor</h5>
						<p></p>
						<a href="/conductorListing" class="icon-link"> Click here <svg
								class="bi" width="1em" height="1em">
								<use xlink:href="#chevron-right" /></svg>
						</a>
					</div>
					<div class="feature col">
						<div class="feature-icon bg-primary bg-gradient">
							<svg class="bi" width="1em" height="1em">
								<use xlink:href="#collection" /></svg>
						</div>
						<h5>Manage Booked User</h5>
						<p></p>
						<a href="/bookedByListing" class="icon-link"> Click here <svg
								class="bi" width="1em" height="1em">
								<use xlink:href="#chevron-right" /></svg>
						</a>
					</div>
					<div class="feature col">
						<div class="feature-icon bg-primary bg-gradient">
							<svg class="bi" width="1em" height="1em">
								<use xlink:href="#collection" /></svg>
						</div>
						<h5>Manage Charges</h5>
						<p></p>
						<a href="/chargeListing" class="icon-link"> Click here <svg
								class="bi" width="1em" height="1em">
								<use xlink:href="#chevron-right" /></svg>
						</a>
					</div>
				</div>
				<div class="row g-5 py-2 row-cols-1 row-cols-lg-5">
				<div class="feature col">
					<div class="feature-icon bg-primary bg-gradient">
						<svg class="bi" width="1em" height="1em">
							<use xlink:href="#collection" /></svg>
					</div>
					<h5>Expense</h5>
					<p></p>
					<a href="/expence" class="icon-link"> Click here <svg
							class="bi" width="1em" height="1em">
							<use xlink:href="#chevron-right" /></svg>
					</a>
				</div>
					<div class="feature col">
						<div class="feature-icon bg-primary bg-gradient">
							<svg class="bi" width="1em" height="1em">
								<use xlink:href="#collection" /></svg>
						</div>
						<h5>Expense Category</h5>
						<p></p>
						<a href="/expenseCategoryListing" class="icon-link"> Click
							here <svg class="bi" width="1em" height="1em">
								<use xlink:href="#chevron-right" /></svg>
						</a>
					</div>

					<div class="feature col">
						<div class="feature-icon bg-primary bg-gradient">
							<svg class="bi" width="1em" height="1em">
								<use xlink:href="#collection" /></svg>
						</div>
						<h5>Expense Sub Category</h5>
						<p></p>
						<a href="/expenseSubCategoryListing" class="icon-link"> Click
							here <svg class="bi" width="1em" height="1em">
								<use xlink:href="#chevron-right" /></svg>
						</a>
					</div>

					<!-- <div class="feature col">
						<div class="feature-icon bg-primary bg-gradient">
							<svg class="bi" width="1em" height="1em">
								<use xlink:href="#collection" /></svg>
						</div>
						<h5>Payment Type</h5>
						<p></p>
						<a href="/paymentTypeListing" class="icon-link"> Click here <svg
								class="bi" width="1em" height="1em">
								<use xlink:href="#chevron-right" /></svg>
						</a>
					</div> -->
				</div>

			</div>
			<br>
			<div class="container px-4 py-1"
				style="background-color: whitesmoke;" id="featured-3">
				<h3 class="pb-2 border-bottom">Report Management</h3>
				<div class="row g-5 py-2 row-cols-1 row-cols-lg-5">
					<div class="feature col">
						<div class="feature-icon bg-primary bg-gradient">
							<svg class="bi" width="1em" height="1em">
								<use xlink:href="#collection" /></svg>
						</div>
						<h5>Booking</h5>
						<p></p>
						<a href="#" class="icon-link"> Click here <svg
								class="bi" width="1em" height="1em">
								<use xlink:href="#chevron-right" /></svg>
						</a>
					</div>
					<div class="feature col">
						<div class="feature-icon bg-primary bg-gradient">
							<svg class="bi" width="1em" height="1em">
								<use xlink:href="#collection" /></svg>
						</div>
						<h5>OGPL</h5>
						<p></p>
						<a href="/report/ogpl" class="icon-link"> Click here <svg
								class="bi" width="1em" height="1em">
								<use xlink:href="#chevron-right" /></svg>
						</a>
					</div>
					<div class="feature col">
						<div class="feature-icon bg-primary bg-gradient">
							<svg class="bi" width="1em" height="1em">
								<use xlink:href="#collection" /></svg>
						</div>
						<h5>Delivery</h5>
						<p></p>
						<a href="/report/delivery" class="icon-link"> Click here <svg
								class="bi" width="1em" height="1em">
								<use xlink:href="#chevron-right" /></svg>
						</a>
					</div>
					<div class="feature col">
						<div class="feature-icon bg-primary bg-gradient">
							<svg class="bi" width="1em" height="1em">
								<use xlink:href="#collection" /></svg>
						</div>
						<h5>Expense</h5>
						<p></p>
						<a href="#" class="icon-link"> Click here <svg
								class="bi" width="1em" height="1em">
								<use xlink:href="#chevron-right" /></svg>
						</a>
					</div>
				</div>
			</div>

		</c:if>
	</main>


	<script src="../../dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>

