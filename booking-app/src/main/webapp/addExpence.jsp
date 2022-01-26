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
</head>
<body onload="toggleFormElements(true)">
	<div class="wrapper">
		<div class="inner" style="width: 90%">
			<div style="width: 15%;">
				<h3>
					<b>expence</b>
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
										<c:when test="${not empty expence}">
											<c:url var="action" value="/editexpence" />
										</c:when>
										<c:otherwise>
											<c:url var="action" value="/addexpence" />
										</c:otherwise>
									</c:choose>
									<form action="/addDelivery" method="post" style="width: 100%;">
										<input type="hidden" class="form-control" name="id" id="id"
											value="">
										<p style="color: red" align="center">${errormsg}</p>

										<div class="row">
											<div class="col-md-6 control-margin">
												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label" for="expenceBranch">Expense
															Branch</label>
													</div>
													<div class="col-sm-8">
														<select class="form-select" name=expenseCategory>
															<c:forEach var="options" items="${locationListing}"
																varStatus="status">
																<%-- <option value="${options.id}" ${options.id == booking.toLocation ? 'selected' : ''}>${options.location}</option> --%>
																<option value="${options.id}"
																	${options.id == expence.fromLocation ? 'selected' : ''}>${options.location}</option>
															</c:forEach>
														</select>
													</div>
												</div>
												<div class="row element-margin">
													<div class="col-sm-4">
														<label for="dtFrom" class="form-label">Expense
															Category</label>
													</div>

													<div class="col-sm-8">
														<c:choose>
															<c:when test="${deliveryB.fromLocation ne ''}">
																<select class="form-select" name=expenseCategory>
																	<c:forEach var="options" items="${locationList}"
																		varStatus="status">
																		<%-- <option value="${options.id}" ${options.id == booking.toLocation ? 'selected' : ''}>${options.location}</option> --%>
																		<option value="${options.id}"
																			${options.id == deliveryB.fromLocation ? 'selected' : ''}>${options.location}</option>
																	</c:forEach>
																</select>
															</c:when>
														</c:choose>
													</div>
												</div>
												<div class="row element-margin">
													<div class="col-sm-4">
														<label for="dtFrom" class="form-label">Expense Sub
															Category</label>
													</div>

													<div class="col-sm-8">
														<c:choose>
															<c:when test="${deliveryB.fromLocation ne ''}">
																<select class="form-select" name=expenseSubCategory>
																	<c:forEach var="options" items="${locationList}"
																		varStatus="status">
																		<%-- <option value="${options.id}" ${options.id == booking.toLocation ? 'selected' : ''}>${options.location}</option> --%>
																		<option value="${options.id}"
																			${options.id == deliveryB.fromLocation ? 'selected' : ''}>${options.location}</option>
																	</c:forEach>
																</select>
															</c:when>
														</c:choose>
													</div>
												</div>
												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label" for="description">Description</label>
													</div>
													<div class="col-sm-8">
														<input type="text" class="form-control" name="description"
															value="${deliveryB.item_count}">
													</div>
												</div>
												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label" for="amount">Amount</label>
													</div>
													<div class="col-sm-8">
														<input type="text" class="form-control" name="amount"
															value="${deliveryB.item_count}">
													</div>
												</div>
												<div class="row element-margin">
													<div class="col-sm-4">
														<label for="dtFrom" class="form-label">Payment
															Mode</label>
													</div>

													<div class="col-sm-8">
														<c:choose>
															<c:when test="${deliveryB.fromLocation ne ''}">
																<select class="form-select" name=paymentMode>
																	<c:forEach var="options" items="${locationList}"
																		varStatus="status">
																		<%-- <option value="${options.id}" ${options.id == booking.toLocation ? 'selected' : ''}>${options.location}</option> --%>
																		<option value="${options.id}"
																			${options.id == deliveryB.fromLocation ? 'selected' : ''}>${options.location}</option>
																	</c:forEach>
																</select>
															</c:when>
														</c:choose>
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
															value="${expenceAddedOn}" required>
													</div>
												</div>
												<div class="well">
													<div class="row element-margin">
														<div class="col-sm-4">
															<label for="bookedOn" class="form-label">Expence
																No</label>
														</div>
														<div class="input-group col-sm-4">
															<input type="text" class="form-control bg-info text-dark"
																placeholder="Expence No" id="expenseNumber"
																name="expenseNumber" value="${expenceNo}">
															<button type="button" class="btn btn-secondary"
																id="btnSearch" onclick="getLRNumberSearch();">Search</button>
														</div>
														<div class="mt-0">
															<label for="txtSearch" class="form-label"><small>(Type
																	Expence No and press Search)</small></label>
														</div>
													</div>
												</div>
												<div class="row element-margin">
												<div class="col-sm-4">
													<label class="form-label" for="createBy">Created By</label>
												</div>
												<div class="col-sm-8">
													<input type="text" class="form-control" name="createBy"
														value="${deliveryI.deliveredBy}">
												</div>
											</div>
										</div>

										<div class="row control-margin">
											<div class="col-md-12 control-margin">
												<button type="submit" class="btn btn-primary button-margin"
													id="btnSave">Save</button>
												<button type="reset" class="btn btn-primary button-margin"
													id="btnClear">Clear</button>
												<a href="/menu"><button type="button"
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