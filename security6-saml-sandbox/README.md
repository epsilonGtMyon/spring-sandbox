# saml-sandbox

- [ドキュメント](https://spring.pleiades.io/spring-security/reference/servlet/saml2/index.html)
- [ドキュメント JP](https://docs.spring.io/spring-security/reference/servlet/saml2/index.html)


## Saml2WebSsoAuthenticationRequestFilter

ここからIdPにリダイレクトする。デフォルトだと
- /saml2/authenticate/{registrationId}
- /saml2/authenticate?registrationId={registrationId}

のURLの時

リダイレクトする前にSaml2AuthenticationRequestRepositoryにリクエストを保存しておきレスポンス検証後に取り出す。

## Saml2WebSsoAuthenticationFilter

SAMLResponseを受け取って検証したりするところ


## Saml2MetadataFilter

SPのメタデータのエンドポイント

- /saml2/service-provider-metadata/{registrationId}
- /saml2/metadata/{registrationId}
- /saml2/metadata

つまり今の設定内容だと
http://localhost:8080/saml2/service-provider-metadata/mock-audience

## OpenSaml4AuthenticationProvider

ProviderManagerから呼ばれている認証Provider
BaseOpenSamlAuthenticationProviderに移譲している。

## SAML20AssertionValidator

レスポンスの検証とか


## Saml2RelyingPartyProperties

IdPの設定とかのクラス


## ログアウト

SLOを利用しない場合は `/logout` にPOSTすればよい

---

## モック用のIdPを用意

[saml-idp](https://github.com/mcguinness/saml-idp) を使う

証明書を作っておいて `pacakge.json` に以下を追記

```json
  "scripts": {
    "saml-idp": "saml-idp --acs http://localhost:8080/login/saml2/sso/mock-audience --aud http://localhost:8080/saml2/service-provider-metadata/mock-audience"
  }
```

あとは `npm run saml-idp` で起動


