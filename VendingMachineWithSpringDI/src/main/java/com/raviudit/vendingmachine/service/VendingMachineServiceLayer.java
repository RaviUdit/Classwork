/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raviudit.vendingmachine.service;

import com.raviudit.vendingmachine.dao.VendingMachineDAOException;
import com.raviudit.vendingmachine.dto.Vendable;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author raviu
 */

 /*
** Name: VendingMachineServiceLayer
** Type: interface
*/

public interface VendingMachineServiceLayer {
    
    Vendable updateVendables(String vendableName, Vendable vendable) throws VendingMachineDAOException;
    
    List<Vendable> getAllVendables() throws VendingMachineDAOException;
    
    Vendable getVendable(String vendableName) throws VendingMachineDAOException, VendingMachineItemDoesNotExistException;
    
    void vendVendable(Vendable vendable, String cash) throws VendingMachineInsufficientFundsException, VendingMachineItemNotInStockException, VendingMachineDAOException;
    
    void makingChange(BigDecimal remainingMoney);
    
    public void isThatMoney(String maybeMoney) throws VendingMachineIsNotMoneyException;
}
