<?php
$endPoint = "https://public-api.hakaru.ai";
$path = "/v2/oauth2/verify_token";
$url = $endPoint . $path;

$accessToken = "ACCESS_TOKEN";

$ch = curl_init($url);
curl_setopt($ch, CURLOPT_HTTPHEADER, array('Authorization: Bearer ' . $accessToken));
curl_setopt($ch, CURLOPT_RETURNTRANSFER, TRUE);

$strResult = curl_exec($ch);
$httpcode = curl_getinfo($ch, CURLINFO_RESPONSE_CODE);

curl_close($ch);

$resultObj = json_decode($strResult);
$result = json_encode($resultObj, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);

print_r("HTTP Status Code: " . $httpcode . "\n");
print_r("Response Body: " . $result . "\n");

?>