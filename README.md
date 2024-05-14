[![Release Version][release-shield]][release-url]
[![Stargazers][stars-shield]][stars-url]


<!-- PROJECT LOGO -->
<br />
<p align="center">
  <a href="https://github.com/hakaru-ai/public_api_documents">
    <img src="https://github.com/hakaru-ai/public_api_documents/blob/master/images/logo_hakaru-ai.png" alt="hakaru.ai" width="329" height="74">
  </a>

  <h3 align="center">hakaru.ai 公開 API 関連ドキュメント</h3>
  <p align="center"><a href="https://github.com/hakaru-ai/public_api_documents/blob/master/README_en.md">For the English version of the documentation, see here.</a></p>
  <p align="center">
    ご利用開始手順、制限事項およびサンプルコード
    <br />
    <a href="https://developer.hakaru.ai/" target="_blank"><strong>API 仕様書はこちらを参照してください</strong></a>
  </p>
</p>



<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary><h2 style="display: inline-block">Table of Contents</h2></summary>
  <ol>
    <li><a href="#概要">概要</a></li>
    <li><a href="#リポジトリ構成">リポジトリ構成</a></li>
    <li><a href="#api-種別">API 種別</a></li>
    <li><a href="#api-利用開始手順">API 利用開始手順</a></li>
    <li><a href="#制限事項">制限事項</a>
      <ul>
        <li><a href="#oauth-認証認可-api-の呼び出し制限">OAuth2 認証認可 API の呼び出し制限</a></li>
        <li><a href="#点検台帳-api-の呼び出し制限">点検・台帳 API の呼び出し制限</a></li>
        <li><a href="#その他">その他</a></li>
      </ul>
    </li>
    <li><a href="#oauth2-認証について">OAuth2 認証について</a></li>
    <li><a href="#oauth2-認証認可-api">OAuth2 認証認可 API</a></li>
    <li><a href="#点検-api">点検 API</a>
      <ul>
        <li><a href="#画像についての制限事項">画像についての制限事項</a></li>
      </ul>
    </li>
    <li><a href="#台帳-api">台帳 API</a></li>
    <li><a href="#コードタイプ一覧">コード・タイプ一覧</a>
      <ul>
        <li><a href="#メータータイプ">メータータイプ</a></li>
        <li><a href="#台帳タイプ">台帳タイプ</a></li>
        <li><a href="#解析コード">解析コード</a></li>
        <li><a href="#異常値通知設定タイプ">異常値通知設定タイプ</a></li>
        <li><a href="#回転式メーターにおける一桁目の取り扱いタイプ">回転式メーターにおける一桁目の取り扱いタイプ</a></li>
      </ul>
    </li>
    <li><a href="#エラーコード一覧">エラーコード一覧</a>
      <ul>
        <li><a href="#共通レスポンスエラー">共通レスポンスエラー</a></li>
        <li><a href="#認証-api-エラー">認証 API エラー</a></li>
        <li><a href="#解析-api-エラー">解析 API エラー</a></li>
      </ul>
    </li>
    <li><a href="#サンプルコード">サンプルコード</a></li>
    <li><a href="#ライセンス">ライセンス</a></li>
  </ol>
</details>




<!-- OVERVIEW -->
## 概要

[hakaru.ai][service-site-url] はメーターの撮影画像を AI で解析し数値を読み取り、自動で台帳記録を完了する、メーター点検業務を効率化するためのサービスです。  
メーター画像は専用のスマホアプリまたは API から送信することができ、こちらのドキュメントでは API の利用開始手順、制限事項および各パラメーターの定義値について説明します。 

hakaru.ai サービスの詳細については[こちら][service-site-url]を参照してください。また、API 仕様については[こちら][api-doc-url]のドキュメントをご確認ください。




<!-- REPOSITORY CONFIGURATION -->
## リポジトリ構成

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




## API 種別

hakaru.ai は次の種別の公開 API を提供します。

