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
public class VendingMachineItemDoesNotExistException extends Exception{
    
    public VendingMachineItemDoesNotExistException(String message){
        super(message);
    }
    
    public VendingMachineItemDoesNotExistException(String message, Throwable cause){
        super(message, cause);
    }
}
