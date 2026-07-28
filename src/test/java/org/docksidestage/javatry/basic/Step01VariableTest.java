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
package org.docksidestage.javatry.basic;

import java.math.BigDecimal;

import org.docksidestage.unit.PlainTestCase;

/**
 * The test of variable. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author kazuki-okamura-biz
 */
public class Step01VariableTest extends PlainTestCase {

    // ===================================================================================
    //                                                                      Local Variable
    //                                                                      ==============
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_variable_basic() { // example, so begin from the next method
        String sea = "mystic";
        log(sea); // your answer? => mystic
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_initial() {
        String sea = "mystic";
        Integer land = 8;
        String piari = null;
        String dstore = "mai";
        sea = sea + land + piari + ":" + dstore;
        log(sea); // your answer? => mystic8null:mai
        // javaでは数字(数値)を文字列に足すと自動的に(数字)文字列に変換される？

        // #1on1: その通り、どのデータ型も文字列と+で連結すると、全部文字列に引きずられる (2026/07/28)
        // Integerは数字になるし、LocalDateは日付表現文字列になるし。
        // 内部的には、toString()というメソッドが呼ばれます。
        // 言葉の使い方: 数値と数字の違い、Integerは数値、その文字は数字 e.g. "123"
        // #1on1: "null" もプログラミング言語の決め (2026/07/28)
        // エラーになる言語もあったり、空文字になる言語もあったり(C#)。
        // 些細なことでも違いがあるからにはメリデメ:
        //
        // "null" という文字列になるデメリット:
        // o データとしてないのに "null" というデータになっちゃう by おかむらさん
        //   (エラーにならないことに対してのデメリット)
        //
        // "null" という文字列になるメリット:
        // o エラーにならない、処理がそこで終わらない、ある程度動く by おかむらさん
        //   (それが良いという場面であればメリット)
        //
        // こういうことを考えること自体が大事。
        // A or B を考える習慣。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_reassigned_basic() {
        String sea = "mystic"; // 1インスタンス(mystic) (1丁目1番地)
        String land = "oneman"; // 2インスタンス(oneman) (2丁目2番地)
        sea = land; // seaの1丁目1番地を消しゴムで消して、2丁目2番地って書き直した
        land = land + "'s dreams"; // 3('s dreams), 4(oneman's dreams)
        log(sea); // your answer? => oneman's dreams
        // oneman
        // log(land)だと思い込んでしまった。ちゃんと読みましょう
        // #1on1: はっはっは (2026/07/28)
        // いい教訓ですね。
        // 変数とインスタンスのお話。
        //
        // インスタンスとは？
        // # オブジェクトではないけど...もの？変数とも違うけど...
        // 一軒家の例。インスタンスフォーカスの大切さ。
        //
        // 変数とは？
        // # 何かしらの値を格納しておくもの
        // オブジェクト型の場合は、アドレス(参照)が入っている。
        // 変数とインスタンスは、必ずしも1:1とは限らず、n:1になることもある。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_reassigned_int() {
        int sea = 94;
        int land = 415;
        sea = land;
        land++;
        log(sea); // your answer? => 415
        // #1on1: プリミティヴ型の補足 (2026/07/28)
        // 値そのものが変数に入っていると考えていい。
        // 言語によっては、intって書いてオブジェクトの場合もある。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_reassigned_BigDecimal() {
        BigDecimal sea = new BigDecimal(94);
        BigDecimal land = new BigDecimal(415);
        sea = land;
        sea = land.add(new BigDecimal(1));
        sea.add(new BigDecimal(1));
        log(sea); // your answer? => 417
        // 416
        // sea.add(new BigDecimal(1))で値は変わらない？
        // sea.add によって417という計算結果が返されるが、元の値は変更しない
        // #1on1: BigDecimalのadd()は戻すスタイル (2026/07/28)
        // add()のコードリーディングしてみた。
        // #1on1: 構造だけに注目して、今知りたいことを局所的に追って知っていく読み方。 (2026/07/28)
        // TODO jflute 次回1on1にて、immutableのお話 (2026/07/28)
    }

    // ===================================================================================
    //                                                                   Instance Variable
    //                                                                   =================
    private String instanceBroadway;
    private int instanceDockside;
    private Integer instanceHangar;
    private String instanceMagiclamp;

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_instance_variable_default_String() {
        String sea = instanceBroadway;
        log(sea); // your answer? => instanceBroadway
        // null
        // instanceBroadwayは文字列じゃなくて変数。まだ値はセットされていない
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_instance_variable_default_int() {
        int sea = instanceDockside;
        log(sea); // your answer? => 0
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_instance_variable_default_Integer() {
        Integer sea = instanceHangar;
        log(sea); // your answer? => 0
        // null
        // Integerの初期値はnull?
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_instance_variable_via_method() {
        instanceBroadway = "bbb";
        instanceMagiclamp = "magician";
        helpInstanceVariableViaMethod(instanceMagiclamp);
        String sea = instanceBroadway + "|" + instanceDockside + "|" + instanceHangar + "|" + instanceMagiclamp;
        log(sea); // your answer? => bigband|1|null|magician
        // #1on1: 引数変数のおさらい (2026/07/28)
        // C言語のときの感覚で値は変わらないと思った by おかむらさん
        // 箱自体が相手にわかることはない、値を受け渡ししている。
        // 厳密には、アドレスが渡っただけ。
    }

    private void helpInstanceVariableViaMethod(String instanceMagiclamp) {
        instanceBroadway = "bigband";
        ++instanceDockside;
        instanceMagiclamp = "burn";
    }

    // ===================================================================================
    //                                                                     Method Argument
    //                                                                     ===============
    // -----------------------------------------------------
    //                                 Immutable Method-call
    //                                 ---------------------
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_method_argument_immutable_methodcall() {
        String sea = "harbor";
        int land = 415;
        helpMethodArgumentImmutableMethodcall(sea, land);
        log(sea); // your answer? => harbor
        // 引数で渡したら値がコピーされるので、もとの値は変わらない
    }

    private void helpMethodArgumentImmutableMethodcall(String sea, int land) {
        ++land;
        String landStr = String.valueOf(land); // is "416"
        sea.concat(landStr);
    }

    // -----------------------------------------------------
    //                                   Mutable Method-call
    //                                   -------------------
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_method_argument_mutable_methodcall() {
        StringBuilder sea = new StringBuilder("harbor");
        int land = 415;
        helpMethodArgumentMethodcall(sea, land);
        log(sea); // your answer? => harbor416
        // StringBuilderは操作しても新たにクラスが生成されず、元のseaに変更が加わる
    }

    private void helpMethodArgumentMethodcall(StringBuilder sea, int land) {
        ++land;
        sea.append(land);
    }

    // -----------------------------------------------------
    //                                   Variable Assignment
    //                                   -------------------
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_method_argument_variable_assignment() {
        StringBuilder sea = new StringBuilder("harbor");
        int land = 415;
        helpMethodArgumentVariable(sea, land);
        log(sea); // your answer? => harbor
        // helpMethodArgumentVariableで作成したseaは元のseaとは別物？
        // #1on1: yes (2026/07/28)
        // seaが二つあって、同じインスタンスを差し示している。n:1の関係になっている。
    }

    private void helpMethodArgumentVariable(StringBuilder sea, int land) {
        ++land;
        String seaStr = sea.toString(); // is "harbor"
        sea = new StringBuilder(seaStr).append(land);
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Define variables as followings:
     * <pre>
     * o local variable named sea typed String, initial value is "mystic"
     * o local variable named land typed Integer, initial value is null
     * o instance variable named piari typed int, without initial value
     * o show all variables by log() as comma-separated
     * </pre>
     * (変数を以下のように定義しましょう):
     * <pre>
     * o ローカル変数、名前はsea, 型はString, 初期値は "mystic"
     * o ローカル変数、名前はland, 型はInteger, 初期値は null
     * o インスタンス変数、名前はpiari, 型はint, 初期値なし
     * o すべての変数をlog()でカンマ区切りの文字列で表示
     * </pre>
     */
    public void test_variable_writing() {
        // define variables here
        String sea = "mystic";
        Integer land = null;
        int piari;
        log(sea + ',' + land + ',' + piari);
        // test_variable_writingメソッドの外に
        // private int piari;
        // を書くとエラーが消える。
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * Make your original exercise as question style about variable. <br>
     * (変数についてあなたのオリジナルの質問形式のエクササイズを作ってみましょう)
     * <pre>
     * _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
     * your question here (ここにあなたの質問を):
     * 以下で表示される結果はなにか
     * _/_/_/_/_/_/_/_/_/_/
     * </pre>
     */
    public void test_variable_yourExercise() {
        // write your code here
        BigDecimal sea = new BigDecimal(56);
        BigDecimal land = new BigDecimal(99);
        sea = land.add(new BigDecimal(1));
        sea.add(new BigDecimal(1));
        land = sea.add(new BigDecimal(1));
        log(sea + "," + land);
    }
}
