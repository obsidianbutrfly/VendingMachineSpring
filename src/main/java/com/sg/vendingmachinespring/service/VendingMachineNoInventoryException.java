/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespring.service;

/**
 *
 * @author ashleybesser
 */
public class VendingMachineNoInventoryException extends Exception{
    
    public VendingMachineNoInventoryException(String string){
        super(string);
    }
    
    public VendingMachineNoInventoryException(String string, Throwable cause){
        super(string, cause);
    }
}
