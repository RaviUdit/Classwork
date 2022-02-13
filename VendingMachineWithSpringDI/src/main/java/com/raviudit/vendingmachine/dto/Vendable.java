/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raviudit.vendingmachine.dto;

import java.util.Objects;

/**
 *
 * @author raviu
 */

 /*
** Name: Vendable
** Type: DTO
** Properties: String itemName
               String itemPrice
               Int currentStock
               Int maxStock
*/

public class Vendable {
    
    
    // Testing Code
    @Override
    public String toString() {
        return "Vendable{" + "itemName=" + itemName + ", itemPrice=" + itemPrice + ", currentStock=" + currentStock + ", maxStock=" + maxStock + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.itemName);
        hash = 67 * hash + Objects.hashCode(this.itemPrice);
        hash = 67 * hash + this.currentStock;
        hash = 67 * hash + this.maxStock;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vendable other = (Vendable) obj;
        if (this.currentStock != other.currentStock) {
            return false;
        }
        if (this.maxStock != other.maxStock) {
            return false;
        }
        if (!Objects.equals(this.itemName, other.itemName)) {
            return false;
        }
        if (!Objects.equals(this.itemPrice, other.itemPrice)) {
            return false;
        }
        return true;
    }
    
    //Functional Code
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
