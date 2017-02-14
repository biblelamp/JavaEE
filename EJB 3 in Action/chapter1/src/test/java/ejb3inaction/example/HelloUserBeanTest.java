/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb3inaction.example;

import javax.ejb.EJB;
import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Unit Test for the HelloUserBean
 */
@RunWith(Arquillian.class)
public class HelloUserBeanTest {

    @EJB
    private HelloUser helloUser;

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(JavaArchive.class, "foo.jar")
        .addClasses(HelloUserBean.class);
    }

    /**
     * Tests the hello message
     */
    @Test
    public void testSayHello() {
        String helloMessage = helloUser.sayHello("Curious George");
        Assert.assertEquals("Message did not match.","Hello Curious George welcome to EJB 3.1!",helloMessage);
    }
}
