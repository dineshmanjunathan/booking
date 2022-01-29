<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html class="no-js" lang="en">
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
<script>
$('document').ready(function() {
	
	
	<c:if test="${empty expense.id}">
		pickSubcategories(true);
	</c:if>
	<c:if test="${not empty expense.id}">
		pickSubcategories(false);
		<c:if test="${sessionScope.ROLE eq 'ADMIN'}">
			disableOnEdit(true);
		</c:if>
		<c:if test="${sessionScope.ROLE ne 'ADMIN'}">
			disableOnEdit(false);
		</c:if>
	</c:if>
	
	$('#expenseCategory').on('change', function() {
		pickSubcategories(true);
	})
});

function pickSubcategories(reset) {
	let category = parseInt($('#expenseCategory').val());
	if (reset) {
		$('select#expenseSubCategory').val('');
	}
	let options = $('select#expenseSubCategory option');
	$.each(options, function(_, option) {
		$(option).removeAttr('disabled');
		if ($(option).data('category') !== category) {
			$(option).attr('disabled', 'disabled');
		}
	});
}
function disableOnEdit(isAdmin) {
	if (!isAdmin) {
		$('select#expenseCategory').attr('disabled', 'disabled');
		$('select#expenseSubCategory').attr('disabled', 'disabled');
		$('select#paymentMode').attr('disabled', 'disabled');
		$('input#amount').attr('disabled', 'disabled');
		$('input#expenseNumber').attr('disabled', 'disabled');
	}
}


</script>
</head>
<body>
	<div class="wrapper">
		<div class="inner" style="width: 90%">
			<div style="width: 15%;">
				<h3>
					<b>Expense</b>
				</h3>
				<img src="../../img/product/parcel.jpg" alt="">
			</div>
			<b style="width: 5%;"></b>
			<div class="blog-details-area mg-b-15">
				<div class="container-fluid" style="width: 90%;">
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="blog-details-inner">
								<main>
									<c:choose>
										<c:when test="${not empty expense.id}">
											<c:url var="action" value="/editExpence" />
										</c:when>
										<c:otherwise>
											<c:url var="action" value="/addExpence" />
										</c:otherwise>
									</c:choose>
									<form action="${action}" method="post" style="width: 100%;">
										<input type="hidden" class="form-control" name="id" id="id" value="${expense.id}">
										<p style="color: red" align="center">${errormsg}</p>

										<div class="row">
											<div class="col-md-6 control-margin">
												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label" for="expenseBranch">Expense
															Branch</label>
													</div>
													<div class="col-sm-8">
														<c:choose>
																<c:when test="${sessionScope.ROLE eq 'ADMIN'}">
																	<select class="form-select bg-info text-dark" name="expenseBranch" id="expenseBranch">
																		<option value="">-Select From Location-</option>
																		<c:forEach var="options" items="${locationListing}" varStatus="status">
																			<option value="${options.id}"
																				${options.id == expense.expenseBranch ? 'selected' : ''}>${options.location}</option>
																		</c:forEach>
																	</select>
																</c:when>
																<c:otherwise>
																	<input type="hidden" name="expenseBranch" id="expenseBranch" value="${sessionScope.USER_LOCATIONID}" readonly>
																	<input type="text" class="form-control" 
																	name="fromdummy" id="fromdummy" value="${sessionScope.USER_LOCATION}" readonly>
																</c:otherwise>
															</c:choose>
														
													</div>
												</div>
												<div class="row element-margin">
													<div class="col-sm-4">
														<label for="dtFrom" class="form-label">Expense
															Category</label>
													</div>

													<div class="col-sm-8">
														<select class="form-select" name=expenseCategory id="expenseCategory">
															<c:forEach var="options" items="${expenseCategoryListing}" varStatus="status">
																<option value="${options.id}" ${options.id == expense.expenseCategoryId ? 'selected' : ''}>${options.category}</option>
															</c:forEach>
														</select>
															
													</div>
												</div>
												<div class="row element-margin">
													<div class="col-sm-4">
														<label for="dtFrom" class="form-label">Expense Sub
															Category</label>
													</div>

													<div class="col-sm-8">
														<select class="form-select" name=expenseSubCategory id ="expenseSubCategory">
															<option value=''>Select Sub Category</option>
															<c:forEach var="options" items="${expenseSubCategoryListing}" varStatus="status">
																<option data-category="${options.expenceCategory.id}" value="${options.id}" ${options.id == expense.expenseSubCategoryId ? 'selected' : ''}>${options.subCategory}</option>
															</c:forEach>
														</select>
													</div>
												</div>
												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label" for="description">Description</label>
													</div>
													<div class="col-sm-8">
														<input type="text" class="form-control" name="description" id="description"
															value="${expense.description}">
													</div>
												</div>
												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label" for="amount">Amount</label>
													</div>
													<div class="col-sm-8">
														<input type="text" class="form-control" name="amount" id="amount" value="${expense.amount}">
													</div>
												</div>
												<div class="row element-margin">
													<div class="col-sm-4">
														<label for="dtFrom" class="form-label">Payment
															Mode</label>
													</div>

													<div class="col-sm-8">
														<select class="form-select" name=paymentMode id="paymentMode">
															<option value="upi">UPI</option>
															<option value="cash">Cash</option>
															<option value="cheque">Cheque</option>
															<option value="card">Card</option>
															<option value="netbanking">Net Banking</option>
														</select>
													</div>
												</div>
											</div>
											<div class="col-md-6 control-margin">
												<div class="row element-margin">
													<div class="col-sm-4">
														<label for="bookedOn" class="form-label">Date</label>
													</div>
													<div class="col-sm-8">
														<input type="text" class="form-control bg-info text-dark"
															id="expenseDate" placeholder="" name="expenseDate"
															value="${expense.expenseDate}" readonly>
													</div>
												</div>
												
												<div class="row element-margin">
													<div class="col-sm-4">
														<label for="bookedOn" class="form-label">Expense No</label>
													</div>
													<div class="col-sm-8">
														<input type="text" class="form-control bg-info text-dark"
															placeholder="Expense No" id="expenseNumber"
															name="expenseNumber" value="${expenseNo}">
													</div>
												</div>
												<div class="row element-margin">
												<div class="col-sm-4">
													<label class="form-label" for="createBy">Created By</label>
												</div>
												<div class="col-sm-8">
													<div class="form-row">
														<input type="text" class="form-control" name="createBy" id="createBy"
																placeholder="Created By" value="${expense.createBy}" readonly="readonly">
													</div>
												</div>
											</div>
										</div>

										<div class="row control-margin">
											<div class="col-md-12 control-margin">
												<button type="submit" class="btn btn-primary button-margin"
													id="btnSave">Save</button>
												<button type="reset" class="btn btn-primary button-margin"
													id="btnClear">Clear</button>
												<a href="/expence"><button type="button"
														class="btn btn-primary button-margin col-md-2"
														id="btnClear">Back</button></a>
											</div>
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
	<script src="js/jquery-3.3.1.min.js"></script>
	<script src="js/main.js"></script>
</body>
</html>