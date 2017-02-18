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
package com.actionbazaar.buslogic;

import com.actionbazaar.persistence.Bidder;
import com.actionbazaar.persistence.Billing;
import com.actionbazaar.persistence.Item;
import com.actionbazaar.persistence.Order;
import com.actionbazaar.persistence.OrderStatus;
import com.actionbazaar.persistence.Shipping;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Asynchronous;
import javax.ejb.Remove;
import javax.ejb.Stateful;

/**
 * Processes orders.
 */
@Stateful
public class OrderProcessorBean implements OrderProcessor {

    /**
     * Bidder
     */
    private Bidder bidder;
    /**
     * Item being bid on
     */
    private Item item;
    /**
     * Shipping information
     */
    private Shipping shipping;
    /**
     * Available shipping choices
     */
    private List<Shipping> shippingChoices = new LinkedList<Shipping>();
    /**
     * Billing information
     */
    private Billing billing;
    /**
     * List of billing choices
     */
    private List<Billing> billingChoices = new LinkedList<Billing>();

    /**
     * Creates a new DefaultOrderProcessor instance.
     */
    public OrderProcessorBean() {
        billingChoices.add(new Billing());
        shippingChoices.add(new Shipping());
    }

    /**
     * Sets the bidder
     * @param bidder - bidder
     */
    @Override
    public void setBidder(Bidder bidder) {
        this.bidder = bidder;
        this.shippingChoices = getShippingHistory(bidder);
        this.billingChoices = getBillingHistory(bidder);
    }

    /**
     * Sets the item
     * @param item - item
     */
    @Override
    public void setItem(Item item) {
        this.item = item;
        this.shippingChoices = filterShippingChoices(shippingChoices, item);
        this.billingChoices = filterBillingChoices(billingChoices, item);
    }

    /**
     * Returns the shipping options
     * @return shipping options
     */
    @Override
    public List<Shipping> getShippingChoices() {
        return shippingChoices;
    }

    /**
     * Sets the shipping option
     * @param shipping - shipping option
     */
    @Override
    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
        updateShippingHistory(bidder, shipping);
        shipping.setCost(calculateShippingCost(shipping, item));
    }

    /**
     * Returns the shipping option
     * @return shipping option
     */
    @Override
    public Shipping getShipping() {
        return shipping;
    }

    /**
     * Returns the billing choices
     * @return billing choices
     */
    @Override
    public List<Billing> getBillingChoices() {
        return billingChoices;
    }

    /**
     * Sets the billing information
     * @param billing - billing information
     */
    @Override
    public void setBilling(Billing billing) {
        this.billing = billing;
        updateBillingHistory(bidder, billing);
    }

    /**
     * Places an order
     */
    @Asynchronous
    @Remove
    @Override
    public void placeOrder() {
        Order order = new Order();
        order.setBidder(bidder);
        order.setItem(item);
        order.setShipping(shipping);
        order.setBilling(billing);
        try {
            bill(order);
            notifyBillingSuccess(order);
            order.setStatus(OrderStatus.COMPLETE);
        } catch (BillingException be) {
            notifyBillingFailure(be, order);
            order.setStatus(OrderStatus.BILLING_FAILED);
        } finally {
            saveOrder(order);
        }
    }

    /**
     * Returns the shipping history for the bidder
     * @param bidder - bidder
     * @return List of shipments
     */
    @Override
    public List<Shipping> getShippingHistory(Bidder bidder) {
        List<Shipping> shippingHistory = new LinkedList<Shipping>();
        shippingHistory.add(new Shipping());
        return shippingHistory;
    }

    /**
     * Returns the billing history
     * @param bidder - bidder
     * @return bidder
     */
    @Override
    public List<Billing> getBillingHistory(Bidder bidder) {
        List<Billing> history = new LinkedList<Billing>();
        history.add(new Billing());
        return history;
    }

    /**
     * Updates the billing history for a bidder
     * @param bidder - bidder
     * @param billing - billing information
     */
    @Override
    public void updateBillingHistory(Bidder bidder, Billing billing) {
    }

    /**
     * Bills an order
     * @param order - order to be billed
     * @throws BillingException - thrown if the order cannot be billed
     */
    @Override
    public void bill(Order order) throws BillingException {
        throw new BillingException();
    }

    /**
     * Sends out notification of billing success
     * @param order - order
     */
    @Override
    public void notifyBillingSuccess(Order order) {
    }

    /**
     * Saves an order
     * @param order - order to be saved
     */
    @Override
    public void saveOrder(Order order) {
    }

    /**
     * Sends out a notification of billing failure
     * @param be - billing exception that was generated
     * @param order - order on which billing failed
     */
    @Override
    public void notifyBillingFailure(BillingException be, Order order) {
    }

    /**
     * Updates the shipping history
     * @param bidder - bidder
     * @param shipping - shipping
     */
    @Override
    public void updateShippingHistory(Bidder bidder, Shipping shipping) {
    }

    /**
     * Calculates the shipping code
     * @param shipping - shipping
     * @param item - item
     */
    @Override
    public double calculateShippingCost(Shipping shipping, Item item) {
        return 0;
    }

    /**
     * Filters the shipping choices
     * @param shippingChoices - shipping choices
     * @param item - item
     * @return List of shipping choices
     */
    @Override
    public List<Shipping> filterShippingChoices(List<Shipping> shippingChoices, Item item) {
        return shippingChoices;
    }

    /**
     * Filters the billing choices
     * @param billingChoices - billing choices
     * @param item - item
     * @return List of billings
     */
    @Override
    public List<Billing> filterBillingChoices(List<Billing> billingChoices, Item item) {
        return billingChoices;
    }

    /**
     * Returns the bidder
     * @return bidder
     */
    @Override
    public Bidder getBidder() {
        return bidder;
    }

    /**
     * Returns the item being processed
     * @return item
     */
    @Override
    public Item getItem() {
        return item;
    }

    /**
     * Sets the shipping choices
     * @param shippingChoices - shipping choices
     */
    @Override
    public void setShippingChoices(List<Shipping> shippingChoices) {
        this.shippingChoices = shippingChoices;
    }

    /**
     * Sets the billing choices
     * @param billingChoices - billing choices
     */
    @Override
    public void setBillingChoices(List<Billing> billingChoices) {
        this.billingChoices = billingChoices;
    }

    /**
     * Returns the selected billing choice
     * @return billing choice
     */
    @Override
    public Billing getBilling() {
        return billing;
    }
}
