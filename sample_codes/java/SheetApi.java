package sample_codes.java;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * hakaru.ai sheet API sample code class
 * 
 * GET /v1/sheets/count?name={sheet_name}
 * GET /v1/sheets?name={sheet_name}
 * GET /v1/sheet/{sheet_id}
 * GET /v1/row/{meter_id}/{from}/{to}
 * 
 */
public class SheetApi {

    private static String endPoint = "https://public-api.hakaru.ai";
    private static String accessToken = "ACCESS_TOKEN";
    private static String XHakaruRequestId = "REQUEST_ID_IF_YOU_WANNA_SET_IT";
    private static String sheetId = "YOUR_SHEET_ID";
    private static String meterId = "YOUR_METER_ID";
    private static String fromDate = "YYYYMMDD";
    private static String toDate = "YYYYMMDD";

    public static void main(String[] args) {
        try {            
            getV1SheetsCount();
            Thread.sleep(500);
            getV1Sheets();
            Thread.sleep(500);
            getV1SheetSheetId();
            Thread.sleep(500);
            getV1RowRowIdFromTo();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * GET /v1/sheets/count?name={sheet_name}
     * 
     * @throws IOException
     * @throws InterruptedException
     */
    public static void getV1SheetsCount() throws IOException, InterruptedException {
        String path = "/v1/sheets/count";
        String url = endPoint + path;
        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
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
            JSONObject apiRes = resJson.getJSONObject("api_res");

            System.out.println("error_code: " + result.getString("error_code"));
            System.out.println("msg: " + result.getString("msg"));
            System.out.println("count: " + apiRes.getInt("count"));
        } else {
            System.out.println("Response Body: " + resBody);
        }
    }

    /**
     * GET /v1/sheets?name={sheet_name}
     * 
     * @throws IOException
     * @throws InterruptedException
     */
    public static void getV1Sheets() throws IOException, InterruptedException {
        String path = "/v1/sheets";
        String url = endPoint + path;
        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
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
            JSONObject apiRes = resJson.getJSONObject("api_res");
            JSONArray aSheet = apiRes.getJSONArray("a_sheet");

            System.out.println("error_code: " + result.getString("error_code"));
            System.out.println("msg: " + result.getString("msg"));
            System.out.println("count: " + apiRes.getInt("count"));

            printSheets(aSheet);
        } else {
            System.out.println("Response Body: " + resBody);
        }
    }

    /**
     * GET /v1/sheet/{sheet_id}
     * 
     * @throws IOException
     * @throws InterruptedException
     */
    public static void getV1SheetSheetId() throws IOException, InterruptedException {
        String path = "/v1/sheet/";
        String url = endPoint + path + sheetId;
        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
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
            JSONObject apiRes = resJson.getJSONObject("api_res");
            JSONObject sheet = apiRes.getJSONObject("sheet");
            JSONArray aRow = sheet.getJSONArray("a_row");

            System.out.println("error_code: " + result.getString("error_code"));
            System.out.println("msg: " + result.getString("msg"));
            System.out.println("sheet.id: " + sheet.getString("id"));
            System.out.println("sheet.name: " + sheet.getString("name"));
            System.out.println("sheet.type: " + sheet.getString("type"));
            System.out.println("sheet.created_at: " + sheet.getString("created_at"));
            System.out.println("sheet.count: " + sheet.getInt("count"));

            printRows(aRow);
        } else {
            System.out.println("Response Body: " + resBody);
        }
    }

    /**
     * GET /v1/row/{meter_id}/{from}/{to}
     * 
     * @throws IOException
     * @throws InterruptedException
     */
    public static void getV1RowRowIdFromTo() throws IOException, InterruptedException {
        String path = "/v1/row/";
        String url = endPoint + path + meterId + "/" + fromDate + "/" + toDate;
        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
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
            JSONObject apiRes = resJson.getJSONObject("api_res");
            JSONObject row = apiRes.getJSONObject("row");
            JSONArray aInspection = row.getJSONArray("a_inspection");

            System.out.println("error_code: " + result.getString("error_code"));
            System.out.println("msg: " + result.getString("msg"));
            System.out.println("row.id: " + row.getString("id"));
            System.out.println("row.no: " + row.getString("no"));
            System.out.println("row.name: " + row.getString("name"));
            System.out.println("row.qr: " + row.getString("qr"));
            System.out.println("row.type: " + row.getString("type"));
            System.out.println("row.template_name: " + row.getString("template_name"));
            System.out.println("row.unit: " + row.getString("unit"));
            System.out.println("row.decimal_digit: " + row.getString("decimal_digit"));
            System.out.println("row.multiplier: " + row.getInt("multiplier"));
            System.out.println("row.outlier_check_type: " + row.getString("outlier_check_type"));
            System.out.println("row.expiry_at: " + row.getString("expiry_at"));
            System.out.println("row.person_in_charge: " + row.getString("person_in_charge"));
            System.out.println("row.note: " + row.getString("note"));
            System.out.println("row.created_at: " + row.getString("created_at"));
            System.out.println("row.count: " + row.getInt("count"));

            printInspections(aInspection);
        } else {
            System.out.println("Response Body: " + resBody);
        }
    }

    private static void printSheets(JSONArray aSheet) {
        for (int i = 0; i < aSheet.length(); i++) {
            JSONObject sheet = aSheet.getJSONObject(i);
            JSONArray aRow = sheet.getJSONArray("a_row");

            System.out.println("a_sheet[" + i + "] id: " + sheet.getString("id"));
            System.out.println("a_sheet[" + i + "] name: " + sheet.getString("name"));
            System.out.println("a_sheet[" + i + "] type: " + sheet.getString("type"));
            System.out.println("a_sheet[" + i + "] created_at: " + sheet.getString("created_at"));
            System.out.println("a_sheet[" + i + "] count: " + sheet.getInt("count"));

            printRows(aRow);
        }
    }

    private static void printRows(JSONArray aRow) {
        for (int i = 0; i < aRow.length(); i++) {
            JSONObject sheet = aRow.getJSONObject(i);

            System.out.println("\ta_row[" + i + "] id: " + sheet.getString("id"));
            System.out.println("\ta_row[" + i + "] no: " + sheet.getString("no"));
            System.out.println("\ta_row[" + i + "] qr: " + sheet.getString("qr"));
            System.out.println("\ta_row[" + i + "] type: " + sheet.getString("type"));
            System.out.println("\ta_row[" + i + "] created_at: " + sheet.getString("created_at"));
        }
    }

    private static void printInspections(JSONArray aInspection) {
        for (int i = 0; i < aInspection.length(); i++) {
            JSONObject sheet = aInspection.getJSONObject(i);

            System.out.println("\ta_inspection[" + i + "] column_name: " + sheet.getString("column_name"));
            System.out.println("\ta_inspection[" + i + "] measured_at: " + sheet.getString("measured_at"));
            System.out.println("\ta_inspection[" + i + "] value: " + sheet.getString("value"));
            System.out.println("\ta_inspection[" + i + "] comment: " + sheet.getString("comment"));
        }
    }
}