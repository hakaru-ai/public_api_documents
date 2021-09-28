# Sample code of hakaru.ai public API in go language


## Middleware Versions

+ `go`: go1.16.x or higher version


## Getting Started

1. Go to the `sample_codes/go` directory.

    ```
    $ cd sample_codes/go
    ```

2. Open `auth_api/post-v2-oauth2-access_token.go` file and edit *YOUR_ACCOUNT_ID* and *YOUR_PASSWORD*.

    ```
    $ vi auth_api/post-v2-oauth2-access_token.go
    
    var username = "YOUR_ACCOUNT_ID" // Set your login ID here (e.g. HKR0123456789)
    var password = "YOUR_PASSWORD" // Set your password here
    ```

3. Let's run the following command to call [POST /v2/oauth2/access_token](https://developer.hakaru.ai/#operation/post-v2-oauth2-access_token) API.

    ```
    $ go run auth_api/post-v2-oauth2-access_token.go

    // Example of the API response
    HTTP Status Code: 200
    Access Token: eyJraWQiOiJ4YjV3VTcyT1draFFoRjN4Tl...
    Refresh Token: eyJjdHkiOiJKV1QiLCJlbmMiOiJBMjU2R...
    ```

4. Save an image of your inspection meter in the `sample_codes/images` directory.

5. Open `inspect_api/post-v1-resources-images-meter_type-meter_type.go` file and edit *YOUR_METER_TYPE*, *ACCESS_TOKEN* and *./YOUR_IMAGE_FILE.jpg*. See [here](https://github.com/AkimiNomiya/hakaru_ai_apidoc#メータータイプ) for meter types.

    ```
    $ cd go
    $ vi inspect_api/post-v1-resources-images-meter_type-meter_type.go
    
    var meterType = "YOUR_METER_TYPE" // Set your meter type (e.g. 'MET0005')
    var accessToken = "ACCESS_TOKEN" // Set access token here
    var imageFilePath = "./YOUR_IMAGE_FILE.jpg" // Set your image file path. (e.g. '../images/LCD_DIGITAL_METER.jpg')
    ```

6. Let's run the following command to call [POST /v1/resources/images](https://developer.hakaru.ai/#operation/post-v1-resources-images-meter_type-meter_type) API.

    ```
    $ go run inspect_api/post-v1-resources-images-meter_type-meter_type.go

    // Example of the API response
    HTTP Status Code: 200
    Response Body: {"result":{"error_code":"OK","msg":""},"api_res":{"resource":{"id":"611d05b0269150077b2cbadd","value":"187.1","measured_code":"OK","measured_at":"2021-08-18T13:05:50.409Z"}}}
    x-hakaru-request-id: REQUEST_ID_IF_YOU_WANNA_SET_IT
    x-hakaru-request-upper-limit-monthly: 300000
    x-hakaru-request-upper-limit-daily: 10000
    x-hakaru-request-total-monthly: 3
    x-hakaru-request-total-daily: 3
    error_code: OK
    msg: 
    resource.id: 611d05b0269150077b2cbadd
    resource.value: 187.1
    resource.measured_code: OK
    resource.measured_at: 2021-08-18T13:05:50.409Z
    ```
