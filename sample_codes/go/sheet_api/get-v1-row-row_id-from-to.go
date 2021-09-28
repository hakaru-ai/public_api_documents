package main

import (
	"encoding/json"
	"fmt"
	"io/ioutil"
	"net/http"
	"strconv"
)

var endPoint = "https://public-api.hakaru.ai"
var path = "/v1/row/"

var meterId = "YOUR_METER_ID" // e.g. 5e4e6ab6cad6a182cc3649a4
var from = "YYYYMMDD"         // e.g. 20210701
var to = "YYYYMMDD"           // e.g. 20210730
var url = endPoint + path + meterId + "/" + from + "/" + to

var accessToken = "ACCESS_TOKEN"
var XHakaruRequestId = "REQUEST_ID_IF_YOU_WANNA_SET_IT"

type ResponseBodyOK struct {
	Result Result `json:"result"`
	ApiRes ApiRes `json:"api_res"`
}

type Result struct {
	ErrorCode string `json:"error_code"`
	Msg       string `json:"msg"`
}

type ApiRes struct {
	Row Row `json:"row"`
}

type Row struct {
	Id               string       `json:"id"`
	No               string       `json:"no"`
	Name             string       `json:"name"`
	QR               string       `json:"qr"`
	Type             string       `json:"type"`
	TemplateName     string       `json:"template_name"`
	Unit             string       `json:"unit"`
	DecimalDigit     string       `json:"decimal_digit"`
	Multiplier       string       `json:"multiplier"`
	OutlierCheckType string       `json:"outlier_check_type"`
	ExpiryAt         string       `json:"expiry_at"`
	PersonInCharge   string       `json:"person_in_charge"`
	Note             string       `json:"note"`
	CreatedAt        string       `json:"created_at"`
	Count            int32        `json:"count"`
	AInspection      []Inspection `json:"a_inspection"`
}

type Inspection struct {
	ColumnName string `json:"column_name"`
	MeasuredAt string `json:"measured_at"`
	Value      string `json:"value"`
	Comment    string `json:"comment"`
}

func main() {
	request, error := http.NewRequest("GET", url, nil)
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
			fmt.Println("row.id:", data.ApiRes.Row.Id)
			fmt.Println("row.no:", data.ApiRes.Row.No)
			fmt.Println("row.name:", data.ApiRes.Row.Name)
			fmt.Println("row.qr:", data.ApiRes.Row.QR)
			fmt.Println("row.type:", data.ApiRes.Row.Type)
			fmt.Println("row.template_name:", data.ApiRes.Row.TemplateName)
			fmt.Println("row.unit:", data.ApiRes.Row.Unit)
			fmt.Println("row.decimal_digit:", data.ApiRes.Row.DecimalDigit)
			fmt.Println("row.multiplier:", data.ApiRes.Row.Multiplier)
			fmt.Println("row.outlier_check_type:", data.ApiRes.Row.OutlierCheckType)
			fmt.Println("row.expiry_at:", data.ApiRes.Row.ExpiryAt)
			fmt.Println("row.person_in_charge:", data.ApiRes.Row.PersonInCharge)
			fmt.Println("row.note:", data.ApiRes.Row.Note)
			fmt.Println("row.created_at:", data.ApiRes.Row.CreatedAt)
			fmt.Println("row.count:", data.ApiRes.Row.Count)
			printInspections(data.ApiRes.Row.AInspection)
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

func printInspections(inspections []Inspection) {
	for i, v := range inspections {
		fmt.Println("\ta_inspection["+strconv.Itoa(i)+"] column_name:", v.ColumnName)
		fmt.Println("\ta_inspection["+strconv.Itoa(i)+"] measured_at:", v.MeasuredAt)
		fmt.Println("\ta_inspection["+strconv.Itoa(i)+"] value:", v.Value)
		fmt.Println("\ta_inspection["+strconv.Itoa(i)+"] comment:", v.Comment)
	}
}
