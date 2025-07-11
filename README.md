# WebTodoList

Java Servlet と JSP を使用して構築されたタスク管理アプリケーションです。ユーザー登録、ログイン、タスクの追加・編集・削除などの機能を備えており、Microsoft SQL Server を使用してデータを管理しています。

---

##  主な機能

- ユーザー登録／ログイン
- タスクの作成・編集・削除
- タスクの完了状態の切り替え
- ログインユーザーによるタスク操作制限

---

## 使用技術

- Java Servlet / JSP
- Microsoft SQL Server + JDBC ドライバー
- Eclipse（Pleiades）開発環境
- Apache Tomcat（ローカル動作）
- HTML / CSS（簡易な UI）

---

## プロジェクト構成
WebTodoList/ 
├── src/ 
│   └── main/java/com/liuhe/SevenGroup/todolist/ 
# 各種 Servlet クラス 
├── src/main/webapp/ 
│   ├── *.jsp                                    
# JSP ページ 
│   └── WEB-INF/ 
│       ├── lib/                                
 # JDBC ドライバー配置 
 │       └── web.xml                             
  # Servlet 設定ファイル 
  ├── .classpath / .project / .settings/           
  # Eclipse 設定ファイル 
  ├── .gitignore                                   
  # Git 除外設定


---

## 依存ライブラリ

以下の JDBC ドライバーが `WEB-INF/lib/` に配置されている必要があります：
mssql-jdbc-12.10.0.jre11.jar

> ドライバーが存在しない場合は、[Microsoft 公式サイト](https://learn.microsoft.com/ja-jp/sql/connect/jdbc/download-microsoft-jdbc-driver-for-sql-server)よりダウンロードしてください。

---

## 実行方法

1. Apache Tomcat をインストール
2. Eclipse（Pleiades）で本プロジェクトをインポート
3. データベース接続情報を編集（`Servlet` クラス内の JDBC URL）
4. Tomcat にデプロイして起動
5. 以下の URL にアクセス：
http://localhost:8080/WebTodoList

---

##  開発者より

このプロジェクトは Java Web 技術とデータベース連携の学習を目的として作成しました。ご質問やフィードバックがございましたら、お気軽にご連絡ください。

GitHub プロフィール：[LH941213](https://github.com/LH941213)




