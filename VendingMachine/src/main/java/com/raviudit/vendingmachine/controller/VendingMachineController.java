/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raviudit.vendingmachine.controller;

import com.raviudit.vendingmachine.dao.VendingMachineDAOException;
import com.raviudit.vendingmachine.dto.Vendable;
import com.raviudit.vendingmachine.service.VendingMachineInsufficientFundsException;
import com.raviudit.vendingmachine.service.VendingMachineIsNotMoneyException;
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
    
    /*
    ** Function Name: run
    ** Return Type: Void
    ** Purpose: Runs the program. Catches VendingMachineDAOException, VendingMachineItemDoesNotExistException, 
    **          or VendingMachineIsNotMoneyException if they are triggered in the loop. 
    */
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
        } catch (VendingMachineDAOException | VendingMachineItemDoesNotExistException | VendingMachineIsNotMoneyException e){
            
            view.createErrorMessageBanner(e.getMessage());
        }
        
    }
    
    /*
    ** Function Name: getMenuSelection
    ** Return Type: int
    ** Purpose: Calls view to list the menu and collect the user's input.  
    */
    private int getMenuSelection(){
        
        return view.vendingMachineMenu();
    }
    
    /*
    ** Function Name: listVendable
    ** Return Type: Void
    ** Purpose: Calls service to get all vendables saved to external file.
    **          Calls view to list all the loaded vendables. 
    */
    private void listVendables() throws VendingMachineDAOException{
        
        view.createDivisionBanner(); 
        
        List<Vendable> stockList = service.getAllVendables();
        view.displayVendablesList(stockList);
        
        view.createDivisionBanner();
    }
    
    /*
    ** Function Name: vendVendable
    ** Return Type: Void
    ** Purpose: Calls view to determine the User's inputted cash and vendable choice. 
    **          Checks that both variable and viable for the program. If they are not, the program is 
    **          interrupted and exceptions are thrown. 
    **          When both variables are valid service is called to dispense the vendable. 
    **          vendVendable then calls service to return the user's change.
    */
    private void vendVendable() throws VendingMachineDAOException, VendingMachineItemDoesNotExistException, VendingMachineIsNotMoneyException{
        
        //declaring variables.
        boolean vendingMachineError = false;
        String userCash = "0.00";
        String additionalCash = "0.00";
        String vendableName = "placeholderName";
        Vendable vendedVendable = new Vendable("placeholderVendable");
       
        view.createDivisionBanner();
        
        do{
           try{ 
                userCash = view.getUserCash(); 
                service.isThatMoney(userCash);
                
                vendingMachineError = false;
           } catch (VendingMachineIsNotMoneyException e){
               vendingMachineError = true;
               
               view.createErrorMessageBanner(e.getMessage());
           }
        } while (vendingMachineError);
        
        do{
            try{
                vendableName = view.getVendableName(); 
                vendedVendable = service.getVendable(vendableName);
                
                vendingMachineError = false;
            } catch (VendingMachineItemDoesNotExistException e){
                vendingMachineError = true;
                
                view.createErrorMessageBanner(e.getMessage());
            }
        } while (vendingMachineError);
        
        
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
                //vendingMachineError = true;
               
                view.createErrorMessageBanner(e.getMessage());
                
                //Get Money post-error
                do{
                    try{
                        additionalCash = view.getUserCash();
                        service.isThatMoney(additionalCash);
                        
                        vendingMachineError = false;
                    } catch (VendingMachineIsNotMoneyException ex){
                        vendingMachineError = true;
               
                        view.createErrorMessageBanner(ex.getMessage());                      
                    }
                } while (vendingMachineError);
                
                //adding old money to new money
                BigDecimal newCash = new BigDecimal(additionalCash);
                BigDecimal oldCash = new BigDecimal(userCash);
                userCash = newCash.add(oldCash).toString();
                
                //Get Vendable Post-Error
                do{
                    try{
                        vendableName = view.getVendableName(); 
                        vendedVendable = service.getVendable(vendableName);
                
                        vendingMachineError = false;
                    } catch (VendingMachineItemDoesNotExistException ex){
                        vendingMachineError = true;
                        
                        view.createErrorMessageBanner(ex.getMessage());
                    }
                } while (vendingMachineError);
                //vendableName = view.getVendableName();
                //vendedVendable = service.getVendable(vendableName);
                
                vendingMachineError = true; 
            }
        } while(vendingMachineError);
         
    }
    
    /*
    ** Function Name: main
    ** Return Type: Void
    ** Purpose: Calls view to create a banner if default in run loop is triggered.
    */
    private void unknownCommand(){
        view.createUnknownCommandBanner();
    }
    
}
