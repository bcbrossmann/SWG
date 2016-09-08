/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.sg.addressbookmvc.dao.AddressBookDAO;
import com.sg.addressbookmvc.dao.SearchTerm;
import com.sg.addressbookmvc.model.Address;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author apprentice
 */
public class AddressBookDaoTest {

    private AddressBookDAO dao;

    private Address a1;
    private Address a2;
    private Address a3;
    
    public AddressBookDaoTest() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("addressBookDAO", AddressBookDAO.class);
        
        a1 = new Address();
        a1.setResidentLastName("Rymal");
        a1.setStreetAddress("10953 Garfield");
        a1.setCityName("Coopersville");
        a1.setState("MI");
        a1.setZipCode("49404");
        
        a2 = new Address();
        a2.setResidentLastName("Smith");
        a2.setStreetAddress("10234 Samson");
        a2.setCityName("Coopersville");
        a2.setState("OH");
        a2.setZipCode("49411");
        
        a3 = new Address();
        a3.setResidentLastName("Jones");
        a3.setStreetAddress("123 Main");
        a3.setCityName("Thompsonville");
        a3.setState("IN");
        a3.setZipCode("12334");
        
        JdbcTemplate cleaner = ctx.getBean("jdbcTemplate", JdbcTemplate.class);
        cleaner.execute("DELETE FROM addresses");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void addGetDeleteAddress() {

        dao.addAddress(a1);
        
        Address fromDb = dao.getAddressById(a1.getAddressId());
        
        assertEquals(a1, fromDb);
        
        dao.removeAddress(a1.getAddressId());
        
        assertNull(dao.getAddressById(a1.getAddressId()));
        
    }

    @Test
    public void addUpdateAddress() {

        dao.addAddress(a1);
        
        a1.setState("FL");
        
        dao.updateAddress(a1);
        
        Address fromDb = dao.getAddressById(a1.getAddressId());
        
        assertEquals(a1,fromDb);
        
    }

    @Test
    public void getAllAddresses() {

        dao.addAddress(a1);
        dao.addAddress(a2);
        
        List<Address> cList = dao.getAllAddresses();
        assertEquals(2,cList.size());
        
    }

    @Test
    public void searchAddresses() {

        dao.addAddress(a1);
        dao.addAddress(a2);
        dao.addAddress(a3);
        
        Map<SearchTerm, String> criteria = new HashMap<>();
        criteria.put(SearchTerm.RESIDENTLASTNAME, "Rymal");
        List<Address> cList = dao.searchAddresses(criteria);
        assertEquals(1, cList.size());
        assertEquals(a1, cList.get(0));

       
        criteria.put(SearchTerm.RESIDENTLASTNAME, "Smith");
        cList = dao.searchAddresses(criteria);
        assertEquals(1, cList.size());

        criteria.put(SearchTerm.STATE, "OH");
        cList = dao.searchAddresses(criteria);
        assertEquals(1, cList.size());
        assertEquals(a2, cList.get(0));
 
        criteria.put(SearchTerm.STATE, "CA");
        cList = dao.searchAddresses(criteria);
        assertEquals(0, cList.size());

    }

}
