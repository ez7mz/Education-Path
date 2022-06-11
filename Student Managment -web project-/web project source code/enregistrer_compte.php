<?php
//	@ MySQL DataBase informations :
	$dns = "mysql:host=localhost;dbname=webproject";
 	$user = "ez7mz";
 	$password = "ez7mz";

//	@ initialize Errors meassges
 	$PWdErr = ""; $RecaErr = ""; $ImgUpErr = "";
 	$ImgSizeErr = ""; $ImgExtErr = ""; $CvUpErr = "";
 	$CvSizeErr = ""; $CvExtErr = "";



//	@ extract forme inputs using POST super global variable:
	$name = $_POST["nom"];
	$login = $_POST["user"];
	$pwd= $_POST["pwd"];
	$Cpwd = $_POST["Cpwd"];
	$email = $_POST['email'];
	$adress = $_POST['adrs'];
	$tele = $_POST['tele'];

	// intialize a flag variable to boolean
	$Flag = true;

	if((int)strcmp($pwd, $Cpwd) != 0){
		$Flag = false;
		$PWdErr = "PwdError";
	}else if(isset($_POST['g-recaptcha-response'])){
		$recaptcha = $_POST['g-recaptcha-response'];
		if (!$recaptcha) {
			$Flag = false;
			$RecaErr = "RecaError";
		}
	}

//	------------------ @ File information : ---------------------
//	@ extract image file informations :
	$imgName = $_FILES['img']['name'];
	$imgTmpName = $_FILES['img']['tmp_name'];
	$imgSize = $_FILES['img']['size'];
	$imgError = $_FILES['img']['error'];
	$imgType = $_FILES['img']['type'];

	$imgFlag = true;

	//Extract image extention
	$imgExt = strtolower(explode(".", $imgName)[1]);
	$imgAllowedExt = array("png", "jpg", "jpeg");

	if (in_array($imgExt, $imgAllowedExt)) { // verify if the extension is valid
		if ($imgError == 0) { // verify if the image uploaded without any error
			if($imgSize > 1024*3){ // verify image size
				$imgNewName = $login."-img.".$imgExt;
				$imgDestination = "UPphotos/".$imgNewName;
				if($Flag==true){ // if all is good then upleaded the file into our repository
					move_uploaded_file($imgTmpName, $imgDestination);
				}
			}else{ // image size error
				$imgFlag = false;
				$ImgSizeErr = "imgSizeError";
			}
		}else{ // image not upladed error
			$imgFlag = false;
			$ImgUpErr = "imgUpError";
		}
	}else{ // image extention error
		$imgFlag = false;
		$ImgExtErr = "imgExtError";
	}


//	@ extract cv file informations :
	$cvName = $_FILES['cv']['name'];
	$cvTmpName = $_FILES['cv']['tmp_name'];
	$cvSize = $_FILES['cv']['size'];
	$cvError = $_FILES['cv']['error'];
	$cvType = $_FILES['cv']['type'];

	$cvFlag = true;

	// extract cv extention
	$cvExt = strtolower(explode(".", $cvName)[1]);
	$cvAllowedExt = array("doc", "pdf", "docx");

	if (in_array($cvExt, $cvAllowedExt)) { // verify if the extension is valid
		if ($cvError == 0) {	// verify if the image uploaded without any error
			if($cvSize > 1024*10){ // verify image size
				$cvNewName = $login."-cv.".$cvExt;
				$cvDestination = "UPcv/".$cvNewName;
				echo $cvDestination;
				if($Flag==true && $imgFlag==true){ // if all is good then upleaded the file into our repository
					move_uploaded_file($cvTmpName, $cvDestination);
				}
			}else{  // image size error
				$cvFlag = false;
				$CvSizeErr = "CvSizeError";
			}
		}else{ // image not upladed error
			$cvFlag = false;
			$CvUpErr = "CvUpError";
		}
	}else{ // image extention error
		$cvFlag = false;
		$CvExtErr = "CvExtError";
	}

//	@ last Step : if everything is fine -- Add to DataBase -- --------------------
	if($Flag==true && $imgFlag==true && $cvFlag==true){
	//	@Add to data Base :
		try{
			// Create DB connection
			$my_con = new PDO($dns, $user, $password);
			// Create SQL query
			$sql = "INSERT INTO etudiant VALUES(NULL, '$name', '$login', '$email', '$imgDestination', '$cvDestination', '$adress', '$tele')";
			// Execute Query 
			$res = $my_con->exec($sql);
 		}catch(PDOException $excep){
			echo "Erreur : " . $excep->getMessage();
		}
		header("location:ajouter_Compte.php?add=Added");
	}else{
		header("location:ajouter_Compte.php?".$PWdErr."&".$RecaErr."&".$ImgUpErr."&".$ImgSizeErr."&".$ImgExtErr."&".$CvUpErr."&".$CvSizeErr."&".$CvExtErr);
	}

?>