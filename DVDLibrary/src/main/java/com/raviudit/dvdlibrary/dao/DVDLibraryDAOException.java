/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raviudit.dvdlibrary.dao;

/**
 *
 * @author raviu
 */
public class DVDLibraryDAOException extends Exception {
    
    /*
    ** Function Name: DVDLibraryDAOException
    ** Purpose: Gives error code if users are able to step out of program 
    ** parameters.  
    */
    public DVDLibraryDAOException(String message){
        super(message);
    }
    
    public DVDLibraryDAOException(String message, Throwable cause){
        super(message, cause);
    }
}
