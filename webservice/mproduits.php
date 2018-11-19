<?php
try{
	$db_name="ecom";
	$db_pass="";
	$db_user="root";
	$db_host="localhost";
	$bdd = new PDO("mysql:host=$db_host;dbname=$db_name;charset=utf8",$db_user, $db_pass);	
	$type=$_GET['type'];	
	$req=$bdd->prepare("select * from produit where type_produit='".$type."'");
	$req->execute();	
	$results=$req->fetchAll(PDO::FETCH_ASSOC);
	print(json_encode($results));
}catch(Exception $e){
	echo "erreur de connexion".$e->getmessage();
	}
?>