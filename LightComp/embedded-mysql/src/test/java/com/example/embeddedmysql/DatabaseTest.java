package com.example.embeddedmysql;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class DatabaseTest {

    @Resource
    private EntityManager entityManager;

    @Test
    @EnabledOnOs({ OS.WINDOWS })
    public void testDatabase() {
        Query query = entityManager.createNativeQuery("SELECT 1");
        assertEquals(BigInteger.valueOf(1L), query.getSingleResult());
    }

    @Test
    @EnabledOnJre({ JRE.JAVA_8 })
    public void testOnlyForJava8() {
        assertEquals(true, true);
    }

}