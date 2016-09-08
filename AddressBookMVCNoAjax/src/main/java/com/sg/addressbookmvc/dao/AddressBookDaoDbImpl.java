/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.addressbookmvc.dao;

import com.sg.addressbookmvc.model.Address;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class AddressBookDaoDbImpl implements AddressBookDAO{

    private static final String SQL_INSERT_ADDRESS
            ="INSERT INTO addresses(residentLastName, streetAddress, cityName, state, zipCode)"
            +"values(?, ?, ?, ?, ?)";
    
    private static final String SQL_DELETE_ADDRESS
            = "DELETE FROM addresses WHERE address_id=?";

    private static final String SQL_SELECT_ADDRESS
            = "SELECT * FROM addresses WHERE address_id=?";

    private static final String SQL_UPDATE_ADDRESS
            = "UPDATE addresses SET residentLastName=?, streetAddress=?, cityName=?, state=?, zipCode=?"
            + "WHERE address_id=?";

    private static final String SQL_SELECT_ALL_ADDRESSES
            = "SELECT * FROM addresses";
    
    private JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public Address addAddress(Address address) {
        jdbcTemplate.update(SQL_INSERT_ADDRESS,
                address.getResidentLastName(),
                address.getStreetAddress(),
                address.getCityName(),
                address.getState(),
                address.getZipCode()
                );
        address.setAddressId(jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class));
        return address;
    }

    @Override
    public void removeAddress(int addressId) {
        jdbcTemplate.update(SQL_DELETE_ADDRESS, addressId);
    }

    @Override
    public void updateAddress(Address address) {
        jdbcTemplate.update(SQL_UPDATE_ADDRESS,
                address.getResidentLastName(),
                address.getStreetAddress(),
                address.getCityName(),
                address.getState(),
                address.getZipCode(),
                address.getAddressId()
        );
    }

    @Override
    public List<Address> getAllAddresses() {
        return jdbcTemplate.query(SQL_SELECT_ALL_ADDRESSES, new AddressMapper());
    }

    @Override
    public Address getAddressById(int addressId) {
        try{
            return jdbcTemplate.queryForObject(SQL_SELECT_ADDRESS, new AddressMapper(), addressId);
        }catch(EmptyResultDataAccessException ex){
            return null;
        }
    }

    @Override
    public List<Address> searchAddresses(Map<SearchTerm, String> criteria) {
        
        if(criteria == null || criteria.size()==0){
            return getAllAddresses();
        }
        
        StringBuilder query = new StringBuilder("SELECT * FROM addresses WHERE ");
        
        int numParams = criteria.size();
        int paramPosition = 0;
        
        String[] paramVals = new String[numParams];
        
        Set<SearchTerm> keyset = criteria.keySet();
        Iterator<SearchTerm> iter = keyset.iterator();
        
        while(iter.hasNext()){
            SearchTerm currentKey = iter.next();
            String currentValue = criteria.get(currentKey);
            
            if(paramPosition>0){
                query.append(" and ");
            }
            
            query.append(currentKey);
            query.append(" =? ");
            
            paramVals[paramPosition] = currentValue;
            paramPosition++;
        }
        
        return jdbcTemplate.query(query.toString(), new AddressMapper(), paramVals);
    }
    
    private static final class AddressMapper implements RowMapper<Address> {

        @Override
        public Address mapRow(ResultSet rs, int i) throws SQLException {
            Address address = new Address();
            address.setAddressId(rs.getInt("address_id"));
            address.setResidentLastName(rs.getString("residentLastName"));
            address.setStreetAddress(rs.getString("streetAddress"));
            address.setCityName(rs.getString("cityName"));
            address.setState(rs.getString("state"));
            address.setZipCode(rs.getString("zipCode"));
            return address;
        }

    }
    
}
