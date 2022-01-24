package sample_codes.java;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import org.json.JSONObject;

/**
 * hakaru.ai inspect API sample code class
 * 
 * POST /v1/resources/images
 * POST /v1/resources/images/meter_type/{meter_type}
 */
public class InspectApi {

    private static String endPoint = "https://public-api.hakaru.ai";
    private static String meterType = "YOUR_METER_TYPE";
    private static String accessToken = "ACCESS_TOKEN";
    private static String XHakaruRequestId = "REQUEST_ID_IF_YOU_WANNA_SET_IT";
    private static String imageFilePath = "./YOUR_IMAGE_FILE.jpg";
    private static String qrCode = "QR_CODE_OF_METER";

    public static void main(String[] args) {
        try {            
            postV1ResourceImagesMeterTypeMeterType();
            Thread.sleep(500);
            postV1ResourceImages();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * POST /v1/resources/images
     * 
     * @throws IOException
     * @throws InterruptedException
     */
    public static void postV1ResourceImagesMeterTypeMeterType() throws IOException, InterruptedException {
        String path = "/v1/resources/images/meter_type/" + meterType;
        String url = endPoint + path;
        
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("measured_at", String.valueOf(System.currentTimeMillis()));
        jsonObj.put("image", getBase64FromImage());

        String body = jsonObj.toString();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .POST(BodyPublishers.ofString(body))
            .setHeader("Content-Type", "application/json; charset=UTF-8")
            .setHeader("Authorization", "Bearer " + accessToken)
            .setHeader("X-Hakaru-Request-Id", XHakaruRequestId)
            .build();

        HttpResponse<String> response = client.send(request,
            HttpResponse.BodyHandlers.ofString());

        int statusCode = response.statusCode();
        String resBody = response.body();
        HttpHeaders resHeaders = response.headers();

        System.out.println("HTTP Status Code: " + statusCode);

        if (statusCode == 200) {
            System.out.println("x-hakaru-request-id: " + resHeaders.firstValue("x-hakaru-request-id"));
            System.out.println("x-hakaru-request-upper-limit-monthly: " + resHeaders.firstValue("x-hakaru-request-upper-limit-monthly"));
            System.out.println("x-hakaru-request-upper-limit-daily: " + resHeaders.firstValue("x-hakaru-request-upper-limit-daily"));
            System.out.println("x-hakaru-request-total-monthly: " + resHeaders.firstValue("x-hakaru-request-total-monthly"));
            System.out.println("x-hakaru-request-total-daily: " + resHeaders.firstValue("x-hakaru-request-total-daily"));

            JSONObject resJson = new JSONObject(resBody);
            JSONObject result = resJson.getJSONObject("result");
            JSONObject resource = resJson.getJSONObject("api_res").getJSONObject("resource");

            System.out.println("error_code: " + result.getString("error_code"));
            System.out.println("msg: " + result.getString("msg"));
            System.out.println("resource.id: " + resource.getString("id"));
            System.out.println("resource.value: " + resource.getString("value"));
            System.out.println("resource.measured_code: " + resource.getString("measured_code"));
            System.out.println("resource.measured_at: " + resource.getString("measured_at"));
        } else {
            System.out.println("Response Body: " + resBody);
        }
    }

    /**
     * POST /v1/resources/images/meter_type/{meter_type}
     * 
     * @throws IOException
     * @throws InterruptedException
     */
    public static void postV1ResourceImages() throws IOException, InterruptedException {
        String path = "/v1/resources/images";
        String url = endPoint + path;
        
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("qr", qrCode);
        jsonObj.put("measured_at", String.valueOf(System.currentTimeMillis()));
        jsonObj.put("image", getBase64FromImage());

        String body = jsonObj.toString();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .POST(BodyPublishers.ofString(body))
            .setHeader("Content-Type", "application/json; charset=UTF-8")
            .setHeader("Authorization", "Bearer " + accessToken)
            .setHeader("X-Hakaru-Request-Id", XHakaruRequestId)
            .build();

        HttpResponse<String> response = client.send(request,
            HttpResponse.BodyHandlers.ofString());

        int statusCode = response.statusCode();
        String resBody = response.body();
        HttpHeaders resHeaders = response.headers();

        System.out.println("HTTP Status Code: " + statusCode);

        if (statusCode == 200) {
            System.out.println("x-hakaru-request-id: " + resHeaders.firstValue("x-hakaru-request-id"));
            System.out.println("x-hakaru-request-upper-limit-monthly: " + resHeaders.firstValue("x-hakaru-request-upper-limit-monthly"));
            System.out.println("x-hakaru-request-upper-limit-daily: " + resHeaders.firstValue("x-hakaru-request-upper-limit-daily"));
            System.out.println("x-hakaru-request-total-monthly: " + resHeaders.firstValue("x-hakaru-request-total-monthly"));
            System.out.println("x-hakaru-request-total-daily: " + resHeaders.firstValue("x-hakaru-request-total-daily"));

            JSONObject resJson = new JSONObject(resBody);
            JSONObject result = resJson.getJSONObject("result");
            JSONObject resource = resJson.getJSONObject("api_res").getJSONObject("resource");

            System.out.println("error_code: " + result.getString("error_code"));
            System.out.println("msg: " + result.getString("msg"));
            System.out.println("resource.id: " + resource.getString("id"));
            System.out.println("resource.value: " + resource.getString("value"));
            System.out.println("resource.measured_code: " + resource.getString("measured_code"));
            System.out.println("resource.measured_at: " + resource.getString("measured_at"));
        } else {
            System.out.println("Response Body: " + resBody);
        }
    }

    private static String getBase64FromImage() throws IOException {
        Path path = Paths.get(imageFilePath);
        File imageFile = new File(path.toAbsolutePath().toString());
        byte[] data = Files.readAllBytes(imageFile.toPath());
        return Base64.getEncoder().encodeToString(data);
    }
}