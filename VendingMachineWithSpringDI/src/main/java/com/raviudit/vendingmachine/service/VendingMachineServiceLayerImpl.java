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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author raviu
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer{
    
    VendingMachineDAO dao; //DAO
    VendingMachineAuditDAO auditDao; //AuditDAO
    VendingMachineChange change; //Change
    
    public VendingMachineServiceLayerImpl(VendingMachineDAO dao, 
                                          VendingMachineAuditDAO auditDao, 
                                          VendingMachineChange change){
        this.dao = dao;
        this.auditDao = auditDao;
        this.change = change;
    }

    /*
    ** Function Name: updateVendables
    ** Return Type: Vendable
    ** Purpose: Passes to DAO updateVendables method. 
    */  
    @Override
    public Vendable updateVendables(String vendableName, Vendable vendable) throws VendingMachineDAOException {
        return dao.updateVendables(vendableName, vendable); //Passthrough to method defined in DAO. 
    }

    /*
    ** Function Name: getAllVendables
    ** Return Type: List<Vendable>
    ** Purpose: Passes to DAO getAllVendables method. 
    */  
    @Override
    public List<Vendable> getAllVendables() throws VendingMachineDAOException {
        return dao.getAllVendables(); //Passthrough to method defined in DAO. 
    }

    /*
    ** Function Name: getVendable
    ** Return Type: Vendable
    ** Purpose: Passes to DAO getVendable method if checkIfVendableExsts function passes. 
    */   
    @Override
    public Vendable getVendable(String vendableName) throws VendingMachineDAOException,
                                                            VendingMachineItemDoesNotExistException{
        
        checkifVendableExists(vendableName);
        return dao.getVendable(vendableName); //Passthrough to method defined in DAO. 
    } 

    /*
    ** Function Name: vendVendable
    ** Return Type: void
    ** Purpose: Vends the passed in vendable if all the condition called by the checkIfInStock and 
    **          checkIfSufficientFunds methods are passed. 
    */   
    @Override
    public void vendVendable(Vendable vendable, String cash) throws VendingMachineInsufficientFundsException, 
                                                                    VendingMachineItemNotInStockException,
                                                                    VendingMachineDAOException{
        
        checkIfInStock(vendable);
        checkIfSufficientFunds(vendable, cash);
        dao.vendVendable(vendable);
        
        auditDao.writeAuditEntry(vendable.getItemName() + " vended.");
        
    }

    /*
    ** Function Name: makingChange
    ** Return Type: void
    ** Purpose: Calls the checkIfMoney fucntion and passes in the maybeMoney parameter.
    */    
    @Override
    public void makingChange(BigDecimal remainingMoney) {
        BigDecimal zero = new BigDecimal("0.00");
        
        BigDecimal remainingChange = change.makeChange(Coins.QUARTER, remainingMoney);
        remainingChange = change.makeChange(Coins.DIME, remainingChange);
        remainingChange = change.makeChange(Coins.NICKEL, remainingChange);
        remainingChange = change.makeChange(Coins.PENNY, remainingChange);
        
        remainingChange = zero;        
    }
    
    
    /*
    ** Function Name: isThatMoney
    ** Return Type: void
    ** Purpose: Calls the checkIfMoney fucntion and passes in the maybeMoney parameter.
    */
    @Override
    public void isThatMoney(String maybeMoney) throws VendingMachineIsNotMoneyException {
        checkIfMoney(maybeMoney);
    }
    
    //helper function to check if item is in stock. 
    /*
    ** Function Name: checkIfInStock
    ** Return Type: void
    ** Purpose: Makes sure the requested vendable is in stock.  
    **          If the the vendable is not in stock, it throws a VendingMachineItemNotInStockException.. 
    */    
    private void checkIfInStock(Vendable vendable) throws VendingMachineItemNotInStockException{
        
        if(vendable.getCurrentStock() <=0){
            throw new VendingMachineItemNotInStockException( vendable.getItemName() + " is not in stock.");
        }
    }
    
    // helper function to check if user had entered enough cash
    /*
    ** Function Name: checkifSufficientFunds
    ** Return Type: void
    ** Purpose: Makes sure the amount of cash a user has submitted is enough for the requested item.  If the given dollar amount 
    **          isn't enough, it throws a VendingMachineInsufficientFundsException. 
    */
    private void checkIfSufficientFunds(Vendable vendable, String cash) throws VendingMachineInsufficientFundsException{
        
        BigDecimal userCash = new BigDecimal(cash);
        BigDecimal itemPrice = new BigDecimal(vendable.getItemPrice());
        
        //while loop for userCash? 
        if (userCash.compareTo(itemPrice) == -1){
            throw new VendingMachineInsufficientFundsException("You have not entered enough money to purchase " + vendable.getItemName() + "Your current funds are: $" + cash);
            
            
        }
    }

    /*
    ** Function Name: checkIfMoney
    ** Return Type: void
    ** Purpose: Takes a string and determines if it is a dollar amount. If the string is not a dollar amount 
    **          the method throws a VendingMachineItemDoesNotExistException. 
    */
    private void checkIfMoney(String maybeMoney) throws VendingMachineIsNotMoneyException{
        
        boolean isMoney = true;
        
        if (maybeMoney.startsWith("0") || maybeMoney.startsWith("1") || maybeMoney.startsWith("2") || maybeMoney.startsWith("3") || maybeMoney.startsWith("4") ||
            maybeMoney.startsWith("5") || maybeMoney.startsWith("6") || maybeMoney.startsWith("7") || maybeMoney.startsWith("8") || maybeMoney.startsWith("9") ||
            maybeMoney.startsWith(".")){
            isMoney = true;
        } else {
            isMoney = false;
        }
        
        if (isMoney != true){
            throw new VendingMachineIsNotMoneyException("That's not real money!");
        }
        
        //check if the string if a dollar amount, then pass through to purchase item.
        BigDecimal queryMoney = new BigDecimal(maybeMoney);
        if (queryMoney.scale() > 2){
            throw new VendingMachineIsNotMoneyException("That's not real money!");
        }
        
    }
    
    /*
    ** Function Name: checkifVendableExists
    ** Return Type: void
    ** Purpose: Takes a string and determines if an item with that name exists in a list made up on the itemName property
    **          of all vendables. If a vendable does not exists the method throws a VendingMachineItemDoesNotExistException. 
    */
    private void checkifVendableExists(String maybeVendable) throws VendingMachineDAOException, 
                                                                    VendingMachineItemDoesNotExistException{
        
        //Create list of item names and check against it if the item exists. 
        List<String> queryList = dao.getAllVendables().stream().map((p)->p.getItemName()).collect(Collectors.toList());
        boolean containsVendable = queryList.contains(maybeVendable);
        //check if the string is actually the name of an item that exists, then pass it through. 
        
       // containsVendable = true;
       
       if (containsVendable != true){
            throw new VendingMachineItemDoesNotExistException(maybeVendable + " is not available from this machine.");
        }
    }
   
}
