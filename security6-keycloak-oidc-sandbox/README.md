# security6-keycloak-oidc-sandbox

## keycloakの準備


### 起動

``` sh
docker compose up -d
```

### コンテナに入って諸々の作業

入る

``` sh
docker compose exec -it keycloak bash
```


変数準備
`KC_CLI_PASSWORD` はパスワード入力をさぼりたいので..

``` sh
KEYCLOAK_HOME=/opt/keycloak
export KC_CLI_PASSWORD=admin
SERVER_URL=http://localhost:8080

cd ${KEYCLOAK_HOME}
```

レルムの作成

```sh
${KEYCLOAK_HOME}/bin/kcadm.sh create realms \
  --no-config \
  --server ${SERVER_URL} \
  --realm master \
  --user admin \
  -s realm=myrealm \
  -s enabled=true
```


クライアントの作成 クライアントシークレットはWeb画面から確認し、application.ymlに反映

```sh
${KEYCLOAK_HOME}/bin/kcadm.sh create clients \
  --no-config \
  --server ${SERVER_URL} \
  --realm master \
  --user admin \
  -r myrealm \
  -s clientId=my-client \
  -s redirectUris='["http://localhost:8080/login/oauth2/code/my-keycloak"]' \
  -s enabled=true
```

ユーザーの作成

```sh
${KEYCLOAK_HOME}/bin/kcadm.sh create users \
  --no-config \
  --server ${SERVER_URL} \
  --realm master \
  --user admin \
  -r myrealm \
  -s username=myuser \
  -s firstName=Taro \
  -s lastName=Yamada \
  -s email=yamada.taro@example.com \
  -s enabled=true
```

ユーザーのパスワード設定

```sh
${KEYCLOAK_HOME}/bin/kcadm.sh set-password \
  --no-config \
  --server ${SERVER_URL} \
  --realm master \
  --user admin \
  -r myrealm \
  --username myuser \
  --new-password mypass01
```


### Web画面

- [管理画面](http://localhost:20080)
- [作成したレルムのログイン画面](http://localhost:20080/realms/myrealm/account)


