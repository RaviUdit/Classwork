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
    
    public static void main(String[] args) {
        
        UserIO myIO = new UserIOConsoleImpl();
        VendingMachineView myView = new VendingMachineView(myIO);
        VendingMachineDAO myDAO = new VendingMachineDAOFileImpl();
        VendingMachineAuditDAO myAuditDao = new VendingMachineAuditDAOFileImpl();
        VendingMachineChange myChange = new VendingMachineChange();
        VendingMachineServiceLayer myService = new VendingMachineServiceLayerImpl(myDAO, myAuditDao, myChange);
        
        VendingMachineController controller = new VendingMachineController(myView, myService);
        controller.run();
        
    }
}
