/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.actionbazaar.web;

import com.actionbazaar.buslogic.BidService;
import com.actionbazaar.persistence.Bid;
import com.actionbazaar.persistence.Bidder;
import com.actionbazaar.persistence.Item;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Bid Manager - handles the add bid form.
 */
@Named
public class BidManager {

    @EJB
    private BidService bidService;

    @EJB
    private Bidder user;

    @Inject
    private Item item;
    private Bid bid = new Bid();

    @Produces
    @Named
    @RequestScoped
    public Bid getBid() {
        return bid;
    }

    public String placeBid() {
        bid.setBidder(user);
        bid.setItem(item);
        // Incomplete bidService.addBid(bid);
        return "bid_confirm.xhtml";
    }
}
