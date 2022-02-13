/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raviudit.vendingmachine.dao;

import com.raviudit.vendingmachine.dto.Vendable;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author raviu
 */
public class VendingMachineDAOFileImpl implements VendingMachineDAO{
    
    private Map<String, Vendable> vendables = new HashMap<>();
    
    //File Code 
    private final String STOCK_LOCATION;
    public static final String DELIMITER = ":&:";
       
    public VendingMachineDAOFileImpl(){
        STOCK_LOCATION = "vendables.txt";
    }
    
    //Used for instances of VendingMachineDAOFileImpl instanced with other files.
    public VendingMachineDAOFileImpl(String stockTextFile){
        STOCK_LOCATION = stockTextFile;
    }


    //Overrides
    
    /*
    ** Function Name: updateVendable
    ** Return Type: Vendable
    ** Purpose: Writes an entry to a file defined by STOCK_LOCATION updating the fille with the most current
    **          numbers and status of vendable items.  
    */
    @Override
    public Vendable updateVendables(String vendableName, Vendable vendable) throws VendingMachineDAOException{
        
        loadFromFile();
        Vendable updatedVendable = vendables.put(vendableName, vendable);
        writeToFile();
        
        return updatedVendable;
    }
    
    /*
    ** Function Name: getAllVendables
    ** Return Type: List<Vendable>
    ** Purpose: Returns a list filled with vendable objects.   
    */
    @Override
    public List<Vendable> getAllVendables() throws VendingMachineDAOException{
        
        loadFromFile();
        return new ArrayList<Vendable>(vendables.values());
    }
    
    /*
    ** Function Name: getVendable
    ** Return Type: Vendable
    ** Purpose: returns a single vendable object after obtaining the name defined by vendableName  
    */
    @Override
    public Vendable getVendable(String vendableName) throws VendingMachineDAOException{
        
        loadFromFile();
        return vendables.get(vendableName);
    }
    
    /*
    ** Function Name: vendVendable
    ** Return Type: Void
    ** Purpose: Sets the vendable stock to current level minus one.
    */
    @Override
    public void vendVendable(Vendable vendable){
        
       // BigDecimal one = new BigDecimal("1");  
       // BigDecimal newStock = new BigDecimal(vendable.getCurrentStock());
       // newStock = newStock.subtract(one);
       
       int newStock = vendable.getCurrentStock() - 1;
       
       vendable.setCurrentStock(newStock);
        
    }
    

    
    //Reading and Writing to File
    
    /*
    ** Function Name: unmarshallVendable
    ** Return Type: Vendable
    ** Purpose: Reads vendables from the a string in a library defined by STOCK_LOCATION
    **          and turns it into a Vendable object. 
    */
    private Vendable unmarshallVendables(String vendableFromFile){
        
        String[] vendableProperties = vendableFromFile.split(DELIMITER);
        
        String vendableName = vendableProperties[0];
        
        Vendable vendableFromLibrary = new Vendable(vendableName);
        vendableFromLibrary.setItemPrice(vendableProperties[1]);
        vendableFromLibrary.setMaxStock(Integer.parseInt(vendableProperties[2]));
        vendableFromLibrary.setCurrentStock(Integer.parseInt(vendableProperties[3]));
        
        return vendableFromLibrary;
    }
    
    /*
    ** Function Name: marshallVendables
    ** Return Type: String
    ** Purpose: Turns a vendable object into a string. 
    */
    private String marshallVendables(Vendable vendable){
        
        String vendableFromText  = vendable.getItemName() + DELIMITER;
        vendableFromText += vendable.getItemPrice()+ DELIMITER;
        vendableFromText += vendable.getMaxStock() + DELIMITER;
        vendableFromText += vendable.getCurrentStock();
        
        return vendableFromText;
    }
    
    /*
    ** Function Name: loadFromFile
    ** Return Type: Void
    ** Purpose: Loads information from a file defined by STOCK_LOCATION and turns that data into Vendable ocjects
    */
    private void loadFromFile() throws VendingMachineDAOException{
        
        Scanner scanner;
        
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(STOCK_LOCATION)));
        } catch(FileNotFoundException e){
            throw new VendingMachineDAOException("could not load stock.", e);
        }
        
        String currentLine;
        Vendable currentVendable;
        
        while (scanner.hasNextLine()){
            currentLine = scanner.nextLine();
            currentVendable = unmarshallVendables(currentLine);
            
            vendables.put(currentVendable.getItemName(), currentVendable);
        }
        
        scanner.close();
    }
    
    /*
    ** Function Name: writeToFile
    ** Return Type: Void
    ** Purpose: Write to a file defined by STOCK_LOCATION a series of strings that represent Vendable objects. 
    */
    private void writeToFile() throws VendingMachineDAOException{
        
        PrintWriter out;
        
        try{
            out = new PrintWriter(new FileWriter(STOCK_LOCATION));
        } catch (IOException e){
            throw new VendingMachineDAOException("Could not save Stock", e);
        }
        
        String vendablesAsText;
        
        List<Vendable> stock = this.getAllVendables();
        for (Vendable currentVendable : stock){
             vendablesAsText = marshallVendables(currentVendable);
             out.println(vendablesAsText);
             
             out.flush();
        }
        
        out.close();
        
    }
}
