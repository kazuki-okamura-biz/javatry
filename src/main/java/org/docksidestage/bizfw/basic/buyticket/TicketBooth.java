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

// TODO okamura author追加お願いします by jflute (2026/10/01)
/**
 * @author jflute
 */
public class TicketBooth {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final int MAX_QUANTITY = 10;
    private static final int ONE_DAY_PRICE = 7400; // when 2019/06/15
    private static final int TWO_DAY_PRICE = 13200;

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    // #1on1: 在庫を統一する？分ける？ (2026/10/01)
    // $別物なら分けた方がいいかな？
    // 統一の場合は、白紙の紙があって、oneDay, twoDayその場で書くイメージ。
    // 分ける場合は、最初からoneDay, twoDayが10毎ずつあるイメージ。
    // TODO okamura 既存の "quantity" をどうするか？ by jflute (2026/10/01)
    // 省略されたquantityをデフォルトのシンプルなoneDayと捉えることもできるかもだけど...
    // totalニュアンスで勘違いする人もいそう。
    // なので、無難にするのであれば、oneDayPassportQuantity ってした方が良いかなと。
    // もう、新しい仕様が追加されたので、クラス全体のバランスが変わったわけなので、
    // 既存の変数名も遠慮なく直して良い。
    private int quantity = MAX_QUANTITY;
    private int twoDayPassportQuantity = MAX_QUANTITY;
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
    /**
     * Buy one-day passport, method for park guest.
     * @param handedMoney The money (amount) handed over from park guest. (NotNull, NotMinus)
     * @throws TicketSoldOutException When ticket in booth is sold out.
     * @throws TicketShortMoneyException When the specified money is short for purchase.
     */
    public void buyOneDayPassport(Integer handedMoney) {
        // TODO okamura 修行++: quantity 2回登場するのどうにかしたい by jflute (2026/10/01)
        buyPassport(handedMoney, quantity, ONE_DAY_PRICE);
        --quantity;
    }

    // TODO okamura JavaDocコメントも入れておいてください (日本語の方で良い) by jflute (2026/10/01)
    public int buyTwoDayPassport(Integer handedMoney) {
        buyPassport(handedMoney, twoDayPassportQuantity, TWO_DAY_PRICE);
        --twoDayPassportQuantity;
        return handedMoney - TWO_DAY_PRICE;
    }

    // TODO okamura publicなbuyとprivateなbuyを区分けするテクニックがある by jflute (2026/10/01)
    // (補完のときや会話の時に紛れるのを避けるために)
    // e.g. doBuyPassport → 実処理しますよ感
    private void buyPassport(Integer handedMoney, int quantity, int price) {
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

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    // #1on1: 今度はpublicメソッドの名前を変えるかどうか？ (2026/10/01) 
    // A. 変えない
    // B. 変える
    // C. 古いメソッドを非推奨にして、新しいメソッドを追加
    //
    // TODO okamura ここ(javatry)では、数が少ないので変えちゃって良いかなと by jflute (2026/10/01)
    // ただ変えるという時に気をつけること。
    // ニュアンスが変わってしまうのでそこに注意。
    //
    // 以前: OneDayのquantityを戻してたけど、OneDayしかなかったので、
    // OneDayというニュアンスが弱かった。
    // getQuantity()が、OneDayの在庫を示してたのか、TicketBoothの(total)在庫を示してたのか、
    // そこが曖昧になっていた可能性も考えられる。(呼び出し側がどういうつもりで呼んでたのか？)
    // 以前は、そこが曖昧でも辻褄が合ってた。OneDayしかないから。
    //
    public int getQuantity() {
        return quantity;
    }

    public int getTwoDayPassportQuantity() {
        return twoDayPassportQuantity;
    }

    public Integer getSalesProceeds() {
        return salesProceeds;
    }
}
