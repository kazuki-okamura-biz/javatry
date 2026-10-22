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
    private final int displayPrice; // written on ticket, park guest can watch this
    private int availableCount; // true means this ticket is unavailable

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public Ticket(int displayPrice, int availableCount) {
        this.displayPrice   = displayPrice;
        this.availableCount = availableCount;
    }

    // ===================================================================================
    //                                                                             In Park
    //                                                                             =======
    // doInParkの想定仕様が不明...
    // twoDayだったら2回使い切ったらIn Park判定？
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

    public boolean isAlreadyIn() {
        return availableCount==0;
    }
}
