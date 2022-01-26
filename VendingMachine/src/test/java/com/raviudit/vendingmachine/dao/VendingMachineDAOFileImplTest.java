/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raviudit.vendingmachine.dao;

import com.raviudit.vendingmachine.dto.Vendable;
import java.io.FileWriter;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author raviu
 */
public class VendingMachineDAOFileImplTest {
    
    VendingMachineDAO testDAO;
    
    public VendingMachineDAOFileImplTest() {
    }
    
    @BeforeEach
    public void setUp() throws Exception{
        
        String testFile = "testDAO.txt";
        
        //Filewriter used to blank file.
        new FileWriter(testFile);
        testDAO = new VendingMachineDAOFileImpl(testFile);
        
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testUpdateVendablesGetVendable() throws Exception{
        
        //Create test inputs
        String itemName = "Pepsi";
        Vendable vendable = new Vendable(itemName);
        vendable.setItemPrice("1.00");
        vendable.setMaxStock(20);
        vendable.setCurrentStock(20);
        
        // Populate DAO with Test inputs
        testDAO.updateVendables(itemName, vendable);
        
        // Get vendable
        Vendable testVendable = testDAO.getVendable(itemName);
        
        // Perform Tests
        // Check Item Name
        assertEquals(vendable.getItemName(), testVendable.getItemName(), "Checking Item Name.");
        
        // Check Price
        assertEquals(vendable.getItemPrice(), testVendable.getItemPrice(), "Checking Item Price.");
        
        // Check Stock
        assertEquals(vendable.getMaxStock(), testVendable.getMaxStock(), "Checking Max Stock.");
        assertEquals(vendable.getCurrentStock(), testVendable.getCurrentStock(), "Checking Current Stock.");
        
        
    }
    
    // Test for getAllVendables
    @Test
    public void testUpdateVendableGetAllVendables() throws Exception{
        
        //Create test inputs
        String itemName = "Pepsi";
        Vendable vendable = new Vendable(itemName);
        vendable.setItemPrice("1.00");
        vendable.setMaxStock(20);
        vendable.setCurrentStock(20);
        
        itemName = "7-Up";
        Vendable vendable2 = new Vendable(itemName);
        vendable2.setItemPrice("1.00");
        vendable2.setMaxStock(20);
        vendable2.setCurrentStock(20);
        
        // Populate DAO with Test inputs
        testDAO.updateVendables(vendable.getItemName(), vendable);
        testDAO.updateVendables(vendable2.getItemName(), vendable2);
        
        // Get the Vendable List from getAllVendables.
        List<Vendable> testList = testDAO.getAllVendables();
        
        //Check List Contents
        assertNotNull(testList, "List should not be null.");
        assertEquals(2, testList.size(), "List should have 2 entries.");
        
        // Test that the testDAO contains the items.
        assertTrue(testDAO.getAllVendables().contains(vendable), "List should contain Pepsi");
        assertTrue(testDAO.getAllVendables().contains(vendable2), "List should contain 7-Up");
    }
    
    
    // Test for vendVendable
    @Test
    public void testUpdateVendablesVendVendable() throws Exception{
        
        //Create test inputs
        String itemName = "Pepsi";
        Vendable vendable = new Vendable(itemName);
        vendable.setItemPrice("1.00");
        vendable.setMaxStock(20);
        vendable.setCurrentStock(20);
        
        // Populate DAO with Test inputs
        testDAO.updateVendables(vendable.getItemName(), vendable);
        
        // Call vendVendable
        testDAO.vendVendable(vendable);
        
        // Assert
        assertEquals(20, vendable.getCurrentStock() + 1, "There should be 19 Pepsi");
    }
    
}
