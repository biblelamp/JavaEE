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
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Manages users
 * Since we are running in the same container, this interface isn't necessary for
 * EJB 3.1 unless we wanted to "hide" public methods.
 */
@Stateless
public class UserServiceBean implements UserService {

    /**
     * Persistence Context
     */
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Retrieves a bidder from the database
     * @param userId - user id
     * @return User
     */
    @Override
    public Bidder getUser(long userId) {
        return entityManager.find(Bidder.class,userId);
    }

    /**
     * Persists a user to the database
     * @param bidder - bidder
     */
    @Override
    public void createUser(Bidder bidder) {
        entityManager.persist(bidder);
    }
}
