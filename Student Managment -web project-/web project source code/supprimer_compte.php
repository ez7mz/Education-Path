<?php
	
// 	@ Get user request Id
	$id = $_GET['id'];

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
		$sql = "DELETE FROM etudiant WHERE Id=$id";

		// get img and cv path
		$sql1 = "SELECT photos, cv FROM etudiant WHERE Id=$id";
		// use exec() because no results are returned
		$res = $conn->query($sql1);
		while ($ligne = $res->fetch(PDO::FETCH_NUM)) {
			$imgPath = $ligne[0];
			$cvPath = $ligne[1];
		}

		// Deleted image and cv from record
		unlink($imgPath);
		unlink($cvPath);

		$conn->exec($sql);
		echo "Record deleted successfully";
		
	} catch(PDOException $e) {
	  echo $sql . "<br>" . $e->getMessage();
	}

	// Dosconect to Data Base
	$conn = null;

	header("location:comptes.php?del=Deleted");
?>