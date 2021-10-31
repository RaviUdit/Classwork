/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raviudit.dvdlibrary.controller;

import com.raviudit.dvdlibrary.dao.DVDLibraryDAO;
import com.raviudit.dvdlibrary.dao.DVDLibraryDAOException;
import com.raviudit.dvdlibrary.dao.DVDLibraryDAOFileManager;
import com.raviudit.dvdlibrary.dto.DVD;
import com.raviudit.dvdlibrary.ui.DVDLibraryView;
import com.raviudit.dvdlibrary.ui.UserIO;
import com.raviudit.dvdlibrary.ui.UserIOManager;
import java.util.List;

/**
 *
 * @author raviu
 */
public class DVDLibraryController {
    
    private DVDLibraryView view;
    private DVDLibraryDAO dao;
    
    public DVDLibraryController(DVDLibraryDAO dao, DVDLibraryView view){
        this.dao = dao;
        this.view = view;
    }
    
    
    // REMOVE WHEN EDIT FUNCTION IS DONE
    private UserIO io = new UserIOManager(); 
    
    
    public void run(){
        
        boolean menuOpen = true; 
        int menuSelection = 0;
        
        try{
            while(menuOpen){

                menuSelection = getMenuSelection();

                switch(menuSelection){
                    case 1:
                        listDVDs();
                        break;
                    case 2:
                        createDVD();
                        break;
                    case 3:
                        removeDVD();
                        break;
                    case 4:
                        displayDVD();
                        break;
                    case 5:
                        io.print("EDIT DVD");
                        break;
                    case 6:
                        menuOpen = false; 
                        break;
                    default:
                        unknownCommand();
                }
            }

            exitMessage();
        } catch (DVDLibraryDAOException e) {
            view.createErrorMessageBanner(e.getMessage());
        }
    }
    
    private int getMenuSelection(){
        return view.libraryMenu();
    }
    
    private void createDVD () throws DVDLibraryDAOException{
        
        view.createDVDBanner();
        DVD newDVD = view.getNewDVDInfo();
        dao.addDVD(newDVD.getTitle(), newDVD);
        view.createDVDCreatedBanner();
    }
    
    private void listDVDs() throws DVDLibraryDAOException{
        view.createDisplayAllDVDsBanner();
        List<DVD> dvdList = dao.getAllDVDs();
        view.displayDVDList(dvdList);
    }
    
    private void displayDVD() throws DVDLibraryDAOException{
        view.createSearchForDVDBanner();
        String dvdTitle = view.getDVDTitle();
        DVD dvd = dao.getDVD(dvdTitle);
        view.displayDVD(dvd);
    }
    
    private void removeDVD() throws DVDLibraryDAOException{
        view.createRemoveDVDBanner();
        String title = view.getDVDTitle();
        DVD removedDVD = dao.deleteDVD(title);
        view.displayRemovedDVD(removedDVD);
    }
    
    private void unknownCommand(){
        view.createUnknownCommandBanner();
    }
    
    private void exitMessage(){
        view.createExitBanner();
    }
    
}
