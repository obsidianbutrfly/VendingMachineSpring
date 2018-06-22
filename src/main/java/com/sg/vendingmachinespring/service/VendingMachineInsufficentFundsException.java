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
public class VendingMachineInsufficentFundsException extends Exception{

    public VendingMachineInsufficentFundsException(String string) {
        super(string);
    }

    public VendingMachineInsufficentFundsException(String string, Throwable cause) {
        super(string, cause);
    }

}
