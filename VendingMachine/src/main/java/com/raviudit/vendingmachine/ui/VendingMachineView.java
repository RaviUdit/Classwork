/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raviudit.vendingmachine.ui;

import com.raviudit.vendingmachine.dto.Vendable;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author raviu
 */
public class VendingMachineView {
    
    private UserIO io;
    
    public VendingMachineView(UserIO io){
        this.io = io;
        
    }
    
    //MENUS
    
    public int vendingMachineMenu(){
        
        io.print("1. Enter Money.");
        io.print("2. Exit");
        
        return io.readInt("Please select from the Listed Options", 1, 2);
    }
    
    //LIBRARY FUNCTION
    
    public void displayVendablesList(List<Vendable> vendablesList){
        
       // STREAM AND LAMBDAS
        List<Vendable> availableVendables = vendablesList.stream().filter((p)->p.getCurrentStock() >=1).collect(Collectors.toList());
        List<Vendable> unAvailableVendables = vendablesList.stream().filter((p)->p.getCurrentStock() <=0).collect(Collectors.toList());
        
        // AVAILABLE ITEMS
        io.print("--- Available Items ---");
        for (Vendable vendableStock : availableVendables){
            String vendableInfo = String.format("%s : %s ", 
                                                vendableStock.getItemName(),
                                                "$" + vendableStock.getItemPrice());
            
            io.print(vendableInfo);
        }
        
        // UNAVAILABLE ITEMS
        io.print("--- Unavailable Items ---");
        for (Vendable vendableStock : unAvailableVendables){
            String vendableInfo = String.format("%s : %s ", 
                                                vendableStock.getItemName(),
                                                "$" + vendableStock.getItemPrice());
            
            io.print(vendableInfo);
        }
    }
    
    public String getVendableName(){
        return io.readString("Please enter the Name of the item you want.");
    }
    
    public String getUserCash(){
        return io.readString("Please enter your money into the machine.");
    }
    
/** ------ MOVED TO DAO   
 * 
 * public void vendVendable(Vendable vendable){
        
        
        BigDecimal one = new BigDecimal("1");
        
        BigDecimal newStock = new BigDecimal(vendable.getCurrentStock());
        newStock = newStock.subtract(one);
        vendable.setCurrentStock(newStock.toString());
        
    }
 */
    
    // BANNERS
    
    public void createDivisionBanner(){
        
        io.print("----------------------------------------");
    }
    
    public void createVendablePurchasedBanner(){
        
        io.print("----   Item Purchased! Thank You For Your Patronage.   ----");
    }
    
    public void createErrorMessageBanner(String errorMessage){
        io.print("==-- ERROR --==");
        io.print(errorMessage);
    }
    
    public void createUnknownCommandBanner(){
        io.print("Unknown Command.");
    }
}
