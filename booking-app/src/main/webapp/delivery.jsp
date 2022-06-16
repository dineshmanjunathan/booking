<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html class="no-js" lang="en">
<head>
<%@ include file="header.jsp"%>

   <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
       
       
<style>
.element-margin {
	margin-bottom: 5px;
}

.button-margin {
	margin-right: 5px;
}
</style>


<script type="text/javascript" charset="utf-8">
function addDoorDeliveryToAMTP()
{
document.getElementById("amtToBePaid").value=parseInt(${unloadingCharges+delivery.demurrage-deliveryB.deliveryDiscount+deliveryB.topay})+parseInt(document.getElementById("doorDeliveryCharges").value);
}
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
		//toggleFormElements(false);
	}
	

	function sumRefund() {
		var total = Number(document.getElementById("amtToBePaid").value);
		var cash = Number(document.getElementById("cash").value);
		let refund = cash -total;
		document.getElementById("refund").value = refund;
	}
	function save_confirm(){
		if(document.getElementById("cash").value =="")
			{
			alert("Cash Field Should Not Be Empty");
			return false;
			}
		if(document.getElementById("refund").value <0){
			alert("Refund Should Not be Negative");
			return false;
		}
		
		if (confirm('Are you sure you want to save?')) {
			document.getElementById("status").disabled  = false;
			document.getElementById("deliveryform").submit();
		}
	}
	
	function searchLRNumber(lrNumber) {
		
		 $(document).ready(function() {
             $(function() {
                 $("#txtSearch").autocomplete({
                     source: function(request, response) {
                         $.ajax({
                        	 url : "/dbSearchParcelLRNO?userId="+document.getElementById("userName").value+"&lrNumber="+lrNumber.value,
                             type: "GET",
                             success: function(data) {
                                 response(data);
                             }
                         });
                     }
                 });
             });
         });
		
	
		
	}
</script>
<script>
	function toggleFormElements() {
		
			var inputs = document.getElementsByTagName("input");
			for (var i = 0; i < inputs.length; i++) {
				inputs[i].readOnly  = true;
			}
			var inputs1 = document.getElementsByTagName("select");
			for (var i = 0; i < inputs1.length; i++) {
				inputs1[i].readOnly = true;
			}
			document.getElementById("txtSearch").readOnly  = false;
			document.getElementById("doorDeliveryCharges").readOnly  = false;
			var toPay=document.getElementById("toPay").value;
			var paid=document.getElementById("paid").value;
			if(toPay != "" ||  document.getElementById("amtToBePaid").value > 0 && document.getElementById("cash").value == ""){
				document.getElementById("cash").readOnly = false;
			}
			else{
				document.getElementById("cash").readOnly = true;
			}
			
			
	
	}
	function sumAmount() {
		var doorDeliveryCharges =Number( document.getElementById("doorDeliveryCharges").value);
		document.getElementById("total").readOnly = false;
		var total = Number(document.getElementById("orgTotal").value);
		let sum = doorDeliveryCharges + total;
		document.getElementById("total").value = (sum);
		document.getElementById("total").readOnly = true;
	}

	function getDeliverySlip() {
		var x = document.getElementsByName('lRNo')[0].value;
		if (x) {
			$.ajax({
				url : "/delivery/print?lrNumber=" + x,
				type : "GET",
				cache : false,
				success : function(data) {
					printJS({printable: data, type: 'pdf', base64: true})
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert('Could not print the LR. - '+ textStatus);
					console.log('ERROR:' + XMLHttpRequest.status
							+ ', status text: ' + XMLHttpRequest.statusText);
				}
			});
		}
	}
	
	function  deliveryCheck(data,status) {
		
		var inputs = document.getElementsByTagName("input");
		for (var i = 0; i < inputs.length; i++) {
			inputs[i].readOnly  = true;
		}
		var inputs1 = document.getElementsByTagName("select");
		for (var i = 0; i < inputs1.length; i++) {
			inputs1[i].readOnly = true;
		}
		document.getElementById("txtSearch").readOnly  = false;
		document.getElementById("doorDeliveryCharges").readOnly  = false;
		var toPay=document.getElementById("toPay").value;
		var paid=document.getElementById("paid").value;
		
		
		

		if(status=="D")
			{
			$('input[value="DELIVERED"]').prop("checked", true);
			document.getElementById("cash").readOnly = true;
			document.getElementById("doorDeliveryCharges").readOnly = true;
			}
		else
			{
			document.getElementById("doorDeliveryCharges").readOnly = false;
			document.getElementById("cash").readOnly = false;

			}
	}
