<?php
try{
	$db_name="ecom";
	$db_pass="";
	$db_user="root";
	$db_host="localhost";
	$bdd = new PDO("mysql:host=$db_host;dbname=$db_name;charset=utf8",$db_user, $db_pass);		
	$idc=$_POST['idc'];
	$req=$bdd->prepare('UPDATE commande set Etat=1 WHERE idCommande=:idc');
	$req->bindValue(":idc",$idc);	
	$req->execute();
}catch(Exception $e){
	echo "erreur de connexion".$e->getmessage();
	}
?>