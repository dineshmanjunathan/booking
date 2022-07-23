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
   /* $(document).ready(function() {
    $('#ogpl-consol-table').DataTable({
    	dom: 'Bfrtip',
    	buttons: [
    		{
    			extend: 'pdfHtml5',
    			   title: 'OGPL-LR CONSOLIDATE REPORT',
    			filename: 'Ogpl-Lr-Report_pdf'
    			},
    			    {
    			extend: 'excelHtml5',
    			   title: 'OGPL-LR CONSOLIDATE REPORT',
    			filename: 'Ogpl-Lr-Report_excel'
    			},
    			    {
    			extend: 'csvHtml5',
    			   title: 'OGPL-LR CONSOLIDATE REPORT',
    			filename: 'Ogpl-Lr-Report_csv'
    			}
        ]
    });
} );  */
 
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

$(document).ready(function() {
    $('#data-table-without-pdf').DataTable({
    	dom: 'Bfrtip',
    	buttons: [
            'excelHtml5',
            'csvHtml5'        ]
    });
} );
 
$(document).ready(function() {
    $('#ogpl-list').DataTable({
    	dom: 'Bfrtip',
    	buttons: [
    		{
    			extend: 'pdfHtml5',
    			   title: 'OUTGOING PARCELS',
    			filename: 'OutgoingParcels',
    			customize: function(doc) {
    				doc.styles.tableHeader = {
    	                    bold: true,
    	                    color: 'black',
    	                    fillColor: 'white'
    	                            };
    				
    			  }
    			},
    			    {
    			extend: 'excelHtml5',
    			   title: 'OUTGOING PARCELS',
    			filename: 'OutgoingParcels'
    			},
    			    {
    			extend: 'csvHtml5',
    			   title: 'OUTGOING PARCELS',
    			filename: 'OutgoingParcels'
    			}
        ]
    });
} );  
$(document).ready(function() {
    $('#income-list').DataTable({
    	dom: 'Bfrtip',
    	buttons: [
    		{
    			extend: 'pdfHtml5',
    			   title: 'INCOMING PARCELS',
    			filename: 'IncomingParcels',
    			customize: function(doc) {
    				doc.styles.tableHeader = {
    	                    bold: true,
    	                    color: 'black',
    	                    fillColor: 'white'
    	                            };
    				
    			  }
    			},
    			    {
    			extend: 'excelHtml5',
    			   title: 'INCOMING PARCELS',
    			filename: 'IncomingParcels'
    			},
    			    {
    			extend: 'csvHtml5',
    			   title: 'INCOMING PARCELS',
    			filename: 'IncomingParcels'
    			}
        ]
    });
} );  

$(document).ready(function() {
    $('#booking-inv').DataTable({
    	dom: 'Bfrtip',
    	buttons: [
    		{
    			extend: 'pdfHtml5',
    			   title: 'BOOKING INVENTORY LIST',
    			filename: 'BookingInventory',
    			customize: function(doc) {
    				doc.styles.tableHeader = {
    	                    bold: true,
    	                    color: 'black',
    	                    fillColor: 'white'
    	                            };
    				
    			  }
    			},
    			    {
    			extend: 'excelHtml5',
    			   title: 'BOOKING INVENTORY LIST',
    			filename: 'BookingInventory'
    			},
    			    {
    			extend: 'csvHtml5',
    			   title: 'BOOKING INVENTORY LIST',
    			filename: 'BookingInventory'
    			}
        ]
    });
} );  


$(document).ready(function() {
    $('#delivery-inv').DataTable({
    	dom: 'Bfrtip',
    	buttons: [
    		{
    			extend: 'pdfHtml5',
    			   title: 'DELIVERY INVENTORY LIST',
    			filename: 'DeliveryInventory',
    			customize: function(doc) {
    				doc.styles.tableHeader = {
    	                    bold: true,
    	                    color: 'black',
    	                    fillColor: 'white'
    	                            };
    				
    			  }
    			},
    			    {
    			extend: 'excelHtml5',
    			   title: 'DELIVERY INVENTORY LIST',
    			filename: 'DeliveryInventory'
    			},
    			    {
    			extend: 'csvHtml5',
    			   title: 'DELIVERY INVENTORY LIST',
    			filename: 'DeliveryInventory'
    			}
        ]
    });
} );  



