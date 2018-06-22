/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespring.model;

import java.math.BigDecimal;

/**
 *
 * @author ashleybesser
 */
public class Snacks {
    
    private String snackName;
    private BigDecimal snackCost;
    private int quantitySnacks;
    private int snackId;

    //I want the menu etc to use both the title and the cost amount

    public Snacks(int snackId, String snackName, BigDecimal snackCost, int quantitySnacks) {
        this.snackName = snackName;
        this.snackCost = snackCost;
        this.snackId = snackId;
        this.quantitySnacks = quantitySnacks;
    }

    public int getSnackId() {
        return snackId;
    }

    public void setSnackId(int snackId) {
        this.snackId = snackId;
    }
    
    public String getSnackName() {
        return snackName;
    }

    public BigDecimal getSnackCost() {
        return snackCost;
    }

    public int getQuantitySnacks() {
        return quantitySnacks;
    }

    public void setQuantitySnacks(int quanitySnacks) {
        this.quantitySnacks = quanitySnacks;
    }


    public void itemsSold() {
        quantitySnacks--;
    }
    

    
    
}
