import requests, json, base64, time

end_point = 'https://public-api.hakaru.ai'
path = '/v1/sheets'
url = end_point + path

access_token = 'ACCESS_TOKEN'
x_hakaru_request_id = 'REQUEST_ID_IF_YOU_WANNA_SET_IT'

sheet_name = 'YOUR_SHEET_NAME'
limit = 10
offset = 1
order = 'desc'

headers = {
    'Content-Type': 'application/json; charset=UTF-8',
    'Authorization': 'Bearer ' + access_token,
    'X-Hakaru-Request-Id': x_hakaru_request_id
}
payload = {
    'name': sheet_name,
    'limit': limit,
    'offset': offset,
    'order': order
}

def print_header_param(res_headers, param_name):
    print(param_name + ':', res_headers[param_name])

try:
    response = requests.get(url, params=payload, headers=headers)
    response.raise_for_status()
    res_headers = response.headers

    print('HTTP Status Code:', response.status_code)
    print_header_param(res_headers, 'x-hakaru-request-id')
    print_header_param(res_headers, 'x-hakaru-request-upper-limit-monthly')
    print_header_param(res_headers, 'x-hakaru-request-upper-limit-daily')
    print_header_param(res_headers, 'x-hakaru-request-total-monthly')
    print_header_param(res_headers, 'x-hakaru-request-total-daily')
except requests.exceptions.RequestException as err:
    print(err)
finally:
    print('Response Body:', json.dumps(response.json(), indent=2, ensure_ascii=False))
