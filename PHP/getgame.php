<?php
require_once 'db_functions.php';
$db = new DB_Functions();


$response = array();
if (isset($_POST['categoryid']))
{
    $categoryid = $_POST['categoryid'];
	
        $games = $db->getGameByCategoryID($categoryid);
       
            echo json_encode($games);
        
		

} else {
  $response["error_msg"] = "Required parameter (categoryid) is missing";
  echo json_encode($response);
}

