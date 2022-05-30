<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="../../fonts/material-design-iconic-font/css/material-design-iconic-font.min.css">

<!-- STYLE CSS -->
<link rel="stylesheet" href="../../css/incoming/style.css">
<link rel="stylesheet" href="../../css/style.css">
<link rel="stylesheet" href="../../css/bootstrap.min.css">
<link rel="stylesheet" href="../../css/bootstrap.min.css">
<link rel="stylesheet" href="../../css/buttons.bootstrap5.min.css">
<link rel="stylesheet" href="../../css/select.bootstrap5.min.css">
<link rel="stylesheet" href="../../css/printjs/print.min.css">

<script src="../../js/jquery-3.5.1.js"></script>
<script src="../../js/main.js"></script>
<script src="../../js/jquery.dataTables.min.js"></script>
<script src="../../js/dataTables.bootstrap5.min.js"></script>
<script src="../../js/dataTables.buttons.min.js"></script>
<script src="../../js/buttons.bootstrap5.min.js"></script>
<script src="../../js/buttons.html5.min.js"></script>
<script src="../../js/dataTables.select.min.js"></script>
<script src="../../js/pdfmake.min.js"></script>
<script src="../../js/vfs_fonts.js"></script>
<script src="../../js/jszip.min.js"></script>
<script src="../../js/printjs/print.min.js"></script>
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
		<div class="inner" style=" padding-left: 4%; ">
			<div id="reportArea">
				<h3><b>OGPL Report</b></h3>
				
				
				<table id="data-table" class="table table-striped" style="width:100%">
					<thead>
						<tr>
							
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

</script>

<script >
$(document).ready(function() {
    $('#data-table').DataTable({
    	dom: 'Bfrtip',
    	buttons: [
            'excelHtml5',
            'csvHtml5',
            'pdfHtml5'
        ]
    });
} );
</script>
</body>
<!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>