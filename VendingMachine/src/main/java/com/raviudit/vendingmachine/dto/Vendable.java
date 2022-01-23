/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raviudit.vendingmachine.dto;

/**
 *
 * @author raviu
 */
public class Vendable {
    
    private String itemName; 
    private String itemPrice;
    private int currentStock;
    private int maxStock;
    
    public Vendable(String name){
        this.itemName = name;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public int getMaxStock() {
        return maxStock;
    }
    
    public int getCurrentStock() {
        return currentStock;
    }


    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setMaxStock(int maxStock) {
        this.maxStock = maxStock;
    }

    public void setCurrentStock(int currentStock) {
        this.currentStock = currentStock;
    }
    
    
}
