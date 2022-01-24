import requests, json, base64, time

end_point = 'https://public-api.hakaru.ai'
meter_type = 'YOUR_METER_TYPE'
path = '/v1/resources/images/meter_type/' + meter_type
url = end_point + path

access_token = 'ACCESS_TOKEN'
x_hakaru_request_id = 'REQUEST_ID_IF_YOU_WANNA_SET_IT'
image_file_path = './YOUR_IMAGE_FILE.jpg'

def read_image_file(file_path):
    with open(file_path, 'rb') as image_file:
        b64_data = base64.b64encode(image_file.read())
    return b64_data.decode('utf-8')

image = read_image_file(image_file_path)
unix_msec = int(time.time() * 1000)

body = {
    'image': image,
    'measured_at': str(unix_msec)
}
headers = {
    'Content-Type': 'application/json; charset=UTF-8',
    'Authorization': 'Bearer ' + access_token,
    'X-Hakaru-Request-Id': x_hakaru_request_id
}

def print_header_param(res_headers, param_name):
    print(param_name + ':', res_headers[param_name])

try:
    response = requests.post(url, data=json.dumps(body), headers=headers)
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
    print('Response Body:', json.dumps(response.json(), indent=2))
