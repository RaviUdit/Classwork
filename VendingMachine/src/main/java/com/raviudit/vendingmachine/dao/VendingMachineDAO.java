/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raviudit.vendingmachine.dao;

import com.raviudit.vendingmachine.dto.Vendable;
import java.util.List;

/**
 *
 * @author raviu
 */
public interface VendingMachineDAO {
    
    Vendable updateVendables(String vendableName, Vendable vendable) throws VendingMachineDAOException;
    
    List<Vendable> getAllVendables() throws VendingMachineDAOException;
    
    Vendable getVendable(String vendableName) throws VendingMachineDAOException;
    
    void vendVendable(Vendable vendable);
    

}
