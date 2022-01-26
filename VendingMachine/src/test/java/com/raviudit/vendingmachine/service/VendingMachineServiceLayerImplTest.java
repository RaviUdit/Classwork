/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raviudit.vendingmachine.service;

import com.raviudit.vendingmachine.dao.VendingMachineAuditDAO;
import com.raviudit.vendingmachine.dao.VendingMachineDAO;
import com.raviudit.vendingmachine.dao.VendingMachineDAOException;
import com.raviudit.vendingmachine.dto.Vendable;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author raviu
 */
public class VendingMachineServiceLayerImplTest {
    
    private VendingMachineServiceLayer service;
    
    public VendingMachineServiceLayerImplTest() {
        VendingMachineDAO dao = new VendingMachineDAOStubImpl();
        VendingMachineAuditDAO auditDAO = new VendingMachineAuditDAOStubImpl();
        VendingMachineChange change = new VendingMachineChange();
        
        service = new VendingMachineServiceLayerImpl(dao, auditDAO, change);
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testUpdateVendables() throws Exception{
        
        //Arrange
        
        //String itemName = "Pepsi";
        //Vendable testVendable = new Vendable(itemName);
        //testVendable.setItemPrice("1.00");
        //testVendable.setMaxStock(20);
        //testVendable.setCurrentStock(20);
        
        //Act
        
        
        //Assert
    }
    
    @Test
    public void testVendVendableValid() throws Exception{
        
        //Arrange
        String testCash = "2.00";
        
        String testItem = "Pepsi";
        Vendable queryVendable = new Vendable(testItem);
        queryVendable.setItemPrice("1.00");
        queryVendable.setMaxStock(20);
        queryVendable.setCurrentStock(20);
        
        //Act
        //BigDecimal queryCash = new BigDecimal(testCash);
        try{
            service.vendVendable(queryVendable, testCash);
        } catch ( VendingMachineInsufficientFundsException |
                VendingMachineItemNotInStockException |
                VendingMachineDAOException e){
        
        // Assert
            fail(" Vendable is valid. No exception should be thrown.");
        }
    }
    
    @Test
    public void testVendVendableFailMoney() throws Exception{
        
        //Arrange
        String testCash = "0.75";
        
        String testItem = "Pepsi";
        Vendable queryVendable = new Vendable(testItem);
        queryVendable.setItemPrice("1.00");
        queryVendable.setMaxStock(20);
        queryVendable.setCurrentStock(20);
        
        //Act
        //BigDecimal queryCash = new BigDecimal(testCash);
        try{
            service.vendVendable(queryVendable, testCash);
        } catch ( VendingMachineItemNotInStockException |
                VendingMachineDAOException e){
        
        // Assert
            fail(" Vendable is valid. No exception should be thrown.");
        } catch (VendingMachineInsufficientFundsException e){
            return;
        }
    }
    
    @Test
    public void testVendVendableFailNotInStock() throws Exception{
    
        //Arrange
        String testCash = "2.00";
        
        String testItem = "Pepsi";
        Vendable queryVendable = new Vendable(testItem);
        queryVendable.setItemPrice("1.00");
        queryVendable.setMaxStock(20);
        queryVendable.setCurrentStock(0);
        
        //Act
        //BigDecimal queryCash = new BigDecimal(testCash);
        try{
            service.vendVendable(queryVendable, testCash);
        } catch ( VendingMachineInsufficientFundsException |
                VendingMachineDAOException e){
        
        // Assert
            fail(" Cash is valid. No exception should be thrown.");
        } catch (VendingMachineItemNotInStockException e){
            return;
        }
    }
    
    // Test getVendable
    @Test
    public void testGetVendableValid() throws Exception{
        
        // Arrange
        List<String> queryList = service.getAllVendables().stream().map((p)->p.getItemName()).collect(Collectors.toList());
        
        String testVendableName = "Pepsi";
        
        // Act and Assert
        assertTrue(queryList.contains(testVendableName), "There is an item named Pepsi in this list.");
        
    }

    // Test isThatMoney
    @Test
    public void testIsThatMoneyValid() throws Exception{
        
        //Arrange
        String isMoney = "1.00";
        
        //Act
        try{
            service.isThatMoney(isMoney);
        } catch (VendingMachineIsNotMoneyException e){
        
        //Assert    
            fail("Money was valid, no exception should have been thrown.");
        }
        
    }
    
    @Test
    public void testIsThatMoneyFail() throws Exception{
        
        //Arrange
        String notMoney = "Cheese";
        
        //Act
        try{
            service.isThatMoney(notMoney);
            fail("Expected exception was not thrown.");
        } catch (VendingMachineIsNotMoneyException e){
        //Assert    
            return;
        }
        
    }
    
}
