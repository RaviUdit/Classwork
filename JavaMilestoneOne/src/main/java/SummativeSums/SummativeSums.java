/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SummativeSums;

/**
 *
 * @author raviu
 */
public class SummativeSums {
    
    public static void main(String[] args) {
        
        // Declaring and Initializing Arrays
        int[] sumsOne = { 1, 90, -33, -55, 67, -16, 28, -55, 15 };
        int[] sumsTwo = { 999, -60, -77, 14, 160, 301 };
        int[] sumsThree = { 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 
                            130, 140, 150, 160, 170, 180, 190, 200, -99 } ;
        
        // Printing Results
        System.out.println("#1 Array sum: " + addArray(sumsOne));
        System.out.println("#2 Array sum: " + addArray(sumsTwo));
        System.out.println("#3 Array sum: " + addArray(sumsThree));
    }
    
    /*
    ** Function Name: AddArray
    ** Return Type: Int
    ** Purpose: Adds together all elements of an array and return the answer as
    **          an int. 
    */
    public static int addArray(int[] array){
        
        // Declaring variable.
        int sum = 0;
        
        // Addition loop.
        for( int i=0; i < array.length ; i++){
            
            sum = sum + array[i];
        }
        
        return sum;
    }
    
}
