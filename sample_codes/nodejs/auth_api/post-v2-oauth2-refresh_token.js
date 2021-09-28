const axios = require('axios'); 

const endPoint = 'https://public-api.hakaru.ai';
const path = '/v2/oauth2/refresh_token';
const url = endPoint + path;

const refreshToken = 'REFRESH_TOKEN';

(async () => { 
    try { 
        const body = {
            refresh_token: refreshToken
        };
        const response = await axios.post(url, body);
        const { data } = response;
        const accessToken = data.access_token;

        console.log('HTTP Status Code:', response.status);
        console.log('Access Token:', accessToken); 
    } catch (error) { 
        console.log('HTTP Status Code:', error.response.status);
        console.log('Response Body:', error.response.data);
    } 
})();
