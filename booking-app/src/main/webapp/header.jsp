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
<script src="../../js/jquery-3.5.1.js"></script>
<script src="../../js/main.js"></script>
<script src="../../js/jquery.dataTables.min.js"></script>
<script src="../../js/dataTables.bootstrap5.min.js"></script>
<script src="../../js/dataTables.select.min.js"></script>

<meta charset="ISO-8859-1">
<script >
$(document).ready(function() {
    $('#data-table').DataTable();
} );
</script>

</head>

 <nav style="background-image: linear-gradient(#0f68b4,#1a1e2c)" class="navbar navbar-dark bg-primary">

	<a class="btn" style="color: white;font-weight: bold;font-size: larger;"  href="/menu">Menu</a>
	<label style="color: white;font-size: medium;" >${sessionScope.USER_NAME}</label>
	<a class="btn" style="color: white;font-weight: bold;font-size: larger;"  onclick="return confirm('Are you sure you want to logout?')" href="/logout">Logout  </a>
</nav>
<!-- <div class="footer" style="background-image: linear-gradient(#0f68b4,#1a1e2c)"><p>© 2021 Copyright: SS Marketing</p>
</div>
 -->
</html>

