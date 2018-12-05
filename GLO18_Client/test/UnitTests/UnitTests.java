/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UnitTests;

import client.Acquaintance.ILink;
import client.Acquaintance.ILogic;
import client.Link.LinkFacade;
import client.Logic.LogicFacade;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import static org.junit.Assert.*;


/**
 *
 * @author Jeppe Enevold
 */
public class UnitTests {
    
    final private static ILogic logic = new LogicFacade();
    final private static ILink link = new LinkFacade();
    
    public UnitTests() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        logic.injectLink(link);
        logic.startConnection();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testLogin() {
        String expected = "true";
        Assert.assertEquals(expected, logic.login("C111234552", "password"));
    }
    
    /*
    @Test
    public void testTransaction() {
        
    }
    */
    
}
