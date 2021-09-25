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
        dogName = dogNameInput.nextLine();
      //  dogNameInput.nextLine();
        
        DetermineBreed(dogName);

    }
    
    /*
    ** Function Name: DetermineBreed
    ** Return Type: Void
    ** Purpose: Generates the Dog's breed and prints the results. 
    */
    public static void DetermineBreed(String name){
 
        // Declaring Breed Array
        String[][] breeds = {{"Labrador Retriever", "Bulldog", "Golden Retriever", "German Shepard", "Beagle", "Poodle"}, 
                             {"Rottweiler", "Short Haired English Pointer", "Daschund", "Corgi", "Terrier", "Boxer"},
                             {"Great Dane", "Siberian Husky", "Shih Tzu", "Doberman", "Havanese", "Cane Corso"},
                             {"Spaniel", "Pug", "Border Collie", "Basset Hound", "Weimaraners", "Brittany"},
                             {"Shiba Inu", "Bloodhound", "Akita", "Bull Mastiff", "Tibetan Mastiff", "Samoyed"}};
        
        // Declaring variables used to determine Breed Percentages        
        int[] breedPercentages = {0, 0, 0, 0, 0};
        int totalPercentage = 0;
        
        Random percentages = new Random();
        Random breedDeterminer = new Random();
        
        // Determining percentages with for loop. 
        for (int i = 0; i < 4; i++){
            breedPercentages[i] = percentages.nextInt(98-totalPercentage) + 1;
            
            totalPercentage = totalPercentage + breedPercentages[i];
        }
        
        breedPercentages[4] = 100 - totalPercentage;
        
        // Printing Result
        System.out.println("Well then, I have this highly reliable report on " + name + "'s prestigious background right here.");
        
        for (int i = 0; i <= 4; i++){
            System.out.println( breedPercentages[i] + " " + breeds[i][breedDeterminer.nextInt(5)]);
        }
        
        System.out.println("How Marvelous!");
        
    }
    
}
