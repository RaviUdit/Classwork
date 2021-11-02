/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raviudit.dvdlibrary.dao;

import com.raviudit.dvdlibrary.dto.DVD;
import java.util.List;

/**
 *
 * @author raviu
 */

    /*
    ** Function Name: DVDLibraryDAO
    ** Type: interface
    */
public interface DVDLibraryDAO {
    
    DVD addDVD(String title, DVD dvd) throws DVDLibraryDAOException;
    
    List<DVD> getAllDVDs() throws DVDLibraryDAOException;
    
    DVD getDVD(String title) throws DVDLibraryDAOException;
    
    DVD deleteDVD(String title) throws DVDLibraryDAOException;
    
    //void editDVD(String title) throws DVDLibraryDAOException;
    
}
