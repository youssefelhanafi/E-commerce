<?php
try{
	$db_name="ecom";
	$db_pass="";
	$db_user="root";
	$db_host="localhost";
	$bdd = new PDO("mysql:host=$db_host;dbname=$db_name;charset=utf8",$db_user, $db_pass);
	$idc=$_GET['idc'];
	$req=$bdd->prepare("select * from livreur where idUser=".$idc);
	$req->execute();
	echo $req->rowCount();
	// echo $number_of_rows;
	// $r=array($number_of_rows);
	// $r['nb']=$number_of_rows;
	// print(json_encode($r));
	}catch(Exception $e){
	echo "erreur de connexion".$e->getmessage();
	}
?>