package main

import (
	"bytes"
	"encoding/base64"
	"encoding/json"
	"fmt"
	"io/ioutil"
	"net/http"
	"os"
	"time"
)

var endPoint = "https://public-api.hakaru.ai"
var meterType = "YOUR_METER_TYPE"
var path = "/v1/resources/images/meter_type/" + meterType
var url = endPoint + path

var accessToken = "ACCESS_TOKEN"
var XHakaruRequestId = "REQUEST_ID_IF_YOU_WANNA_SET_IT"
var imageFilePath = "./YOUR_IMAGE_FILE.jpg"

type RequestBody struct {
	Image      string `json:"image"`
	MeasuredAt int64  `json:"measured_at"`
}

type ResponseBodyOK struct {
	Result Result `json:"result"`
	ApiRes ApiRes `json:"api_res"`
}

type Result struct {
	ErrorCode string `json:"error_code"`
	Msg       string `json:"msg"`
}

type ApiRes struct {
	Resource Resource `json:"resource"`
}

type Resource struct {
	Id           string `json:"id"`
	Value        string `json:"value"`
	MeasuredCode string `json:"measured_code"`
	MeasuredAt   string `json:"measured_at"`
}

func main() {
	unixMsec := time.Now().UnixNano() / (int64(time.Millisecond) / int64(time.Nanosecond))

	requestBody := new(RequestBody)
	requestBody.Image = readImageFile(imageFilePath)
	requestBody.MeasuredAt = unixMsec
	requestBodyJson, _ := json.Marshal(requestBody)

	request, error := http.NewRequest("POST", url, bytes.NewBuffer(requestBodyJson))
	request.Header.Set("Content-Type", "application/json; charset=UTF-8")
	request.Header.Set("Authorization", "Bearer "+accessToken)
	request.Header.Set("X-Hakaru-Request-Id", XHakaruRequestId)

	client := &http.Client{}
	response, error := client.Do(request)
	if error != nil {
		panic(error)
	}
	defer response.Body.Close()

	body, _ := ioutil.ReadAll(response.Body)

	fmt.Println("HTTP Status Code:", response.StatusCode)
	fmt.Println("Response Body:", string(body))

	if response.StatusCode == 200 {
		printHeaderParam("x-hakaru-request-id", response)
		printHeaderParam("x-hakaru-request-upper-limit-monthly", response)
		printHeaderParam("x-hakaru-request-upper-limit-daily", response)
		printHeaderParam("x-hakaru-request-total-monthly", response)
		printHeaderParam("x-hakaru-request-total-daily", response)

		data := ResponseBodyOK{}
		if err := json.Unmarshal([]byte(body), &data); err == nil {
			fmt.Println("error_code:", data.Result.ErrorCode)
			fmt.Println("msg:", data.Result.Msg)
			fmt.Println("resource.id:", data.ApiRes.Resource.Id)
			fmt.Println("resource.value:", data.ApiRes.Resource.Value)
			fmt.Println("resource.measured_code:", data.ApiRes.Resource.MeasuredCode)
			fmt.Println("resource.measured_at:", data.ApiRes.Resource.MeasuredAt)
		}
	}
}

func readImageFile(filePath string) string {
	file, error := os.Open(filePath)
	if error != nil {
		panic(error)
	}

	defer file.Close()

	image, _ := ioutil.ReadAll(file)
	return base64.StdEncoding.EncodeToString(image)
}

func printHeaderParam(param string, r *http.Response) {
	fmt.Println(param+":", r.Header.Get(param))
}
