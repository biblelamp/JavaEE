/**
 *  EJB 3 in Action
 *  Book: http://manning.com/panda2/
 *  Code: http://code.google.com/p/action-bazaar/
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.actionbazaar.persistence;

import java.io.Serializable;

/**
 * Represents an Order
 */
public class Order implements Serializable {

    /**
     * Person that bid
     */
    private Bidder bidder;

    /**
     * Item on that was bid on
     */
    private Item item;

    /**
     * Shipping address
     */
    private Shipping shipping;

    /**
     * Billing address
     */
    private Billing billing;

    /**
     * Order status
     */
    private OrderStatus orderStatus;

    public Bidder getBidder() {
        return bidder;
    }

    public void setBidder(Bidder bidder) {
        this.bidder = bidder;
    }

    public Billing getBilling() {
        return billing;
    }

    public void setBilling(Billing billing) {
        this.billing = billing;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Shipping getShipping() {
        return shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    /**
     * Sets the order status
     * @param orderStatus - order status
     */
    public void setStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * Returns the order status
     * @return order status
     */
    public OrderStatus getStatus() {
        return orderStatus;
    }

}
