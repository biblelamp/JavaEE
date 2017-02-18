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
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 * Represents an item on which bidders bid.
 */
@Entity
@Table(name="ITEMS")
public class Item implements Serializable {

    /**
     * Unique identifier for the item
     */
    @Id
    @GeneratedValue
    @Column(name="ITEM_ID")
    private Long itemId;

    /**
     * Name of the item
     */
    private String itemName;

    /**
     * End date of the bid
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date bidEndDate;

    /**
     * Bid start date
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date bidStartDate;

    /**
     * Created date
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date createdDate;

    /**
     * Initial price
     */
    private Double initialPrice;

    /**
     * List of bids
     */
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<Bid> bids;

    /**
     * Creates an Item
     */
    public Item() {
        // empty constructor
    }

    /**
     * Creates a new item
     * @param itemId - item id
     */
    public Item(Long itemId) {
        this.itemId = itemId;
    }

    /**
     * Creates a new item.
     * @param itemName - item name
     * @param bidStartDate - bid start date
     * @param createdDate - created date
     * @param initialPrice - initial price
     */
    public Item(String itemName, Date bidStartDate, Date createdDate, double initialPrice) {
        this.itemName = itemName;
        this.bidStartDate = bidStartDate;
        this.initialPrice = initialPrice;
    }

    /**
     * Returns an item name
     * @return item name
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Creates an item name
     * @param itemName - item name
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * Returns the bid end date
     * @return bid end date
     */
    public Date getBidEndDate() {
        return bidEndDate;
    }

    /**
     * Sets the bid end date
     * @param bidEndDate - bid end date
     */
    public void setBidEndDate(Date bidEndDate) {
        this.bidEndDate = bidEndDate;
    }

    /**
     * Returns a bid start date
     * @return bid start date
     */
    public Date getBidStartDate() {
        return bidStartDate;
    }

    /**
     * Sets the bid start date
     * @param bidStartDate - bid start date
     */
    public void setBidStartDate(Date bidStartDate) {
        this.bidStartDate = bidStartDate;
    }

    /**
     * Returns the created date
     * @return created date
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets the created date
     * @param createdDate - created date
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Sets the initial price
     * @return initial price
     */
    public Double getInitialPrice() {
        return initialPrice;
    }

    /**
     * Sets the initial price
     * @param initialPrice - initial price
     */
    public void setInitialPrice(Double initialPrice) {
        this.initialPrice = initialPrice;
    }

    /**
     * Returns the item id
     * @return item id
     */
    public Long getItemId() {
        return itemId;
    }

    /**
     * Sets the item id
     * @param itemId - item id
     */
    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    /**
     * Returns the bids
     * @return bids
     */
    public List<Bid> getBids() {
        return bids;
    }

    /**
     * Sets the bids
     * @param bids - bids
     */
    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }

    /**
     * Returns the bid
     * @param bid - bid
     * @return Bid
     */
    public Bid addBid(Bid bid) {
        getBids().add(bid);
        return bid;
    }

    /**
     * Removes the bid
     * @param bid - bid
     * @return Bid
     */
    public Bid removeBid(Bid bid) {
        getBids().remove(bid);
        return bid;
    }
}
