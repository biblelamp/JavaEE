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

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Represents an address
 */
@Embeddable
public class Address implements java.io.Serializable {

    /**
     * Street name - first line
     */
    protected String streetName1;
    /**
     * Street name - second long
     */
    protected String streetName2;
    /**
     * City
     */
    protected String city;
    /**
     * State
     */
    protected String state;
    /**
     * Zip code
     */
    protected String zipCode;
    /**
     * Country
     */
    protected String country;

    /**
     * No arg default constructor. Required for JPA.
     */
    public Address() {
        // No arg default constructor
    }

    /**
     * Constructor
     * @param streetName1 - street line 1
     * @param streetName2 - street line 2
     * @param city - city
     * @param state - state
     * @param zipCode - zip code
     * @param country - country
     */
    public Address(String streetName1, String streetName2, String city,
            String state, String zipCode, String country) {
        this.streetName1 = streetName1;
        this.streetName2 = streetName2;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
    }

    /**
     * Returns the street - line 1
     * @return street line 1
     */
    public String getStreetName1() {
        return streetName1;
    }

    /**
     * Returns the street line 2
     * @return street line 2
     */
    public String getStreetName2() {
        return streetName2;
    }

    /**
     * Returns the city
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * Returns the state
     * @return state
     */
    @Column(name = "STATE_CODE")
    public String getState() {
        return state;
    }

    /**
     * Returns the country
     * @return country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Returns the zip code
     * @return zip code
     */
    @Column(name = "ZIP_CODE")
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Sets the street name line 1
     * @param streetName1 - line 1 street
     */
    public void setStreetName1(String streetName1) {
        this.streetName1 = streetName1;
    }

    /**
     * Sets the street name line 2
     * @param streetName2 - street name line 2
     */
    public void setStreetName2(String streetName2) {
        this.streetName1 = streetName2;
    }

    /**
     * Sets the city
     * @param city - city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Sets the state
     * @param state - state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Sets the country
     * @param country - country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Sets the zip code
     * @param zipCode - zip code
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
