<?php
try{
	$db_name="dbpt";
	$db_pass="";
	$db_user="root";
	$db_host="localhost";
	$bdd = new PDO("mysql:host=$db_host;dbname=$db_name;charset=utf8",$db_user, $db_pass);		
	$firstn=$_POST['firstname'];
	$lastn=$_POST['lastname'];
	$req=$bdd->prepare('insert into user(firestname, lastname) values(:f, :l)');
	$req->bindValue(":f",$firstn);
	$req->bindValue(":l",$lastn);
	$req->execute();	
	echo 'HELLO WORLD DONE XD';
}catch(Exception $e){
	echo "erreur de connexion".$e->getmessage();
	}
?>