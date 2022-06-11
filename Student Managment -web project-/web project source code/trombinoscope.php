<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="shortcut icon" href="images/logo-sm.png">
	<title>Trombinoscope</title>
	
	<!-- include the header page -->
	<?php
		include('inc/header.php');
	?>

	<div id="main-content" class="container">


		<section class="row">
			<?php
			//	@ Data Base informations
				$dns = "mysql:host=localhost;dbname=webproject";
			 	$user = "ez7mz";
			 	$password = "ez7mz";

			 	// Create connection to Data Base
			 	$my_con = new PDO($dns, $user, $password);
			 	// Create SQL Query
				$sql = "SELECT name, photos FROM etudiant";
				// Execute Query 
				$res = $my_con->query($sql);
			?>

			<!-- retrieve data from query result -->
			<?php while($ligne = $res->fetch(PDO::FETCH_NUM ))
				{
				 ?>
				 	<div class="col-md-3 col-sm-4 col-xs-6 col-xxs-12">
						<div class="category-tile" style="background-image: url(<?php echo $ligne[1]; ?>); background-size: cover; background-position: center;">
							<span><?php echo $ligne[0]; ?></span>
						</div>
					</div>
				<?php } ?>
		</section>
	</div>

	<!-- include the footer page -->
	<?php
		include('inc/footer.php');
	?>