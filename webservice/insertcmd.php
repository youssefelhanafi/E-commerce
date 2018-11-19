<?php
	$db_name="ecom";
	$db_pass="";
	$db_user="root";
	$db_host="localhost";
	$bdd = new PDO("mysql:host=$db_host;dbname=$db_name;charset=utf8",$db_user, $db_pass);
function insertLigneCmd($cmd,$idPrdt,$qt){
	// echo "insertLigneCmd cmd: ".$cmd." idPrdt: ".$idPrdt." QTE: ".$qt; 
	$db_name="ecom";
	$db_pass="";
	$db_user="root";
	$db_host="localhost";
	$bdd = new PDO("mysql:host=$db_host;dbname=$db_name;charset=utf8",$db_user, $db_pass);
	$Qr=$bdd->prepare("insert into lignecommande(idCommande, idProduit, Qte_cmd) values(:idc,:idp,:qte)");
	$Qr->bindValue(":idc",$cmd);
	$Qr->bindValue(":idp",$idPrdt);
	$Qr->bindValue(":qte",$qt);
	$Qr->execute();
}

try{
	$idProduit=$_POST['idProduit'];
	$Qte=$_POST['qte'];	
	 $idC=$_POST['idc'];
	
	//SELECT * FROM `commande` WHERE date(date_commande)=CURDATE();
	$testQuery="SELECT * FROM commande WHERE date(date_commande)=CURDATE()";
	$req1=$bdd->prepare($testQuery);
	$req1->execute();
	if($req1->rowCount() > 0){
	$result = $req1->fetchAll(PDO::FETCH_ASSOC); 
	$commandeId=$result[0]['idCommande'];
	insertLigneCmd($commandeId,$idProduit,$Qte);	
	}else{
	$req=$bdd->prepare("insert into commande(idProduit, Qte_cmd, adresse_livraison, nom_livraison, prenom_livraison, tel_livraison, date_commande,idClient) values(:id, :qt, 'adr', 'livn', 'livp', '0655442233', NOW(),:idx)");
	$req->bindValue(":id",$idProduit);
	$req->bindValue(":qt",$Qte);
	$req->bindValue(":idx",$idC);
	$req->execute();
	insertLigneCmd($req->fetchColumn(),$idProduit,$Qte);	
	}
	
	
	/*
	insert into commande(idProduit, Qte_cmd, adresse_livraison, nom_livraison, prenom_livraison, tel_livraison, date_commande,idLivreur,idClient) values(1, 1, "adr", "livn", "livp", "0655005544", NOW(), 1,2);
	*/	
	
	
	}catch(Exception $e){
	echo "erreur de connexion".$e->getmessage();
	}
?>