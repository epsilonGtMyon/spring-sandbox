# securitycontextsaving-sandbox

`SecurityContextHolderFilter` に移行した場合にどうやって `SeucirytContext` を保存しようか考える

`SecurityContextHolderFilter` の次にフィルターを構えてそこで保存するようにした。

また、通常の構成だとわかりにくいので `spring-session` を使ってDBにセッション情報を保存することで、検証しやすくしている。
