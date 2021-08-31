<!doctype html>
<html lang="en">
  <head>
    <title>BookingApp</title>
	 <link rel="stylesheet" href="../../css/bootstrap.min.css">
	
	<style>
		.control-margin{
			margin-top: 20px;
		}
		.element-margin{
			margin-bottom: 5px;
		}
		.button-margin{
			margin-right: 5px;
		}		
	</style>
	
  </head>
 
 <nav style="background-image: linear-gradient(#0f68b4,#1a1e2c)" class="navbar navbar-dark bg-primary">

	<div class="header-right-info">
		<ul class="nav navbar-nav mai-top-nav header-right-menu" style="padding: 20px 60px;">
			<li class="nav-item">
					
				</li>
		</ul>
	</div>
</nav>
 
   <body class="bg-light">    
   <form action="/login" method="post">  
                      	<p style="color:green" align="center">${adminlogout}</p>
                      	<p style="color:green" align="center">${registersuccess}</p>        
	<div class="blog-details-area mg-b-15">
            <div class="container-fluid" style="width: 90%;">
                <div class="row">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <div class="blog-details-inner">
	  <main>
		<div class="row">
		  <div class="col-md-4 control-margin" style="width: 50%;">
			
				<div class="image-holder">
					<img src="../../img/product/parcel.jpg" alt="">
				</div>
		  </div>
		  
		  <div class="col-md-6 " style="margin-top: 14%;">
		   <div class="row element-margin">
				 <div class="col-sm-8" style="margin-left: 50%;">
					<h4><b>LOGIN</b></h4>
				  </div>
				<div class="col-sm-4" style="margin-left: 50%;">
			  <p style="color:red">${errormsg}</p>
			  </div>
			  </div>
			  <div class="row element-margin">
				  <div class="col-sm-4">
					<label class="form-label" for="txtHamali">User ID</label>
				  </div>
				  <div class="col-sm-8">
					<input type="text" class="form-control" name ="userId" id="userId">
				  </div>
			  </div>
			  <div class="row element-margin">
				  <div class="col-sm-4">
					<label class="form-label" for="txtUnloadingCharges">Password</label>
				  </div>
				  <div class="col-sm-8">
					<input type="password" class="form-control" name ="password" id="password">
				  </div>
			  </div>
			  <div class="row control-margin">
			<div class="col-md-6 offset-4 control-margin">
			<button type="button" class="btn btn-primary button-margin" id="btnClear">Clear</button>
				<button type="submit" class="btn btn-primary button-margin" name="submit">Login</button>
				<button type="button" class="btn btn-primary button-margin" id="btnPrint">Register</button>
			</div>
		</div>
		  </div>
		

		</div>
	  </main>
	</div>
	</div></div></div></div>
</form>
  </body>
</html>