$(document).ready(function() {
    $('#booking-table').DataTable({
    dom: 'Bfrtip',
   
    buttons: [
    {
extend: 'pdfHtml5',
   title: 'BOOKING REPORT',
   orientation: 'landscape',
filename: 'Booking_pdf',
customize: function(doc) {
	doc.styles.tableHeader = {
            bold: true,
            color: 'black',
            fillColor: 'white'
                    };
	
  }
},
    {
extend: 'excelHtml5',
   title: 'BOOKING REPORT',
filename: 'Booking_excel'
},
    {
extend: 'csvHtml5',
   title: 'BOOKING REPORT',
filename: 'Booking_csv'
}
        ]
    });
} );
 $(document).ready(function() {
	 
	 
	    $('#ogpl-table').DataTable({
	    dom: 'Bfrtip',
	   
	    buttons: [
	    {
	extend: 'pdfHtml5',
	   title: 'OGPL REPORT',
	   orientation: 'landscape',
	filename: 'Ogpl_pdf',
		customize: function(doc) {
			doc.styles.tableHeader = {
                    bold: true,
                    color: 'black',
                    fillColor: 'white'
                            };
			
		  }
	},
	    {
	extend: 'excelHtml5',
	   title: 'OGPL REPORT',
	filename: 'Ogpl_excel'
	},
	    {
	extend: 'csvHtml5',
	   title: 'OGPL REPORT',
	filename: 'Ogpl_csv'
	}
	        ]
	    });
	} );
 $(document).ready(function() {
	    $('#ogplShow-table').DataTable({
	    dom: 'Bfrtip',
	   
	    buttons: [
	    {
	extend: 'pdfHtml5',
	   title: 'OGPL CONSOLIDATED REPORT',
	filename: 'OgplReport_pdf',
	customize: function(doc) {
		doc.styles.tableHeader = {
                bold: true,
                color: 'black',
                fillColor: 'white'
                        };
		
	  }
	},
	    {
	extend: 'excelHtml5',
	   title: 'OGPL CONSOLIDATED REPORT',
	filename: 'OgplReport_excel'
	},
	    {
	extend: 'csvHtml5',
	   title: 'OGPL CONSOLIDATED REPORT',
	filename: 'OgplReport_csv'
	}
	        ]
	    });
	} );
 $(document).ready(function() {
	    $('#delivery-table').DataTable({
	    dom: 'Bfrtip',
	   
	    buttons: [
	    {
	extend: 'pdfHtml5',
	   title: 'DELIVERY REPORT',
	   orientation: 'landscape',
	filename: 'Delivery_pdf',
	customize: function(doc) {
		doc.styles.tableHeader = {
                bold: true,
                color: 'black',
                fillColor: 'white'
                        };
		
	  }
	},
	    {
	extend: 'excelHtml5',
	   title: 'DELIVERY REPORTT',
	filename: 'Delivery_excel'
	},
	    {
	extend: 'csvHtml5',
	   title: 'DELIVERY REPORT',
	filename: 'Delivery_csv'
	}
	        ]
	    });
	} );
 $(document).ready(function() {
	    $('#expense-table').DataTable({
	    dom: 'Bfrtip',
	   
	    buttons: [
	    {
	extend: 'pdfHtml5',
	   title: 'EXPENSE REPORT',
	filename: 'Expense_pdf',
	customize: function(doc) {
		doc.styles.tableHeader = {
                bold: true,
                color: 'black',
                fillColor: 'white'
                        };
		
	  }
	},
	    {
	extend: 'excelHtml5',
	   title: 'EXPENSE REPORT',
	filename: 'Expense_excel'
	},
	    {
	extend: 'csvHtml5',
	   title: 'EXPENSE REPORT',
	filename: 'Expense_csv'
	}
	        ]
	    });
	} );
 $(document).ready(function() {
	    $('#booking-without-table').DataTable({
	    dom: 'Bfrtip',
	   
	    buttons: [
	    {
	extend: 'excelHtml5',
	   title: 'BOOKING REPORT',
	filename: 'Booking_excel'
	},
	    {
	extend: 'csvHtml5',
	   title: 'BOOKING REPORT',
	filename: 'Booking_csv'
	}
	        ]
	    });
	} );
 
 
 $(document).ready(function() {
	 
	 
	    $('#ogpl-table-insert').DataTable({
	    dom: 'Bfrtip',
	   
	    buttons: [
	    {
	extend: 'pdfHtml5',
	exportOptions: {
        columns: [ 0,2,3,4,5,6,7,8,9,10 ]
    },
    pageSize : 'A4',
	   title: 'Outgoing Parcel',
	filename: 'Outgoing_Parcel',
		customize: function(doc) {
			doc.styles.tableHeader = {
                 bold: true,
                 color: 'black',
                 fillColor: 'white'
                         };
            doc.defaultStyle.fontSize = 8;


			
		  }
	},
	    {
	extend: 'excelHtml5',
	   title: 'Outgoing Parcel',
	filename: 'Outgoing_Parcel'
	},
	    {
	extend: 'csvHtml5',
	   title: 'Outgoing Parcel',
	filename: 'Outgoing_Parcel'
	}
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

