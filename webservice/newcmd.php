<?php
$db_name="ecom";
$db_pass="";
$db_user="root";
$db_host="localhost";
$bdd = new PDO("mysql:host=$db_host;dbname=$db_name;charset=utf8",$db_user, $db_pass);
// $adrliv=$_POST['adrliv'];
$idPrd=$_POST['idProduit'];
$qte=$_POST['qte'];
// $idc=$_POST['idc'];
	$squ="SELECT * FROM commande order by idCommande desc LIMIT 1";
	$req1=$bdd->prepare($squ);
	$req1->execute();
	$result = $req1->fetchAll(PDO::FETCH_ASSOC); 
	$commandeId=$result[0]['idCommande'];
	$Qr=$bdd->prepare("insert into lignecommande(idCommande, idProduit, Qte_cmd) values(:idc,:idp,:qte)");
	$Qr->bindValue(":idc",$commandeId);
	$Qr->bindValue(":idp",$idPrd);
	$Qr->bindValue(":qte",$qte);
	$Qr->execute();
	?>