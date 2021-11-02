/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raviudit.dvdlibrary.controller;

import com.raviudit.dvdlibrary.dao.DVDLibraryDAO;
import com.raviudit.dvdlibrary.dao.DVDLibraryDAOException;
import com.raviudit.dvdlibrary.dto.DVD;
import com.raviudit.dvdlibrary.ui.DVDLibraryView;
import java.util.List;

/**
 *
 * @author raviu
 */
public class DVDLibraryController {
    
    //declaring and assigning class instances. 
    private DVDLibraryView view;
    private DVDLibraryDAO dao;
    
    public DVDLibraryController(DVDLibraryDAO dao, DVDLibraryView view){
        this.dao = dao;
        this.view = view;
    }   
    // REMOVE WHEN EDIT FUNCTION IS DONE
    //private UserIO io = new UserIOManager(); 
    
    
    /*
    ** Function Name: run
    ** Return Type: void
    ** Purpose: Serves as primary program interface.
    */
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
                        editDVD();
                        break;
                    case 6:
                        // Ends main loop. 
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
    
    /*
    ** Function Name: getMenuSelection
    ** Return Type: void
    ** Purpose: calls libraryMenu() from DVDLibraryView
    */
    private int getMenuSelection(){
        return view.libraryMenu();
    }
    
    /*
    ** Function Name: createDVD
    ** Return Type: void
    ** Purpose: Creates a new DVD object with getNewDVDInfo() and stores
    **          the DVD object in an external file with addDVD(). 
    */
    private void createDVD () throws DVDLibraryDAOException{
        
        view.createDVDBanner();
        DVD newDVD = view.getNewDVDInfo();
        dao.addDVD(newDVD.getTitle(), newDVD);
        view.createDVDCreatedBanner();
    }
    
    /*
    ** Function Name: listDVDs
    ** Return Type: void
    ** Purpose: Loads a List with DVD objects with getAllDVDs() and displays 
    **          the list in the console with displayDVDList(). 
    */
    private void listDVDs() throws DVDLibraryDAOException{
        view.createDisplayAllDVDsBanner();
        List<DVD> dvdList = dao.getAllDVDs();
        view.displayDVDList(dvdList);
    }
    
    /*
    ** Function Name: displayDVD
    ** Return Type: void
    ** Purpose: Captures the name of a DVD from user input with getDVDTitle()
    **          and displays all information about that dvd with displayDVD().
    */
    private void displayDVD() throws DVDLibraryDAOException{
        view.createSearchForDVDBanner();
        String dvdTitle = view.getDVDTitle();
        DVD dvd = dao.getDVD(dvdTitle);
        view.displayDVD(dvd);
    }
    
    /*
    ** Function Name: removeDVD
    ** Return Type: void
    ** Purpose: Catches the name of a DVD from user input with getDVDTitle()
    **          and then removes that DVD from the library with deleteDVD(). 
    */
    private void removeDVD() throws DVDLibraryDAOException{
        view.createRemoveDVDBanner();
        String title = view.getDVDTitle();
        DVD removedDVD = dao.deleteDVD(title);
        view.displayRemovedDVD(removedDVD);
    }
    
    /*
    ** Function Name: editDVD
    ** Return Type: void
    ** Purpose: Catches the name of a DVD from user input with getDVDTitle()
    **          and then allows the user to edit the remaining properties of 
    **          the specified DVD object with editDVD(). The new data then 
    **          overwrites the previous dvd data by calling addDVD().
    */
    private void editDVD() throws DVDLibraryDAOException{
        
        view.createEditDVDBanner();
        String title = view.getDVDTitle();
        DVD editedDVD = dao.getDVD(title);
        view.editDVD(editedDVD);
        dao.addDVD(editedDVD.getTitle() , editedDVD);
    }
    
    private void unknownCommand(){
        view.createUnknownCommandBanner();
    }
    
    private void exitMessage(){
        view.createExitBanner();
    }
    
}
