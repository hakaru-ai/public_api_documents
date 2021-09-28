package main

import (
	"encoding/json"
	"fmt"
	"io/ioutil"
	"net/http"
)

var endPoint = "https://public-api.hakaru.ai"
var path = "/v2/oauth2/verify_token"
var url = endPoint + path

var accessToken = "ACCESS_TOKEN"

type ResponseBodyOK struct {
	OK      bool     `json:"ok"`
	LoginId string   `json:"login_id"`
	Expire  string   `json:"expire"`
	AuthRes struct{} `json:"auth_res"`
}

func main() {
	request, error := http.NewRequest("GET", url, nil)
	request.Header.Set("Authorization", "Bearer "+accessToken)

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
			fmt.Println("ok:", data.OK)
			fmt.Println("login_id:", data.LoginId)
			fmt.Println("expire:", data.Expire)
		} else {
			fmt.Println(err)
		}
	} else {
		fmt.Println("Response Body:", string(body))
	}
}
