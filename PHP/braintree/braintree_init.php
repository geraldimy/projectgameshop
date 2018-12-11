<?php

session_start();
require_once("lib/autoload.php");

if(file_exists(__DIR__ . "/../.env"))
{
    $dotenv = new Dotenv\Dotenv(__DIR__ . "/../");
    $dotenv->load();
}

Braintree_Configuration::environment('sandbox');
Braintree_Configuration::merchantId('r3jb9x2f3dd4kj57');
Braintree_Configuration::publicKey('w9m52fxw85j2tp44');
Braintree_Configuration::privateKey('4b14b93bcd663e4e047217d4048d6e4c');



?>