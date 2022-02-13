/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raviudit.vendingmachine.dao;

/**
 *
 * @author raviu
 */

 /*
** Name: VendingMachineAuditDAO
** Type: interface
*/
public interface VendingMachineAuditDAO{
    
    public void writeAuditEntry(String entry) throws VendingMachineDAOException;
    
}
