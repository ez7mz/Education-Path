<!-- include the header page -->
<?php
	
	include('inc/header.php');

?>
	<title>About us</title>
	<!-- page stylesheet -->
	<style type="text/css">
		.category-tile {
			background-color: #222222;
			background-size: cover;
			background-position: center;
			opacity: 0.8;
			width: 200px;
			height: 200px;
		}
		h1 {
			margin-top: 20px;
			margin-bottom: 50px;
		}
		sup {
			text-transform: lowercase;
		}
	</style>

	<!-- page main content --> 
	<h1 class="text-center">About US</h1>
	<div id="main-content" class="container">
		<section class="row">
			<a href="https://ensak.usms.ac.ma/ensak/" target="_blank">
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<div class="category-tile" style="background-image: url('images/ensa-logo.png');">
						<span>ENSA Khouribga</span>
					</div>
				</div>
			</a>
			<a href="https://www.linkedin.com/in/hamza-mesrar-a27062210/" target="_blank">
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<div class="category-tile" style="background-image: url('images/ez7mz-logo.png');">
						<span>MESRAR Hamza</span>
					</div>
				</div>
			</a>
			<a href="https://www.linkedin.com/in/ilias-elmazouti-118587231/" target="_blank">
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<div class="category-tile" style="background-image: url('images/ily-logo.png');">
						<span>ElMazouti Ilias</span>
					</div>
				</div>
			</a>
			<a href="https://www.facebook.com/ENSAKIID" target="_blank">
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<div class="category-tile" style="background-image: url('images/iid-logo.png');">
						<span>1<sup>ére</sup> année IID</span>
					</div>
				</div>
			</a>
		</section>
	</div>

<!-- include the footer page -->
<?php

	include('inc/footer.php');

?>