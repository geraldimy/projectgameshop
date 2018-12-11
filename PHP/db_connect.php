<?php

/**
 *
 */
class DB_Connect

{
    private $conn;
  public function connect()
  {
    require_once 'config.php';
    $this-> conn = new mysqli(HOST,USER,PASS,DB);
    return $this->conn;
  }

}




