# container-sandbox

コンテナまわりいろいろと


## ビルドしたり動かしたり

アプリのビルド

```
gradlew clean build
```

アプリ用コンテナのビルド

```
docker build -t my-container-sandbox-app-image .
```


コンテナ起動

```
docker run -p 8080:8080 -e spring.datasource.url=jdbc:h2:tcp://host.docker.internal/test -e spring.data.redis.host=host.docker.internal -e TZ=Asia/Tokyo --name my-container-sandbox-app my-container-sandbox-app-image
```



## メモ

### コンテナからホストを見る

`host.docker.internal` というホスト名でアクセスするとホスト側にアクセスできる。

