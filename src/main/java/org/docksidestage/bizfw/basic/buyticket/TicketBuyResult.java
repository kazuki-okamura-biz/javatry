// done okamura ↑なんかある by jflute (2026/10/16)

package org.docksidestage.bizfw.basic.buyticket;

// done okamura 空行、JavaDoc直下はあまり空行入れないので削除でOK by jflute (2026/10/16)
// JavaDocは対象が決まっているので、その対象行とくっついていた方がわかりやすい。
/**
 * @author kazuki-okamura-biz
 */
public class TicketBuyResult {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final Ticket ticket;
    private final int change;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public TicketBuyResult(Ticket ticket, int change) {
        this.ticket = ticket;
        this.change = change;
    }

    // done okamura タグコメントの直下、他のだと空行開けてないので削除で by jflute (2026/10/16)
    // #1on1: 空行にこだわる理由 (2026/10/16)
    // 大事な空行を目立たせるために、意味のない空行はない状態にしておきたい。
    // LikeSearchOptionのインスタンス変数のところの例で説明。
    // 意味を持ってる空行、意味を持ってない空行。意味を持ってる空行の足を引っ張らないように。
    // よもやま: タグコメントなんで右側話
    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public Ticket getTicket() {
        return ticket;
    }

    public int getChange() {
        return change;
    }
}
