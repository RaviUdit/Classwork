/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raviudit.vendingmachine.controller;

import com.raviudit.vendingmachine.dao.VendingMachineDAOException;
import com.raviudit.vendingmachine.dto.Vendable;
import com.raviudit.vendingmachine.service.VendingMachineInsufficientFundsException;
import com.raviudit.vendingmachine.service.VendingMachineItemDoesNotExistException;
import com.raviudit.vendingmachine.service.VendingMachineItemNotInStockException;
import com.raviudit.vendingmachine.service.VendingMachineServiceLayer;
import com.raviudit.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author raviu
 */
public class VendingMachineController {
    
    private VendingMachineView view;
    private VendingMachineServiceLayer service; 
    
    public VendingMachineController(VendingMachineView view, VendingMachineServiceLayer service){
        this.view = view;
        this.service = service;
    }
    
    public void run(){
        
        boolean menuOpen = true; 
        int menuSelection = 0;
        
        try{
            while(menuOpen){
                
                listVendables();
                
                menuSelection = getMenuSelection();
                
                switch(menuSelection){
                    case 1:
                        vendVendable();
                        break;
                    case 2:
                        menuOpen = false;
                        break;
                    default:
                        unknownCommand();
                }
            }
        } catch (VendingMachineDAOException | VendingMachineItemDoesNotExistException e){
            
            view.createErrorMessageBanner(e.getMessage());
        }
        
    }
    
    private int getMenuSelection(){
        
        return view.vendingMachineMenu();
    }
    
    private void listVendables() throws VendingMachineDAOException{
        
        view.createDivisionBanner(); 
        
        List<Vendable> stockList = service.getAllVendables();
        view.displayVendablesList(stockList);
        
        view.createDivisionBanner();
    }
    
    private void vendVendable() throws VendingMachineDAOException, VendingMachineItemDoesNotExistException{
        
        view.createDivisionBanner();
        
        String userCash = view.getUserCash(); //NEED TO WRITE FUNCTION TO CHECK IF STRING IF A NUMBER.
        
        String vendableName = view.getVendableName(); //NEED TO WRITE FUNCTION TO MAKE SURE ITEM EXISTS.
        Vendable vendedVendable = service.getVendable(vendableName);
        
        boolean vendingMachineError = false;
        
        do{
            try{
                service.vendVendable(vendedVendable, userCash);
                service.updateVendables(vendedVendable.getItemName(), vendedVendable);
                
                view.createVendablePurchasedBanner();
                
                BigDecimal purchasedPrice = new BigDecimal(userCash);
                BigDecimal vendedPrice = new BigDecimal(vendedVendable.getItemPrice());
                BigDecimal change = purchasedPrice.subtract(vendedPrice);
                
                service.makingChange(change);
                
                vendingMachineError = false;
            } catch(VendingMachineInsufficientFundsException | VendingMachineItemNotInStockException e){
                vendingMachineError = true;
               
                view.createErrorMessageBanner(e.getMessage());
                
                String additionalCash = view.getUserCash();
                BigDecimal newCash = new BigDecimal(additionalCash);
                BigDecimal oldCash = new BigDecimal(userCash);
                userCash = newCash.add(oldCash).toString();
                
                vendableName = view.getVendableName();
                vendedVendable = service.getVendable(vendableName);
            }
        } while(vendingMachineError);
         
    }
    
    private void unknownCommand(){
        view.createUnknownCommandBanner();
    }
    
}
