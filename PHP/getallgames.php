<?php
require_once 'db_functions.php';
$db = new DB_Functions();

$games = $db->getAllGames();
if($games)
    echo json_encode($games);
else
    echo json_encode("Error !");
?>
