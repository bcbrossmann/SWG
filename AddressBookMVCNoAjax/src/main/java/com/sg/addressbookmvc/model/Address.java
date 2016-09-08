/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.addressbookmvc.model;

import java.util.Objects;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author apprentice
 */
public class Address {
    private Integer addressId;
    @NotEmpty(message="You must supply a value for Resident Last Name.")
    @Length(max=50, message="Resident Last Name must be no more than 50 characters in length.")
    private String residentLastName;
    @NotEmpty(message="You must supply a value for Street Address.")
    @Length(max=50, message="Street Address must be no more than 50 characters in length.")
    private String streetAddress;
    @NotEmpty(message="You must supply a value for City Name.")
    @Length(max=50, message="City Name must be no more than 50 characters in length.")
    private String cityName;
    @NotEmpty(message="You must supply a 2-letter abbreviation for State.")
    @Length(max=2, message="State must be no more than 2 characters in length.")
    private String state;
    @NotEmpty(message="You must supply a value for Zip Code.")
    @Length(max=5, message="Zip Code must be no more than 5 characters in length.")
    private String zipCode;

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + Objects.hashCode(this.streetAddress);
        hash = 19 * hash + Objects.hashCode(this.cityName);
        hash = 19 * hash + Objects.hashCode(this.state);
        hash = 19 * hash + Objects.hashCode(this.zipCode);
        hash = 19 * hash + Objects.hashCode(this.addressId);
        hash = 19 * hash + Objects.hashCode(this.residentLastName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Address other = (Address) obj;
        if (!Objects.equals(this.streetAddress, other.streetAddress)) {
            return false;
        }
        if (!Objects.equals(this.cityName, other.cityName)) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.zipCode, other.zipCode)) {
            return false;
        }
        if (!Objects.equals(this.addressId, other.addressId)) {
            return false;
        }
        if (!Objects.equals(this.residentLastName, other.residentLastName)) {
            return false;
        }
        return true;
    }


 
    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }


    public String getResidentLastName() {
        return residentLastName;
    }

    public void setResidentLastName(String residentLastName) {
        this.residentLastName = residentLastName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    

    
    
}
