const axios = require('axios'); 

const endPoint = 'https://public-api.hakaru.ai';
const path = '/v1/row/';
const meterId = 'YOUR_METER_ID'; // e.g. 5e4e6ab6cad6a182cc3649a4
const from = 'YYYYMMDD'; // e.g. 20210701
const to = 'YYYYMMDD'; // e.g. 20210730
const url = endPoint + path + meterId + '/' + from + '/' + to;

const accessToken = 'ACCESS_TOKEN';
const XHakaruRequestId = 'REQUEST_ID_IF_YOU_WANNA_SET_IT';

(async () => { 
    try {
        const options = {
            headers: {
                Authorization: `Bearer ${accessToken}`,
                'X-Hakaru-Request-Id': XHakaruRequestId
            }
        };
        const response = await axios.get(url, options);
        const { headers, data } = response;

        console.log('HTTP Status Code:', response.status);
        console.log('x-hakaru-request-id:', headers['x-hakaru-request-id']);
        console.log('x-hakaru-request-upper-limit-monthly:', headers['x-hakaru-request-upper-limit-monthly']);
        console.log('x-hakaru-request-upper-limit-daily:', headers['x-hakaru-request-upper-limit-daily']);
        console.log('x-hakaru-request-total-monthly:', headers['x-hakaru-request-total-monthly']);
        console.log('x-hakaru-request-total-daily:', headers['x-hakaru-request-total-daily']);
        console.log('Response Body:',　JSON.stringify(data, null, 2));
    } catch (error) { 
        console.log('HTTP Status Code:', error.response.status);
        console.log('Response Body:', error.response.data);
    } 
})();
