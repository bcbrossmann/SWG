/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.addressbookmvc;

import com.sg.addressbookmvc.dao.AddressBookDAO;
import com.sg.addressbookmvc.dao.SearchTerm;
import com.sg.addressbookmvc.model.Address;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
* 
* @author apprentice
*/
    @Controller
public class SearchController {
    
    private AddressBookDAO dao;
    
    @Inject
    public SearchController(AddressBookDAO dao){
        this.dao = dao;
    }
    
    @RequestMapping(value="/search", method = RequestMethod.GET)
    public String displaySearchPage()
    {
        return "search";
    }
    
    @RequestMapping(value="search/addresses", method=RequestMethod.POST)
    @ResponseBody 
    public List<Address> searchAddresses(@RequestBody Map<String, String> searchMap){
        Map<SearchTerm, String> criteriaMap = new HashMap<>();
        
        String currentTerm = searchMap.get("residentLastName");
        if(!currentTerm.isEmpty()){
            criteriaMap.put(SearchTerm.RESIDENTLASTNAME, currentTerm);
        }
        
        currentTerm = searchMap.get("streetAddress");
        if(!currentTerm.isEmpty()){
            criteriaMap.put(SearchTerm.STREET_ADDRESS, currentTerm);
        }
        
        currentTerm = searchMap.get("cityName");
        if(!currentTerm.isEmpty()){
            criteriaMap.put(SearchTerm.CITY, currentTerm);
        }
        
        currentTerm = searchMap.get("state");
        if(!currentTerm.isEmpty()){
            criteriaMap.put(SearchTerm.STATE, currentTerm);
        }
        
        currentTerm = searchMap.get("zipCode");
        if(!currentTerm.isEmpty()){
            criteriaMap.put(SearchTerm.ZIP, currentTerm);
        }
        
        return dao.searchAddresses(criteriaMap);
        
    }
    
}

