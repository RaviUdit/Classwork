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
 
/** Function Name: VendingMachineInsufficientFundsException
  ** Type: Exception
  ** Purpose: Throws Exception if the funds given are not enough. 
  */
public class VendingMachineInsufficientFundsException extends Exception {
    
    public VendingMachineInsufficientFundsException(String message){
        super(message);
    }
    
    public VendingMachineInsufficientFundsException(String message, Throwable cause){
        super(message, cause);
    }
}
