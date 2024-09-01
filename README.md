こちらは「Coooms」のバックエンドのリポジトリになります。フロントエンドのリポジトリは[こちら](https://github.com/Takumi-Itaya/coooms-frontend)です。

# Coooms

![service-image](https://github.com/Takumi-Itaya/coooms-frontend/blob/main/public/images/coooms-main-image.png)
[![HTML](https://img.shields.io/badge/HTML-gray?style=flat&logo=html5)](https://developer.mozilla.org/ja/docs/Learn/Getting_started_with_the_web/HTML_basics)
[![CSS](https://img.shields.io/badge/CSS-gray?style=flat&logo=css3&logoColor=%231572B6)](https://developer.mozilla.org/ja/docs/Learn/Getting_started_with_the_web/CSS_basics)
[![Java](https://img.shields.io/badge/Java-gray?style=flat)](https://www.java.com/ja/)
[![Spring](https://img.shields.io/badge/Spring-gray?style=flat&logo=spring)](https://spring.io/)
[![Javascript](https://img.shields.io/badge/Javascript-gray?style=flat&logo=javascript)](https://developer.mozilla.org/ja/docs/Web/JavaScript)
[![React](https://img.shields.io/badge/React-gray?style=flat&logo=React)](https://ja.react.dev/)
[![Amazon AWS](https://img.shields.io/badge/Amazon_AWS-gray?style=flat&logo=amazonwebservices)](https://aws.amazon.com/jp/what-is-aws/)
[![Thanks](https://img.shields.io/badge/Thank%20you-for%20visiting-00aab9)](https://coooms.com/)

## 概要

現在は自然音、雨音、波音、しゃろう BGM の 4 種類の BGM を再生できます。ログインすると勉強時間の合計が見れるようになります。

# ▼ サービス URL

[https://coooms.com/](https://coooms.com/)  
ゲストログインは以下のアカウントをご利用ください。  
`E メール: guest@email.com`  
`パスワード: Guest000/`

## 使用技術

**フロントエンド:** HTML / CSS / JavaScript / React  
**バックエンド** Java / Spring  
**インフラ** AWS(Route53 / Certificate Manager / ALB / VPC / EC2 / / RDS MySQL / S3) / Nginx

## インフラ構成図

![infrastructure-diagram](https://github.com/Takumi-Itaya/coooms-frontend/blob/main/public/images/diagrams/aws-config.drawio.png)

## ER 図

![er-diagram](https://github.com/Takumi-Itaya/coooms-frontend/blob/main/public/images/diagrams/coooms-er-diagram.drawio.png)

## なぜ作ろうと思ったのか

私は勉強をするとき youtube で作業用 BGM を聞いて勉強するのですが、youtube だとおすすめの動画や広告などで集中力が切れることがよくありました。そこでできる限り集中力を落とさずシンプルに BGM だけ聞けるサービスがあればいいのにという思いがあり Coooms と言うサービスを開発しました。

## 工夫した点

・オーディオビジュアライザーやボタン、サイドバー、ページ遷移にアニメーションを設定し、モダンな Web デザインにしたこと。  
・ユーザーの認証に JWT を使用したこと。

## 大変だった点、その対策

複数の BGM をループしながら再生する機能の実装するときに音声がならなかったり複数の音声が混ざったりするというバグが発生しました。私はこういうエラーが出ないバグが発生したときの大体の原因が使っている技術の理解の浅さだと思います。なので今回でしたら React のコンポーネントの動きや new Audio の使い方などをもう一度勉強しなおすこで解決しました。  
もう一つは spring security で認証機能を実装することです。spring security は全体像が広かったりセキュリティの知識を必要とするので理解するのにとても時間がかかりました。ですが根気強く何回もドキュメントをみたりセキュリティの基礎知識を勉強したり実際に手を動かして動作を確認することで実装することができました。
