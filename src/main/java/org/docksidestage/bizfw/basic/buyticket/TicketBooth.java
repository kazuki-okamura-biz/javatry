/*
 * Copyright 2019-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.bizfw.basic.buyticket;

// done okamura author追加お願いします by jflute (2026/10/01)
/**
 * @author jflute
 * @kazuki-okamura-biz
 */
public class TicketBooth {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final int MAX_QUANTITY = 10;
    private static final int ONE_DAY_PRICE = 7400; // when 2019/06/15
    private static final int TWO_DAY_PRICE = 13200;
    private static final int FOUR_DAY_PRICE = 22400;
    private static final int NIGHT_ONLY_TWO_DAY_PRICE = 7400;
    private static final int ONE_DAY_AVAILABLE = 1;
    private static final int TWO_DAY_AVAILABLE = 2;
    private static final int FOUR_DAY_AVAILABLE = 4;

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    // #1on1: 在庫を統一する？分ける？ (2026/10/01)
    // $別物なら分けた方がいいかな？
    // 統一の場合は、白紙の紙があって、oneDay, twoDayその場で書くイメージ。
    // 分ける場合は、最初からoneDay, twoDayが10毎ずつあるイメージ。
    // done okamura 既存の "quantity" をどうするか？ by jflute (2026/10/01)
    // 省略されたquantityをデフォルトのシンプルなoneDayと捉えることもできるかもだけど...
    // totalニュアンスで勘違いする人もいそう。
    // なので、無難にするのであれば、oneDayPassportQuantity ってした方が良いかなと。
    // もう、新しい仕様が追加されたので、クラス全体のバランスが変わったわけなので、
    // 既存の変数名も遠慮なく直して良い。
    // done okamura ダイヤになってる by jflute (2026/10/16)
    private int oneDayPassportQuantity = MAX_QUANTITY;
    private int twoDayPassportQuantity = MAX_QUANTITY;
    private int fourDayPassportQuantity = MAX_QUANTITY;
    private int nightOnlyTwoDayPassportQuantity = MAX_QUANTITY;
    private Integer salesProceeds; // null allowed: until first purchase

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public TicketBooth() {
    }

    // ===================================================================================
    //                                                                          Buy Ticket
    //                                                                          ==========
    // you can rewrite comments for your own language by jflute
    // e.g. Japanese
    // /**
    // * 1Dayパスポートを買う、パークゲスト用のメソッド。
    // * @param handedMoney パークゲストから手渡しされたお金(金額) (NotNull, NotMinus)
    // * @throws TicketSoldOutException ブース内のチケットが売り切れだったら
    // * @throws TicketShortMoneyException 買うのに金額が足りなかったら
    // */
    // done okamura JavaDoc, 戻り値の説明をぜひ @return by jflute (2026/10/16)
    // メソッドインターフェースが変わったらJavaDocも修正。
    /**
     * Buy one-day passport, method for park guest.
     * @param handedMoney The money (amount) handed over from park guest. (NotNull, NotMinus)
     * @throws TicketSoldOutException When ticket in booth is sold out.
     * @throws TicketShortMoneyException When the specified money is short for purchase.
     * @return チケットクラスを返す
     */
    public Ticket buyOneDayPassport(Integer handedMoney) {
        // done okamura 修行++: quantity 2回登場するのどうにかしたい by jflute (2026/10/01)
        // $こんなんでいいのかな？と思いつつも
        // でも一行になってる、近いところにきたから、以前は良い。(行が分かれると片方違うバグが発生しやすい)
        // TODO okamura 修行#: 再び、quantityが2回登場するのどうにかしたい by jflute (2026/10/16)
        // $本当は実装したいことがあった。渡した変数自体を減らすみたいな...immutable/mutableもあるので...
        // いい発想。immutable/mutableのためらいはあるかもだけど、その発想やってみたら。
        oneDayPassportQuantity = doBuyPassport(handedMoney, oneDayPassportQuantity, ONE_DAY_PRICE);
        return new Ticket(ONE_DAY_PRICE, ONE_DAY_AVAILABLE);
    }

    // done okamura JavaDocコメントも入れておいてください (日本語の方で良い) by jflute (2026/10/01)
    // done okamura JavaDoc, 戻り値の説明をぜひ by jflute (2026/10/16)
    /**
     * Buy two-day passport, method for park guest.
     * @param handedMoney The money (amount) handed over from park guest. (NotNull, NotMinus)
     * @throws TicketSoldOutException When ticket in booth is sold out.
     * @throws TicketShortMoneyException When the specified money is short for purchase.
     * @return TicketBuyResult クラス（チケット購入結果）を返す
     */
    public TicketBuyResult buyTwoDayPassport(Integer handedMoney) {
        twoDayPassportQuantity = doBuyPassport(handedMoney, twoDayPassportQuantity, TWO_DAY_PRICE);
        return new TicketBuyResult(new Ticket(TWO_DAY_PRICE, TWO_DAY_AVAILABLE), handedMoney - TWO_DAY_PRICE);
    }

