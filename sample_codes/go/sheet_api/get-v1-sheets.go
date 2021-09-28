package main

import (
	"encoding/json"
	"fmt"
	"io/ioutil"
	"net/http"
	"strconv"
)

var endPoint = "https://public-api.hakaru.ai"
var path = "/v1/sheets"
var url = endPoint + path

var accessToken = "ACCESS_TOKEN"
var XHakaruRequestId = "REQUEST_ID_IF_YOU_WANNA_SET_IT"

var sheetName = "YOUR_SHEET_NAME"
var limit = 10
var offset = 1
var order = "desc"

type ResponseBodyOK struct {
	Result Result `json:"result"`
	ApiRes ApiRes `json:"api_res"`
}

type Result struct {
	ErrorCode string `json:"error_code"`
	Msg       string `json:"msg"`
}

type ApiRes struct {
	Count  int32   `json:"count"`
	ASheet []Sheet `json:"a_sheet"`
}

type Sheet struct {
	Id        string `json:"id"`
	Name      string `json:"name"`
	Type      string `json:"type"`
	CreatedAt string `json:"created_at"`
	Count     int32  `json:"count"`
	ARow      []Row  `json:"a_row"`
}

type Row struct {
	Id        string `json:"id"`
	No        string `json:"no"`
	Name      string `json:"name"`
	QR        string `json:"qr"`
	Type      string `json:"type"`
	CreatedAt string `json:"created_at"`
}

func main() {
	request, error := http.NewRequest("GET", url, nil)
	request.Header.Set("Authorization", "Bearer "+accessToken)
	request.Header.Set("X-Hakaru-Request-Id", XHakaruRequestId)

	params := request.URL.Query()
	params.Add("name", sheetName)
	params.Add("limit", strconv.Itoa(limit))
	params.Add("offset", strconv.Itoa(offset))
	params.Add("order", order)
	request.URL.RawQuery = params.Encode()

	client := &http.Client{}
	response, error := client.Do(request)
	if error != nil {
		panic(error)
	}
	defer response.Body.Close()

	body, _ := ioutil.ReadAll(response.Body)

	fmt.Println("HTTP Status Code:", response.StatusCode)

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
			fmt.Println("count:", data.ApiRes.Count)
			printSheets(data.ApiRes.ASheet)
		} else {
			fmt.Println(err)
		}
	} else {
		fmt.Println("Response Body:", string(body))
	}
}

func printHeaderParam(param string, r *http.Response) {
	fmt.Println(param+":", r.Header.Get(param))
}

func printSheets(sheets []Sheet) {
	for i, v := range sheets {
		fmt.Println("a_sheet["+strconv.Itoa(i)+"] id:", v.Id)
		fmt.Println("a_sheet["+strconv.Itoa(i)+"] name:", v.Name)
		fmt.Println("a_sheet["+strconv.Itoa(i)+"] type:", v.Type)
		fmt.Println("a_sheet["+strconv.Itoa(i)+"] created_at:", v.CreatedAt)
		fmt.Println("a_sheet["+strconv.Itoa(i)+"] count:", v.Count)
		printRows(v.ARow)
	}
}

func printRows(rows []Row) {
	for i, v := range rows {
		fmt.Println("\ta_row["+strconv.Itoa(i)+"] id:", v.Id)
		fmt.Println("\ta_row["+strconv.Itoa(i)+"] no:", v.No)
		fmt.Println("\ta_row["+strconv.Itoa(i)+"] qr:", v.QR)
		fmt.Println("\ta_row["+strconv.Itoa(i)+"] type:", v.Type)
		fmt.Println("\ta_row["+strconv.Itoa(i)+"] created_at:", v.CreatedAt)
	}
}
