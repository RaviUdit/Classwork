/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raviudit.dvdlibrary.dao;

import com.raviudit.dvdlibrary.dto.DVD;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author raviu
 */
public class DVDLibraryDAOFileManager implements DVDLibraryDAO{
    
    //File Code   
    public static final String LIBRARY_LOCATION = "dvdlibrary.txt";
    public static final String DELIMITER = ":&:";
    
    /*
    ** Function Name: addDVD()
    ** Return Type: DVD
    ** Parameters: title, dvd
    ** Purpose: Loads a library into memory from an external file: loadLibrary().
    **          Adds a new entry to Map dvds if the passed title property does not 
    **          match a current entry, or overwrites a previous entry with the same 
    **          name.
    **          Writes library to external file from memory.
    **          
    */
    @Override
    public DVD addDVD(String title, DVD dvd) throws DVDLibraryDAOException{
        loadLibrary();
        DVD newDVD = dvds.put(title, dvd);
        writeLibrary();
        return newDVD;
    }
    
    /*
    ** Function Name: getAllDVDs()
    ** Return Type: List
    ** Purpose: loads a library into memory from an external file: loadLibrary().
    **          Returns the loaded library as a list. 
    */
    @Override
    public List<DVD> getAllDVDs() throws DVDLibraryDAOException{
        loadLibrary();
        return new ArrayList<DVD>(dvds.values());
    }

    /*
    ** Function Name: getDVD()
    ** Return Type: DVD
    ** Parameters: title
    ** Purpose: loads a library into memory from an external file: loadLibrary().
    **          Returns the DVD object specified by title parameter. 
    */
    @Override
    public DVD getDVD(String title) throws DVDLibraryDAOException{
        loadLibrary();
        return dvds.get(title);
    }
    
    /*
    ** Function Name: deleteDVD()
    ** Return Type: DVD
    ** Parameters: title
    ** Purpose: loads a library into memory from an external file: loadLibrary().
    **          Removes the DVD object specified by title parameter from the 
    **          library. 
    **          Writes the library to an external file: writeLibrary(). 
    */
    @Override
    public DVD deleteDVD(String title) throws DVDLibraryDAOException{
        loadLibrary();
        DVD deletedDVD = dvds.remove(title);
        writeLibrary();
        return deletedDVD;
    }

    //@Override
    //public void editDVD(String title) throws DVDLibraryDAOException{
    //    
    //}
    
    /*
    ** Function Name: unmarshallDVD()
    ** Return Type: DVD
    ** Parameters: dvdFromFile
    ** Purpose: Transforms a single line of information into several
    **          different pieces of information and places that information into
    **          a string array. The String array is then seeded into a new DVD object.
    */
    private DVD unmarshallDVD(String dvdFromFile){
        
        String[] dvdProperties = dvdFromFile.split(DELIMITER);
        
        String dvdTitle = dvdProperties[0];
        
        DVD dvdFromLibrary = new DVD(dvdTitle);
        
        dvdFromLibrary.setReleaseDate(dvdProperties[1]);
        dvdFromLibrary.setRating(dvdProperties[2]);
        dvdFromLibrary.setDirector(dvdProperties[3]);
        dvdFromLibrary.setStudio(dvdProperties[4]);
        dvdFromLibrary.setUserComments(dvdProperties[5]);
        
        return dvdFromLibrary;
    }
    
    /*
    ** Function Name: loadLibrary
    ** Return Type: void
    ** Purpose: loads a library from an external location (LIBRARY_LOCATION)
    **          into memory.
    */
    private void loadLibrary() throws DVDLibraryDAOException{
        Scanner scanner; 
        
        try{
            scanner = new Scanner(new BufferedReader(new FileReader(LIBRARY_LOCATION)));
        } catch(FileNotFoundException e){
            throw new DVDLibraryDAOException("Could not load library", e);
        }
        
        String currentLine;
        
        DVD currentDVD; 
        
        while (scanner.hasNextLine()){
            currentLine = scanner.nextLine();
            currentDVD = unmarshallDVD(currentLine);
            
            dvds.put(currentDVD.getTitle(), currentDVD);
        }
        
        scanner.close();
    }
    
    
    /*
    ** Function Name: marshallDVD()
    ** Return Type: String
    ** Parameters: dvd
    ** Purpose: Takes a DVD object and parses all it's properties into a single line.
    **          Returns that line as a string with all the DVD's information ready 
    **          to be written. 
    */
    private String marshallDVD(DVD dvd){
        
        String dvdFromText = dvd.getTitle() + DELIMITER;
        
        dvdFromText += dvd.getReleaseDate() + DELIMITER;
        dvdFromText += dvd.getRating() + DELIMITER;
        dvdFromText += dvd.getDirector() + DELIMITER;
        dvdFromText += dvd.getStudio() + DELIMITER;
        dvdFromText += dvd.getUserComments();
        
        return dvdFromText;
    }
    
    
    /*
    ** Function Name: writeLibrary()
    ** Return Type: void
    ** Purpose: writes all DVD objects saved in memory to an external file. 
    */
    private void writeLibrary() throws DVDLibraryDAOException{
        
        PrintWriter out;
        
        try{
            out = new PrintWriter(new FileWriter(LIBRARY_LOCATION));
        } catch (IOException e){
            throw new DVDLibraryDAOException("Could not write to Library", e);
        }
        
        String dvdAsText;
        
        List<DVD> dvdLibrary = this.getAllDVDs();
        for (DVD currentDVD : dvdLibrary){
            dvdAsText = marshallDVD(currentDVD);
            out.println(dvdAsText);
            
            out.flush();
        }
        
        out.close();
    }
    
    // Map object to hold and efficiently search through different DVD objects. 
    private Map<String, DVD> dvds = new HashMap<>();    
    
}
