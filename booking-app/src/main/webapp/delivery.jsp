<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html class="no-js" lang="en">
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


<script type="text/javascript" charset="utf-8">
	function getSearchParcel() {
		/* var x = document.getElementById("searchSelection").selectedIndex;
		var type = document.getElementsByTagName("option")[x].value; */
		var value = document.getElementById("txtSearch").value;
		//if (type == 'lrNo') {
		value = value.replaceAll('/', '%2F');
		window.location.href = "/searchParcelLRNO?lrNumber=" + value;
		/* } else {
			window.location.href = "/searchParcelName/" + value;
		} */
		toggleFormElements(false);
	}
</script>
<script>
	function toggleFormElements() {
		
			var inputs = document.getElementsByTagName("input");
			for (var i = 0; i < inputs.length; i++) {
				inputs[i].disabled = true;
			}
			var inputs1 = document.getElementsByTagName("select");
			for (var i = 0; i < inputs1.length; i++) {
				inputs1[i].disabled = true;
			}
			document.getElementById("txtSearch").disabled = false;
	
	}
</script>

</head>
<!-- style="overflow-y: hidden;overflow-x: hidden;" -->
<body onload="toggleFormElements(true)">
	<div class="wrapper">
		<div class="inner" style="width: 90%">
			<div style="width: 15%;">
				<h3>
					<b>Delivery</b>
				</h3>
				<img src="../../img/product/parcel.jpg" alt="">
			</div>
			<b style="width: 5%;"></b>
			<div class="blog-details-area mg-b-15">
				<div class="container-fluid" style="width: 90%;">
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="blog-details-inner">
								<main>
									<form action="/addDelivery" method="post" style="width: 100%;">
										<input type="hidden" class="form-control" name="id" id="id"
											value="">
										<p style="color: red" align="center">${errormsg}</p>
										<p style="color: green" align="center">${DeliverysuccessMessage}</p>
										
										<div class="row">
											<div class="col-md-4 control-margin">
												<div class="row element-margin">
													<div class="input-group">

														<input type="text" class="form-control"
															placeholder="Type LR No" name="txtSearch" id="txtSearch">
														<button type="button" class="btn btn-secondary"
															id="btnSearch" onclick="getSearchParcel();">Search</button>
													</div>
													<div class="mt-0">
														<label for="txtSearch" class="form-label"><small>(Type
																LR No. or Party Name and press Search)</small></label>
													</div>
												</div>
												<div class="row element-margin">
													<div class="col-sm-6">
														<input type="checkbox" class="form-check-input"
															name="chkDeliveredParcel"> <label
															class="form-check-label" for="chkDeliveredParcel">Include
															Delivered Parcels</label>
													</div>
													<div class="col-sm-6">
														<input type="checkbox" class="form-check-input"
															name="chkDeliveredParcel"> <label
															class="form-check-label" for="chkDeliveredParcel">Search
															Bill</label>
													</div>
												</div>
												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label" for="deliverySelection">Delivery
															Section</label>
													</div>
													<div class="col-sm-8">
														<select class="form-select" name="deliverySelection">
															<option selected disabled value="">Choose...</option>
															<option>Selection 1</option>
															<option>Selection 2</option>
															<option>Selection 3</option>
														</select>
													</div>
												</div>
												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label" for="txtName">Name</label>
													</div>
													<div class="col-sm-8">
														<input type="text" class="form-control" name="name"
															value="${deliveryB.toName}">
													</div>
												</div>
												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label" for="txtPaid">Paid</label>
													</div>
													<div class="col-sm-8">
														<input type="text" class="form-control" name="paid"
															value="${deliveryB.paid}">
													</div>
												</div>
												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label" for="txtNoofItem">No. of
															Items</label>
													</div>
													<div class="col-sm-8">
														<input type="text" class="form-control" name="noOfItems"
															value="${deliveryB.item_count}">
													</div>
												</div>
												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label" for="txtddVehicle">DD
															Vehicle</label>
													</div>
													<div class="col-sm-8">
														<select id="ddVehicle" name="ddVehicle"
															class="form-control">
															<c:forEach var="options1" items="${vehicleList}"
																varStatus="status">
																<option value="${options1.id}"
																	${options1.id==deliveryI.vehicleNo ? 'selected' : ''}>${options1.vehicle}</option>
															</c:forEach>
														</select>
													</div>
												</div>
												<div class="row element-margin">
													<div class="col-sm-4">
														<input type="radio" class="form-check-input" name="status"
															value="HOLD"> <label class="form-check-label"
															for="chkHold">Hold</label>
													</div>
													<div class="col-sm-4">
														<input type="radio" class="form-check-input" name="status"
															value="DELIVERED"> <label
															class="form-check-label" for="chkDelivered">Delivered</label>
													</div>
													<div class="col-sm-4">
														<input type="radio" class="form-check-input" name="status"
															value="PRINTED"> <label class="form-check-label"
															for="chkPrinted">Printed</label>
													</div>
												</div>
												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label" for="txtDeliveredBy">Delivered
															By</label>
													</div>
													<div class="col-sm-8">
														<input type="text" class="form-control" name="deliveredBy"
															value="${deliveryI.deliveredBy}">
													</div>
												</div>
											</div>
											<div class="col-md-4 control-margin">
												<div class="row element-margin">
													<div class="col-sm-4">
														<label for="txtNo" class="form-label">No</label>
													</div>
													<div class="col-sm-8">
														<input type="number" class="form-control" name="no"
															value="${deliveryB.bookingNo}">
													</div>
												</div>
												<div class="row element-margin">
													<div class="col-sm-4">
														<label for="txtOGPL" class="form-label">OGPL</label>
													</div>
													<div class="col-sm-8">
														<input type="text" class="form-control" name="ogpl"
															value="${deliveryI.ogplNo}">
													</div>
												</div>
												<%-- <div class="row element-margin">
												<div class="col-sm-4">
													<label for="dtFromDate" class="form-label">From
														Date</label>
												</div>
												<div class="col-sm-8">
													<input type="date" class="form-control" name="fromDate"
														placeholder="From Date" value="${deliveryB.bookedOn}">
												</div>
											</div>
											<div class="row element-margin">
												<div class="col-sm-4">
													<label for="dtToDate" class="form-label">To Date</label>
												</div>
												<div class="col-sm-8">
													<input type="date" class="form-control" name="toDate"
														placeholder="To Date" value="">
												</div>
											</div> --%>
												<div class="row element-margin">
													<div class="col-sm-4">
														<label for="dtFrom" class="form-label">From</label>
													</div>

													<div class="col-sm-8">
														<c:choose>
															<c:when test="${deliveryB.fromLocation ne ''}">
																<select class="form-select" name=fromLocation>
																	<c:forEach var="options" items="${locationList}"
																		varStatus="status">
																		<%-- <option value="${options.id}" ${options.id == booking.toLocation ? 'selected' : ''}>${options.location}</option> --%>
																		<option value="${options.id}"
																			${options.id == deliveryB.fromLocation ? 'selected' : ''}>${options.location}</option>
																	</c:forEach>
																</select>
															</c:when>
														</c:choose>
													</div>
												</div>
												<div class="row element-margin">
													<div class="col-sm-4">
														<label for="dtTo" class="form-label">To</label>
													</div>

													<div class="col-sm-8">
														<select class="form-select" name="toLocation">
															<c:forEach var="options" items="${locationList}"
																varStatus="status">
																<%-- <option value="${options.id}" ${options.id == booking.toLocation ? 'selected' : ''}>${options.location}</option> --%>
																<option value="${options.id}"
																	${options.id == deliveryB.toLocation ? 'selected' : ''}>${options.location}</option>
															</c:forEach>
														</select>
													</div>
												</div>
												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label" for="txtLRNo">LR No.</label>
													</div>
													<div class="col-sm-8">
														<input type="text" class="form-control" name="lRNo"
															value="${deliveryB.lrNumber}">
													</div>
												</div>
												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label" for="txtDeliveryBillNo">Delivery
															Bill No.</label>
													</div>
													<div class="col-sm-8">
														<input type="number" class="form-control"
															name="deliveryBillNo" value="${deliveryB.billNumber}">
													</div>
												</div>
												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label" for="dtDeliveryDate">Delivery
															Date</label>
													</div>
													<div class="col-sm-8">
														<input type="text" class="form-control"
															name="deliveryDate" id="deliveryDate"
															value="${delivery.deliveryDate}">
													</div>
												</div>
												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label" for="txtToPay">To Pay</label>
													</div>
													<div class="col-sm-8">
														<input type="text" class="form-control" name="toPay"
															value="${deliveryB.topay}">
													</div>
												</div>
											</div>
											<div class="col-md-4 control-margin">
												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label" for="txtHamali">Hamali</label>
													</div>
													<div class="col-sm-8">
														<input type="text" class="form-control" name="hamali"
															value="0">
													</div>
												</div>
												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label" for="txtUnloadingCharges">Unloading
															Charges</label>
													</div>
													<div class="col-sm-8">
														<input type="text" class="form-control"
															name="unloadingCharges" value="0">
													</div>
												</div>
												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label" for="txtDoorDeliveryCharges">Door
															Delivery Charges</label>
													</div>
													<div class="col-sm-8">
														<input type="text" class="form-control"
															name="doorDeliveryCharges" value="0">
													</div>
												</div>
												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label" for="txtDemurrage">Demurrage</label>
													</div>
													<div class="col-sm-8">
														<input type="text" class="form-control" name="demurrage"
															value="0">
													</div>
												</div>
												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label" for="txtOthers">Others</label>
													</div>
													<div class="col-sm-8">
														<input type="text" class="form-control" name="others"
															value="${deliveryB.othercharges}">
													</div>
												</div>
												<div class="row element-margin">
													<div class="col-sm-8">
														<label class="form-label" for="txtTotal">TOTAL</label>
													</div>
													<div class="col-sm-4">
														<input type="number" class="form-control" name="total"
															value="${deliveryB.total}">
													</div>
												</div>
												<div class="row element-margin">
													<div class="col-sm-2">
														<label class="form-label" for="txtPaidTotal">Paid</label>
													</div>
													<div class="col-sm-6">
														<input type="text" class="form-control" name="txtPaidBy">
													</div>
													<div class="col-sm-4">
														<input type="text" class="form-control" name="paid"
															value="${deliveryB.paid}">
													</div>
													<div class="mt-0" style="padding-left: 80px">
														<label for="txtPaidBy" class="form-label"><small>(Cheque
																No;Bank;Branch)</small></label>
													</div>
												</div>
												<div class="row element-margin">
													<div class="col-sm-8">
														<label class="form-label" for="txtRefund">Refund</label>
													</div>
													<div class="col-sm-4">
														<input type="text" class="form-control" name="refund"
															value="${deliveryB.refund}">
													</div>
												</div>
											</div>
										</div>

										<div class="row control-margin">
											<div class="col-md-6 control-margin">
												<button type="submit" class="btn btn-primary button-margin"
													id="btnSave">Save</button>
												<button type="reset" class="btn btn-primary button-margin"
													id="btnClear">Clear</button>
												<a href="/menu"><button type="button"
														class="btn btn-primary button-margin col-md-2"
														id="btnClear">Back</button></a>
											</div>
											<!-- 										<div class="col-md-4 control-margin"> -->
											<!-- 											<button type="button" class="btn btn-primary button-margin" -->
											<!-- 												id="btnNext">Next</button> -->
											<!-- 											<button type="button" class="btn btn-primary button-margin" -->
											<!-- 												id="btnPrevious">Previous</button> -->
											<!-- 											<button type="button" class="btn btn-primary button-margin" -->
											<!-- 												id="btnCurrent">Current</button> -->
											<!-- 										</div> -->
											<div class="col-md-6 control-margin">
												<button type="button" class="btn btn-primary button-margin"
													id="btnDeliver">Deliver</button>
												<button type="button" class="btn btn-primary button-margin"
													id="btnPrint">Print</button>

											</div>
										</div>
									</form>
								</main>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>