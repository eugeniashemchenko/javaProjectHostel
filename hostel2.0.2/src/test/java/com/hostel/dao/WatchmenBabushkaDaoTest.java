package com.hostel.dao;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.dbunit.DataSourceBasedDBTestCase;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.Test;

import com.hostel.model.WatchmenBabushka;

import lombok.SneakyThrows;

public class WatchmenBabushkaDaoTest extends DataSourceBasedDBTestCase {
    private JdbcDataSource ds;

    public void testFindById() {
        JdbcGenericDao<WatchmenBabushka> dao = new WatchmenBabushkaDao(getDataSource());
        WatchmenBabushka watchmenBabushka = dao.findById(Long.valueOf(1L));
        assertNotNull(watchmenBabushka);
        assertEquals(Long.valueOf(1L), watchmenBabushka.getId());
        assertEquals("Галя", watchmenBabushka.getName());
    }
    public void testFindByIdNull() {
        JdbcGenericDao<WatchmenBabushka> dao = new WatchmenBabushkaDao(getDataSource());
        WatchmenBabushka watchmenBabushka = dao.findById(Long.valueOf(2L));
        assertNull(watchmenBabushka);
    }

    @SneakyThrows
    public void testCreate() {
        JdbcGenericDao<WatchmenBabushka> dao = new WatchmenBabushkaDao(getDataSource());
        
        WatchmenBabushka watchmenBabushka = WatchmenBabushka.builder().name("Strange").build();
        
        watchmenBabushka = dao.create(watchmenBabushka);
        assertNotNull(watchmenBabushka);
        assertNotNull(watchmenBabushka.getId());
        
        assertEquals(2, getConnection().createDataSet().getTable("watchmenBabushka").getRowCount());
    }

    @Override
    protected DataSource getDataSource() {
        if (ds == null) {
            String file = getClass().getClassLoader().getResource("schema.sql").getFile();
            
            ds = new JdbcDataSource();
            ds.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;INIT=RUNSCRIPT FROM '" + file + "'");
            ds.setUser("sa");
            ds.setPassword("");
        }
        return ds;
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(getClass()
                .getClassLoader().getResourceAsStream("dataset/watchmenBabushka.xml"));
    }

}
