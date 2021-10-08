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
.wrapper {
    min-height: 65vh;
    display: flex;
    align-items: center;
    background-size: cover;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/jsbarcode/3.6.0/JsBarcode.all.min.js"></script>

<script>
$('document').ready(function() {
	$('#generateBarcode').on('click', function() {	
		var barcodeValue = $("#Lrno").val();
		JsBarcode("#barcode", barcodeValue, {
			//format: barcodeType,
			displayValue: false,
			lineColor: "#24292e",
			width:2,
			height:50,	
			fontSize: 20					
		});		

		var mywindow = window.open('', 'PRINT', 'height=400,width=600');
		var printContents = document.getElementById("barcodediv").innerHTML;

		mywindow.document.write(printContents);
		 mywindow.document.close(); // necessary for IE >= 10
		mywindow.focus(); // necessary for IE >= 10*/
		mywindow.print();
		

	});
	
});
</script>
</head>

<body >
<form method="post">
<div class="wrapper">
		<div class="inner">
			<div style="width: 365px;">
				<img src="../../img/product/parcel.jpg" alt="">
			</div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<div class="blog-details-area mg-b-15">
		<div class="container-fluid" style="width: 100%;">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="blog-details-inner">
						<br><br>
						<p style="color: green" align="center">${bookingsuccessmessage}</p>
						<%-- <p style="color: green;font-weight: bold; " align="center">${LRNumber}</p> --%>
						<p style="color: green;font-weight: bold; " align="center">LR Number : ${LRNumber}</p>
						<input type="hidden" class="form-control" name="Lrno" id="Lrno" value="${LRNumber}">
						<br>
						<div class="col-md-4" id="barcodediv" style="display: none;">		
							<svg id="barcode"></svg>
						</div>
						<div class="row control-margin">
						<div class="col-md-12 control-margin">
						<a href="/booking"><button type="button" class="btn btn-primary button-margin" id="BB">Back to Booking</button></a>
						<button type="button" class="btn btn-primary button-margin" id="generateBarcode" name="generateBarcode" >Print BR Code</button>
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