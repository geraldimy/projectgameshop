<?php
require_once 'db_functions.php';
$db = new DB_Functions();


$category = $db->getCategory();
echo json_encode($category);
