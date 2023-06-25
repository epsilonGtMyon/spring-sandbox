# message-source-sandbox


Spring の `MessageSource` の挙動を確認するもの


## bootの場合のメモ

- `MessageSource` の設定は `MessageSourceAutoConfiguration` で行われている
- `ConditionalOnMissingBean` で`messageSource` というBeanが登録されてなければ設定実行
- 実装は `ResourceBundleMessageSource` が使われる
- `basename` は `messages` だが `spring.messages.basename=messages,messages2` とすることで複数設定できる
- エンコーディングは `UTF-8` になっている( `ResourceBundleMessageSource` のデフォルトは `ISO-8859-1` )
