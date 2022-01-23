/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raviudit.dvdlibrary.ui;

import com.raviudit.dvdlibrary.dto.DVD;
import java.util.List;

/**
 *
 * @author raviu
 */
public class DVDLibraryView {
    
    private UserIO io;
    
    public DVDLibraryView(UserIO io){
        this.io = io;
    }
    
    /*
    ** Function Name: libraryMenu()
    ** Return Type: int
    ** Purpose: Displays the main menu of the program. 
    */
    public int libraryMenu(){
        
        io.print("Ravi's DVD Library. Please make your Selection");
        io.print("1. List DVDs");
        io.print("2. Add DVD");
        io.print("3. Remove DVD");
        io.print("4. Search for DVD");
        io.print("5. Edit DVD");
        io.print("6. Exit.");
        
        return io.readInt("Please select from the Listed Options", 1, 6);
    }
    
    //Banners: used to help distinguish different parts of the program. 
    
    public void createDVDBanner(){
        io.print("==-- Enter DVD Information -- ==");        
    }
    
    public void createDVDCreatedBanner(){
        io.print("==-- DVD Successfully Created --==");
    }
    
    public void createDisplayAllDVDsBanner(){
        io.print("==-- Displaying All DVDs --==");
    }
    
    public void createSearchForDVDBanner(){
        io.print("==-- Search for DVD --==");
    }
    
    public void createRemoveDVDBanner(){
        io.print("==-- Remove DVD --==");
    }
    
    public void createUnknownCommandBanner(){
        io.print("Unknown Command.");
    }
    
    public void createExitBanner(){
        io.print("==-- Goodbye. Thank you for using Ravi's DVD Library. --==");
    }
    
    public void createErrorMessageBanner(String errorMessage){
        io.print("==-- ERROR --==");
        io.print(errorMessage);
    }
    
    public void createEditDVDBanner(){
        io.print("==-- Edit DVD --==");
    }
    
    //Library Functions
    
    /*
    ** Function Name: getNewDVDInfo()
    ** Return Type: DVD
    ** Purpose: Gets necessary DVD information from user and stores it as a new 
    **          DVD object.
    */
    public DVD getNewDVDInfo(){
        String title = io.readString("Please Enter DVD's Title.");
        String releaseDate = io.readString("Please Enter DVD's Release Date [ month / year ].");
        String rating = io.readString("Please Enter DVD's MPAA Rating.");
        String director = io.readString("Please Enter DVD's director.");
        String studio = io.readString("Please Enter DVD's studio.");
        String comments = io.readString("Please Enter Any User Comments.");
        
        DVD currentDVD = new DVD(title);
        
        currentDVD.setReleaseDate(releaseDate);
        currentDVD.setRating(rating);
        currentDVD.setDirector(director);
        currentDVD.setStudio(studio);
        currentDVD.setUserComments(comments);
        
        return currentDVD;
    }
    
    /*
    ** Function Name: displayDVDList()
    ** Return Type: void
    ** Paramemters: dvdList
    ** Purpose: Displays the Title, Release Date, and Director off all DVDs in 
    **          the library. 
    */    
    public void displayDVDList(List<DVD> dvdList){
        for (DVD currentDVD : dvdList){
            String dvdInfo = String.format("%s : %s %s", 
                    currentDVD.getTitle(),
                    currentDVD.getReleaseDate(),
                    currentDVD.getDirector());
            io.print(dvdInfo);
        }
        
        io.readString("Hit Enter to Continue");
    }
    
    /*
    ** Function Name: getDVDTitle()
    ** Return Type: String
    ** Purpose: Gets and returns the title of a DVD from user input.
    */
    public String getDVDTitle(){
        return io.readString("Please Enter the Title of the DVD.");
    }
    
    /*
    ** Function Name: displayDVD()
    ** Return Type: void
    ** Parameters: dvd
    ** Purpose: Takes a DVD objects and displays all related data for that DVD 
    **          that is stored in the library. 
    */
    public void displayDVD(DVD dvd){
        if (dvd != null){
            io.print("Title: " + dvd.getTitle());
            io.print("Rating: " + dvd.getRating());
            io.print("Director: " + dvd.getDirector());
            io.print("Release Date: " + dvd.getReleaseDate());
            io.print("Studio: " + dvd.getStudio());
            io.print("User Comments: " + dvd.getUserComments());
            io.print(" ");
        } else {
            io.print("DVD not Found.");
        }
        
        io.readString("Press ENTER to continue.");
    }
    
    /*
    ** Function Name: displayRemovedDVD()
    ** Return Type: void
    ** Parameters: title
    ** Purpose: Informs user that the DVD specified by title has been removed 
    **          from the library.
    */
    public void displayRemovedDVD(DVD title){
        if (title != null){
            io.print("DVD Removed from Library.");
        } else {
            io.print("DVD is not in Library.");
        }
        
        io.readString("Press ENTER to continue.");
    }
    
    /*
    ** Function Name: editDVD()
    ** Return Type: void
    ** Parameters: dvd
    ** Purpose: Performs a loop that allows a user to edit multiple entries of a DVD. 
    */
    public void editDVD(DVD dvd){
       //RUN getDVD BEFORE THIS
        boolean editMenuOpen = true;
        while(editMenuOpen){
         
            io.print("What property of " + dvd.getTitle() + " would you like to edit?");
            io.print("1. MPAA Rating");
            io.print("2. Director");
            io.print("3. Release Date");
            io.print("4. Studio");
            io.print("5. User Comments");
            io.print("6. Stop Editing");

            int userChoice = io.readInt("Please select from the preceding options", 1, 6);

                switch(userChoice){
                    case 1:
                        dvd.setRating(io.readString("Please enter MPAA new rating."));
                        break;
                    case 2:
                        dvd.setDirector(io.readString("Please enter new Director."));
                        break;
                    case 3: 
                        dvd.setReleaseDate(io.readString("Please enter new Release Date."));
                        break;
                    case 4:
                        dvd.setStudio(io.readString("Please enter new Studio."));
                        break;
                    case 5:
                        dvd.setUserComments(io.readString("Please Enter User Comments."));
                        break;
                    case 6:
                        editMenuOpen = false; 
                        break;
                    default:
                        createUnknownCommandBanner();
                }
            }
        
        io.print("DVD Editing Completed.");
        
    }
    
}
