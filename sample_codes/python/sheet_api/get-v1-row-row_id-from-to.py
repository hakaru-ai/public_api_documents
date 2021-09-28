import requests, json

end_point = 'https://public-api.hakaru.ai'
path = '/v1/row/'

meter_id = 'YOUR_METER_ID' # e.g. 5e4e6ab6cad6a182cc3649a4
from_day = 'YYYYMMDD' # e.g. 20210701
to_day = 'YYYYMMDD' # e.g. 20210730

url = end_point + path + meter_id + '/' + from_day + '/' + to_day

access_token = 'ACCESS_TOKEN'
x_hakaru_request_id = 'REQUEST_ID_IF_YOU_WANNA_SET_IT'

headers = {
    'Content-Type': 'application/json; charset=UTF-8',
    'Authorization': 'Bearer ' + access_token,
    'X-Hakaru-Request-Id': x_hakaru_request_id
}

def print_header_param(res_headers, param_name):
    print(param_name + ':', res_headers[param_name])

try:
    response = requests.get(url, headers=headers)
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
