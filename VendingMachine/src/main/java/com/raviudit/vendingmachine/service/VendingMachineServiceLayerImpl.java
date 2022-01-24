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

    @Override
    public Vendable updateVendables(String vendableName, Vendable vendable) throws VendingMachineDAOException {
        return dao.updateVendables(vendableName, vendable); //Passthrough to method defined in DAO. 
    }

    @Override
    public List<Vendable> getAllVendables() throws VendingMachineDAOException {
        return dao.getAllVendables(); //Passthrough to method defined in DAO. 
    }

    @Override
    public Vendable getVendable(String vendableName) throws VendingMachineDAOException,
                                                            VendingMachineItemDoesNotExistException{
        
        checkifVendableExists(vendableName);
        return dao.getVendable(vendableName); //Passthrough to method defined in DAO. 
    } 

    @Override
    public void vendVendable(Vendable vendable, String cash) throws VendingMachineInsufficientFundsException, 
                                                                    VendingMachineItemNotInStockException,
                                                                    VendingMachineDAOException{
        
        checkIfInStock(vendable);
        checkIfSufficientFunds(vendable, cash);
        dao.vendVendable(vendable);
        
        auditDao.writeAuditEntry(vendable.getItemName() + " vended.");
        
    }
    
    @Override
    public void makingChange(BigDecimal remainingMoney) {
        BigDecimal zero = new BigDecimal("0.00");
        
        BigDecimal remainingChange = change.makeChange(Coins.QUARTER, remainingMoney);
        remainingChange = change.makeChange(Coins.DIME, remainingChange);
        remainingChange = change.makeChange(Coins.NICKEL, remainingChange);
        remainingChange = change.makeChange(Coins.PENNY, remainingChange);
        
        remainingChange = zero;        
    }
    
    //helper function to check if item is in stock. 
    
    private void checkIfInStock(Vendable vendable) throws VendingMachineItemNotInStockException{
        
        if(vendable.getCurrentStock() <=0){
            throw new VendingMachineItemNotInStockException( vendable.getItemName() + " is not in stock.");
        }
    }
    
    // helper function to check if user had entered enough cash
    
    private void checkIfSufficientFunds(Vendable vendable, String cash) throws VendingMachineInsufficientFundsException{
        
        BigDecimal userCash = new BigDecimal(cash);
        BigDecimal itemPrice = new BigDecimal(vendable.getItemPrice());
        
        //while loop for userCash? 
        if (userCash.compareTo(itemPrice) == -1){
            throw new VendingMachineInsufficientFundsException("You have not entered enough money to purchase " + vendable.getItemName() + "Your current funds are: $" + cash);
            
            
        }
    }
    
    private void checkIfMoney(String maybeMoney){
        
        //check if the string if a dollar amount, then pass through to purchase item.
        
    }
    
    private void checkifVendableExists(String maybeVendable) throws VendingMachineDAOException, 
                                                                    VendingMachineItemDoesNotExistException{
        
        //Vendable queryVendable = dao.getVendable(maybeVendable);
        //List<Vendable> checkVendable = dao.getAllVendables();
        List<String> queryList = dao.getAllVendables().stream().map((p)->p.getItemName()).collect(Collectors.toList());
        boolean containsVendable = queryList.contains(maybeVendable);
        //check if the string is actually the name of an item that exists, then pass it through. 
        
       // containsVendable = true;
        
        if (containsVendable != true){
            throw new VendingMachineItemDoesNotExistException(maybeVendable + " is not available from this machine.");
        }
    }
   
}
