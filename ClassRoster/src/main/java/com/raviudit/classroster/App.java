/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raviudit.classroster;

import com.raviudit.classroster.controller.ClassRosterController;
import com.raviudit.classroster.dao.ClassRosterDao;
import com.raviudit.classroster.dao.ClassRosterDaoFileImpl;
import com.raviudit.classroster.ui.ClassRosterView;
import com.raviudit.classroster.ui.UserIO;
import com.raviudit.classroster.ui.UserIOConsoleImpl;

/**
 *
 * @author raviu
 */
public class App {
    
    public static void main(String[] args) {
       
        // ClassRosterController controller = new ClassRosterController();
        // controller.run();
        
        UserIO myIo = new UserIOConsoleImpl();
        ClassRosterView myView = new ClassRosterView(myIo);
        ClassRosterDao myDao =  new ClassRosterDaoFileImpl();
        ClassRosterController controller = new ClassRosterController(myDao, myView);
        controller.run();
    }
    
}
