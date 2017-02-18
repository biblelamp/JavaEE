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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Represents a bidder
 */
@Entity
@Table(name="BIDDERS")
public class Bidder extends User implements Serializable {

    /**
     * Unique identifier for the bidder
     */
    @Id
    @GeneratedValue
    @Column(name="USER_ID")
    private Long bidderId;

    /**
     * credit rating
     */
    private Long creditRating;

    /**
     * Creates a bidder
     */
    public Bidder() {
        // Constructs a new bidder
    }

    /**
     * Creates a new bidder with the initial credit rating provided
     * @param firstName - first name
     * @param lastName - last name
     * @param creditRating - credit rating
     */
    public Bidder(String firstName, String lastName, Long creditRating) {
        super(firstName,lastName);
        this.creditRating = creditRating;
    }

    /**
     * Creates a new bidder object
     * @param creditRating - credit rating
     */
    public Bidder(Long creditRating) {
        this.creditRating = creditRating;
    }

    /**
     * Returns the id of the bidder
     * @return bidder id
     */
    public Long getBidderId() {
        return bidderId;
    }

    /**
     * Creates a new credit rating
     * @return credit rating
     */
    public Long getCreditRating() {
        return creditRating;
    }

    /**
     * Sets the credit rating
     * @param creditRating - credit rating
     */
    public void setCreditRating(Long creditRating) {
        this.creditRating = creditRating;
    }
}
