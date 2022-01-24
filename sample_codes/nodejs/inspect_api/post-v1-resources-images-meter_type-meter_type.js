const axios = require('axios');
const fs = require('fs');

const endPoint = 'https://public-api.hakaru.ai';
const meterType = 'YOUR_METER_TYPE';
const path = '/v1/resources/images/meter_type/' + meterType;
const url = endPoint + path;

const accessToken = 'ACCESS_TOKEN';
const XHakaruRequestId = 'REQUEST_ID_IF_YOU_WANNA_SET_IT';
const imageFilePath = './YOUR_IMAGE_FILE.jpg';

const now = new Date();
const measuredAt = now.getTime();

(async () => { 
    try {
        fs.statSync(imageFilePath);

        const options = {
            headers: {
                Authorization: `Bearer ${accessToken}`,
                'X-Hakaru-Request-Id': XHakaruRequestId
            }
        };

        fs.readFile(imageFilePath, 'base64', async function (err, imageBase64) {
            if (err) {
                throw err;
            }

            const body = {
                image: imageBase64,
                measured_at: measuredAt.toString()
            };

            const response = await axios.post(url, body, options);
            const { headers, data } = response;

            console.log('HTTP Status Code:', response.status);
            console.log('x-hakaru-request-id:', headers['x-hakaru-request-id']);
            console.log('x-hakaru-request-upper-limit-monthly:', headers['x-hakaru-request-upper-limit-monthly']);
            console.log('x-hakaru-request-upper-limit-daily:', headers['x-hakaru-request-upper-limit-daily']);
            console.log('x-hakaru-request-total-monthly:', headers['x-hakaru-request-total-monthly']);
            console.log('x-hakaru-request-total-daily:', headers['x-hakaru-request-total-daily']);
            console.log('Response Body:', data);
        });
    } catch (error) {
        if (error.response) {
            console.log('HTTP Status Code:', error.response.status);
            console.log('Response Body:', error.response.data);
        } else {
            console.log(error);
        }
    } 
})();
