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
 * Shipping information
 */
public class Shipping implements Serializable {

    /**
     * Shipping cost
     */
    protected double cost;

    /**
     * Cost of the shipping
     * @param cost - cost
     */
    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
     * Returns the cost of the shipping
     * @return shipping cost
     */
    public double getCost() {
        return cost;
    }


}
