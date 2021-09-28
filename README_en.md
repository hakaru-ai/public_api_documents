[![Release Version][release-shield]][release-url]
[![Stargazers][stars-shield]][stars-url]
[![License: MIT][license-shield]][license-url]


<!-- PROJECT LOGO -->
<br />
<p align="center">
  <a href="https://github.com/hakaru-ai/public_api_documents">
    <img src="https://github.com/hakaru-ai/public_api_documents/blob/master/images/logo_hakaru-ai.png" alt="hakaru.ai" width="329" height="74">
  </a>

  <h3 align="center">hakaru.ai Public API Related Document</h3>

  <p align="center">
    Steps to start using API, restrictions and sample codes
    <br />
    <strong>Please refer <a href="https://developer.hakaru.ai/" target="_blank">here</a> for API specifications.</strong>
  </p>
</p>



<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary><h2 style="display: inline-block">Table of Contents</h2></summary>
  <ol>
    <li><a href="#overview">Overview</a></li>
    <li><a href="#repository-configuration">Repository Configuration</a></li>
    <li><a href="#api-types">API Types</a></li>
    <li><a href="#steps-to-start-using-api">Steps to start using API</a></li>
    <li><a href="#restrictions">Restrictions</a>
      <ul>
        <li><a href="#restrictions-on-calling-the-oauth2-authentication-and-authorization-api">Restrictions on calling the OAuth2 Authentication and Authorization API</a></li>
        <li><a href="#restrictions-on-calling-the-inspection-api-and-ledger-api">Restrictions on calling the Inspection API and Ledger API</a></li>
        <li><a href="#others">Others</a></li>
      </ul>
    </li>
    <li><a href="#about-oauth2-authentication">About OAuth2 Authentication</a></li>
    <li><a href="#oauth2-authentication-and-authorization-api">OAuth2 Authentication and Authorization API</a></li>
    <li><a href="#inspection-api">Inspection API</a>
      <ul>
        <li><a href="#restrictions-on-photo-images">Restrictions on photo images</a></li>
      </ul>
    </li>
    <li><a href="#ledger-api">Ledger API</a></li>
    <li><a href="#list-of-codes-and-types">List of Codes and Types</a>
      <ul>
        <li><a href="#meter-type">Meter type</a></li>
        <li><a href="#ledger-type">Ledger type</a></li>
        <li><a href="#analysis-code">Analysis code</a></li>
        <li><a href="#abnormal-value-notification-setting-type">Abnormal value notification setting type</a></li>
      </ul>
    </li>
    <li><a href="#list-of-error-codes">List of Error Codes</a>
      <ul>
        <li><a href="#common-response-errors">Common response errors</a></li>
        <li><a href="#authentication-api-errors">Authentication API errors</a></li>
        <li><a href="#analysis-api-errors">Analysis API errors</a></li>
      </ul>
    </li>
    <li><a href="#sample-code">Sample Code</a></li>
    <li><a href="#license">License</a></li>
  </ol>
</details>




<!-- OVERVIEW -->
## Overview

[hakaru.ai][service-site-url] provides a service to improve the efficiency of meter inspection work by using AI to analyze the photographed images of meters, read the numerical data, and complete the ledger recording automatically.    
The meter image can be sent via a dedicated smartphone application or API. This document describes the steps to start using API, as well as restrictions and defined values for each parameter.   

For details on the hakaru.ai service, please see [here][service-site-url]. For API specifications, please refer to [this document][api-doc-url].





<!-- REPOSITORY CONFIGURATION -->
## Repository Configuration

