<?php
$endPoint = "https://public-api.hakaru.ai";
$path = "/v2/oauth2/refresh_token";
$url = $endPoint . $path;

$refreshToken = "REFRESH_TOKEN";
$requestBody = json_encode(array(
        "refresh_token" => $refreshToken
    ));

$ch = curl_init($url);
curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-type: application/json'));
curl_setopt($ch, CURLOPT_POST, TRUE);
curl_setopt($ch, CURLOPT_POSTFIELDS, $requestBody);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, TRUE);

$strResult = curl_exec($ch);
$httpcode = curl_getinfo($ch, CURLINFO_RESPONSE_CODE);

curl_close($ch);

$result = json_decode($strResult, TRUE);

print_r("HTTP Status Code: " . $httpcode . "\n");

if ($httpcode == 200) {
    print_r("Access Token: " . $result["access_token"] . "\n");
} else {
    print_r("Response Body: " . $strResult . "\n");
}

?>