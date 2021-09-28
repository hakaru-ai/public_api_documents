# Sample code of hakaru.ai public API in Java


## Middleware Versions

+ `JDK`: 11.x


## Getting Started

1. Download the following external library and place it in the `sample_cods/java/lib` directory.

    - [json-yyyymmdd.jar](https://mvnrepository.com/artifact/org.json/json)

2. Open this repository in [Visual Studio Code](https://azure.microsoft.com/ja-jp/products/visual-studio-code/). 

3. Click `Ctrl + P` (If you use MacOS `Command + P`) and select *"Java: Configure Classpath"*.

4. Close *Classpath Configuration*, open `.vscode/setting.json` and edit it as follows.

    ```json
    {
        "java.project.sourcePaths": [
            ""
        ],
        "java.project.referencedLibraries": [
            "sample_codes/java/lib/*.jar"
        ]
    }
    ```

5. Open `sample_codes/java/AuthApi.java` file in VSCode, edit *YOUR_ACCOUNT_ID* and *YOUR_PASSWORD*.

    ```    
    private static String username = "YOUR_ACCOUNT_ID"; // Set your login ID here (e.g. HKR0123456789)
    private static String password = "YOUR_PASSWORD"; // Set your password here
    ```

6. Let's run the AuthApi class to call [POST /v2/oauth2/access_token](https://developer.hakaru.ai/#operation/post-v2-oauth2-access_token) API, [POST /v2/oauth2/refresh_token](https://developer.hakaru.ai/#operation/post-v2-oauth2-refresh_token) API and [GET /v2/oauth2/verify_token](https://developer.hakaru.ai/#operation/get-v2-oauth2-verify_token) API.

    ```
    // Example of the /v2/oauth2/access_token API response
    HTTP Status Code: 200
    Access Token: eyJraWQiOiJ4YjV3VTcyT1draFFoRjN4Tl...
    Refresh Token: eyJjdHkiOiJKV1QiLCJlbmMiOiJBMjU2R...

    // Example of the /v2/oauth2/refresh_token API response
    HTTP Status Code: 200
    Access Token: eyJjdHkiOiJKV1QiLCJlbmMiOiJBMjU2R0...

    // Example of the /v2/oauth2/verify_token API response
    HTTP Status Code: 200
    ok: true
    login_id: HKR0123456789
    expire: 2021-08-19T18:36:13.000+09:00
    ```

7. Save an image of your inspection meter in the `sample_codes/images` directory.

8. Open `sample_codes/java/InspectApi.java` file and edit *YOUR_METER_TYPE*, *ACCESS_TOKEN*, *./YOUR_IMAGE_FILE.jpg* and *QR_CODE_OF_METER*. See [here](https://github.com/AkimiNomiya/hakaru_ai_apidoc#メータータイプ) for meter types.

    ```
    private static String meterType = "YOUR_METER_TYPE"; // Set your meter type (e.g. 'MET0005')
    private static String accessToken = "ACCESS_TOKEN"; // Set access token here
    private static String imageFilePath = "./YOUR_IMAGE_FILE.jpg"; // Set your image file path. (e.g. 'sample_codes/images/LCD_DIGITAL_METER.jpg')
    private static String qrCode = "QR_CODE_OF_METER"; // Set your meter's QR code. (e.g. 'hkr12345Abcd')
    ```

9. Let's run the InspectApi class to call [POST /v1/resources/images](https://developer.hakaru.ai/#operation/post-v1-resources-images) API and [POST /v1/resources/images/meter_type/:meter_type](https://developer.hakaru.ai/#operation/post-v1-resources-images-meter_type-meter_type).

    ```
    // Example of the /v1/resources/images API response
    HTTP Status Code: 200
    x-hakaru-request-id: Optional[REQUEST_ID_IF_YOU_WANNA_SET_IT]
    x-hakaru-request-upper-limit-monthly: Optional[300000]
    x-hakaru-request-upper-limit-daily: Optional[10000]
    x-hakaru-request-total-monthly: Optional[10]
    x-hakaru-request-total-daily: Optional[7]
    error_code: OK
    msg: 
    resource.id: 611e2b6d269150077b2cbc82
    resource.value: 187.1
    resource.measured_code: OK
    resource.measured_at: 2021-08-19T09:59:07.438Z

    // Example of the /v1/resources/images API response
    HTTP Status Code: 200
    x-hakaru-request-id: Optional[REQUEST_ID_IF_YOU_WANNA_SET_IT]
    x-hakaru-request-upper-limit-monthly: Optional[300000]
    x-hakaru-request-upper-limit-daily: Optional[10000]
    x-hakaru-request-total-monthly: Optional[11]
    x-hakaru-request-total-daily: Optional[8]
    error_code: OK
    msg: 
    resource.id: 611e2b6f269150077b2cbc85
    resource.value: 187.1
    resource.measured_code: OK
    resource.measured_at: 2021-08-19T09:59:10.142Z
    ```
