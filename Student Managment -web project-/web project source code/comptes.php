<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="shortcut icon" href="images/logo-sm.png">
	<title>Comptes</title>
	
	<!-- include the header page -->
	<?php
		include('inc/header.php');
	?>


	<!-- page main content -->
	<div id="main-content" class="container">

		<div class="row">
			<div id="title" class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
				Liste des comptes etudiants
			</div>
		</div>

		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<?php
				//	@ connexion to data base
						//> DataBase information
					$dns = "mysql:host=localhost;dbname=webproject";
				 	$user = "ez7mz";
				 	$password = "ez7mz";

				 		//> Connect to DataBase
				 	$my_con = new PDO($dns, $user, $password);
				 		//> Creation of SQL query and execute it
					$sql = "SELECT * FROM etudiant"; 
					$res = $my_con->query($sql);
				?>

				<!-- Create an HTML table -->
				<table border=1>
					<tr style="padding: 5px;">
						<!-- Table heading -->
						<th>ID</th>
						<th>Name</th>
						<th>Login</th>
						<th>email</th>
						<th>photos</th>
						<th>cv</th>
						<th>adresse</th>
						<th>tele</th>
						<th>Action</th>
					</tr>

				<!-- extract data from the result of the query -->
				<!-- and put it in a HTML table -->
				<?php while($ligne = $res->fetch(PDO::FETCH_NUM ))
				{
				 ?>
					<tr>
						<td> <?php echo $ligne[0]; ?> </td>
						<td> <?php echo $ligne[1]; ?> </td>
						<td> <?php echo $ligne[2]; ?> </td>
						<td> <?php echo "<a href='mailto:".$ligne[3]."' class='link'>".$ligne[3]."</a>"; ?> </td>
						<td> <?php echo "<img src=".$ligne[4]." width='50px' class='link'>"; ?> </td>
						<td> <?php echo "<a href=".$ligne[5]." download class='link'>download-CV</a>"; ?> </td>
						<td> <?php echo $ligne[6]; ?> </td>
						<td> <?php echo "<a href='tel:".$ligne[7]."' class='link'>".$ligne[7]."</a>"; ?> </td>
						<td> <a href="supprimer_compte.php?id=<?php echo $ligne[0] ?>" class='link'> Supprimer </a> - <a href="modifier_compte.php?id=<?php echo $ligne[0] ?>" class='link'> Modifier </a></td>
					</tr>
				<?php } ?>
				</table>
				<a href="ajouter_compte.php" class="link"> + Ajouter un nouvel utilisateur</a>
			</div>
		</div>

		<!-- alert an message if everything geos perfectly -->
		<?php

			if(isset($_GET['del'])){
				echo "<script> alert('Student Deleted Seccesfuly!') </script>";
			}

			if(isset($_GET['mod'])){
				echo "<script> alert('Student Modified Seccesfuly!') </script>";	
			}

		?>

		<!-- include the footer page -->
		<?php
			include('inc/footer.php');
		?>