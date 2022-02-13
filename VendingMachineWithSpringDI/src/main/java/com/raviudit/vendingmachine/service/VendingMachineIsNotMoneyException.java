/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raviudit.vendingmachine.service;

/**
 *
 * @author raviu
 */

/** Function Name: VendingMachineIsNotMoneyException
  ** Type: Exception
  ** Purpose: Throws Exception if the funds given isn't actually money. 
  */
public class VendingMachineIsNotMoneyException extends Exception {
    
    public VendingMachineIsNotMoneyException(String message){
        super (message);
    }
    
    public VendingMachineIsNotMoneyException(String message, Throwable cause){
        super (message, cause);
    }
    
}
