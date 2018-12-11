<?php
require_once 'db_functions.php';
$db = new DB_Functions();

if(isset($_POST['orderId']) && isset($_POST['userPhone']))
{
    $orderId = $_POST['orderId'];
    $userPhone = $_POST['userPhone'];

    $result = false;
    $result = $db->cancelOrder($orderId,$userPhone);

    if($result)
        echo json_encode("Order Has Been Cancelled");
    else
        echo json_encode("Error While Write To Database");

}
else
{
    echo json_encode("Required Parameters(orderId,userPhone) Is Missing");
}
