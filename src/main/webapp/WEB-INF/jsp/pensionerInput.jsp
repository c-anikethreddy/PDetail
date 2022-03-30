<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
	<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.6.3/css/all.css">
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<title>Pension Management</title>
<style>
.box-design {
	width: 350px;
	background-color: transparent;
	margin: 20px;
	padding: 20px;
	box-shadow: 5px 10px 8px 10px #888888;
	padding: 30px;
	border-radius: 5mm;
}
.transparent
{  
     background-color: transparent ;   /*whichever you want*/
    opacity: 0.7;           /*0.5 determines transparency value*/ 
    filter:(opacity=70);       
}
.footer {
   position: fixed;
   left: 0;
   bottom: 0;
   width: 100%;
   color: #4d0000;
   text-align: center;
    display:inline-block;
    font-weight:bold;
}
</style>
<script type="text/javascript"> 
        window.history.forward(); 
        function noBack() { 
            window.history.forward(); 
        } 
    </script>
</head>
<body
	style="background-image: url(https://pm-image.s3.amazonaws.com/retire-rich.jpg); background-size: auto; background-repeat: no-repeat; background-position: inherit;">
	<header>
	<nav class="navbar bg-light transparent">
		<h3 class=" text-center display-5 text-uppercase font-bold"
			style="margin-top: 5px; font-family: sans-serif;color:#4d0000"><i class="fab fa-google-wallet"> </i>&nbsp; Pension
			Management Portal</h3></nav>
	</header>
	<section>
	<div class="container">
	<p class="lead text-center" style="color: RED" id="errorMessage">${fillmsg}</p>
	
		<div class="row">
			<div class="col float-left box-design" style="margin-right: 80px;">
				<form:form name="form" action="pensionerdetail" method="post"
					modelAttribute="pensionInput">
					<div class="form-group">
						<input id="name" name="name" path="name" type="text"
							class="form-control" placeholder="Name" required="required" />
					</div>
					<div class="form-group">
						<input name="dateOfBirth" path="dateOfBirth" type="date"
							class="form-control" placeholder="Date of Birth"
							required="required" />
					</div>
					<div class="form-group">
						<input name="pan" path="pan" type="text" class="form-control"
							placeholder="PAN Number" required="required" />
					</div>
					<div class="form-group">
						<input placeholder="Aadhar" name="aadharNumber"
							path="aadharNumber" type="text" class="form-control"
							required="required" />
						<small class="form-text text-muted">Your Aadhar Number
							will not ever be shared</small>
					</div>
	
					<h6 class="test-dark">Pension Type:</h6>
					<div class="form-check form-check-inline">
						<form:label class="form-check-label" path="pensionType" for="self">Self
<form:radiobutton path="pensionType" name="type" id="self"
								class="form-check-input" value="self" />
						</form:label>
					</div>
					<div class="form-check form-check-inline">
						<form:label class="form-check-label" path="pensionType"
							for="family">Family
<form:radiobutton path="pensionType" name="type" id="family"
								class="form-check-input" value="family" />
						</form:label>
					</div>
					<br>
					<br>
					<div class="form-group float-left">
						<input id="submitBtn" name="submit" class="btn btn-dark"
							style="cursor: pointer;" type="submit" value="Fetch Details"
							formmethod="post" />
					</div>
				</form:form>
			</div>

			<div class="col float-right box-design" style="margin-left: 80px;">
				<div class="form-group">
					<label>Pensioner Name:</label>
					<p class="form-control is-valid">${resultMap.get("name")}</p>
				</div>
				<div class="form-group">
					<label>Pensioner PAN:</label>
					<p class="form-control is-valid">${resultMap.get("pan")}</p>
				</div>
				<div class="form-group">
					<label>Pension Type:</label>
					<p class="form-control is-valid">
						${resultMap.get("selfOrFamilyPension")}</p>
				</div>
				<div class="form-group">
					<label>Pensioner DOB:</label>
					<p class="form-control is-valid">${resultMap.get("dateOfBirth")}</p>
				</div>
				<div class="form-group">
					<label>Pension Amount:</label>
					<p class="form-control">${resultMap.get("pensionAmount")}</p>
				</div>
				<div class="form-group float-right">
					<form method="get" action="pensiondisburse">
					<input id="verifyBtn" name="verify" class="btn btn-dark"
						style="cursor: pointer;" type="submit" value="Disburse Pension"
						formmethod="post" />
					</form>	
				</div>
			</div>
		</div>
	</div>
</section>
	<div class="navbar bg-light footer transparent ">&copy; Copyright 2022 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<i class="fab fa-facebook"> </i>
<i class="fab fa-twitter"> </i>
<i class="fab fa-google"> </i>
<i class="fab fa-instagram"> </i>
<i class="fab fa-youtube"> </i>


</div>
	
</body>
</html>