```
├── LICENSE
├── README.md
├── README_en.md
├── images
│   └── logo_hakaru-ai.png
├── docs
│   └── swagger.yaml
└── sample_codes
    ├── go
    │   ├── README.md
    │   ├── auth_api
    │   ├── inspect_api
    │   └── sheet_api
    ├── images
    ├── java
    │   ├── AuthApi.java
    │   ├── InspectApi.java
    │   ├── README.md
    │   ├── SheetApi.java
    │   └── lib
    ├── nodejs
    │   ├── README.md
    │   ├── auth_api
    │   ├── inspect_api
    │   └── sheet_api
    ├── php
    │   ├── README.md
    │   ├── auth_api
    │   ├── inspect_api
    │   └── sheet_api
    └── python
        ├── README.md
        ├── auth_api
        ├── inspect_api
        └── sheet_api
```




## API Types

hakaru.ai provides the following types of public API.

1. [OAuth2 Authentication and Authorization API](#oauth2-authentication-and-authorization-api)
1. [Inspection API](#inspection-api)
1. [Ledger API](#ledger-api)


## Steps to start using API

Once you register a new account from the [hakaru.ai account temporary registration form][application-form-url] and start using the service, you can use the public API as a trial user (free of charge for a maximum of one month). However, **trial users can only use the [OAuth2 Authentication and Authorization API](#oauth2-authentication-and-authorization-api) and [Inspection API](#inspection-api), and are limited to a maximum of 50 requests per month**.  
If you wish to use the Ledger API or make more frequent requests, or if the trial period has expired, you will need to sign up for API separately (for a fee).

For more information about the API usage contract, please contact us using [this form][contact-form-url].


| API Types | Target Users |
|:--------|:--------|
| OAuth2 Authentication and Authorization API | All users under contract |
| Inspection API | All users under contract |
| Ledger API | Ledger API subscribed users only |

## Restrictions

### Restrictions on calling the OAuth2 Authentication and Authorization API

Calls to the OAuth2 Authentication and Authorization API will be restricted as follows: 

| Conditions | Restrictions | 
|:--------|:--------|
| Request transmission interval | `0.5 sec.` |

Restrictions are applied on a per user basis. For example, if you call `/v2/oauth2/access_token` API again within 0.5 seconds after calling the same API with the same user account, the hakaru.ai system will refuse to accept the request and return an HTTP Status Code 429.


### Restrictions on calling the Inspection API and Ledger API

Calls to the Inspection API and Ledger API are restricted as follows, respectively:

- **Inspection API**

  | Conditions | Restrictions | 
  |:--------|:--------|
  | Request transmission interval | `0.5 sec.` |
  | Number of requests per day | Up to `10,000 times` |
  | Number of requests per month | Up to `300,000 times` (up to `50 times` for API trial users) |

- **Ledger API**

  | Conditions | Restrictions | 
  |:--------|:--------|
  | Request transmission interval | `0.5 sec.` |
  | Number of requests per day | Up to `10,000 times` |
  | Number of requests per month | Up to `300,000 times` |

#### Request transmission interval

Restrictions are applied by user account and by API type. For example, if any Inspection API is called within 0.5 seconds after calling `/v1/resources/images` API, namely the Inspection API, under the same user account, the hakaru.ai system will refuse to accept the request and return an HTTP Status Code 429 error. 
Similarly, if the same account calls `/v1/sheets/count` API, namely the Ledger API, and then calls any Ledger API within 0.5 seconds, API will also return an HTTP Status Code 429 error.  

Please note that the request intervals for the Inspection API and the Ledger API are counted separately. So, if you call the Ledger API within 0.5 seconds after calling the Inspection API, the call will be successful.

#### Maximum number of requests

Restrictions are applied **per contract** and per API type.
For example, if user A and user B exist in the same contract, and the total number of times each user calls the Inspection API reaches 10,000 times in a day, the limit is reached, and the hakaru.ai system will return an HTTP Status Code 429.
This limit will be reset at 0:00 on the next day according to Japanese Standard Time (JST).
Similarly, if the total number of Inspection API requests by each user in the same contract reaches 300,000 per month, API will return an HTTP Status Code 429 error thereafter, but this limit will be reset at 0:00 on the first day of the following month according to JST.
The number of requests for the Inspection API and the Ledger API is counted separately.
For example, even if the daily request count of the Ledger API reaches the limit, a call to the Inspection API will succeed if the request count of the Inspection API has not reached the limit.

**For API trial users**  
When the total number of Inspection API requests made by each user in the same contract reaches 50 per month, the limit is reached, and subsequent requests will be returned with an HTTP Status Code 429 error.


### Others

Please note that if the system is judged to be overloaded due to a large number of requests from the same user or client, the system may restrict the acceptance of API requests from the target user or connection source.



## About OAuth2 Authentication

The hakaru.ai public API uses the OAuth2 authorization code flow to authenticate and authorize the caller in the following steps:  

1. Call the [Access Token Acquisition API](https://developer.hakaru.ai/#operation/post-v2-oauth2-access_token) to acquire the access token and refresh token.
    - Token validity period
        - Access token: `3,600 seconds (1 hour)`
        - Refresh token: `2,678,400 seconds (31 days)`
2. Call the Inspection and Ledger API using the access token acquired in step 1.
3. When the access token has expired, call the [Refresh Token Authentication API](https://developer.hakaru.ai/#operation/post-v2-oauth2-refresh_token) using the refresh token acquired in step 1 to reacquire the access token. In this case, the refresh token will not be reissued. 
5. If the refresh token has expired, please perform the procedure again from step 1 to acquire the access token.

## OAuth2 Authentication and Authorization API

Performs user authentication.

- `POST` /v2/oauth2/access_token - [Access token acquisition API](https://developer.hakaru.ai/#operation/post-v2-oauth2-access_token)
- `POST` /v2/oauth2/refresh_token - [Refresh Token Authentication API](https://developer.hakaru.ai/#operation/post-v2-oauth2-refresh_token)
- `GET` /v2/oauth2/verify_token - [Access token verification API](https://developer.hakaru.ai/#operation/get-v2-oauth2-verify_token)

## Inspection API

Analyzes the image of the inspection meter and returns a numerical value.

- `POST` `/v1/resources/images` - [Inspection API (QR code available)](https://developer.hakaru.ai/#operation/post-v1-resources-images)
- `POST` /v1/resources/images/meter_type/{meter_type} - [Inspection API (QR code not required, meter type specified)](https://developer.hakaru.ai/#operation/post-v1-resources-images-meter_type-meter_type)

### Restrictions on photo images

- **Format**
    - JPEG or PNG format

- **Data size**
    - Recommended: `100KB`
    - Upper limit: `3MB`

    *If the size of the image exceeds the limit, API will return an HTTP Status Code 413.*

- **Size**
    - Aspect ratio: 1:1  
    - Recommended: `540x540` (`300x300` or larger)


## Ledger API

Returns the web ledger, inspection meter information, and inspection values already registered in the hakaru.ai system.

- `GET` /v1/sheets/count?name={name} - [Web Ledger Count Acquisition API](https://developer.hakaru.ai/#operation/get-v1-sheets-count)
- `GET` /v1/sheets?name={name}&limit={limit}&offset={offset}&order={order} - [Web Ledger List Acquisition API](https://developer.hakaru.ai/#operation/get-v1-sheets)
- `GET` /v1/sheet/{sheet_id} - [Web Ledger Information Acquisition API](https://developer.hakaru.ai/#operation/get-v1-sheet-sheet_id)
- `GET` /v1/row/{row_id}/{from}/{to} - [Inspection Data Acquisition API](https://developer.hakaru.ai/#operation/get-v1-row-row_id-from-to)

## List of Codes and Types

### Meter type

| Meter type | Name | Inspection API Support | Ledger API Support | Remarks |
|:----------|:-----------|:-----------:|:-----------:|:-----------|
| MET0000     | Digital meter |  | ✓ | Old model. Will be disused in the future. |
| MET0001     | Analog meter |  | ✓ | |
| MET0002     | Rotating meter | ✓ | ✓ | |
| MET0003     | Water meter | ✓ | ✓ | |
| MET0004     | Analog panel meter | ✓ | ✓ | |
| MET0005     | LCD digital meter | ✓ | ✓ | |
| MET0006     | LED digital meter | ✓ | ✓ | |
| MET0007     | Horizontal scale manometer β |  | ✓ | |

### Ledger type

| Ledger type | Contents |
|:----------|:----------|
| SHEET_PER_HOURS | Meter reading and recording to be done at least once a day |
| SHEET_PER_MONTH | Meter reading and recording to be done once a month |

### Analysis code

| Code | Contents |
|:----------|:-----------|
| OK    | Successful analysis |
| MEA0001    | Unsuccessful analysis |

### Abnormal value notification setting type

| Abnormal value notification setting type | Contents |
|:----------|:-----------|
| GTE (threshold:{Upper limit}) | Above threshold level |
| LTE (threshold:{Lower limit}) | Below threshold level |
| EQ (normal value:{Normal level}) | All but normal level |
| BETWEEN (lower threshold:{Lower limit},upper threshold:{pper limit}) | Out of threshold range |

## List of Error Codes

### Common response errors

| Error Code | Error Contents |
|:----------|:-----------|
| PUB0001     | The request is invalid |
| PUB0002     | JSON is invalid |
| PUB0003     | Maximum number of requests exceeded |
| PUB0004     | The specified URL cannot be found |
| PUB9999     | System error |

### Authentication API errors

| Error Code | Error Contents |
|:----------|:-----------|
| UAC0001     | Authentication error |
| UAC0001-01  | Invalid user name or password |
| UAC0001-02  | The channel ID is invalid |
| UAC0001-03  | The user does not exist |
| UAC0001-04  | Unable to authenticate due to temporary user status |
| UAC0001-05  | Unauthorized access token |
| UAC0001-06  | Account not found |
| UAC0001-07  | Unauthorized refresh token |
| UAC0001-08  | Your trial period expired |
| UAC0001-09  | Invalid user status |
| UAC0001-10  | Invalid contract status |
| UAC0001-11  | Your contract has expired |
| UAC0002     | Your access token has expired |
| UAC0003     | You are not a contracted user of the public API |
| UAC0004     | Request error |
| UAC0005     | Invalid process for the target user |
| UAC0005-01  | The channel ID does not exist |
| UAC0005-02  | The client does not exist |
| UAC0005-03  | The target user does not exist |
| UAC0005-04  | There are duplicate target users |

### Analysis API errors

| Error Code | Error Contents |
|:----------|:-----------|
| ANA0001     | The QR code does not exist |
| ANA0002     | The image value is invalid |
| ANA0003     | The specified time is invalid |
| ANA0004     | The meter type is incorrect |
| ANA0005     | The server is suspended |



## Sample Code

Please refer to the [sample_codes](sample_codes) directory for sample code of each API.  
Supported languages are [go](sample_codes/go), [java](sample_codes/java), [node.js](sample_codes/nodejs), [php](sample_codes/php) and [python](sample_codes/python).




<!-- LICENSE -->
## License

[MIT License](LICENSE)




<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[release-shield]: https://img.shields.io/github/v/release/hakaru-ai/public_api_documents?color=blue&style=for-the-badge
[release-url]: https://github.com/hakaru-ai/public_api_documents/releases
[stars-shield]: https://img.shields.io/github/stars/hakaru-ai/public_api_documents?color=yellow&style=for-the-badge
[stars-url]: https://github.com/hakaru-ai/public_api_documents/stargazers
[license-shield]: https://img.shields.io/github/license/hakaru-ai/public_api_documents?color=green&style=for-the-badge
[license-url]: https://github.com/hakaru-ai/public_api_documents/blob/master/LICENSE
[service-site-url]: https://www.hakaru.ai/
[application-form-url]: https://hakaru.ai/account/register/pre
[contact-form-url]: https://form.gmogshd.com/iot/hakaru-ai/contact/
[api-doc-url]: https://developer.hakaru.ai/