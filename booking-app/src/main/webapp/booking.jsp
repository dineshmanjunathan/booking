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
<script type="text/javascript" charset="utf-8">
	function selectPayOption() {
		var e = document.getElementById("payOption");
		var strUser = e.value;
		document.getElementById("loading_charges_pay").value = strUser;
		document.getElementById("door_pick_charges_pay").value = strUser;
		(document.getElementById("freight_value").value = "");
		(document.getElementById("loading_charges").value = "");
		(document.getElementById("door_pick_charges").value = "");
		document.getElementById("topay").value = "";
		document.getElementById("paid").value = "";
		document.getElementById("loading_charges_pay").disabled = true;
		document.getElementById("door_pick_charges_pay").disabled = true;
	}
	function disabledFieldsOnLoad() {
		document.getElementById("loading_charges_pay").disabled = false;
		document.getElementById("door_pick_charges_pay").disabled = false;
		document.getElementById("refund").disabled = true;
	}
	function checkFromNameExists() {
		var value = document.getElementById("from_phone").value;
		$.ajax({
			url : "/searchCustomerName/" + value,
			type : "get",
			cache : false,
			success : function(data) {
				if (data.length > 0) {
					document.getElementById("fromName").value = data;
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log('ERROR:' + XMLHttpRequest.status
						+ ', status text: ' + XMLHttpRequest.statusText);
			}
		});
	}
	function checkToNameExists() {
		var value = document.getElementById("to_phone").value;
		$.ajax({
			url : "/searchCustomerName/" + value,
			type : "get",
			cache : false,
			success : function(data) {
				if (data.length > 0) {
					document.getElementById("toName").value = data;
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log('ERROR:' + XMLHttpRequest.status
						+ ', status text: ' + XMLHttpRequest.statusText);
			}
		});
	}
	function sumAmount() {
		var option = document.getElementById("loading_charges_pay").value;
		var frieght = Number(document.getElementById("freight_value").value);
		var loading = Number(document.getElementById("loading_charges").value);
		var doorPick = Number(document.getElementById("door_pick_charges").value);
		let total = frieght + loading + doorPick;
		if (option == 'TOPAY') {
			document.getElementById("topay").value = total;
		} else {
			document.getElementById("paid").value = total;
		}
	}
</script>
</head>

<body >
<div class="wrapper">
		<div class="inner">
			<div style="width: 250px;">
				<img src="../../img/product/parcel.jpg" alt="">
			</div>
			<h3><b>Booking</b></h3>
	<div class="blog-details-area mg-b-15">
		<div class="container-fluid" style="width: 90%;">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="blog-details-inner">
						<form action="/booking/save" method="POST"
							onload="disabledFieldsOnLoad();">
							<p style="color: green" align="center">${bookingsuccessmessage}</p>
							<p style="color: green;font-weight: bold; " align="center">${LRNumber}</p>
							<main>
								<div class="row">
									<div class="col-md-4 control-margin">
										<div class="well">
											<div class="row element-margin">
												<div class="col-sm-4">
													<label for="from" class="form-label">From</label>
												</div>

												<div class="col-sm-8">
												<c:choose>
													<c:when test="${sessionScope.ROLE eq 'ADMIN'}">
														<select class="form-select bg-info text-dark"
														id="fromLocation" name="fromLocation">
														<option value="">-Select From Location-</option>
														<c:forEach var="options" items="${locationList}"
															varStatus="status">
															<option value="${options.id}">${options.location}</option>
														</c:forEach>
													</select>
													</c:when>
													<c:otherwise>
													<input type="hidden" name="fromLocation"
															id="fromLocation" value="${sessionScope.USER_LOCATIONID}"
															readonly>
														<input type="text" class="form-control" name="fromdummy"
															id="fromdummy" value="${sessionScope.USER_LOCATION}"
															readonly>
													</c:otherwise>
												</c:choose>
												</div>
												</div>
											<div class="row element-margin">
												<div class="col-sm-4">
													<label for="to" class="form-label">To</label>
												</div>

												<div class="col-sm-8">
													<select class="form-select bg-info text-dark"
														id="toLocation" name="toLocation">
														<!--  <option selected disabled value="">Choose...</option>
					  <option>...</option> -->
														<option value="">-Select To Location-</option>
														<c:forEach var="options" items="${locationList}"
															varStatus="status">
															<option value="${options.id}">${options.location}</option>
														</c:forEach>
													</select>
												</div>
											</div>
										</div>
										<div class="row element-margin">
											<div class="col-sm-4">
												<label class="form-label" for="fromName">From Name</label>
											</div>
											<div class="col-sm-8">
												<input type="text" class="form-control" id="fromName"
													name="fromName">
											</div>
										</div>

										<div class="row element-margin">
											<div class="col-sm-4">
												<label class="form-label" for="from_phone">From
													Phone No</label>
											</div>
											<div class="col-sm-8">
												<input type="number" class="form-control" id="from_phone"
													name="from_phone" onblur="checkFromNameExists();">
											</div>
										</div>
										<div class="row element-margin">
											<div class="col-sm-4">
												<label class="form-label" for="remarks">Remarks</label>
											</div>
											<div class="col-sm-8">
												<input type="text" class="form-control" id="remarks"
													name="remarks">
											</div>
										</div>
										<div class="row element-margin">
											<div class="col-sm-4">
												<label class="form-label" for="fromValue">Value</label>
											</div>
											<div class="col-sm-8">
												<input type="text" class="form-control" id="fromValue"
													name="fromValue">
											</div>
										</div>
										<div class="row element-margin">
											<div class="col-sm-4">
												<label class="form-label" for="invNo">Cons INV No</label>
											</div>
											<div class="col-sm-8">
												<input type="text" class="form-control" id="invNo"
													name="invNo">
											</div>
										</div>

										<div class="row element-margin">
											<div class="col-sm-4">
												<label class="form-label" for="tinNo">Cons TIN No</label>
											</div>
											<div class="col-sm-8">
												<input type="text" class="form-control" id="tinNo"
													name="tinNo">
											</div>
										</div>
										<div class="row element-margin">
											<div class="col-sm-4">
												<label class="form-label" for="billDesc">Bill Desc</label>
											</div>
											<div class="col-sm-8">
												<input type="text" class="form-control" id="billDesc"
													name="billDesc">
											</div>
										</div>
										<div class="row element-margin">
											<div class="col-sm-4">
												<label class="form-label" for="billValue">Bill Value</label>
											</div>
											<div class="col-sm-8">
												<input type="number" class="form-control" id="billValue"
													name="billValue">
											</div>
										</div>
										<div class="row element-margin">
											<div class="col-sm-4">
												<label class="form-label" for="eway">E Way Bill
													Number</label>
											</div>
											<div class="col-sm-8">
												<input type="number" class="form-control" id="eBillNo"
													name="eBillNo">
											</div>
										</div>
										<div class="row element-margin">
											<div class="col-sm-4">
												<input type="checkbox" class="form-check-input"
													id="isPrinted" name="isPrinted" disabled> <label
													class="form-check-label" for="isPrinted">Printed</label>
											</div>
										</div>
										<div class="row element-margin">
											<div class="col-sm-4">
												<label class="form-label" for="bookedBy">Booked By</label>
											</div>
											<div class="col-sm-8">
												<input type="text" class="form-control" id="bookedBy"
													name="bookedBy">
											</div>
										</div>
									</div>
									<div class="col-md-4 control-margin">
										<div class="row element-margin">
											<div class="col-sm-4">
												<label for="bookingNo" class="form-label">Book No</label>
											</div>
											<div class="col-sm-8">
												<input type="text" class="form-control bg-info text-dark"
													id="bookingNo" name="bookingNo">
											</div>
										</div>
										<div class="row element-margin">
											<div class="col-sm-4">
												<label for="bookedOn" class="form-label">Date</label>
											</div>
											<div class="col-sm-8">
												<input type="date" class="form-control bg-info text-dark"
													id="bookedOn" placeholder="" name="bookedOn">
											</div>
										</div>

										<div class="row element-margin">
											<div class="col-sm-4">
												<label class="form-label" for="toName">To Name</label>
											</div>
											<div class="col-sm-8">
												<input type="text" class="form-control" id="toName"
													name="toName">
											</div>
										</div>
										<div class="row element-margin">
											<div class="col-sm-4">
												<label class="form-label" for="to_phone">To Phone No</label>
											</div>
											<div class="col-sm-8">
												<input type="number" class="form-control" id="to_phone"
													name="to_phone" onblur="checkToNameExists();">
											</div>
										</div>

										<div class="row element-margin">
											<div class="col-sm-4">
												<label class="form-label" for="item_count">No Of
													Items</label>
											</div>
											<div class="col-sm-8">
												<input type="number" class="form-control" id="item_count"
													name="item_count">
											</div>
										</div>

										<div class="row element-margin">
											<div class="col-sm-4">
												<label class="form-label" for="weight">Weight in KG</label>
											</div>
											<div class="col-sm-8">
												<input type="number" class="form-control" id="weight"
													name="weight">
											</div>
										</div>
										<div class="row element-margin">
											<div class="col-sm-4">
												<label class="form-label" for="freight_value">Freight</label>
											</div>
											<div class="col-sm-4">
												<select class="form-select bg-info text-dark" id="payOption"
													name="payOption" onchange="selectPayOption();">
													<option value="">-Select-</option>
													<option value="TOPAY">TOPAY</option>
													<option value="PAID">PAID</option>
												</select>
											</div>
											<div class="col-sm-4">
												<input type="text" class="form-control" id="freight_value"
													name="freight_value" onblur="sumAmount();">
											</div>

										</div>
										<div class="row element-margin">
											<div class="col-sm-4">
												<label class="form-label" for="loading_charges">Loading
													Charges</label>
											</div>
											<div class="col-sm-4">
												<select class="form-select bg-info text-dark"
													id="loading_charges_pay" name="loading_charges_pay">
													<option value="">-Select-</option>
													<option value="TOPAY">TOPAY</option>
													<option value="PAID">PAID</option>
												</select>
											</div>
											<div class="col-sm-4">
												<input type="text" class="form-control" id="loading_charges"
													name="loading_charges" onblur="sumAmount();">
											</div>

										</div>
										<div class="row element-margin">
											<div class="col-sm-4">
												<label class="form-label" for="door_pick_charges">Door
													Pickup Charges</label>
											</div>
											<div class="col-sm-4">
												<select class="form-select bg-info text-dark"
													id="door_pick_charges_pay" name="door_pick_charges_pay">
													<option value="">-Select-</option>
													<option value="TOPAY">TOPAY</option>
													<option value="PAID">PAID</option>
												</select>
											</div>
											<div class="col-sm-4">
												<input type="number" class="form-control"
													id="door_pick_charges" name="door_pick_charges"
													onblur="sumAmount();">
											</div>

										</div>

										<div class="row element-margin">
											<div class="col-sm-4">
												<label class="form-label" for="other_charges">Others</label>
											</div>
											<div class="col-sm-8">
												<input type="number" class="form-control" id="other_charges"
													name="other_charges">
											</div>
										</div>

										<div class="row element-margin">
											<div class="col-sm-4">
												<label class="form-label" for="paid">Paid</label>
											</div>
											<div class="col-sm-8">
												<input type="number" class="form-control" id="paid"
													name="paid">
											</div>
										</div>
										<div class="row element-margin">
											<div class="col-sm-4">
												<label class="form-label" for="topay">To Pay</label>
											</div>
											<div class="col-sm-8">
												<input type="number" class="form-control" id="topay"
													name="topay">
											</div>
										</div>


									</div>
									<div class="col-md-4 control-margin">
										<div class="well">
											<div class="row element-margin">
												<div class="input-group">
												<c:choose>
													<c:when test="${sessionScope.ROLE eq 'ADMIN'}">
													<input type="text" class="form-control bg-info text-dark"
														placeholder="LR No" id="lrNumber" name="lrNumber" value="${LRnumber}">
												</c:when>
												<c:otherwise>
												<input type="text" class="form-control bg-info text-dark"
														placeholder="LR No" id="lrNumber" name="lrNumber" value="${LRnumber}" readonly>
												</c:otherwise>		
												</c:choose>
													<button type="submit" class="btn btn-secondary"
														id="btnSearch">Search</button>
												</div>
												<div class="mt-0">
													<label for="txtSearch" class="form-label"><small>(Type
															LR No and press Search)</small></label>
												</div>
											</div>
										</div>


										<div class="row element-margin">
											<div class="col-sm-4">
												<label class="form-label"></label>
											</div>
											<div class="col-sm-8"></div>
										</div>
										<div class="row element-margin">
											<div class="col-sm-4">
												<label class="form-label"></label>
											</div>
											<div class="col-sm-8"></div>
										</div>
										<div class="row element-margin">
											<div class="col-sm-4">
												<label class="form-label"></label>
											</div>
											<div class="col-sm-8"></div>
										</div>
										<div class="row element-margin">
											<div class="col-sm-4">
												<label class="form-label"></label>
											</div>
											<div class="col-sm-8"></div>
										</div>
										<div class="row element-margin">
											<div class="col-sm-4">
												<label class="form-label"></label>
											</div>
											<div class="col-sm-8"></div>
										</div>

										<div class="row element-margin">
											<div class="col-sm-4">
												<label class="form-label"></label>
											</div>
											<div class="col-sm-8"></div>
										</div>
										<div class="row element-margin">
											<div class="col-sm-4">
												<label class="form-label"></label>
											</div>
											<div class="col-sm-8"></div>
										</div>
										<div class="row element-margin">
											<div class="col-sm-4">
												<label class="form-label"></label>
											</div>
											<div class="col-sm-8"></div>
										</div>
										<div class="row element-margin">
											<div class="col-sm-4">
												<label class="form-label"></label>
											</div>
											<div class="col-sm-8"></div>
										</div>
										<div class="row element-margin">
											<div class="col-sm-4">
												<label class="form-label"></label>
											</div>
											<div class="col-sm-8"></div>
										</div>
										<div class="row element-margin">
											<div class="col-sm-4">
												<label class="form-label"></label>
											</div>
											<div class="col-sm-8"></div>
										</div>
										<div class="row element-margin">
											<div class="col-sm-4">
												<label class="form-label"></label>
											</div>
											<div class="col-sm-8"></div>
										</div>
										<div class="row element-margin">
											<div class="col-sm-4">
												<label class="form-label"></label>
											</div>
											<div class="col-sm-8"></div>
										</div>
										<div class="row element-margin">
											<div class="col-sm-4">
												<label class="form-label"></label>
											</div>
											<div class="col-sm-8"></div>
										</div>



										<div class="row element-margin">
											<div class="col-sm-4">
												<label class="form-label" for="txtTotal">Total</label>
											</div>
											<div class="col-sm-8">
												<input type="text" class="form-control" id="total"
													name="total">
											</div>
										</div>
										<div class="row element-margin">
											<div class="col-sm-4">
												<label class="form-label" for="txtTotal">Cash</label>
											</div>
											<div class="col-sm-8">
												<input type="text" class="form-control" id="cash"
													name="cash">
											</div>
										</div>
										<div class="row element-margin">
											<div class="col-sm-4">
												<label class="form-label" for="txtRefund">Refund</label>
											</div>
											<div class="col-sm-8">
												<input type="text" class="form-control" id="refund"
													name="refund">
											</div>
										</div>

									</div>
								</div>
								<div class="row control-margin">
									<div class="col-md-6 control-margin">
										<button type="button" class="btn btn-primary button-margin col-md-2"
											id="btnNew">New</button>
										<button type="button" class="btn btn-primary button-margin col-md-3"
											id="btnEdit">Edit</button>
										<button type="button" class="btn btn-primary button-margin col-md-3"
											id="btnDelete">Delete</button>
										<button type="button" class="btn btn-primary button-margin col-md-2"
											id="btnBill">Bill..</button>

									</div>
									<!-- <div class="col-md-4 control-margin">
			<button type="button" class="btn btn-primary button-margin" id="btnHelp">Help</button>
				<button type="button" class="btn btn-primary button-margin" id="btnNext">Next</button>
				<button type="button" class="btn btn-primary button-margin" id="btnPrevious">Previous</button>
				<button type="button" class="btn btn-primary button-margin" id="btnCurrent">Current</button>
			</div> -->
									<div class="col-md-6 control-margin">

										<button type="submit" class="btn btn-primary button-margin col-md-2"
											id="btnSave">Save</button>
										<button type="reset" class="btn btn-primary button-margin col-md-2"
											id="btnClear">Clear</button>
										<a href="/menu"><button type="button" class="btn btn-primary button-margin col-md-2" id="btnClear">Back</button></a>
										<button type="button" class="btn btn-primary button-margin col-md-2" id="btnPrint">Print</button>
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
