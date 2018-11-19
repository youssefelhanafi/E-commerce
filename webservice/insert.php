<?php
try{
	$db_name="ecom";
	$db_pass="";
	$db_user="root";
	$db_host="localhost";
	$bdd = new PDO("mysql:host=$db_host;dbname=$db_name;charset=utf8",$db_user, $db_pass);
	// $val=intval($_GET['idc']);
	//$val=$_POST['idc'];
	//$val=$_GET['idc'];
	
	$nomc=$_POST['nom'];
	$prec=$_POST['prenom'];	
	$emailc=$_POST['email'];
	$mdp=md5($_POST['mdp']);
	$login=$_POST['login'];
	$adr=$_POST['adr'];
	$tel=$_POST['tel'];
	$type=$_POST['type'];	
	$req=$bdd->prepare('insert into utilisateur(nom, prenom, email, motdepass, login, adresse, telephone) values(:nm, :pr, :em, :mp, :lg, :ad, :tl )');
	$req->bindValue(":nm",$nomc);
	$req->bindValue(":pr",$prec);
	$req->bindValue(":em",$emailc);	
	$req->bindValue(":mp",$mdp);
	$req->bindValue(":lg",$login);
	$req->bindValue(":ad",$adr);
	$req->bindValue(":tl",$tel);	
	$req->execute();
	
	
	$req2=$bdd->prepare('SELECT * FROM `utilisateur` ORDER by idUser desc limit 1');
	$req2->execute();	
	$results=$req2->fetchAll(PDO::FETCH_ASSOC);
	echo '<br> IDUSER: '.$results[0]['idUser'].'<br>';
	echo '<br> case: '.$type.'<br>';
	switch($type){
		case 1:		
			// 	idClient	idUser	date_naissance	
		echo 'CAS 1'.'<br>';
		$dateNaissance=date("Y-m-d",strtotime($_POST['daten']));
		$req2=$bdd->prepare('insert into client(idUser, date_naissance) values(:idu, :dt)');
		$req2->bindValue(":idu",$results[0]['idUser']);
		echo 'DATE '.$dateNaissance;
		$req2->bindValue(":dt",$dateNaissance);
		$req2->execute();	
		break;
		case 2:
		echo 'CAS 2';
		//	idLivreur	idUser
		$req2=$bdd->prepare('insert into livreur(idUser) values(:idu)');
		$req2->bindValue(":idu",$results[0]['idUser']);	
		$req2->execute();
		break;
		case 3:
		echo 'CAS 3';
		$req2=$bdd->prepare('insert into administrateur(idUser) values(:idu)');
		$req2->bindValue(":idu",$results[0]['idUser']);	
		$req2->execute();
		break;	
	}
	// echo "WORKED";	
}catch(Exception $e){
	echo "erreur de connexion".$e->getmessage();
	}
?>