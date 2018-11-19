<?php
try{
	$db_name="dbpt";
	$db_pass="";
	$db_user="root";
	$db_host="localhost";
	$bdd = new PDO("mysql:host=$db_host;dbname=$db_name;charset=utf8",$db_user, $db_pass);		
	$firstn=$_POST['firstname'];
	$lastn=$_POST['lastname'];
	$req=$bdd->prepare('DELETE FROM user WHERE firestname=:f and lastname=:l');
	$req->bindValue(":f",$firstn);
	$req->bindValue(":l",$lastn);
	$req->execute();	
	echo 'DELETE DONE XD';
}catch(Exception $e){
	echo "erreur de connexion".$e->getmessage();
	}
?>