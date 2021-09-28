# Sample code of hakaru.ai public API in PHP


## Middleware Versions

+ `PHP`: 7.3 or higher version


## Getting Started

1. Go to the `sample_codes/php` directory.

    ```
    $ cd sample_codes/php
    ```

2. Open `auth_api/post-v2-oauth2-access_token.php` file and edit *YOUR_ACCOUNT_ID* and *YOUR_PASSWORD*.

    ```
    $ vi auth_api/post-v2-oauth2-access_token.php
    
    $username = "YOUR_ACCOUNT_ID"; // Set your login ID here (e.g. HKR0123456789)
    $password = "YOUR_PASSWORD"; // Set your password here
    ```

3. Let's run the following command to call [POST /v2/oauth2/access_token](https://developer.hakaru.ai/#operation/post-v2-oauth2-access_token) API.

    ```
    $ php auth_api/post-v2-oauth2-access_token.php

    // Example of the API response
    HTTP Status Code: 200
    Access Token: eyJraWQiOiJ4YjV3VTcyT1draFFoRjN4Tl...
    Refresh Token: eyJjdHkiOiJKV1QiLCJlbmMiOiJBMjU2R...
    ```

4. Save an image of your inspection meter in the `sample_codes/images` directory.

5. Open `inspect_api/post-v1-resources-images-meter_type-meter_type.php` file and edit *YOUR_METER_TYPE*, *ACCESS_TOKEN* and *./YOUR_IMAGE_FILE.jpg*. See [here](https://github.com/AkimiNomiya/hakaru_ai_apidoc#メータータイプ) for meter types.

    ```
    $ cd php
    $ vi inspect_api/post-v1-resources-images-meter_type-meter_type.php
    
    $meterType = "YOUR_METER_TYPE"; // Set your meter type (e.g. 'MET0005')
    $accessToken = "ACCESS_TOKEN"; // Set access token here
    $imageFilePath = "./YOUR_IMAGE_FILE.jpg"; // Set your image file path. (e.g. '../images/LCD_DIGITAL_METER.jpg')
    ```

6. Let's run the following command to call [POST /v1/resources/images](https://developer.hakaru.ai/#operation/post-v1-resources-images-meter_type-meter_type) API.

    ```
    $ php inspect_api/post-v1-resources-images-meter_type-meter_type.php

    // Example of the API response
    HTTP Status Code: 200
    x-hakaru-request-id: REQUEST_ID_IF_YOU_WANNA_SET_IT
    x-hakaru-request-upper-limit-monthly: 300000
    x-hakaru-request-upper-limit-daily: 10000
    x-hakaru-request-total-monthly: 8
    x-hakaru-request-total-daily: 5
    Response Body: {
        "result": {
            "error_code": "OK",
            "msg": ""
        },
        "api_res": {
            "resource": {
                "id": "611de252ccfb8d08a4323b5b",
                "value": "187.1",
                "measured_code": "OK",
                "measured_at": "2021-08-19T04:47:12.858Z"
            }
        }
    }
    ```
