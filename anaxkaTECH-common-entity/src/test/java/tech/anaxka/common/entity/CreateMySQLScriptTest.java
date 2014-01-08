package tech.anaxka.common.entity;

import javax.persistence.EntityManager;
import org.testng.annotations.Test;

import static javax.persistence.Persistence.createEntityManagerFactory;
import static org.testng.Assert.assertNotNull;

public class CreateMySQLScriptTest {
    
    public CreateMySQLScriptTest() {
    }
    
    @Test
    public void createSQLScripts() {
        final EntityManager __manager = Persistence.createEntityManagerFactory("MySQLddlPU").createEntityManager();
        Assert.assertNotNull(__manager);
        __manager.close();
    }
}
