/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.addressbookmvc;

import com.sg.addressbookmvc.dao.AddressBookDAO;
import com.sg.addressbookmvc.model.Address;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author apprentice
 */
@Controller
public class HomeControllerNoAjax {

    private AddressBookDAO dao;

    @Inject
    public HomeControllerNoAjax(AddressBookDAO dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/displayAddressBookNoAjax", method = RequestMethod.GET)
    public String displayAddressBookNoAjax(Model model) {

        List<Address> cList = dao.getAllAddresses();

        model.addAttribute("addressList", cList);

        return "displayAddressBookNoAjax";
    }

    @RequestMapping(value = "/displayNewAddressFormNoAjax", method = RequestMethod.GET)
    public String displayNewAddressFormNoAjax() {

        return "newAddressFormNoAjax";

    }

    @RequestMapping(value = "/addNewAddressNoAjax", method = RequestMethod.POST)
    public String addNewAddressNoAjax(HttpServletRequest req) {

        String residentLastName = req.getParameter("residentLastName");
        String streetAddress = req.getParameter("streetAddress");
        String cityName = req.getParameter("cityName");
        String state = req.getParameter("state");
        String zipCode = req.getParameter("zipCode");

        Address address = new Address();
        address.setResidentLastName(residentLastName);
        address.setStreetAddress(streetAddress);
        address.setCityName(cityName);
        address.setState(state);
        address.setZipCode(zipCode);

        dao.addAddress(address);

        return "redirect:displayAddressBookNoAjax";
    }

    @RequestMapping(value = "/deleteAddressNoAjax", method = RequestMethod.GET)
    public String deleteAddressNoAjax(HttpServletRequest req) {

        int addressId = Integer.parseInt(req.getParameter("addressId"));

        dao.removeAddress(addressId);

        return "redirect:displayAddressBookNoAjax";
    }

    @RequestMapping(value = "/displayEditAddressFormNoAjax", method = RequestMethod.GET)
    public String displayEditAddressFormNoAjax(HttpServletRequest req, Model model) {

        int addressId = Integer.parseInt(req.getParameter("addressId"));

        Address address = dao.getAddressById(addressId);

        model.addAttribute("address", address);

        return "editAddressFormNoAjax";
    }

    @RequestMapping(value = "/editAddressNoAjax", method = RequestMethod.POST)
    public String editAddressNoAjax(@Valid @ModelAttribute("address") Address address, BindingResult result) {

        if (result.hasErrors()) {
            return "editAddressFormNoAjax";
        }

        dao.updateAddress(address);
        return "redirect:displayAddressBookNoAjax";
    }
}
