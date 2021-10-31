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

    @Override
    public DVD addDVD(String title, DVD dvd) throws DVDLibraryDAOException{
        loadLibrary();
        DVD newDVD = dvds.put(title, dvd);
        writeLibrary();
        return newDVD;
    }

    @Override
    public List<DVD> getAllDVDs() throws DVDLibraryDAOException{
        loadLibrary();
        return new ArrayList<DVD>(dvds.values());
    }

    @Override
    public DVD getDVD(String title) throws DVDLibraryDAOException{
        loadLibrary();
        return dvds.get(title);
    }

    @Override
    public DVD deleteDVD(String title) throws DVDLibraryDAOException{
        loadLibrary();
        DVD deletedDVD = dvds.remove(title);
        writeLibrary();
        return deletedDVD;
    }

    @Override
    public DVD editDVD(String title) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
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
    
    private String marshallDVD(DVD dvd){
        
        String dvdFromText = dvd.getTitle() + DELIMITER;
        
        dvdFromText += dvd.getReleaseDate() + DELIMITER;
        dvdFromText += dvd.getRating() + DELIMITER;
        dvdFromText += dvd.getDirector() + DELIMITER;
        dvdFromText += dvd.getStudio() + DELIMITER;
        dvdFromText += dvd.getUserComments();
        
        return dvdFromText;
    }
    
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
    
    private Map<String, DVD> dvds = new HashMap<>();
    
    
    
}