1. [OAuth2 認証認可 API](#oauth2-認証認可-api)
1. [点検 API](#点検-api)
1. [台帳 API](#台帳-api)


## API 利用開始手順

[hakaru.ai アカウント仮登録フォーム][application-form-url]から新規アカウントを登録しサービスのご利用を開始すると、公開 API をトライアルユーザー（最大１カ月無料）としてご利用いただけますが、**トライアルユーザーは [OAuth2 認証認可 API](#oauth2-認証認可-api) と[点検 API](#点検-api) のみを利用でき、かつ月次でのリクエスト数は最大 50 回に制限されます**。  
[台帳 API](#台帳-api) のご利用、より多くのリクエスト回数を希望される場合、または無料期間が終了した場合は、別途 API 利用をご契約（有償）いただく必要があります。 

API 利用のご契約については、[こちらのフォーム][contact-form-url]からお問い合わせください。


| API 種別 | 利用可能ユーザー |
|:--------|:--------|
| OAuth2 認証認可 API | ご契約中のすべてのユーザー |
| 点検 API | ご契約中のすべてのユーザー |
| 台帳 API | 台帳 API 契約ユーザーのみ |

## 制限事項

### OAuth2 認証認可 API の呼び出し制限

OAuth2 認証認可 API の呼び出しは次のように制限されます。  

| 条件 | 制限 | 
|:--------|:--------|
| リクエスト送信間隔 | `0.5 秒` |

制限は 1 ユーザーごとに適用され、たとえば同一ユーザーアカウントにて `/v2/oauth2/access_token` API を呼び出したあと 0.5 秒以内に再びこの API を呼び出すと、hakaru.ai システムはリクエストの受け付けを拒否し HTTP Status Code 429 を返します。

### 点検・台帳 API の呼び出し制限

点検 API および台帳 API の呼び出しはそれぞれ次のように制限されます。

- **点検 API**

  | 条件 | 制限 | 
  |:--------|:--------|
  | リクエスト送信間隔 | `0.5 秒` |
  | 1 日あたりのリクエスト数 | 最大 `10,000 回` |
  | 1 カ月あたりのリクエスト数 | 最大 `300,000 回`（API トライアルユーザーは最大 `50 回`） |

- **台帳 API**

  | 条件 | 制限 | 
  |:--------|:--------|
  | リクエスト送信間隔 | `0.5 秒` |
  | 1 日あたりのリクエスト数 | 最大 `10,000 回` |
  | 1 カ月あたりのリクエスト数 | 最大 `300,000 回` |

#### リクエスト送信間隔

制限はユーザーアカウント別および API の種別ごとに適用され、たとえば同一ユーザーアカウントにて点検 API である `/v1/resources/images` API を呼び出したあと 0.5 秒以内に何らかの 点検 API を呼び出すと、hakaru.ai システムはリクエストの受付けを拒否し HTTP Status Code 429 エラーを返します。
同様に同一アカウントが台帳 API である `/v1/sheets/count` API を呼び出したあと、0.5 秒以内に何らかの台帳 API を呼び出した場合も API は HTTP Status Code 429 エラーを返します。  

なお、点検 API と台帳 API のリクエスト間隔はそれぞれ別々に計測されます。  
もし点検 API を呼び出した直後、0.5 秒以内に台帳 API を呼び出しても API は成功します。 

#### 最大リクエスト数

制限は**契約単位**および API の種別ごとに適用されます。  
たとえば、同一契約内にユーザーA とユーザーB が存在し、各ユーザーが点検 API を呼び出した回数の合計が日間で 10,000 回に達した時点で上限となり、以降 hakaru.ai システムは HTTP Status Code 429 を返します。  
この制限は日本標準時間（JST）基準で翌日 0:00 になるとリセットされます。
 
同様に、同一契約内の各ユーザーによる点検 API リクエスト数が月間合計で 300,000 回に達した場合、以降 API は HTTP Status Code 429 エラーを返しますが、この制限は日本標準時間（JST）基準で翌月 1 日の 0:00 になるとリセットされます。

なお、点検 API と台帳 API のリクエスト回数はそれぞれ別々に計測されます。  
たとえば台帳 API の１日のリクエスト回数が上限に達していても、点検 API のリクエスト回数が上限に達していなければ点検 API の呼び出しは成功します。

**API トライアルユーザーの場合**  
同一契約内の各ユーザーによる点検 API リクエスト数の月間合計が 50 回に達した時点で上限となり、以降のリクエストは HTTP Status Code 429 エラーで返されます。  


### その他

同一ユーザーまたはクライアントからの大量のリクエストにより、システムに負荷がかかっていると判断された場合は、対象ユーザーまたは接続元からの API リクエストの受付けを制限する場合があります。  
あらかじめご了承ください。



## OAuth2 認証について

hakaru.ai 公開 API は OAuth2 認可コードフローを利用し、次の手順で呼び出し元の認証、認可を行います。  

1. [アクセストークン取得 API](https://developer.hakaru.ai/#operation/post-v2-oauth2-access_token) を呼び出し、アクセストークンおよびリフレッシュトークンを取得します。
    - トークン有効期間
        - アクセストークン: `3,600 秒（1 時間）`
        - リフレッシュトークン: `2,678,400 秒（31 日間）`
2. 1 で取得したアクセストークンを使って点検・台帳 API を呼び出します。  
3. アクセストークンが失効した場合、1 で取得したリフレッシュトークンを利用して[リフレッシュトークン認証 API](https://developer.hakaru.ai/#operation/post-v2-oauth2-refresh_token) を呼び出しアクセストークンを再取得してください。このときリフレッシュトークンは再発行されません。  
5. リフレッシュトークンが失効した場合は、再び 1 のアクセストークン取得手順から実行してください。

## OAuth2 認証認可 API

ユーザー認証を行います。

- `POST` /v2/oauth2/access_token - [アクセストークン取得 API](https://developer.hakaru.ai/#operation/post-v2-oauth2-access_token)
- `POST` /v2/oauth2/refresh_token - [リフレッシュトークン認証 API](https://developer.hakaru.ai/#operation/post-v2-oauth2-refresh_token)
- `GET` /v2/oauth2/verify_token - [アクセストークン検証 API](https://developer.hakaru.ai/#operation/get-v2-oauth2-verify_token)

## 点検 API
### 点検APIのエンドポイント
点検メーターの撮影画像を解析し、数値を返します。
点検APIは、hakaru.aiサービスの台帳に登録されたメーターの設定情報を基に数値を読み取る「QRコード指定」のエンドポイントと、
メータータイプを指定してメーター画像を読み取る「メータータイプ指定」のエンドポイントがあります。

- `POST` /v1.1/resources/images - [点検 API（QRコード指定）](https://developer.hakaru.ai/#operation/post-v1.1-resources-images)
- `POST` /v1.1/resources/images/meter_type/{meter_type} - [点検 API（メータータイプ指定）](https://developer.hakaru.ai/#operation/post-v1.1-resources-images-meter_type-meter_type)

### 台帳への値の表示
QRコード指定のAPIを利用した際、displayed,multiのパラメーターがtrueの状態のAPIリクエストに対し、下記の値確定APIを利用してhakaru.aiの台帳に記録・表示する情報を確定させます。

- `POST` /v1.1/resources/determine - [値確定API](https://developer.hakaru.ai/#operation/post-v1.1-resources-determine)


※v1.1バージョンの提供に際し、下記v1バージョンのエンドポイントを非推奨といたします。

- `POST` /v1/resources/images - [点検 API（QRコードあり）](https://developer.hakaru.ai/#operation/post-v1-resources-images)
- `POST` /v1/resources/images/meter_type/{meter_type} - [点検 API（QRコード不要・メータータイプ指定）](https://developer.hakaru.ai/#operation/post-v1-resources-images-meter_type-meter_type)

### 画像についての制限事項

- **フォーマット**
    - JPEG または PNG 形式

- **容量**
    - 推奨: `100KB`
    - 上限: `3MB`

    *画像の大きさが上限容量を超えた場合、API は `HTTP Status Code 413` を返します。*

- **サイズ**
    - アスペクト比: 1:1  
    - 推奨: `540x540` (`300x300` 以上)


## 台帳 API

hakaru.ai システムに登録済みの Web 台帳、点検メーター情報および点検値を返します。

- `GET` /v1/sheets/count?name={name} - [Web台帳数取得 API](https://developer.hakaru.ai/#operation/get-v1-sheets-count)
- `GET` /v1/sheets?name={name}&limit={limit}&offset={offset}&order={order} - [Web台帳一覧取得 API](https://developer.hakaru.ai/#operation/get-v1-sheets)
- `GET` /v1/sheet/{sheet_id} - [Web台帳情報取得 API](https://developer.hakaru.ai/#operation/get-v1-sheet-sheet_id)
- `GET` /v1/row/{row_id}/{from}/{to} - [点検データ取得 API](https://developer.hakaru.ai/#operation/get-v1-row-row_id-from-to)

## コード・タイプ一覧

### メータータイプ <a href="https://www.hakaru.ai/support/meter-type.html">> イラストで見る</a>

| 名称 | メータータイプ | 点検 API : QRコード指定 | 点検 API : メータータイプ指定 | 台帳 API | 備考 |
|:----------|:-----------|:-------------------:|:----------------------:|:-----------:|:-----------|
| アナログ／丸型 | MET0010 | ✓ | ✓ | ✓ | |
| アナログ／丸型（カスタム設定） | MET0001 | ✓ |  | ✓ | |
| アナログ／角型（電圧・電流） | MET0004 | ✓ | ✓ | ✓ | |
| アナログ／横目盛 | MET0007 | ✓ |  | ✓ | |
| デジタル／ 7セグ | MET0005 |  ✓ | ✓ | ✓ | |
| デジタル／その他 | MET0009 | ✓ | ✓ | ✓ | |
| 回転式・カウンター（電気・ガス・水道） | MET0002 |  ✓ | ✓ | ✓ | |
| デジタルメーター | MET0000 |  |  | ✓ | ***非推奨（廃止予定）*** ※1 |
| 水道計 | MET0003 |   | ✓ | ✓ | ***非推奨（廃止予定）*** ※1<br>*MET0002*へ代替を推奨 ※2 |
| LEDデジタルメーター | MET0006 |   | ✓ | ✓ | ***非推奨（廃止予定）*** ※1<br>*MET0005*へ代替を推奨 ※2 |
| - 未実装 - | MET0008 |  |  | | 次期リリース用 |
* ※1 備考欄に「廃止予定」とあるモデルは、将来的に廃止予定の旧モデルとなります。新たなご利用はお控えください。
* ※2 2022年7月より、MET0003(水道計)はMET0002(回転式)へ、MET0005(液晶デジタルメーター)はMET0006(デジタル／ 7セグ)へ統合されました。

### 台帳タイプ

| 台帳タイプ | 内容 |
|:----------|:----------|
| SHEET_PER_HOURS | 1日に1回以上検針・記録するもの |
| SHEET_PER_MONTH | 月に1回検針・記録するもの |

### 解析コード

| コード | 内容 |
|:----------|:-----------|
| OK    | 解析成功 |
| MEA0001    | 解析失敗 |

### 異常値通知設定タイプ

| 異常値通知設定タイプ | 内容 |
|:----------|:-----------|
| GTE (threshold:{上限値}) | しきい値以上 |
| LTE (threshold:{下限値}) | しきい値以下 |
| EQ (normal value:{正常値}) | 正常値以外すべて |
| BETWEEN (lower threshold:{下限値},upper threshold:{上限値}) | しきい値の範囲外 |

## 回転式メーターにおける一桁目の取り扱いタイプ

| 値   | 処理内容 |
|------------|----------|
| ROUND_UP   | 切り上げ |
| ROUND_DOWN | 切り捨て |
| ROUND_OFF  | 四捨五入 |

## 点検タイプ

| 値   | 内容 |
|------------|----------|
| METER   | メーター点検 |
| SELECT | 設備点検 |

## エラーコード一覧

### 共通レスポンスエラー

| エラーコード | エラー内容 |
|:----------|:-----------|
| PUB0001     | リクエストが不正 |
| PUB0002     | JSONが不正 |
| PUB0003     | リクエスト上限回数超過  |
| PUB0004     | 指定されたURLが見つかりません |
| PUB9999     | システムエラー  |

### 認証 API エラー

| エラーコード | エラー内容 |
|:----------|:-----------|
| UAC0001     | 認証エラー |
| UAC0001-01  | ユーザー名またはパスワードが不正です |
| UAC0001-02  | チャネルIDが不正です |
| UAC0001-03  | 存在しないユーザーです |
| UAC0001-04  | 仮登録ユーザーのため認証できません |
| UAC0001-05  | 不正なアクセストークンです |
| UAC0001-06  | アカウントが見つかりません |
| UAC0001-07  | 不正なリフレッシュトークンです |
| UAC0001-08  | トライアル期限切れです |
| UAC0001-09  | 無効なユーザーステータスです |
| UAC0001-10  | 無効な契約ステータスです |
| UAC0001-11  | 契約プランが有効期限外です |
| UAC0002     | アクセストークンが有効期限切れです |
| UAC0003     | 公開APIの契約ユーザーではありません |
| UAC0004     | リクエストエラー |
| UAC0005     | 対象ユーザーが不正な処理です |
| UAC0005-01  | チャネルIDが存在しません |
| UAC0005-02  | クライアントが存在しません |
| UAC0005-03  | 対象ユーザーが存在しません |
| UAC0005-04  | 対象ユーザーが重複しています |

### 解析 API エラー

| エラーコード | エラー内容 |
|:----------|:-----------|
| ANA0001     | QRコードが存在しません |
| ANA0002     | 画像の値が不正です |
| ANA0003     | 指定された時間は無効です |
| ANA0004     | メータータイプが不正です |
| ANA0005     | サーバーが停止しています |
| ANA0006     | 点検項目の設定に不備があります。Web画面から編集行ってください。 |
| ANA0007     | 点検値が存在しません |
| ANA0008     | 値確定ができる点検値ではありません |



## サンプルコード
各 API のサンプルコードは [sample_codes](sample_codes) ディレクトリをご確認ください。  
対応言語は [go](sample_codes/go)、[java](sample_codes/java)、[node.js](sample_codes/nodejs)、[php](sample_codes/php) および [python](sample_codes/python) です。





<!-- LICENSE -->
## ライセンス

[MIT ライセンス](LICENSE)




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