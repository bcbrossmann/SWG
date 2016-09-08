/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.addressbookmvc.dao;

import com.sg.addressbookmvc.model.Address;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * @author apprentice
 */
public class AddressBookDaoInMemImpl implements AddressBookDAO {

    private Map<Integer, Address> addressMap = new HashMap<>();

    private static int addressIdCounter = 0;

    @Override
    public Address addAddress(Address address) {
        address.setAddressId(addressIdCounter);
        addressIdCounter++;
        addressMap.put(address.getAddressId(), address);
        return address;
    }

    @Override
    public void removeAddress(int addressId) {
        addressMap.remove(addressId);
    }

    @Override
    public void updateAddress(Address address) {
        addressMap.put(address.getAddressId(), address);
    }

    @Override
    public List<Address> getAllAddresses() {
        Collection<Address> c = addressMap.values();
        return new ArrayList(c);
    }

    @Override
    public Address getAddressById(int addressId) {
        return addressMap.get(addressId);
    }

    @Override
    public List<Address> searchAddresses(Map<SearchTerm, String> criteria) {
        String lastNameCriteria = criteria.get(SearchTerm.RESIDENTLASTNAME);
        String streetAddressCriteria = criteria.get(SearchTerm.STREET_ADDRESS);
        String cityCriteria = criteria.get(SearchTerm.CITY);
        String stateCriteria = criteria.get(SearchTerm.STATE);
        String zipCriteria = criteria.get(SearchTerm.ZIP);

        Predicate<Address> lastNameMatches;
        Predicate<Address> streetAddressMatches;
        Predicate<Address> cityMatches;
        Predicate<Address> stateMatches;
        Predicate<Address> zipMatches;

        Predicate<Address> truePredicate = (c) -> {
            return true;
        };

        lastNameMatches = (lastNameCriteria == null || lastNameCriteria.isEmpty())
                ? truePredicate
                : (c) -> c.getResidentLastName().equals(lastNameCriteria);
        
        streetAddressMatches = (streetAddressCriteria == null || streetAddressCriteria.isEmpty())
                ? truePredicate
                : (c) -> c.getStreetAddress().equals(streetAddressCriteria);
        
        cityMatches = (cityCriteria == null || cityCriteria.isEmpty())
                ? truePredicate
                : (c) -> c.getCityName().equals(cityCriteria);
        
        stateMatches = (stateCriteria == null || stateCriteria.isEmpty())
                ? truePredicate
                : (c) -> c.getState().equals(stateCriteria);
        
        zipMatches = (zipCriteria == null || zipCriteria.isEmpty())
                ? truePredicate
                : (c) -> c.getZipCode().equals(zipCriteria);

        return addressMap.values().stream()
                .filter(lastNameMatches
                        .and(streetAddressMatches)
                        .and(cityMatches)
                        .and(stateMatches)
                        .and(zipMatches))
                .collect(Collectors.toList());

    }

}
