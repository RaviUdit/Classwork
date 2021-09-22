/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HealthyHearts;

import java.util.Scanner;

/**
 *
 * @author raviu
 */
public class HealthyHearts {
    
    public static void main(String[] args) {
        
        // Declare needed variables.
        Scanner userAgeInput = new Scanner(System.in);
        int userAge = 0;
        
        
        // Ask user for their age
        System.out.println("What is your age?");
        userAge = userAgeInput.nextInt();
        userAgeInput.nextLine();
        
        Calculate(userAge);
        
    }
    
    /*
    ** Function Name: Calculate
    ** Return Type: Void
    ** Purpose: Calculates and Prints the user's Max Heart Rate
    ** and taget HR Zone
    */
    public static void Calculate (int age){
        
        int maxHeartRate = 0;
        int zoneMin = 0;
        int zoneMax = 0;
        
        // Calculating Variables
        maxHeartRate = 220 - age; 
        zoneMin = (int) (maxHeartRate * 0.5);
        zoneMax = (int) (maxHeartRate * 0.85);
        
        // Printing out max heart rate and HR Zone. 
        System.out.println("Your maximum heart rate should be " + maxHeartRate + " beats per minute.");
        System.out.println("Your target HR Zone is " + zoneMin + " - " + zoneMax + " beats per minute.");
        
    }
    
}
