/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raviudit.dvdlibrary;

import com.raviudit.dvdlibrary.controller.DVDLibraryController;
import com.raviudit.dvdlibrary.dao.DVDLibraryDAO;
import com.raviudit.dvdlibrary.dao.DVDLibraryDAOFileManager;
import com.raviudit.dvdlibrary.ui.DVDLibraryView;
import com.raviudit.dvdlibrary.ui.UserIO;
import com.raviudit.dvdlibrary.ui.UserIOManager;

/**
 *
 * @author raviu
 */
public class Main {
    
    public static void main(String[] args) {
        UserIO myIo = new UserIOManager();
        DVDLibraryView myView = new DVDLibraryView(myIo);
        DVDLibraryDAO myDAO = new DVDLibraryDAOFileManager();
        
        DVDLibraryController controller = new DVDLibraryController(myDAO, myView);
        controller.run();
    }
    
}