</script>

</head>
<!-- style="overflow-y: hidden;overflow-x: hidden;" -->
<body>
	<div class="wrapper">
		<div class="inner" style="width: 90%">
			<div style="width: 1%;">
				<h3>
				  <b>Delivery</b>
				</h3>
				<!-- <img src="../../img/product/parcel.jpg" alt=""> -->
			</div>
			<b style="width: 5%;"></b>
			<div class="blog-details-area mg-b-15">
				<div class="container-fluid" style="width: 90%;">
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="blog-details-inner">
								<main>
									<form action="/addDelivery" method="post" style="width: 100%;" name="deliveryform" id="deliveryform">
										<input type="hidden" class="form-control" name="id" id="id"
											value="">
										<p style="color: red" align="center">${errormsg}</p>
										<p style="color: orange" align="center">${warningmsg}</p>
										<p style="color: green" align="center">${DeliverysuccessMessage}</p>
										
										<input type="hidden" id="userName" value="${sessionScope.USER_LOGIN_ID}"/>
										
										<div class="row">
											<div class="col-md-4 control-margin">
												<div class="row element-margin">
													<div class="input-group">


														 <input type="text" class="form-control"
															placeholder="Type LR No" name="txtSearch" id="txtSearch" onkeyup="searchLRNumber(this);">
														 
														<button type="button" class="btn btn-secondary"
															id="btnSearch" onclick="getSearchParcel();">Search</button>
													</div>
													<div class="mt-0">
														<label for="txtSearch" class="form-label"><small>(Type
																LR No. or Party Name and press Search)</small></label>
													</div>
												</div>
												
												<input type="hidden" name="fromLocation" value="${deliveryB.fromLocation}">
												<input type="hidden" name="toLocation" value="${deliveryB.toLocation}">
												
												
												
												
												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label" for="txtName">From Name</label>
													</div>
													<div class="col-sm-8">
														<input type="text" class="form-control" name="fromName"
															value="${deliveryB.fromName}">
													</div>
												</div>
												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label" for="from_phone">From
															Phone No</label>
													</div>
													<div class="col-sm-8">
														<input type="tel"
															class="form-control" placeholder="1234567890" pattern="[0-9]{10}" id="from_phone" name="from_phone"
															value="${deliveryB.from_phone}"
															 required>
													</div>
												</div>
												
												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label" for="txtName">To Name</label>
													</div>
													<div class="col-sm-8">
														<input type="text" class="form-control" name="toName"
															value="${deliveryB.toName}">
													</div>
												</div>
												
												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label" for="to_phone">To Phone
															No</label>
													</div>
													<div class="col-sm-8">
														<input type="tel" placeholder="1234567890" pattern="[0-9]{10}" class="form-control"
															id="to_phone" name="to_phone" value="${deliveryB.to_phone}"
															 required>
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
														<label for="txtOGPL" class="form-label">OGPL</label>
													</div>
													<div class="col-sm-8">
														<input type="text" class="form-control" name="ogpl"
															value="${deliveryI.ogplNo}">
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
												
											</div> 
											<div class="col-md-4 control-margin">
												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label" for="txtddVehicle">Vechile No</label>
													</div>
													<div class="col-sm-8">
														<select id="ddVehicle" name="ddVehicle"
															class="form-control" disabled>
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
														<input type="radio" class="form-check-input" name="status" id="status"
															value="HOLD" disabled> <label class="form-check-label"
															for="chkHold">Hold</label>
													</div>
													<div class="col-sm-4">
													
													
														<input type="radio" class="form-check-input" name="status" id="status"
															value="DELIVERED" disabled> <label
															class="form-check-label" for="chkDelivered" >Delivered</label>
													</div>
													<div class="col-sm-4">
														<input type="radio" class="form-check-input" name="status" id="status"
															value="PRINTED" disabled> <label class="form-check-label"
															for="chkPrinted">Printed</label>
													</div>
												</div>	
												
												
											
												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label" for="txtDeliveryBillNo">E-Way Bill No.
															</label>
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
														<label class="form-label" for="txtPaidTotal">Paid</label>
													</div>
													<div class="col-sm-8">
														<input type="text" class="form-control" name="paid" id="paid"
															value="${deliveryB.paid}">
													</div>
													
												</div>
												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label" for="txtToPay">To Pay</label>
													</div>
													<div class="col-sm-8">
														<input type="text" class="form-control" name="toPay" id="toPay"
															value="${deliveryB.topay}">
													</div>
												</div>
												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label" for="txtTotal">Booking Discount</label>
													</div>
													<div class="col-sm-8">
														<input type="number" class="form-control" name="discount" id="discount"
															value="${deliveryB.discount}">															
													</div>
												</div>
												
												
											</div>
											<div class="col-md-4 control-margin">
												<!-- <div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label" for="txtHamali">Hamali</label>
													</div>
													<div class="col-sm-8">
														<input type="text" class="form-control" name="hamali"
															value="0">
													</div>
												</div> -->
												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label" for="txtUnloadingCharges">Unloading
															Charges</label>
													</div>
													<div class="col-sm-8">
														<input type="text" class="form-control"
															name="unloadingCharges" id="unloadingCharges" value="${unloadingCharges}">
												</div>
												</div>
												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label" for="txtDoorDeliveryCharges">Door
															Delivery Charges</label>
													</div>
													<div class="col-sm-8">
														<input type="text" class="form-control"
															name="doorDeliveryCharges" id="doorDeliveryCharges" onchange="addDoorDeliveryToAMTP();" value="${outItems.doorDeliveryCharges}"  onblur="sumAmount();">
													</div>
												</div>
												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label" for="txtDemurrage">Demurrage</label>
													</div>
													<div class="col-sm-8">
														<input type="text" class="form-control" name="demurrage" id="demurrage" value="${delivery.demurrage}">
													</div>
												</div>
												
												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label" for="txtTotal">Delivery Discount</label>
													</div>
													<div class="col-sm-8">
														<input type="number" class="form-control" name="deliverydiscount" id="deliverydiscount"
															value="${deliveryB.deliveryDiscount}">															
													</div>
												</div>
												&nbsp;
												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label" for="txtTotal">TOTAL</label>
													</div>
													<div class="col-sm-8">
														<input type="number" class="form-control" name="total" id="total"
															value="${deliveryB.total+delivery.demurrage-deliveryB.deliveryDiscount+unloadingCharges+outItems.doorDeliveryCharges}">
															<input type="hidden" class="form-control" name="orgTotal" id="orgTotal"
															value="${deliveryB.total+delivery.demurrage-deliveryB.deliveryDiscount+unloadingCharges+outItems.doorDeliveryCharges}">
													</div>
												</div>
												<div class="row element-margin">
												
													<div class="col-sm-4">
														<label class="form-label" for="txtTotal">Balance Amount</label>
													</div>
													<div class="col-sm-8">
														<input type="number" class="form-control" name="amtToBePaid" id="amtToBePaid"
															value="${unloadingCharges+delivery.demurrage-deliveryB.deliveryDiscount+deliveryB.topay+outItems.doorDeliveryCharges}">
									
													</div>
												</div>
												&nbsp;
												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label" for="txtTotal">Cash</label>
													</div>
													<div class="col-sm-8">
														<input type="number" class="form-control" 
															name="cash" id="cash" onblur="sumRefund();" value="${outItems.cash}" required>
													</div>
												</div>
												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label" for="txtRefund">Refund</label>
													</div>
													<div class="col-sm-8">
														<input type="text" id="refund"
															name="refund" class="form-control" name="refund" value="${outItems.refund}">
													</div>
												</div>
											</div>
										</div>
												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label"></label>
													</div>
													<div class="col-sm-8"></div>
												</div>
										<div class="row control-margin">
											<div class="col-md-6 control-margin">
												<button type="button" class="btn btn-primary button-margin col-md-2"
													id="btnSave" onclick="save_confirm();">Deliver</button>
												<a href="/menu"><button type="button"
														class="btn btn-primary button-margin col-md-2"
														id="btnClear">Back</button></a>
												<button type="button" class="btn btn-primary button-margin col-md-2"
													onclick="getDeliverySlip()" id="btnPrint">Print</button>

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

<script type="text/javascript">






deliveryCheck("${deliveryB.fromName}","${deliveryB.igplStatus}");








</script>
</html>