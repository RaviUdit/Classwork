/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DogGenetics;

import java.util.Random;
import java.util.Scanner;


/**
 *
 * @author raviu
 */
public class DogGenetics {
    
    public static void main(String[] args) {
        
        //Declaring Variables
        String dogName = "";
        Scanner dogNameInput = new Scanner(System.in);
        
        // Getting the Dog's name.
        System.out.println("What is the name of your dog?");
        dogName = dogNameInput.next();
        dogNameInput.nextLine();
        
        DetermineBreed(dogName);

    }
    
    /*
    ** Function Name: DetermineBreed
    ** Return Type: Void
    ** Purpose: Generates the Dog's breed and prints the results. 
    */
    public static void DetermineBreed(String name){
 
        // Declaring Breed Array
        String[] breeds = {"Labrador Retriever", "Bulldog", "Golden Retriever", "German Shepard", "Beagle", "Poodle", 
                            "Rottweiler", "Short Haired English Pointer", "Daschund", "Corgi", "Terrier", "Boxer"};
        
    }
    
}
