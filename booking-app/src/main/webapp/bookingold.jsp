<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Booking</title>
        <link rel="stylesheet" href="../../css/bootstrap.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
    </head>
    <body class="bg-primary">
        <div id="layoutAuthentication">
            <div id="layoutAuthentication_content">
                <main>
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-lg-12">
                                <div class="card shadow-lg border-0 rounded-lg mt-5">
                                    <div class="card-header"><h3 class="text-center font-weight-light my-4">Booking</h3></div>
                                    <div class="card-body">
                                        <form action="/booking/save" method="post">
										
											<div class="row mb-3">
                                                <div class="col-md-6">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <div class="form-group">
														  <label for="from">From:</label>
														  <select class="form-control" id="from" name="from">
															<option>Coimbatore</option>
															<option>Chennai</option>
															<option>Madurai</option>
															<option>Salem</option>
														  </select>
														</div>
                                                    </div>
                                                </div> 

												<div class="col-md-6">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <input class="form-control" id="inputBookNo" type="text" placeholder="Book No" name="bookingNo"/>
														<label for="inputBookNo">Book No</label>
                                                    </div>
                                                </div>
                                            </div>
											
											<div class="row mb-3">
                                                <div class="col-md-6">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <div class="form-group">
														  <label for="to">To:</label>
														  <select class="form-control" id="to" name="to">
															<option>Coimbatore</option>
															<option>Chennai</option>
															<option>Madurai</option>
															<option>Salem</option>
														  </select>
														</div>
                                                    </div>
                                                </div> 
												<div class="col-md-6">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <input class="form-control" id="inputDateTime" type="text" placeholder="Date/ Time" name="bookedOn"/>
                                                        <label for="inputDateTime">Date/ Time</label>
													</div>
                                                </div>  
                                            </div>
                                            <div class="row mb-3">
                                                <div class="col-md-6">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <input class="form-control" id="inputFrom" type="text" placeholder="From" name="fromName"/>
                                                        <label for="inputFirstName">From Name</label>
                                                    </div>
                                                </div> 

												<div class="col-md-4">
												<div class="row mb-3">
                                                    <div class="form-floating mb-2 mb-md-0">
                                                        <input class="form-control" id="inputLRNo" type="text" placeholder="LR No" name="lrNumber"/>
                                                        <label for="inputLRNo">LR No</label>												
													</div>
												</div>
												<div class="col-md-6">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <a class="btn btn-primary">Search</a>
                                                    </div>
                                                </div> 
                                                </div>  												
                                            </div>
											
											<div class="row mb-3">
                                                <div class="col-md-6">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <input class="form-control" id="inputPhone" type="text" placeholder="Phone" name="from_phone"/>
                                                        <label for="inputPhone">Phone</label>
                                                    </div>
                                                </div> 
												<div class="col-md-6">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <input class="form-control" id="inputTo" type="text" placeholder="To" name="toName"/>
                                                        <label for="inputTo">To</label>
                                                    </div>
                                                </div>												
                                            </div>
											
											<div class="row mb-3">
                                                <div class="col-md-6">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <input class="form-control" id="inputRemarks" type="email" placeholder="Remarks" name="remarks"/>
														<label for="inputRemarks">Remarks</label>
                                                    </div>
                                                </div>  
												<div class="col-md-6">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <input class="form-control" id="inputToPhone" name = "to_phone" type="text" placeholder="Phone" />
                                                        <label for="inputToPhone">Phone</label>
                                                    </div>
                                                </div> 
                                            </div>
											
											<div class="row mb-3">
                                                <div class="col-md-6">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <input class="form-control" id="inputValue" type="text" placeholder="Value" name = "fromValue" />
														<label for="inputValue">Value</label>
                                                    </div>
                                                </div> 
												<div class="col-md-6">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <input class="form-control" id="inputNoOfItems" name = "item_count" type="number" placeholder="No Of Items" />
                                                        <label for="inputNoOfItems">No Of Items</label>
                                                    </div>
                                                </div> 												
                                            </div>
											
											<div class="row mb-3">
                                                <div class="col-md-6">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <input class="form-control" id="inputConsInvNo" type="text" placeholder="Cons. INV No." name="invNo"/>
														<label for="inputConsInvNo">Cons. INV No.</label>
                                                    </div>
                                                </div>
												<div class="col-md-6">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <input class="form-control" id="inputWeight" name = "weight" type="number" placeholder="Weight (KG)" />
                                                        <label for="inputWeight">Weight (KG)</label>
                                                    </div>
                                                </div>
                                            </div>
											
											<div class="row mb-3">
                                                <div class="col-md-6">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <input class="form-control" id="inputConsTinNo" type="text" placeholder="Cons. TIN No." />
														<label for="inputConsTinNo">Cons. TIN No.</label>
                                                    </div>
                                                </div>
												<div class="col-md-6">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <input class="form-control" id="inputFrieght" name = "freight_status" type="number" placeholder="Freight (TOPAY)" />
                                                        <label for="inputFrieght">Freight (TOPAY)</label>
                                                    </div>
                                                </div>
                                            </div>
											
											<div class="row mb-3">
                                                <div class="col-md-6">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <input class="form-control" id="inputBillDesc" type="text" placeholder="Bill Desc" name="billDesc"/>
														<label for="inputBillDesc">Bill Desc</label>
                                                    </div>
                                                </div> 
												<div class="col-md-6">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <input class="form-control" id="inputLoadingCharges" name = "loading_charges" type="number" placeholder="Loading Charges (TOPAY)" />
                                                        <label for="inputLoadingCharges">Loading Charges (TOPAY)</label>
                                                    </div>
                                                </div>
                                            </div>
											
											<div class="row mb-3">
                                                <div class="col-md-6">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <input class="form-control" id="inputBillValue" type="text" placeholder="Bill Value" name="billValue" />
														<label for="inputBillvalue">Bill Value</label>
                                                    </div>
                                                </div>   
												<div class="col-md-6">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <input class="form-control" id="inputPickupCharges" name = "door_pick_charges" type="number" placeholder="Door Pickup Charges (TOPAY)" />
                                                        <label for="inputPickupCharges">Door Pickup Charges (TOPAY)</label>
                                                    </div>
                                                </div>
                                            </div>
											
											<div class="row mb-3">
                                                <div class="col-md-6">
                                                    <div class="form-floating mb-3 mb-md-0">
													<div class="checkbox disabled">
													  <label><input type="checkbox" value="" disabled name="isPrinted"> Printed</label>
													</div>
                                                    </div>
                                                </div>   
												<div class="col-md-6">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <input class="form-control" id="inputOthers" name = "other_charges" type="number" placeholder="Others" />
                                                        <label for="inputOthers">Others</label>
                                                    </div>
                                                </div>
                                            </div>
											
											<div class="row mb-3">
                                                <div class="col-md-6">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <input class="form-control" id="inputBookedBy" type="text" placeholder="Booked By" name="bookedBy" />
														<label for="inputBookedBy">Booked By</label>
                                                    </div>
                                                </div>  
                                            </div>
                                          
                                            <div class="row mb-3">
                                                <div class="col-md-6">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <input class="form-control" id="inputPaid" name = "paid" type="number" placeholder="Paid" />
                                                        <label for="inputPaid">Paid</label>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <input class="form-control" id="inputToPay" name = "topay" type="number" placeholder="To Pay" />
                                                        <label for="inputToPay">To Pay</label>
                                                    </div>
                                                </div>
                                            </div>
											<div class="row mb-3">
											<div class="col-md-2">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <input class="form-control" id="inputTotal" name = "total" type="number" placeholder="Total" />
                                                        <label for="inputTotal">Total</label>
                                                    </div>
                                             </div>
											 <div class="col-md-2">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <input class="form-control" id="inputCash" name = "cash" type="number" placeholder="Cash" />
                                                        <label for="inputCash">Cash</label>
                                                    </div>
                                             </div>
											 
											 <div class="col-md-2">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <input class="form-control" id="inputRefund" name = "refund" type="number" placeholder="Refund" />
                                                        <label for="inputRefund">Refund</label>
                                                    </div>
                                             </div>
											 </div>
                                            <div class="row mb-1">
											<div class="col-md-1">
                                                <div class="d-grid"><button class="btn btn-primary btn-block" type="submit" name="submit" value="save">Save</button></div>
											</div>
												
											<div class="col-md-1">
                                                <div class="d-grid"><button class="btn btn-primary btn-block">Clear</button></div>
											</div>
												
											<div class="col-md-1">
                                                <div class="d-grid"><button class="btn btn-primary btn-block">Quit</button></div>
											</div>
												
											<div class="col-md-1">
                                                <div class="d-grid"><button class="btn btn-primary btn-block">Help</button></div>
											</div>
												
											<div class="col-md-1">
                                                <div class="d-grid"><button class="btn btn-primary btn-block">New</button></div>
											</div>
											<div class="col-md-1">
                                                <div class="d-grid"><button class="btn btn-primary btn-block">Edit</button></div>
											</div>
											<div class="col-md-1">
                                                <div class="d-grid"><button class="btn btn-primary btn-block">Delete</button></div>
											</div>
											
											<div class="col-md-1">
                                                <div class="d-grid"><button class="btn btn-primary btn-block">Bill..</button></div>
											</div>
											
											<div class="col-md-1">
                                                <div class="d-grid"><button class="btn btn-primary btn-block">Print</button></div>
											</div>
                                            </div>
                                        </form>
                                    </div>                                   
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
            </div>
        </div>
		<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="../../js/scripts.js"></script>
    </body>
</html>
