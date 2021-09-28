<?php
$endPoint = "https://public-api.hakaru.ai";
$path = "/v1/resources/images";
$url = $endPoint . $path;

$accessToken = "ACCESS_TOKEN";
$XHakaruRequestId = "REQUEST_ID_IF_YOU_WANNA_SET_IT";
$qrCode = "QR_CODE_OF_METER"; // e.g. hkr12345Abcd
$imageFilePath = "./YOUR_IMAGE_FILE.jpg";

$imageBase64 = base64_encode(file_get_contents($imageFilePath));
$measuredAt = ceil(microtime(TRUE) * 1000);

$requestBody = json_encode(array(
        "qr" => $qrCode,
        "image" => $imageBase64,
        "measured_at" => $measuredAt
    ));

$ch = curl_init($url);
curl_setopt($ch, CURLOPT_HTTPHEADER, array(
    "Content-type: application/json",
    "Authorization: Bearer " . $accessToken,
    "X-Hakaru-Request-Id: " . $XHakaruRequestId 
));
curl_setopt($ch, CURLOPT_POST, TRUE);
curl_setopt($ch, CURLOPT_POSTFIELDS, $requestBody);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, TRUE);
curl_setopt($ch, CURLOPT_HEADER, TRUE);

$strResult = curl_exec($ch);
$httpcode = curl_getinfo($ch, CURLINFO_RESPONSE_CODE);
$headerSize = curl_getinfo($ch, CURLINFO_HEADER_SIZE);
curl_close($ch);

$responseHeader = explode("\n", substr($strResult, 0, $headerSize));
$responseBody = substr($strResult, $headerSize);

$resultObj = json_decode($responseBody, TRUE);
$result = json_encode($resultObj, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);

print_r("HTTP Status Code: " . $httpcode . "\n");

if ($httpcode == 200) {
    print_r("x-hakaru-request-id: " . getHeaderValue($responseHeader, "x-hakaru-request-id"). "\n");
    print_r("x-hakaru-request-upper-limit-monthly: " . getHeaderValue($responseHeader, "x-hakaru-request-upper-limit-monthly"). "\n");
    print_r("x-hakaru-request-upper-limit-daily: " . getHeaderValue($responseHeader, "x-hakaru-request-upper-limit-daily"). "\n");
    print_r("x-hakaru-request-total-monthly: " . getHeaderValue($responseHeader, "x-hakaru-request-total-monthly"). "\n");
    print_r("x-hakaru-request-total-daily: " . getHeaderValue($responseHeader, "x-hakaru-request-total-daily"). "\n");
}

print_r("Response Body: " . $result . "\n");

/**
 * Get the value of the specified parameter from the response header.
 */
function getHeaderValue($headerArray, $paramName) {
    foreach($headerArray as $item) {
        if (preg_match("/" . $paramName . "/i", $item)) {
            $hr = explode(":", $item);
            if (count($hr) === 2) {
                return trim($hr[1]);
            }
        }
    }
    return "";
}
?>