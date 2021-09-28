import requests, json

end_point = 'https://public-api.hakaru.ai'
path = '/v2/oauth2/verify_token'
url = end_point + path

access_token = 'ACCESS_TOKEN'

headers = { 'Authorization': 'Bearer ' + access_token }

try:
    response = requests.get(url, headers=headers)
    response.raise_for_status()

    print('HTTP Status Code:', response.status_code)
except requests.exceptions.RequestException as err:
    print(err)
finally:
    print('Response Body:', json.dumps(response.json(), indent=2))
