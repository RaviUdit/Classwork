/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raviudit.vendingmachine.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 *
 * @author raviu
 */
public class VendingMachineAuditDAOFileImpl implements VendingMachineAuditDAO{
    
    //Declaring Variables
    public static final String AUDIT_FILE = "audit.txt";
    
    /*
    ** Function Name: writeAuditEntry
    ** Return Type: Void
    ** Purpose: Writes an entry to a file defined by AUDIT_FILE.  
    */
    @Override
     public void writeAuditEntry(String entry) throws VendingMachineDAOException{
         PrintWriter out;
         
         try{
             out = new PrintWriter(new FileWriter(AUDIT_FILE, true)); //Set to Append mode with 'true'.
         } catch (IOException e){
             throw new VendingMachineDAOException("Could not write audit information", e);
         }
         
         LocalDateTime timeStamp = LocalDateTime.now();
         out.println(timeStamp.toString() + " : " + entry);
         out.flush();
     }
    
}
