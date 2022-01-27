/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raviudit.vendingmachine.service;

import com.raviudit.vendingmachine.dao.VendingMachineAuditDAO;
import com.raviudit.vendingmachine.dao.VendingMachineDAOException;

/**
 *
 * @author raviu
 */

/*
    ** Function Name: VendingMachineAuditDAOStubImpl
    ** Type: Stub
    ** Purpose: used for tests in VendingMachineServiceLayerImplTest 
    */  
public class VendingMachineAuditDAOStubImpl implements VendingMachineAuditDAO{
    
    @Override
    public void writeAuditEntry(String entry) throws VendingMachineDAOException{
        //nothing...
    }
    
}
