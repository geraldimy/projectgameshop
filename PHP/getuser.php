<?php

require_once 'db_functions.php';
$db = new DB_Functions();


$response = array();
if (isset($_POST['phone']))
{
    $phone = $_POST['phone'];
        $user = $db->getUserInformation($phone);
        if ($user) {
            $response["phone"] = $user["Phone"];
            $response["name"] = $user["Name"];
            $response["birthdate"] = $user["Birthdate"];
            $response["address"] = $user["Address"];
            $response["avatarUrl"] = $user["avatarUrl"];


            echo json_encode($response);
        } else {
            $response["error_msg"] = "User Does Not Exists ";
            echo json_encode($response);
        }
    }

else {
  $response["error_msg"] = "Required parameter (phone) is missing";
  echo json_encode($response);
}

