/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raviudit.vendingmachine;

import com.raviudit.vendingmachine.controller.VendingMachineController;
import com.raviudit.vendingmachine.dao.VendingMachineAuditDAO;
import com.raviudit.vendingmachine.dao.VendingMachineAuditDAOFileImpl;
import com.raviudit.vendingmachine.dao.VendingMachineDAO;
import com.raviudit.vendingmachine.dao.VendingMachineDAOFileImpl;
import com.raviudit.vendingmachine.service.VendingMachineChange;
import com.raviudit.vendingmachine.service.VendingMachineServiceLayer;
import com.raviudit.vendingmachine.service.VendingMachineServiceLayerImpl;
import com.raviudit.vendingmachine.ui.UserIO;
import com.raviudit.vendingmachine.ui.UserIOConsoleImpl;
import com.raviudit.vendingmachine.ui.VendingMachineView;

/**
 *
 * @author raviu
 */
public class App {
    
    /*
    ** Function Name: main
    ** Return Type: Void
    ** Purpose: Creates an instance of the classes necessary to run the program 
    **          and feeds them into the approppriate method in order to run the
    **          program. 
    */
    
    public static void main(String[] args) {
        
        //User Input/Output
        UserIO myIO = new UserIOConsoleImpl();
        
        //View
        VendingMachineView myView = new VendingMachineView(myIO);
        
        //DAO
        VendingMachineDAO myDAO = new VendingMachineDAOFileImpl();
        
        //Audit DAO
        VendingMachineAuditDAO myAuditDao = new VendingMachineAuditDAOFileImpl();
        
        //Change
        VendingMachineChange myChange = new VendingMachineChange();
        
        //Service Layer
        VendingMachineServiceLayer myService = new VendingMachineServiceLayerImpl(myDAO, myAuditDao, myChange);
        
        //Controller
        VendingMachineController controller = new VendingMachineController(myView, myService);
        controller.run();
        
    }
}
