/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raviudit.vendingmachine.service;

import com.raviudit.vendingmachine.dao.VendingMachineDAO;
import com.raviudit.vendingmachine.dao.VendingMachineDAOException;
import com.raviudit.vendingmachine.dto.Vendable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author raviu
 */

/*
    ** Function Name: VendingMachineDAOStubImpl
    ** Type: Stub
    ** Purpose: used for tests in VendingMachineServiceLayerImplTest 
    */  
public class VendingMachineDAOStubImpl implements VendingMachineDAO{
    
    public Vendable testVendable;
    
    public VendingMachineDAOStubImpl(){
        
        String testItem = "Pepsi";
        testVendable = new Vendable(testItem);
        testVendable.setItemPrice("1.00");
        testVendable.setMaxStock(20);
        testVendable.setCurrentStock(20);
    }
    
    public VendingMachineDAOStubImpl(Vendable testingVendable){
        this.testVendable = testingVendable;
    }
    
    @Override 
    public Vendable updateVendables(String vendableName, Vendable vendable) throws VendingMachineDAOException{
        
        if (vendableName.equals(testVendable.getItemName())){
            return testVendable;
        } else {
            return null;
        }
    }
    
    @Override
    public List<Vendable> getAllVendables() throws VendingMachineDAOException{
        
        List<Vendable> stockList = new ArrayList<>();
        stockList.add(testVendable);
        return stockList;
    }
    
    @Override
    public Vendable getVendable(String vendableName) throws VendingMachineDAOException{
        
        
        
        if(vendableName.equals(testVendable.getItemName())){
            return testVendable;
        } else {
            return null;
        }
    }
    
    @Override
    public void vendVendable(Vendable vendable){
        //nothing
        
    }
}
