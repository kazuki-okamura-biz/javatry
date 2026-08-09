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

import java.util.ArrayList;
import java.util.List;

import org.docksidestage.unit.PlainTestCase;

/**
 * The test of if-for. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author your_name_here
 */
public class Step02IfForTest extends PlainTestCase {

    // ===================================================================================
    //                                                                        if Statement
    //                                                                        ============
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_if_basic() { // example, so begin from the next method
        int sea = 904;
        if (sea >= 904) {
            sea = 2001;
        }
        log(sea); // your answer? => 2001
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_if_else_basic() {
        int sea = 904;
        if (sea > 904) {
            sea = 2001;
        } else {
            sea = 7;
        }
        log(sea); // your answer? => 7
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_if_elseif_basic() {
        int sea = 904;
        if (sea > 904) {
            sea = 2001;
        } else if (sea >= 904) {
            sea = 7;
        } else if (sea >= 903) {
            sea = 8;
        } else {
            sea = 9;
        }
        log(sea); // your answer? => 7
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_if_elseif_nested() {
        boolean land = false;
        int sea = 904;
        if (sea > 904) {
            sea = 2001;
            sea = sea++ * 2;
        } else if (land && sea >= 904) {
            sea = 7;
            sea = ++sea * 2;
        } else if (sea >= 903 || land) {
            if (sea % 2 == 0) {
                sea = sea++ * 2; //1809? => 1808
            }
            log(sea);
            if (!land) {
                land = true;
            } else if (sea <= 903) {
                sea++;
            }
            if (sea < 1810) {
                sea = 8;
            }
        } else if (sea == 8) {
            sea++;
            land = false;
        } else {
            sea = 9;
        }
        if (sea >= 9 || (sea > 7 && sea < 9)) {
            sea--;
            if (sea % 2 == 1) {
                sea++;
            }
        }
        if (land) {
            sea = 10;
        }
        log(sea); // your answer? => 10
        // sea = sea++ * 2;　について、sea++ * 2;の計算が終わったらseaは905になるが、計算結果が代入されて1808になる？
        // sea++の場合、計算には元のseaの値が使われるのは知ってたけど、どのタイミングでインクリメントされるかが曖昧だった
        // TODO okamura [ふぉろー] この挙動、めちゃくちゃ直感的じゃないですよね笑 by jflute (2026/08/09)
        // 演算子の優先順位で、計算と代入よりもインクリメントが後になるので、へんてこりんになります。
        // 個人的には、こういう挙動もあって、インクリメントするときは単独行でやるのが習慣になっています。

        // TODO jflute 次回1on1にて、漠然読みのお話をする予定 (2026/08/09)
    }

    // ===================================================================================
    //                                                                       for Statement
    //                                                                       =============
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_inti_basic() {
        List<String> stageList = prepareStageList();
        String sea = null;
        for (int i = 0; i < stageList.size(); i++) {
            String stage = stageList.get(i);
            if (i == 1) {
                sea = stage;
            }
        }
        log(sea); // your answer? => dockside
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_foreach_basic() {
        List<String> stageList = prepareStageList();
        String sea = null;
        for (String stage : stageList) {
            sea = stage;
        }
        log(sea); // your answer? => magiclamp
        // for (String stage : stageList) で各ループでstageにstageListの値が順番に入る
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_foreach_continueBreak() {
        List<String> stageList = prepareStageList();
        String sea = null;
        for (String stage : stageList) {
            if (stage.startsWith("br")) {
                continue;
            }
            sea = stage;
            if (stage.contains("ga")) {
                break;
            }
        }
        log(sea); // your answer? => hangar
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_listforeach_basic() {
        List<String> stageList = prepareStageList();
        StringBuilder sb = new StringBuilder();
        stageList.forEach(stage -> {
            if (sb.length() > 0) {
                return;
            }
            if (stage.contains("i")) {
                sb.append(stage);
            }
        });
        String sea = sb.toString();
        log(sea); // your answer? => dockside
        // stageList.forEach(stage -> と for (String stage : stageList) は同じ挙動？
        // TODO okamura [ふぉろー] 基本的には同じようなループ処理をします。 by jflute (2026/08/09)
        // 良ければ、forEach()メソッドのソースコードを読んでみてください(^^。

        // TODO jflute 1on1にて、なんにせよ一緒にコード読む (2026/08/09)
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Make list containing "a" from list of prepareStageList() and show it as log by loop. (without Stream API) <br>
     * (prepareStageList()のリストから "a" が含まれているものだけのリストを作成して、それをループで回してログに表示しましょう。(Stream APIなしで))
     */
    public void test_iffor_making() {
        // write if-for here
        List<String> stageList = prepareStageList();
        List<String> ansList = new ArrayList<>();
        for (String stage : stageList) {
            if (stage.contains("a")) {
                ansList.add(stage);
            }
        }
        for (String ans : ansList) {
            log(ans);
        }
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * Change foreach statement to List's forEach() (keep result after fix) <br>
     * (foreach文をforEach()メソッドへの置き換えてみましょう (修正前と修正後で実行結果が同じになるように))
     */
    //    public void test_iffor_refactor_foreach_to_forEach() {
    //        List<String> stageList = prepareStageList();
    //        String sea = null;
    //        stageList.forEach(stage -> {
    //            if (stage.startsWith("br")) {
    //                return;
    //            }
    //            sea = stage;
    //            if (stage.contains("ga")) {
    //                break;
    //            }
    //        });
    //        log(sea); // should be same as before-fix
    //    }

    // 何も見ずに書こうとしたらここまでしか出ませんでした...
    // forEach()メソッドの中でseaの代入ができない！breakが使えない！
    // TODO okamura [いいね] 頑張ってくださりありがとうございます。 by jflute (2026/08/09)
    // できなかった理由が書いてあってわかりやすい。
    // seaの代入ができない、breakが使えないのはなぜでしょう？
    // TODO jflute 次回1on1にて、forEach()の仕組みを深掘り (2026/08/09)

    public void test_iffor_refactor_foreach_to_forEach() {
        // TODO okamura flagという目的は伝わりますが、具体的に何が入るのか？を変数に示したいところですね by jflute (2026/08/09)
        // 短いプログラムですが、変数を見るだけでもう少し直感的に理解できるようにしてみましょう。
        StringBuilder flag = new StringBuilder();
        List<String> stageList = prepareStageList();
        StringBuilder sea = new StringBuilder();
        stageList.forEach(stage -> {
            if (flag.length() > 0 || stage.startsWith("br")) {
                return;
            }
            sea.setLength(0);
            sea.append(stage);
            if (stage.contains("ga")) {
                flag.append("ga");
            }
        });
        log(sea); // should be same as before-fix
    }
    // このコードは調べたりAI使いながら自分の考えをコードにしました
    // continue->return
    // 変数の代入はできないので、StringBuilderを用いて、空にしてからappend
    // breakに相当するものは無い？

    // TODO okamura 修行++: flag変数を使わずに同じこと実現できます by jflute (2026/08/09)
    // ちょっとだけ考えてみましょう(^^。

    /**
     * Make your original exercise as question style about if-for statement. <br>
     * (if文for文についてあなたのオリジナルの質問形式のエクササイズを作ってみましょう)
     * <pre>
     * _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
     * your question here (ここにあなたの質問を): 変数seaの値は？
     * 
     * _/_/_/_/_/_/_/_/_/_/
     * </pre>
     */
    public void test_iffor_yourExercise() {
        // write your code here
        int sea = 0;
        for (int i = 0; i < 10; i++) {
            sea += 7;
            if (sea % 2 == 0) {
                continue;
            }
            sea -= 4;
            if (sea > 10) {
                break;
            }
        }
        log(sea);

        // TODO okamura [いいね] 当たった、ああ良かった by jflute (2026/08/09)
        // 絶妙に読み飛ばせるところがなくて、しっかり読み切らないといけないエクササイズですね(^^。
    }

    // ===================================================================================
    //                                                                        Small Helper
    //                                                                        ============
    private List<String> prepareStageList() {
        List<String> stageList = new ArrayList<>();
        stageList.add("broadway");
        stageList.add("dockside");
        stageList.add("hangar");
        stageList.add("magiclamp");
        return stageList;
    }
}
