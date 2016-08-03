package com.sg.dvdlibrarymvc;

import com.sg.dvdlibrarymvc.dao.DvdLibraryDao;
import com.sg.dvdlibrarymvc.dao.SearchTerm;
import com.sg.dvdlibrarymvc.model.DVD;
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
public class DvdLibraryDaoTest {

    private DvdLibraryDao dao;
    private DVD dvd1;
    private DVD dvd2;
    private DVD dvd3;

    public DvdLibraryDaoTest() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("dvdLibraryDao", DvdLibraryDao.class);
        dvd1 = new DVD();
        dvd1.setTitle("Star Wars");
        dvd1.setReleaseDate("1977");
        dvd1.setMpaaRating("PG");
        dvd1.setDirectorName("George Lucas");
        dvd1.setStudio("Lucasfilm");
        dvd1.setUserRating("9");
        dvd2 = new DVD();
        dvd2.setTitle("The Empire Strikes Back");
        dvd2.setReleaseDate("1980");
        dvd2.setMpaaRating("PG");
        dvd2.setDirectorName("Ian Kirshner");
        dvd2.setStudio("Lucasfilm");
        dvd2.setUserRating("10");
        dvd3 = new DVD();
        dvd3.setTitle("Return of the Jedi");
        dvd3.setReleaseDate("1983");
        dvd3.setMpaaRating("PG");
        dvd3.setDirectorName("George Lucas");
        dvd3.setStudio("Lucasfilm");
        dvd3.setUserRating("10");
        JdbcTemplate cleaner = ctx.getBean("jdbcTemplate", JdbcTemplate.class);
        cleaner.execute("DELETE FROM dvds");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void addGetDeleteDVD() {
        dao.addDVD(dvd1);
        DVD fromDb = dao.getDVDById(dvd1.getDVDId());
        assertEquals(dvd1, fromDb);
        dao.removeDVD(dvd1.getDVDId());
        assertNull(dao.getDVDById(dvd1.getDVDId()));
    }

    @Test
    public void addUpdateDVD() {
        dao.addDVD(dvd1);
        dvd1.setReleaseDate("2015");
        dao.updateDVD(dvd1);
        DVD fromDb = dao.getDVDById(dvd1.getDVDId());
        assertEquals(dvd1, fromDb);
    }

    @Test
    public void getAllDVD() {
        dao.addDVD(dvd1);
        dao.addDVD(dvd2);
        List<DVD> cList = dao.getAllDVDs();
        assertEquals(2, cList.size());
    }

    @Test
    public void searchDVDs() {
        dao.addDVD(dvd1);
        dao.addDVD(dvd2);
        dao.addDVD(dvd3);
        Map<SearchTerm, String> criteria = new HashMap<>();
        criteria.put(SearchTerm.TITLE, "The Empire Strikes Back");
        List<DVD> cList = dao.searchDVDs(criteria);
        assertEquals(1, cList.size());
        assertEquals(dvd2, cList.get(0));
        criteria = new HashMap<>();
        criteria.put(SearchTerm.USER_RATING, "10");
        cList = dao.searchDVDs(criteria);
        assertEquals(2, cList.size());
        criteria = new HashMap<>();
        criteria.put(SearchTerm.TITLE, "Bob");
        cList = dao.searchDVDs(criteria);
        assertEquals(0, cList.size());
        criteria.put(SearchTerm.TITLE, "Return of the Jedi");
        cList = dao.searchDVDs(criteria);
        assertEquals(1, cList.size());
        assertEquals(dvd3, cList.get(0));
        criteria.put(SearchTerm.TITLE, "Apple");
        cList = dao.searchDVDs(criteria);
        assertEquals(0, cList.size());
    }
}
