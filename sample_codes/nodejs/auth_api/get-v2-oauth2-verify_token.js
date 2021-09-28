const axios = require('axios'); 

const endPoint = 'https://public-api.hakaru.ai';
const path = '/v2/oauth2/verify_token';
const url = endPoint + path;

const accessToken = 'ACCESS_TOKEN';

(async () => { 
    try { 
        const options = {
            headers: {
                Authorization: `Bearer ${accessToken}`,
            }
        };
        const response = await axios.get(url, options);
        const { data } = response;

        console.log('HTTP Status Code:', response.status);
        console.log('Response Body:', data);
    } catch (error) { 
        console.log('HTTP Status Code:', error.response.status);
        console.log('Response Body:', error.response.data);
    } 
})();
