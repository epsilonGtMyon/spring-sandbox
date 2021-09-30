# ajax-json-authentication

ログインにリクエストボディをJSONで送信する場合のサンプル

`Spring Security` の `UsernamePasswordAuthenticationFilter` を拡張し、
JSONのリクエストボディから 認証に必要な情報を取り出すようにしている。

これを適用するには `HttpSecurity#addFilterAt` を使うと、諸々のオブジェクトがセットされないので
`FormLoginConfigurer` を参考にし 独自の`AbstractAuthenticationFilterConfigurer` を継承したクラスを作成する。
