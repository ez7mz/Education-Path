<!--  Header page contient -->
	<!-- Neccecary linked resources -->
		<!-- stylesheetlink -->
	<link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
    	<!-- google font link -->
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Montserrat&display=swap" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css2?family=Macondo&display=swap" rel="stylesheet">
</head>
<body>

	<!-- Start heading section -->
	<header>
		<nav id="header-nav" class="navbar navbar-default">
			<div class="container">
				<div class="navbar-header"> <!-- Header logo -->
					<a href="index.php" class="pull-left visible-md visible-lg">
						<div id="logo-img" alt="Logo image"></div>
					</a>

					<div class="navbar-brand"> <!-- Header title -->
						<a href="index.php"><h1>Student Managment</h1></a>
						<p>
							<span class="glyphicon glyphicon-heart"></span>
							<span>Made with Love</span>
						</p>						
					</div>


					<!-- Navigation button for small screens -->
					<button type="button" id="navbar-toggle" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#collapsable-nav" aria-expanded="false"> 
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
				</div>

				<!-- navigation button -->
				<div id="collapsable-nav" class="collapse navbar-collapse">
					<ul id="nav-list" class="nav navbar-nav navbar-right">
						<li class="hidden-sm hidden-md hidden-lg">
							<a href="index.php">
								<span class="glyphicon glyphicon-home"></span><br class="hidden-xs">
								Home
							</a>
						</li>
						<li>
							<a href="trombinoscope.php">
								<span class="glyphicon glyphicon-th"></span><br class="hidden-xs">View Trombo</a>
						</li>
						<li>
							<a href="comptes.php">
								<span class="glyphicon glyphicon-th-list"></span><br class="hidden-xs">Shows</a>
						</li>
						<li>
							<a href="About.php">
								<span class="glyphicon glyphicon-info-sign"></span><br class="hidden-xs">About</a>
						</li>
					 </ul>
				</div> 
			</div>
		</nav> <!-- End navigation section -->
	</header> <!-- End header -->