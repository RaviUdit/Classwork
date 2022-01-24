/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raviudit.vendingmachine.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author raviu
 */
public class VendingMachineChange {
    
    public BigDecimal makeChange(Coins coin, BigDecimal remainingMoney){
        
        BigDecimal quarter = new BigDecimal("0.25");
        BigDecimal dime = new BigDecimal("0.10");
        BigDecimal nickel = new BigDecimal("0.05");
        BigDecimal penny = new BigDecimal("0.01");
        
        BigDecimal coinsReturned = new BigDecimal("0.00");
        BigDecimal change = new BigDecimal("0.00");
        
        
        // change.compareTo(quarter); //-1 if less than
                                   // 0 if equal
                                   // 1 if greater than
                                   
        switch (coin){
            case QUARTER:
                coinsReturned = remainingMoney.divide(quarter, 0, RoundingMode.DOWN);
                System.out.println("You receive back " + coinsReturned.toString() + " quarters");
                
                change = remainingMoney.subtract(coinsReturned.multiply(quarter));
                return change;
                
            case DIME:
                coinsReturned = remainingMoney.divide(dime, 0, RoundingMode.DOWN);
                System.out.println("You receive back " + coinsReturned.toString() + " dimes");
                
                change = remainingMoney.subtract(coinsReturned.multiply(dime));
                return change;
                
            case NICKEL:
                coinsReturned = remainingMoney.divide(nickel, 0, RoundingMode.DOWN);
                System.out.println("You receive back " + coinsReturned.toString() + " nickels");
                
                change = remainingMoney.subtract(coinsReturned.multiply(nickel));
                return change;
                
            case PENNY:
                coinsReturned = remainingMoney.divide(penny, 0, RoundingMode.DOWN);
                System.out.println("You receive back " + coinsReturned.toString() + " pennies");
                
                return change;
                
            default:
                throw new UnsupportedOperationException("Unknown Coin Type");
        }
        
    }
}
