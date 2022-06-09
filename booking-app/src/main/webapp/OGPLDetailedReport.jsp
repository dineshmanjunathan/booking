<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="header.jsp"%>

</head>
<style>
@media (max-width: 767px)
#reportArea {
    transform: translateX(0);
    padding-top: 40px;
}
@media (max-width: 991px)
#reportArea {
    padding-top: 0;
}
#reportArea {
    padding-top: 42px;
    min-width: 63.88%;
    transform: translateX(-34px);
}
</style>

<body>
	<div class="wrapper">
		<div class="" style=" padding-left: 4%;width:100%; ">
			<div id="reportArea">
				<h3><b>OGPL Report</b></h3>
				<div class="row control-margin">
					<div class="col-md-12">
						<a class="btn btn-primary button-margin" href="/menu">Back</a>
					</div>
				</div>
				<div class="row control-margin" style="margin-top: 15px;margin-bottom: 15px">
					<div class="col-md-12">
					OGPL No :	<input type="text" id="ogpl" name="ogpl" placeholder="OGPL No">
					
				&nbsp;&nbsp;&nbsp;	From Date :	<input type="date" id="fromDate" name="fromDate" placeholder="From Date">
				&nbsp;&nbsp;&nbsp;	To Date :	<input type="date" id="toDate" name="toDate" placeholder="To Date">
				&nbsp;&nbsp;&nbsp;		<button
														class="btn btn-primary button-margin col-md-2"
														onclick="getreport();"> Show </button>
					</div>
				</div>
				
				<table id="data-table" class="table table-striped" style="width:100%">
					<thead>
						<tr>
							
							<!-- <th scope="col">Booked on</th> -->
							<th scope="col">OGPL No</th>
							<th scope="col">Total LR</th>
							<th scope="col">Paid</th>
							<th scope="col">TO PAY</th>
							<th scope="col">Fright</th>
							<th scope="col">Loading</th>
							<th scope="col">Fuel</th>
							<th scope="col">Unloading</th>
							<th scope="col">Demurage</th>
							<th scope="col">Booking Discount</th>
							<th scope="col">Delivery Discount</th>
							<th scope="col">Total Cost</th>
							<th scope="col">Action</th>
							
							
						</tr>
					</thead>
					<tbody>
						<c:forEach var="details" items="${OGPL}"
							varStatus="status">
							<tr>
								<td>${details.ogplNo}</td>
								<td>${details.totLR}</td>
								<td>${details.paid}</td>
								<td>${details.toPay}</td>
								<td>${details.fright}</td>
								<td>${details.loading}</td>
								<td>${details.fuel}</td>
								<td>${details.unloading}</td>
								<td>${details.demurage}</td>
								<td>${details.bookingDiscount}</td>
								<td>${details.deliveryDiscount}</td>
								
								<td>${details.totalCost}</td>
								
								
								<td>	<button type="button" style="width: auto"
														class="btn btn-primary button-margin col-md-2" id="bclear"
														onclick="getreportPOP(${details.ogplNo});">Show</button></td>
							
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<br>
				</div>
		</div>
	</div>
<script type="text/javascript">
function getreport() {
	
	var ogpl=document.getElementById("ogpl").value;
	var fromDate=document.getElementById("fromDate").value;
	var toDate=document.getElementById("toDate").value;



if(ogpl.length == 0)
{
ogpl="ALL";
}


if(fromDate.length==0)
{
	fromDate="1970-01-01";
	
	}


if(toDate.length==0)
{
	toDate=getCurrentDate();
}


window.location.href = "/report/ogplConsolidatedFilterReport?ogpl="+ogpl+"&fromDate="+fromDate+"&toDate="+toDate;




}

function getCurrentDate() {
    var d = new Date(),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2) 
        month = '0' + month;
    if (day.length < 2) 
        day = '0' + day;

    return [year, month, day].join('-');
}


function getreportPOP(ogpl) {
	


	window.open('/report/ogplConsolidatedFilterReportPOPUp?ogpl='+ogpl,'popUpWindow','height=500,width=1000,left=100,top=100,resizable=yes,scrollbars=yes,toolbar=yes,menubar=no,location=no,directories=no, status=yes');
	





}
</script>
</body>
<!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>