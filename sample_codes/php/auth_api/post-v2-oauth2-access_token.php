<?php
$endPoint = "https://public-api.hakaru.ai";
$path = "/v2/oauth2/access_token";
$url = $endPoint . $path;

$username = "YOUR_ACCOUNT_ID";
$password = "YOUR_PASSWORD";

$requestBody = json_encode(array(
        "username" => $username,
        "password" => $password
    ));

$ch = curl_init($url);
curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-type: application/json'));
curl_setopt($ch, CURLOPT_POST, TRUE);
curl_setopt($ch, CURLOPT_POSTFIELDS, $requestBody);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, TRUE);

$strResult = curl_exec($ch);
$httpcode = curl_getinfo($ch, CURLINFO_RESPONSE_CODE);

curl_close($ch);

$resultObj = json_decode($strResult, TRUE);
$result = json_encode($resultObj, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);

print_r("HTTP Status Code: " . $httpcode . "\n");

if ($httpcode == 200) {
    print_r("Access Token: " . $resultObj["access_token"] . "\n");
    print_r("Refresh Token: " . $resultObj["refresh_token"] . "\n");
} else {
    print_r("Response Body: " . $result . "\n");
}

?>