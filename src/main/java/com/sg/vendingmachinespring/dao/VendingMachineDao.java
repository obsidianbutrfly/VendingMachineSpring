/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespring.dao;

import com.sg.vendingmachinespring.model.Snacks;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author ashleybesser
 */
public interface VendingMachineDao {
    
    
    public List<Snacks> getAllSnacks() throws VendingMachinePersistenceException, NumberFormatException;


    public BigDecimal getSnackPrice(int snackId)throws VendingMachinePersistenceException;
    
    void sellSnack(int snackId) throws VendingMachinePersistenceException;
    
        
}
