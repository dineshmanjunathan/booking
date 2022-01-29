<!doctype html>
<html class="no-js" lang="en">

<head>
<meta charset="utf-8">
<title>BookingApp</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- MATERIAL DESIGN ICONIC FONT -->
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

<style type="text/css">
.form-control {
 border: 1px solid #212529 !important;
 height: auto;
 }
 #data-table_wrapper {
 border: solid 2px black;
 border-radius: 12px;
 padding-left: 20px;
 padding-top: 10px;
 }
 .dt-buttons button {
 	margin: 10px;
 }
</style>

<meta charset="ISO-8859-1">
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

</head>

 <nav style="background-image: linear-gradient(#0f68b4,#1a1e2c)" class="navbar navbar-dark bg-primary">

	<a class="btn" style="color: white;font-weight: bold;font-size: medium;"  href="/menu">Back to Menu</a>
	<label style="color: white;font-size: medium;" >${sessionScope.USER_NAME}</label>
	<a class="btn" style="color: white;font-weight: bold;font-size: medium;"  onclick="return confirm('Are you sure you want to logout?')" href="/logout">Logout  </a>
</nav>
</html>

