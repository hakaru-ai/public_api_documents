package sample_codes.java;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;

import org.json.JSONObject;

/**
 * hakaru.ai auth api sample code class
 * 
 * POST /v2/oauth2/access_token
 * POST /v2/oauth2/refresh_token
 * GET /v2/oauth2/verify_token 
 */
public class AuthApi {

    private static String endPoint = "https://public-api.hakaru.ai";
    private static String username = "YOUR_ACCOUNT_ID";
    private static String password = "YOUR_PASSWORD";

    private static String accessToken = "";
    private static String refreshToken = "";

    public static void main(String[] args) {
        try {            
            postV2Oauth2AccessToken();
            postV2Oauth2RefreshToken();
            getV2Oauth2VerifyToken();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * POST /v2/oauth2/access_token
     * 
     * @throws IOException
     * @throws InterruptedException
     */
    public static void postV2Oauth2AccessToken() throws IOException, InterruptedException {
        String path = "/v2/oauth2/access_token";
        String url = endPoint + path;
        
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("username", username);
        jsonObj.put("password", password);

        String body = jsonObj.toString();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .POST(BodyPublishers.ofString(body))
            .setHeader("Content-Type", "application/json; charset=UTF-8")
            .build();

        HttpResponse<String> response = client.send(request,
            HttpResponse.BodyHandlers.ofString());

        int statusCode = response.statusCode();
        String resBody = response.body();

        System.out.println("HTTP Status Code: " + statusCode);

        if (statusCode == 200) {
            JSONObject resJson = new JSONObject(resBody);
            accessToken = resJson.getString("access_token");
            refreshToken = resJson.getString("refresh_token");
            System.out.println("Access Token: " + accessToken);
            System.out.println("Refresh Token: " + refreshToken);
        } else {
            System.out.println("Response Body: " + resBody);
        }
    }

    /**
     * POST /v2/oauth2/refresh_token
     * 
     * @throws IOException
     * @throws InterruptedException
     */
    public static void postV2Oauth2RefreshToken() throws IOException, InterruptedException {
        String path = "/v2/oauth2/refresh_token";
        String url = endPoint + path;
        
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("refresh_token", refreshToken);

        String body = jsonObj.toString();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .POST(BodyPublishers.ofString(body))
            .setHeader("Content-Type", "application/json; charset=UTF-8")
            .build();

        HttpResponse<String> response = client.send(request,
            HttpResponse.BodyHandlers.ofString());

        int statusCode = response.statusCode();
        String resBody = response.body();

        System.out.println("HTTP Status Code: " + statusCode);

        if (statusCode == 200) {
            JSONObject resJson = new JSONObject(resBody);
            accessToken = resJson.getString("access_token");
            System.out.println("Access Token: " + accessToken);
        } else {
            System.out.println("Response Body: " + resBody);
        }
    }

    /**
     * GET /v2/oauth2/verify_token
     * 
     * @throws IOException
     * @throws InterruptedException
     */
    public static void getV2Oauth2VerifyToken() throws IOException, InterruptedException {
        String path = "/v2/oauth2/verify_token";
        String url = endPoint + path;
        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .setHeader("Authorization", "Bearer " + accessToken)
            .build();

        HttpResponse<String> response = client.send(request,
            HttpResponse.BodyHandlers.ofString());

        int statusCode = response.statusCode();
        String resBody = response.body();

        System.out.println("HTTP Status Code: " + statusCode);

        if (statusCode == 200) {
            JSONObject resJson = new JSONObject(resBody);
            System.out.println("ok: " + resJson.getBoolean("ok"));
            System.out.println("login_id: " + resJson.getString("login_id"));
            System.out.println("expire: " + resJson.getString("expire"));
        } else {
            System.out.println("Response Body: " + resBody);
        }
    }
}