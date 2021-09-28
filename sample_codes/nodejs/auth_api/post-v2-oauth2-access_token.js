const axios = require('axios'); 

const endPoint = 'https://public-api.hakaru.ai';
const path = '/v2/oauth2/access_token';
const url = endPoint + path;

const username = 'YOUR_ACCOUNT_ID';
const password = 'YOUR_PASSWORD';

(async () => { 
    try { 
        const body = {
            username: username,
            password: password
        };
        const response = await axios.post(url, body);
        const { data } = response;
        const accessToken = data.access_token;
        const refreshToken = data.refresh_token;

        console.log('HTTP Status Code:', response.status);
        console.log('Access Token:', accessToken); 
        console.log('Refresh Token:', refreshToken); 
    } catch (error) { 
        console.log('HTTP Status Code:', error.response.status);
        console.log('Response Body:', error.response.data);
    } 
})();
