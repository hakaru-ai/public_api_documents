package main

import (
	"bytes"
	"encoding/json"
	"fmt"
	"io/ioutil"
	"net/http"
)

var endPoint = "https://public-api.hakaru.ai"
var path = "/v2/oauth2/refresh_token"
var url = endPoint + path

var refreshToken = "REFRESH_TOKEN"

type RequestBody struct {
	RefreshToken string `json:"refresh_token"`
}

type ResponseBodyOK struct {
	AccessToken string `json:"access_token"`
	TokenType   string `json:"token_type"`
	ExpiresIn   int32  `json:"expires_in"`
	Scope       string `json:"scope"`
}

func main() {
	requestBody := new(RequestBody)
	requestBody.RefreshToken = refreshToken
	requestBodyJson, _ := json.Marshal(requestBody)

	request, error := http.NewRequest("POST", url, bytes.NewBuffer(requestBodyJson))
	request.Header.Set("Content-Type", "application/json; charset=UTF-8")

	client := &http.Client{}
	response, error := client.Do(request)
	if error != nil {
		panic(error)
	}
	defer response.Body.Close()

	body, _ := ioutil.ReadAll(response.Body)

	fmt.Println("HTTP Status Code:", response.StatusCode)

	if response.StatusCode == 200 {
		data := ResponseBodyOK{}
		if err := json.Unmarshal([]byte(body), &data); err == nil {
			fmt.Println("Access Token:", data.AccessToken)
		} else {
			fmt.Println(err)
		}
	} else {
		fmt.Println("Response Body:", string(body))
	}
}
