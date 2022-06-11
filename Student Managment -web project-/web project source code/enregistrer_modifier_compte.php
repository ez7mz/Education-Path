<?php
	
//	@ Extract Data from form inputs using POST super global variable
	$id = $_POST['Id'];
	$nom = $_POST['nom'];
	$adrs = $_POST['adrs'];
	$tele = $_POST['tele'];
	$email = $_POST['email'];

//	@ Data Base informations
	$servername = "localhost";
	$username = "ez7mz";
	$password = "ez7mz";
	$dbname = "webproject";

	try {
		// Create connection to Data Base
		$conn = new PDO("mysql:host=$servername;dbname=$dbname", $username, $password);
		// set the PDO error mode to exception
		$conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
		// sql to delete a record
		$sql = "UPDATE etudiant SET name='".$nom."', email='".$email."', adresse='".$adrs."', tel='".$tele."' WHERE Id=".$id;
		// Prepare statement
		$stmt = $conn->prepare($sql);
		// execute the query
		$stmt->execute();
		// echo a message to say the UPDATE succeeded
		echo $stmt->rowCount() . " records UPDATED successfully";
		echo "Record deleted successfully";
	} catch(PDOException $e) {
	  echo $sql . "<br>" . $e->getMessage();
	}

//	@ Disconnect to Data Base
	$conn = null;

	header("location:comptes.php?mod=Modified");

?>