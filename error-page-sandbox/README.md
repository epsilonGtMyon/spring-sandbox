# error-page-sandbox

エラーページについて調べる。

`text/html` の時は デフォルトだと 
`BasicErrorController#errorHtml` から `DefaultErrorViewResolver` で処理される。

ここでHTTPステータスに応じたビューが選択され処理が行われる。

## ErrorViewResolver

これがエラーの時の表示するビューを決定し、デフォルトだと `DefaultErrorViewResolver` が使われる

これはステータスコードに対応したページの htmlファイルが存在すればそれを利用、
なければ その百番台のファイルを使う(404なら 4xx.html, 503 なら5xx.html)。

詳細は `DefaultErrorViewResolver` のjavadocを参照

## BasicErrorController

ここでは `text/html` のときに `BasicErrorController#errorHtml` でハンドリングが行われ、
上記の  `ErrorViewResolver` でハンドリングが行われる。

対象のビューが見つからないときは `error` のパスを使う。
`thymeleaf` だと `template/error.html` にファイルをおけばここで処理がされる。


という感じ