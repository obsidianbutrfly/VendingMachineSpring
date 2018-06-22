/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespring.controller;

import com.sg.vendingmachinespring.dao.VendingMachinePersistenceException;
import com.sg.vendingmachinespring.model.Change;
import com.sg.vendingmachinespring.service.VendingMachineInsufficentFundsException;
import com.sg.vendingmachinespring.service.VendingMachineNoInventoryException;
import com.sg.vendingmachinespring.service.VendingMachineService;
import java.math.BigDecimal;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author ashleybesser
 */
@Controller
public class VendingController {

    int snackId = 0;
    BigDecimal snackCost = BigDecimal.ZERO;
    String message = "";
    String change = "";

    @Inject
    VendingMachineService service;

    public VendingController(VendingMachineService service) {
        this.service = service;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {

        try {
            model.addAttribute("items", service.getSnackInventory());
            if (snackId == 0) {
                model.addAttribute("selection", "");
            } else {
                model.addAttribute("selection", snackId);
            }
            model.addAttribute("userMoney", snackCost);

            model.addAttribute("change", change);

            model.addAttribute("messages", message);

        } catch (VendingMachinePersistenceException e) {

        }
        return "index";

    }

    @RequestMapping(value = "/inputMoney", method = RequestMethod.POST)
    public String inputMoney(HttpServletRequest request, Model model) {
        BigDecimal newSnackCost = new BigDecimal(request.getParameter("userMoney"));
        snackCost = snackCost.add(newSnackCost);

        return "redirect:/";
    }

    @RequestMapping(value = "/makePurchase", method = RequestMethod.POST)
    public String makePurchase() {
        //check if the selection is valid
        //check if they have enough money to purcahse
        //should also get error if sold out
        try {
            if (service.validSelection(snackId)) {
                snackCost = snackCost.subtract(service.sellItem(snackId, snackCost));
                message = "THANK YOU!!";
                change = Change.makeChange(snackCost);
                resetAllForms();
            }

        } catch (VendingMachinePersistenceException | VendingMachineNoInventoryException | VendingMachineInsufficentFundsException ex) {
            message = ex.getMessage();
        }

        return "redirect:/";

    }

    @RequestMapping(value = "/selectItem/{snackId}", method = RequestMethod.POST)
    public String selectItem(@PathVariable Integer snackId) {
        this.snackId = snackId;

        return "redirect:/";
    }

    @RequestMapping(value = "/returnChange", method = RequestMethod.POST)
    public String returnChange() {

        change = Change.makeChange(snackCost);
        
        resetAllForms();
        return "redirect:/";
        
    }

    public void resetAllForms() {
        snackId = 0;
        snackCost = BigDecimal.ZERO;

    }

}
