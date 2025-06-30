# security6-saml-slo-sandbox

署名が必要なので一式作る

```
openssl req -x509 -new -newkey rsa:2048 -nodes -keyout rp-private.key -out rp-certificate.crt -days 7300
```


## Saml2RelyingPartyInitiatedLogoutFilter

SLOを有効にした場合 `LogoutFilter`ではなくそのサブクラスのこれが使われる。

違いとしては `SuccessHandler` に `Saml2RelyingPartyInitiatedLogoutSuccessHandler`が使われるようになっている。


## Saml2RelyingPartyInitiatedLogoutSuccessHandler

`/logout` の時に実行される。

APにSaml2:LogoutRequestを送信する。

送信方法はリダイレクト(or POST)

この後APからSaml2:LogoutResponseが返ってくるが、これは `Saml2LogoutResponseFilter`がハンドリングする。


## Saml2LogoutRequestFilter

AP開始のログアウトで使う

APからのSaml2:LogoutRequestをハンドリング(検証だったり、ログアウトだったり)しレスポンスを返す。


## Saml2LogoutResponseFilter

SP開始のログアウトで使う

SPでログアウトしAPにLogoutRequest(リダイレクト or POST)
-> APからSPにリクエストLogoutResponse

の時に動くフィルター
