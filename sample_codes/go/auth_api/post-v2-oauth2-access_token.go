package main

import (
	"bytes"
	"encoding/json"
	"fmt"
	"io/ioutil"
	"net/http"
)

var endPoint = "https://public-api.hakaru.ai"
var path = "/v2/oauth2/access_token"
var url = endPoint + path

var username = "YOUR_ACCOUNT_ID"
var password = "YOUR_PASSWORD"

type RequestBody struct {
	Username string `json:"username"`
	Password string `json:"password"`
}

type ResponseBodyOK struct {
	AccessToken  string `json:"access_token"`
	RefreshToken string `json:"refresh_token"`
	TokenType    string `json:"token_type"`
	ExpiresIn    int32  `json:"expires_in"`
	Scope        string `json:"scope"`
}

func main() {
	requestBody := new(RequestBody)
	requestBody.Username = username
	requestBody.Password = password
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
			fmt.Println("Refresh Token:", data.RefreshToken)
		} else {
			fmt.Println(err)
		}
	} else {
		fmt.Println("Response Body:", string(body))
	}
}
