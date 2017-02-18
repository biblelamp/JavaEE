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

import java.util.Date;
import org.jboss.arquillian.api.Run;
import org.jboss.arquillian.api.RunModeType;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.actionbazaar.persistence.Bid;
import com.actionbazaar.persistence.Bidder;
import com.actionbazaar.persistence.Item;
import javax.ejb.EJB;
import org.jboss.arquillian.api.Deployment;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;

/**
 * This test verifies that items can be persisted and retrieved.
 */
@RunWith(Arquillian.class)
@Run(RunModeType.IN_CONTAINER)
public class ItemServiceTest {

    /**
     * Entity manager
     */
    @EJB
    private ItemService itemService;


    /**
     * Creates a deployment item.
     * @return ShrinkWrap
     */
    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(JavaArchive.class, "foo.jar").addClasses(OrderProcessor.class,
                OrderProcessorBean.class,
                ItemService.class,
                ItemServiceBean.class, Bid.class, Bidder.class, Item.class).addManifestResource("test-persistence.xml", ArchivePaths.create("persistence.xml"));
    }

    /**
     * Test persistence of item
     */
    @Test
    public void testItemPersistence() {
        Item item = new Item("Apple IIGS", new Date(), new Date(), 45.0f);
        itemService.createItem(item);
        Assert.assertNotNull(item.getItemId());
        itemService.getItem(item.getItemId());
    }
}
