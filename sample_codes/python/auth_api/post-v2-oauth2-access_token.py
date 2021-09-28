import requests, json

end_point = 'https://public-api.hakaru.ai'
path = '/v2/oauth2/access_token'
url = end_point + path

username = 'YOUR_ACCOUNT_ID'
password = 'YOUR_PASSWORD'

body = {
    'username': username,
    'password': password
}
headers = { 'Content-Type': 'application/json' }

try:
    response = requests.post(url, data=json.dumps(body), headers=headers)
    response.raise_for_status()

    data = response.json()
    print('HTTP Status Code:', response.status_code)
    print('Access Token:', data['access_token'])
    print('Refresh Token:', data['refresh_token'])
except requests.exceptions.RequestException as err:
    print(err)
    print('Response Body:', json.dumps(response.json(), indent=2))
