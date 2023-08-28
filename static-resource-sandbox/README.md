# static-resource-sandbox

静的リソース取得の検証

通常は `WebMvcAutoConfiguration$WebMvcAutoConfigurationAdapter#addResourceHandlers`にある通り webjarsのパスとstaticPathPattern、あとは`ServletContextResource` がハンドリングに使われる

`ServletContextResource` があることで `webapp` フォルダにあるファイルのハンドリングも行われるようになる

通常はこれで問題ないが `application.properties` で

```
spring.mvc.servlet.path=/app/
```

などを設定した場合 `webapp` フォルダのファイルの取得において `DispatcherServlet` のハンドリング範囲外のURLを指定した場合に問題になる。

warで起動しているときは デフォルトサーブレットが居るので問題にならないが、組み込みのTomcatだとDefaultServletがないので、ハンドリングが行われない


```
server.servlet.register-default-servlet=true
```

を設定することで組み込みTomcatの場合でもデフォルトサーブレットが登録されるのでハンドリングできるようになる。

他のサーブレットコンテナは試してないがおそらく似たようなものかと


## 参考

[1.1.5. Static Content](https://docs.spring.io/spring-boot/docs/3.1.3/reference/html/web.html#web.servlet.spring-mvc.static-content)
