<?php
try{
	$db_name="ecom";
	$db_pass="";
	$db_user="root";
	$db_host="localhost";
	$bdd = new PDO("mysql:host=$db_host;dbname=$db_name;charset=utf8",$db_user, $db_pass);
	
	$mdp=md5($_GET['mdp']);
	$login=$_GET['login'];
	// echo " login $login et Mot de pass: $mdp";
	$req=$bdd->prepare('select * from utilisateur where login= "'.$login.'" and motdepass= "'.$mdp.'"');
	$req->execute();	
	$results=$req->fetchAll(PDO::FETCH_ASSOC);
	print(json_encode($results));
}catch(Exception $e){
	echo "erreur de connexion".$e->getmessage();
	}
?>