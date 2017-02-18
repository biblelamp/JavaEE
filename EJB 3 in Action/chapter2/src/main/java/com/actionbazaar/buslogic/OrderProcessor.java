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
import com.actionbazaar.persistence.Shipping;
import java.util.List;
import javax.ejb.Local;

/**
 * Local order processor interface
 */
@Local
public interface OrderProcessor {

   /**
     * Sets the bidder
     * @param bidder - bidder
     */
    public void setBidder(Bidder bidder);
    /**
     * Sets the item
     * @param item - item
     */
    public void setItem(Item item);

    /**
     * Returns the shipping options
     * @return shipping options
     */
    public List<Shipping> getShippingChoices();

    /**
     * Sets the shipping option
     * @param shipping - shipping option
     */
    public void setShipping(Shipping shipping);

    /**
     * Returns the shipping option
     * @return shipping option
     */
    public Shipping getShipping();

    /**
     * Returns the billing choices
     * @return billing choices
     */
    public List<Billing> getBillingChoices();

    /**
     * Sets the billing information
     * @param billing - billing information
     */
    public void setBilling(Billing billing);

    /**
     * Places an order
     */
    public void placeOrder();

    /**
     * Returns the shipping history for the bidder
     * @param bidder - bidder
     * @return List of shipments
     */
    public List<Shipping> getShippingHistory(Bidder bidder);

    /**
     * Returns the billing history
     * @param bidder - bidder
     * @return bidder
     */
    public List<Billing> getBillingHistory(Bidder bidder);

    /**
     * Updates the billing history for a bidder
     * @param bidder - bidder
     * @param billing - billing information
     */
    public void updateBillingHistory(Bidder bidder, Billing billing);
    /**
     * Bills an order
     * @param order - order to be billed
     * @throws BillingException - thrown if the order cannot be billed
     */
    public void bill(Order order) throws BillingException;

    /**
     * Sends out notification of billing success
     * @param order - order
     */
    public void notifyBillingSuccess(Order order);

    /**
     * Saves an order
     * @param order - order to be saved
     */
    public void saveOrder(Order order);

    /**
     * Sends out a notification of billing failure
     * @param be - billing exception that was generated
     * @param order - order on which billing failed
     */
    public void notifyBillingFailure(BillingException be, Order order);

    /**
     * Updates the shipping history
     * @param bidder - bidder
     * @param shipping - shipping
     */
    public void updateShippingHistory(Bidder bidder, Shipping shipping);

    /**
     * Calculates the shipping code
     * @param shipping - shipping
     * @param item - item
     */
    public double calculateShippingCost(Shipping shipping, Item item);

    /**
     * Filters the shipping choices
     * @param shippingChoices - shipping choices
     * @param item - item
     * @return List of shipping choices
     */
    public List<Shipping> filterShippingChoices(List<Shipping> shippingChoices, Item item);

    /**
     * Filters the billing choices
     * @param billingChoices - billing choices
     * @param item - item
     * @return List of billings
     */
    public List<Billing> filterBillingChoices(List<Billing> billingChoices, Item item);

    /**
     * Returns the bidder
     * @return bidder
     */
    public Bidder getBidder();

    /**
     * Returns the item being processed
     * @return item
     */
    public Item getItem();

    /**
     * Sets the shipping choices
     * @param shippingChoices - shipping choices
     */
    public void setShippingChoices(List<Shipping> shippingChoices);

    /**
     * Sets the billing choices
     * @param billingChoices - billing choices
     */
    public void setBillingChoices(List<Billing> billingChoices);

    /**
     * Returns the selected billing choice
     * @return billing choice
     */
    public Billing getBilling();
}