    public TicketBuyResult buyFourDayPassport(Integer handedMoney) {
        fourDayPassportQuantity = doBuyPassport(handedMoney, fourDayPassportQuantity, FOUR_DAY_PRICE);
        return new TicketBuyResult(new Ticket(FOUR_DAY_PRICE, FOUR_DAY_AVAILABLE), handedMoney - FOUR_DAY_PRICE);
    }

    public TicketBuyResult buyNightOnlyTwoDayPassport(Integer handedMoney) {
        nightOnlyTwoDayPassportQuantity = doBuyPassport(handedMoney, nightOnlyTwoDayPassportQuantity, NIGHT_ONLY_TWO_DAY_PRICE);
        return new TicketBuyResult(new Ticket(NIGHT_ONLY_TWO_DAY_PRICE, TWO_DAY_AVAILABLE), handedMoney - NIGHT_ONLY_TWO_DAY_PRICE);
    }

    // done? okamura publicなbuyとprivateなbuyを区分けするテクニックがある by jflute (2026/10/01)
    // (補完のときや会話の時に紛れるのを避けるために)
    // e.g. doBuyPassport → 実処理しますよ感
    // done okamura priceの "金額" は、料金の方がいいかな by jflute (2026/10/16)
    // (ぼくらは言葉にこだわる)
    /**
     * 代金と購入したいチケット、在庫を受け取って購入処理を行う
     * @param handedMoney 渡された金額
     * @param quantity 在庫数
     * @param price 料金
     * @return 購入後の在庫数を返す
     */
    private int doBuyPassport(Integer handedMoney, Integer quantity, int price) {
        if (quantity <= 0) {
            throw new TicketSoldOutException("Sold out");
        }
        if (handedMoney < price) {
            throw new TicketShortMoneyException("Short money: " + handedMoney);
        }
        if (salesProceeds != null) {
            salesProceeds = salesProceeds + price;
        } else {
            salesProceeds = price;
        }
        return --quantity;
    }

    public static class TicketSoldOutException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketSoldOutException(String msg) {
            super(msg);
        }
    }

    public static class TicketShortMoneyException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketShortMoneyException(String msg) {
            super(msg);
        }
    }

    // #1on1: $日本語が一番難しい「priceの "金額" は、料金の方がいいかな」でふと思った (2026/10/16)
    // $コメント書く時とか意識していることは？
    // 何か書く時の絶対的に意識、「読み手になるシミュレーションをすること」
    // 読み手のことを考えて書くとは？
    // 難しいこと。なんで難しいかも難しい。
    // o 読み手が色々で持ってる知識が違うので、想像が難しい
    // o そもそも想像するという行為自体が難しい
    // 難しいことを知ることが大事、そんな簡単にわからない。
    // だからこそ、考えようとする。安易に結論づけない。
    // 些細なことに時間を使っちゃってもったいない意識も芽生えてしまうが...
    // 難しいことだから考えて当然って思えれば、遠慮なく考えるようになる。
    //
    // 考え続けないと上手にはならない。考え続けるためのコツがこれ↑
    //
    // 事業会社だと、良い意味でフランクに会話ができる環境なので、ハッピーだけど鍛えられないこともある。
    // コードのコメントは、顔の見えない、知らない人と会話しているに等しい。
    // 仕事環境自体は良いので、ただ得られないことがあることを知って、何かしら別のことで得ること大事。
    // つまみぐいでも成長するための工夫はできる。(月1のテニススクールの例を)

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    // #1on1: 今度はpublicメソッドの名前を変えるかどうか？ (2026/10/01) 
    // A. 変えない
    // B. 変える
    // C. 古いメソッドを非推奨にして、新しいメソッドを追加
    //
    // done okamura ここ(javatry)では、数が少ないので変えちゃって良いかなと by jflute (2026/10/01)
    // ただ変えるという時に気をつけること。
    // ニュアンスが変わってしまうのでそこに注意。
    //
    // 以前: OneDayのquantityを戻してたけど、OneDayしかなかったので、
    // OneDayというニュアンスが弱かった。
    // getQuantity()が、OneDayの在庫を示してたのか、TicketBoothの(total)在庫を示してたのか、
    // そこが曖昧になっていた可能性も考えられる。(呼び出し側がどういうつもりで呼んでたのか？)
    // 以前は、そこが曖昧でも辻褄が合ってた。OneDayしかないから。
    //
    public int getOneDayPassportQuantity() {
        return oneDayPassportQuantity;
    }

    public int getTwoDayPassportQuantity() {
        return twoDayPassportQuantity;
    }

    public Integer getSalesProceeds() {
        return salesProceeds;
    }
}
