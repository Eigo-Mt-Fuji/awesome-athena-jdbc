# awesome-athena-jdbc

## はじめに

* AWS AthenaにJDBCでアクセスするJavaサンプルコードを作りました
* 以下のリンク先の情報を見ながらやっていて大変だったのでコード化しました
  - [参考: Using Athena with the JDBC Driver](https://docs.aws.amazon.com/athena/latest/ug/connect-with-jdbc.html/)
  - [参考: Connect to Amazon Athena with federated identities using temporary credentials](https://aws.amazon.com/jp/blogs/big-data/connect-to-amazon-athena-with-federated-identities-using-temporary-credentials/)
  - [参考: Simba JDBC Driver Install Guide](https://cdn.simba.com/products/Athena/doc/JDBC_InstallGuide/)

## 使い方

```
export AWS_REGION=ap-northeast-1
export AWS_ACCESS_KEY_ID=アクセスキー
export AWS_SECRET_ACCESS_KEY=シークレットアクセスキー
export IAM_ROLE_ARN=IAMロールのARN
export ATHENA_WORKGROUP_NAME=Athenaワークグループ名
export GLUE_DB_NAME=GlueDB名
mvn clean install exec:java
```

## 実行結果(DB名、テーブル名は伏字にしています)

```sh
$ mvn clean install exec:java

[INFO] 
[INFO] >>> exec-maven-plugin:1.2.1:java (default-cli) > validate @ efgriver-athena-jdbc >>>
[INFO] 
[INFO] <<< exec-maven-plugin:1.2.1:java (default-cli) < validate @ efgriver-athena-jdbc <<<
[INFO] 
[INFO] 
[INFO] --- exec-maven-plugin:1.2.1:java (default-cli) @ efgriver-athena-jdbc ---
log4j:WARN No appenders could be found for logger (com.simba.athena.amazonaws.AmazonWebServiceClient).
log4j:WARN Please initialize the log4j system properly.
log4j:WARN See http://logging.apache.org/log4j/1.2/faq.html#noconfig for more info.
WARNING: An illegal reflective access operation has occurred
WARNING: Illegal reflective access by com.simba.athena.shaded.fasterxml.jackson.databind.util.ClassUtil (file:/Users/eigo.fujikawa/Desktop/gitlab.veevadev.com/start-query-execution-example/lib/AthenaJDBC42_2.0.9.jar) to method java.lang.Throwable.setCause(java.lang.Throwable)
WARNING: Please consider reporting this to the maintainers of com.simba.athena.shaded.fasterxml.jackson.databind.util.ClassUtil
WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations
WARNING: All illegal access operations will be denied in a future release
SELECT id FROM ******.****** limit 10
ID: 1
ID: 2
ID: 3
ID: 4
ID: 5
ID: 6
ID: 7
ID: 8
ID: 9
ID: 10
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  11.173 s
[INFO] Finished at: 2020-06-04T21:23:58+09:00
[INFO] ------------------------------------------------------------------------
```

## 実行環境

* OS: Mac OS 10.15.4

* Java 13

```
% java --version
openjdk 13.0.2 2020-01-14
OpenJDK Runtime Environment (build 13.0.2+8)
OpenJDK 64-Bit Server VM (build 13.0.2+8, mixed mode, sharing)
```

* Maven 3.6

```
mvn --version
Apache Maven 3.6.3 (cecedd343002696d0abb50b32b541b8a6ba2883f)
Maven home: /usr/local/Cellar/maven/3.6.3_1/libexec
Java version: 13.0.2, vendor: N/A, runtime: /usr/local/Cellar/openjdk/13.0.2+8_2/libexec/openjdk.jdk/Contents/Home
Default locale: ja_JP, platform encoding: UTF-8
OS name: "mac os x", version: "10.15.4", arch: "x86_64", family: "mac"
```
