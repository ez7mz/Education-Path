<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="shortcut icon" href="images/logo-sm.png">
	<title>Home</title>
	
	<!-- include the header page -->
	<?php
		include('inc/header.php');
	?>

	<div id="main-content" class="container">

		<div class="jumbotron">
			<p class="text-center">Welcome</p>
		</div>

		<div id="home-titles" class="row">
			<div class="col-md-6 col-sm-6 col-xs-12">
				<a href="ajouter_compte.php">
					<div id="add-tile">
						<span>Add Member</span>
					</div>
				</a>
			</div>
			<div class="col-md-6 col-sm-6 col-xs-12">
				<a href="about.php">
					<div id="about-tile">
						<span>About</span>
					</div>
				</a>
			</div>
		</div>


	<!-- include the footer page -->
	<?php
		include('inc/footer.php');
	?>