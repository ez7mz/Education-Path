<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="shortcut icon" href="images/logo-sm.png">
	<title>Add member</title>
	<!-- google Recaptcha script -->
	<script src="https://www.google.com/recaptcha/api.js" async defer></script>
	<script type="text/javascript">
      var onloadCallback = function() {
        grecaptcha.render('html_element', {
          'sitekey' : 'your_site_key'
        });
      };
    </script>

  <!-- include the header page -->
	<?php
		include('inc/header.php');
	?>

	<!-- page main content -->
	<div id="main-content" class="container">
		<div id="forme" class="row">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<fieldset>
					<legend>Fill in informations bellow</legend>
					<form method="post" action="enregistrer_compte.php" id="myForm" enctype="multipart/form-data">
						<label>Nom</label><br>
						<input type="text" name="nom" class="form-control" placeholder="Nom" required><br>
						<label>Login</label><br>
						<input type="text" name="user" class="form-control" placeholder="Login" required><br>
						<label>Password</label><br>
						<input type="Password" name="pwd" class="form-control" id="inputPassword" placeholder="Password" required minlength="8"><br>
						<label>Confirm Password</label><br>
						<input type="Password" name="Cpwd" class="form-control" id="CinputPassword" placeholder="Confirm Password" required minlength="8">
						
						<!-- Display password error -->
						<?php 

							$url = "http://".$_SERVER['HTTP_HOST'].$_SERVER['REQUEST_URI'];
							if(strpos($url, "PwdError")){
								echo "<p class='error'> * Password Doasn't Match </p>";
							}

						?>

						<label>Photo</label><br>
						<input type="file" name="img" class="form-control" required>

						<!-- Display image upload errors -->
						<?php 

							$url = "http://".$_SERVER['HTTP_HOST'].$_SERVER['REQUEST_URI'];
							if(strpos($url, "imgSizeError")){
								echo "<p class='error'> * image size larg! must be 3Mo in max </p>";
							}

							if(strpos($url, "imgUpError")){
								echo "<p class='error'> * Image not uploaded Seccesfuly! </p>";
							}

							if(strpos($url, "imgExtError")){
								echo "<p class='error'> * Image should be PNG or JPG or JPEG! </p>";
							}

						?>

						<label>CV</label><br>
						<input type="file" name="cv" class="form-control" required>

						<!-- Display cv upload errors -->
						<?php 

							$url = "http://".$_SERVER['HTTP_HOST'].$_SERVER['REQUEST_URI'];
							if(strpos($url, "CvSizeError")){
								echo "<p class='error'> * Cv size larg! must be 10Mo in max </p>";
							}

							if(strpos($url, "CvUpError")){
								echo "<p class='error'> * Cv File not uploaded Seccesfuly! </p>";
							}

							if(strpos($url, "CvExtError")){
								echo "<p class='error'> * Cv Files should be DOC or PDF or DOCX! </p>";
							}

						?>

						<label>Date de maissance</label><br>
						<input type="date" name="Dtn" class="form-control"><br>
						<label>Adresse</label><br>
						<input type="text" name="adrs" class="form-control" id="Adresse" placeholder="Your Adresse" required><br>
						<label>Telephone</label><br>
						<input type="text" name="tele" class="form-control" id="Telephone" placeholder="Phone number" required><br>
						<label>Email</label><br>
						<input type="email" name="email" class="form-control" id="Email" aria-describedby="emailHelp" placeholder="Enter email" required><br>
						<div class="g-recaptcha" data-sitekey="6Lc950AgAAAAANkasCHv3qYWrFOC926p28b7P2y1
"></div>
						<!-- Display Recaptcha errors -->
						<?php 

							$url = "http://".$_SERVER['HTTP_HOST'].$_SERVER['REQUEST_URI'];
							if(strpos($url, "RecaError")){
								echo "<p class='error'> * Check the Recaptcha Box first </p>";
							}

						?>

						<input type="submit" name="save" value="Save"><br>
					</form>
					<!-- Recaptcha script -->
					<script src="https://www.google.com/recaptcha/api.js?onload=onloadCallback&render=explicit" async defer>
    				</script>
				</fieldset>
			</div>
		</div>
	</div>

	<!-- Alert a message if everything geos perfectly -->
	<?php

		if(isset($_GET['add'])){
			echo "<script> alert('Student Added Seccesfuly!') </script>";
		}

	?>

	<!-- include the footer page -->
	<?php
		include('inc/footer.php');
	?>