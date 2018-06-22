/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespring.dao;

import com.sg.vendingmachinespring.model.Snacks;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author ashleybesser
 */
@Component
public class VendingMachineDaoInMemImpl implements VendingMachineDao {
    
    
    private List<Snacks> snackList = new ArrayList<>();

    public VendingMachineDaoInMemImpl() {
        loadSnacks();
    }
    
    
    
    
    @Override
    public List<Snacks> getAllSnacks() throws VendingMachinePersistenceException , NumberFormatException{
        return new ArrayList<>(snackList);
    }


    @Override
    public BigDecimal getSnackPrice(int snackId) {
        return snackList.get(selectedItemInfo(snackId)).getSnackCost();
    }

    @Override
    public void sellSnack(int snackId) throws VendingMachinePersistenceException {
        snackList.get(selectedItemInfo(snackId)).itemsSold();
        
    }
    
    //using this to get the price, id etc for the item the user selects.
    private int selectedItemInfo(int snackId){
        for(int i=0; i < snackList.size(); i++){
            if(snackList.get(i).getSnackId() == snackId) {
                return i;
            }
        }
        return -1;
    }
    
    
    private void loadSnacks() {
        snackList.clear();
        snackList.add(new Snacks(1, "Snickers", new BigDecimal(1.85).setScale(2, RoundingMode.HALF_UP),9));
        snackList.add(new Snacks(2, "Reese's", new BigDecimal(1.45).setScale(2, RoundingMode.HALF_UP),11));
        snackList.add(new Snacks(3, "Twix", new BigDecimal(1.25).setScale(2, RoundingMode.HALF_UP),6));
        snackList.add(new Snacks(4, "Twizzlers", new BigDecimal(2.10).setScale(2, RoundingMode.HALF_UP),5));
        snackList.add(new Snacks(5, "Cupcake", new BigDecimal(1.05).setScale(2, RoundingMode.HALF_UP),4));
        snackList.add(new Snacks(6, "Muffin", new BigDecimal(1.95).setScale(2, RoundingMode.HALF_UP),8));
        snackList.add(new Snacks(7, "PayDay", new BigDecimal(1.35).setScale(2, RoundingMode.HALF_UP),0));
        snackList.add(new Snacks(8, "Swiss Roll", new BigDecimal(2.25).setScale(2, RoundingMode.HALF_UP),10));
        snackList.add(new Snacks(9, "Gummy Worms", new BigDecimal(2.65).setScale(2, RoundingMode.HALF_UP),9));
    }



}
    
