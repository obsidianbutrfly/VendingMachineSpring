

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespring.service;

import com.sg.vendingmachinespring.dao.VendingMachineDao;
import com.sg.vendingmachinespring.dao.VendingMachinePersistenceException;
import com.sg.vendingmachinespring.model.Snacks;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Component;

/**
 *
 * @author ashleybesser
 */
@Component
public class VendingMachineService {
    
    @Inject
    private VendingMachineDao dao; //create, read, write, objects
    
    public VendingMachineService (VendingMachineDao dao){
        this.dao = dao;
    }
    
 
    public List<Snacks> getSnackInventory() throws VendingMachinePersistenceException, NumberFormatException {
        return dao.getAllSnacks();
    }
    

    public boolean validSelection(int id) throws VendingMachinePersistenceException, VendingMachineNoInventoryException {
        //checking to see if the selection works
        //if it does then it checks to see if there is inventory of the item
        boolean validItem = false;
        List<Snacks> snacks = getSnackInventory();
        for (Snacks snack : snacks) {
            if (snack.getSnackId() == id) { //checking to see if what the user entered is a valid selection

                if (snack.getQuantitySnacks() <= 0) { // if the item is valid then check the inventory stock
                    validItem = false;
                    throw new VendingMachineNoInventoryException("SOLD OUT!!!");
                } else {
                    validItem = true;
                }
            }
        }
        return validItem;
    }


    public BigDecimal sellItem(int id, BigDecimal userMoney) throws VendingMachinePersistenceException, VendingMachineInsufficentFundsException {
        //the item is valid, now we are checking to see if theu have enough money to buy the item
        //money comes from the main menu
        //compare it to the cost of the item
        //if the money they have is greater than the snackCost let them buy it
        //otherwise return insufficent funds error

        if (userMoney.compareTo(dao.getSnackPrice(id)) >= 0) { // if the item is greater than the cost sell it, subtract 1
            dao.sellSnack(id);   
            return dao.getSnackPrice(id);
        } else {
            BigDecimal checkMoney = dao.getSnackPrice(id).subtract(userMoney);
            throw new VendingMachineInsufficentFundsException("Please Deposit: $"+ checkMoney.setScale(2, RoundingMode.HALF_UP));
        }
        
    }
}
