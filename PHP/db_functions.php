<?php
error_reporting(E_ALL);
ini_set('display_errors', 1);

  /**
   *
   */
  class DB_Functions
  {
      private $conn;


      function __construct()
      {
          require_once 'db_connect.php';
          $db = new DB_Connect();
          $this->conn = $db->connect();
      }

      function __destruct()
      {

      }

      function checkExistsUser($phone)
      {
          $stmt = $this->conn->prepare('SELECT * FROM user WHERE Phone=?');
          $stmt->bind_param('s', $phone);
          $stmt->execute();
          $stmt->store_result();
          if ($stmt->num_rows > 0) {
              $stmt->close();
              return true;
          } else {
              $stmt->close();
              return false;
          }
      }

      public function registerNewUser($phone, $name, $birthdate, $address)
      {
          $stmt = $this->conn->prepare('INSERT INTO user (Phone,Name,Birthdate,Address) VALUES(?,?,?,?) ');
          $stmt->bind_param("ssss", $phone, $name, $birthdate, $address);

          $result = $stmt->execute();
          $stmt->close();

          if ($result) {
              $stmt = $this->conn->prepare("SELECT Phone,Name,Birthdate,Address FROM user WHERE Phone=?");
              $stmt->bind_param("s", $phone);

              $stmt->execute();
              $user = $stmt->get_result()->fetch_assoc();
              $stmt->close();
              return $user;
          } else {
              return false;
          }
      }


      public function getUserInformation($phone)
      {
          $stmt = $this->conn->prepare("SELECT * FROM user WHERE Phone=?");
          $stmt->bind_param("s", $phone);

          if ($stmt->execute()) {
              $user = $stmt->get_result()->fetch_assoc();
              $stmt->close();
              return $user;
          } else {
              return NULL;
          }
      }

      public function getBanner()
      {
          $result = $this->conn->query("SELECT * FROM banner ORDER BY ID LIMIT 4   ");

          $banners = array();

          while ($item = $result->fetch_assoc())
              $banners[] = $item;
          return $banners;
      }

      public function getCategory()
      {
          $result = $this->conn->query("SELECT * FROM category");

          $category = array();

          while ($item = $result->fetch_assoc())
              $category[] = $item;
          return $category;
      }

      public function getGameByCategoryID($categoryId)
      {
          $query = "SELECT * FROM game WHERE CategoryId='" . $categoryId . "'";
          $result = $this->conn->query($query);

          $games = array();

          while ($item = $result->fetch_assoc())
              $games[] = $item;
          return $games;
      }

      public function updateAvatar($phone, $fileName)
      {
          return $result = $this->conn->query("UPDATE user SET avatarUrl='$fileName' WHERE Phone='$phone '");
      }

      public function getAllGames()
      {
          $result = $this->conn->query("SELECT *  FROM game WHERE 1") or die($this->conn->error);
          $games = array();
          while ($item = $result->fetch_assoc())
              $games[] = $item;
          return $games;
      }

      public function insertNewOrder($orderPrice, $orderComment, $orderAddress, $orderDetail, $userPhone, $paymentMethod)
      {
          $stmt = $this->conn->prepare("INSERT INTO `order`(`OrderStatus`, `OrderPrice`, `OrderDetail`, `OrderComment`, `OrderAddress`, `UserPhone`, `PaymentMethod`) VALUES (0,?,?,?,?,?,?)")
          or die($this->conn->error);
          $stmt->bind_param("ssssss", $orderPrice, $orderDetail, $orderComment, $orderAddress, $userPhone, $paymentMethod);
          $result = $stmt->execute();
          $stmt->close();

          if ($result)
              return true;
          else
              return false;


      }

      public function getOrderByStatus($userPhone, $status)
      {
          $query = "SELECT * FROM `order` WHERE `OrderStatus` = '" . $status . "' AND `UserPhone` = '" . $userPhone . "' ORDER BY `OrderId` DESC";
          $result = $this->conn->query($query) or die($this->conn->error);

          $orders = array();
          while ($order = $result->fetch_assoc())
              $orders[] = $order;
          return $orders;
      }

      public function cancelOrder($orderId, $userPhone)
      {
          $stmt = $this->conn->prepare("UPDATE `order` SET `OrderStatus`=-1 WHERE `OrderStatus`=0 AND `OrderId`=? AND `UserPhone`=?") or die($this->conn->error);
          $stmt->bind_param("ss", $orderId, $userPhone);
          $result = $stmt->execute() or die($stmt->error);
          return $result;
      }

      public function getNearbyStore($lat, $lng)
      {
          $result = $this->conn->query("SELECT id,name,lat,lng, ROUND(111.045 * DEGREES (ACOS(COS(RADIANS($lat))"
              . "* COS(RADIANS(lat))"
              . "* COS(RADIANS(lng) - RADIANS($lng))"
              . "+ SIN(RADIANS($lat))"
              . "* SIN(RADIANS(lat)))),2)"
              . "  AS distance_in_km From Store"
              . "  ORDER BY distance_in_km ASC") or die($this->conn->error);
          $stores = array();
          while ($store = $result->fetch_assoc())
              $stores[] = $store;
          return $stores;
      }

  }


