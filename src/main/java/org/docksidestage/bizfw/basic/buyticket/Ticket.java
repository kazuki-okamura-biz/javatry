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

/**
 * @author jflute
 */
public class Ticket {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    // TODO done okamura availableCountに変わったのに、trueの説明が書いてある by jflute (2026/10/29)
    private final int displayPrice; // written on ticket, park guest can watch this
    private int availableCount; // チケットの残り使用可能回数。0になると使用不可

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public Ticket(int displayPrice, int availableCount) {
        this.displayPrice = displayPrice;
        this.availableCount = availableCount;
    }

    // ===================================================================================
    //                                                                             In Park
    //                                                                             =======
    // doInParkの想定仕様が不明...
    // twoDayだったら2回使い切ったらIn Park判定？
    // #1on1: ↑でOKです (2026/10/29)
    public void doInPark() {
        if (availableCount == 0) {
            throw new IllegalStateException("Already in park by this ticket: displayedPrice=" + displayPrice);
        }
        --availableCount;
    }

    // ===================================================================================
    //                                                                       Night In Park
    //                                                                       =============
    public void doNightInPark(boolean isNight) {
        if (!isNight) {
            throw new IllegalStateException("The Night Ticket is only available at night");
        }
        if (availableCount == 0) {
            throw new IllegalStateException("Already in park by this ticket: displayedPrice=" + displayPrice);
        }
        --availableCount;
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getDisplayPrice() {
        return displayPrice;
    }

    // #1on1: alreadyInの概念の互換性をしっかりキープしている (2026/10/29)
    // $ちょっと迷ったけど...入れないのが大事だから
    // Good, そういう感覚良いです。実際には、元々のインスタンス変数にコメントで書いてあった。
    // よもやま: $javatryは難しくあって欲しい (2026/10/29)
    //
    // 一方で、AlreadyInという業務概念がすでに曖昧でわかりにくいので変えちゃうと言う選択肢も。
    // ただ、呼び出し側がたくさんあると修正の影響範囲が大きいので、実際にやるかはケースバイケース。
    // チームポリシーにもよる (UnitTestの充実性にもよる)
    // 残しておくなら、こういうコメントが欲しくなる。
    //  e.g. public boolean isAlreadyIn() { // 互換性のために用語を変えずに残している
    //
    // こういうコメント書ける人、ベテランでも多くはない。
    // 経験と体験の違い。
    // $通じるところあった、学びを得てるかな？目の前の業務をこなすだけになってないか？
    // 同じ体験から得られる経験値、2,3だともったいない、8,9くらい取っておきたい。
    // 8,9広げておけば、将来近い事象に出会った時に、デジャブ感を感じることができて思い出せる。
    // 
    // 8,9取る (費用対効果を高くする) にはどうするか？振り返りをするに限る。
    // 分析して深掘りして論理の線を広げていくこと。
    // 振り返り習慣、最初はKPTのようなフレームワーク、徐々に脳内で。
    //
    public boolean isAlreadyIn() {
        return availableCount == 0;
    }
}
