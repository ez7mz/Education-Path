<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="shortcut icon" href="images/logo-sm.png">
	<title>Modify comptes</title>
	
	<!-- include the header page -->
	<?php
		include('inc/header.php');
	?>

	<!-- page main centent -->
	<div id="main-content" class="container">
		<div id="forme" class="row">
			<fieldset>
				<legend>Fill in informations bellow</legend>
				<form method="post" action="enregistrer_modifier_compte.php" id="myForm" enctype="multipart/form-data">
					<label>ID</label><br>
					<input type="text" name="Id" value="<?php echo $_GET['id']; ?>" readonly class="form-control"><br>
					<label>Nom</label><br>
					<input type="text" name="nom" class="form-control" id="Name" placeholder="Nom" required><br>
					<label>Adresse</label><br>
					<input type="text" name="adrs" class="form-control" id="Adresse" placeholder="Your Adresse" required><br>
					<label>Telephone</label><br>
					<input type="text" name="tele" class="form-control" id="Telephone" placeholder="Phone number" required><br>
					<label>Email</label><br>
					<input type="email" name="email" class="form-control" id="mail" placeholder="Exemple : user@email.com" required><br>
					<input type="submit" name="save" value="Save">
				</form>
			</fieldset>
		</div>
	</div>

	<!-- include the footer page -->
	<?php
		include('inc/footer.php');
	?>