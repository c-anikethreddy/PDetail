<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
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
		<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
<title>Pension Management</title>
<style>
.transparent
{  
     background-color: transparent ;   /*whichever you want*/
    opacity: 0.1;           /*0.5 determines transparency value*/ 
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
	style="background-image: url(https://pm-image.s3.amazonaws.com/woman-putting-money-glass-jar-table-closeup-166153072.jpg); background-size:cover; background-repeat: no-repeat; background-position: cover;">
	<header>
	<nav class="navbar bg-light transparent">
		<h3 class=" text-center display-5 text-uppercase font-bold"
			style="margin-top: 5px; font-family: sans-serif;color:#4d0000"><i class="fab fa-google-wallet"> </i>&nbsp; Pension
			Management Portal</h3></nav>
	</header>
	<section>
	<div class="container">
	<p class="lead text-center" style="color: RED" id="errorMessage">${errormessage}</p>
		<div class="float-right"
			style="margin-top: 100px; width: 400px; box-shadow: 5px 10px 8px 10px #888888; padding: 30px; border-radius: 5mm;">
<div class="text-center text-dark font-italic"
					style="margin-bottom: 25px;">
					<h2>Verify Details</h2>
				</div>
			<form action="pensiondisbursed" method="POST">
				<div class="form-group">
					<label class="text-dark" for="aadhar">Please Enter Aadhar
						Number</label> <input name="aadhar" id="aadhar"
						type="text" class="form-control" maxlength=12 placeholder="Aadhar Number"
						required="required" /> <small class="form-text text-muted">Your
						Aadhaar Number will be Confidential</small>
				</div>
				<div class="form-group float-right"
					style="margin-top: 25px; margin-bottom: 100px;">
					<input id="submitBtn" name="submit" class="btn btn-outline-dark"
						style="cursor: pointer;" type="submit"
						value="Verify and Next" formmethod="post" />
				</div>
				<div class="clearfix"></div>
			</form>
			<p style="color: RED" id="errorMessage">${msg}</p>
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
