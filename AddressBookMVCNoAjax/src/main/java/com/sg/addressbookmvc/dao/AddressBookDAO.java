/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.addressbookmvc.dao;

import com.sg.addressbookmvc.model.Address;
import java.util.Map;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface AddressBookDAO {

    public Address addAddress(Address address);

    // remove the Contact with the given id from the data store

    public void removeAddress(int addressId);

    // update the given Contact in the data store

    public void updateAddress(Address address);

    // retrieve all Contacts from the data store

    public List<Address> getAllAddresses();

    // retrieve the Contact with the given id from the data store

    public Address getAddressById(int addressId);

    // search for Contacts by the given search criteria values

    public List<Address> searchAddresses(Map<SearchTerm, String> criteria);

}
