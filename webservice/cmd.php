<?php
$db_name="ecom";
$db_pass="";
$db_user="root";
$db_host="localhost";
$bdd = new PDO("mysql:host=$db_host;dbname=$db_name;charset=utf8",$db_user, $db_pass);
$adrliv=$_POST['adrliv'];
$idc=$_POST['idc'];
$query=$bdd->prepare("insert into commande(adresse_livraison, date_commande, idClient, Etat) values(:adr, CURRENT_DATE, :idC, 0) ");
	$query->bindValue(":adr",$adrliv);	
	$query->bindValue(":idC",$idc);	
	$query->execute();
?>