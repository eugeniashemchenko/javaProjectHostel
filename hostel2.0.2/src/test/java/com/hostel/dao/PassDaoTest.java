package com.hostel.dao;

import static org.junit.Assert.*;

import java.io.File;

import javax.sql.DataSource;

import org.dbunit.DataSourceBasedDBTestCase;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.Test;

import com.hostel.model.Pass;

import lombok.SneakyThrows;


public class PassDaoTest extends DataSourceBasedDBTestCase {
    private JdbcDataSource ds;
    
    private PassDao dao = new PassDao(getDataSource());

    public void testFindById() {
        Pass pass = dao.findById(Long.valueOf(1L));
        assertNotNull(pass);
        assertEquals(Long.valueOf(1L), pass.getId());
        assertEquals("SKU001", pass.getSku());
    }
    
    public void testFindByIdNull() {
        Pass pass = dao.findById(Long.valueOf(2L));
        assertNull(pass);
    }

    @SneakyThrows
    public void testCreate() {
        
        Pass pass = Pass.builder().sku("SKU002").build();
        
        pass = dao.create(pass);
        assertNotNull(pass);
        assertNotNull(pass.getId());
        
        assertEquals(2, getConnection().createDataSet().getTable("pass").getRowCount());
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
                .getClassLoader().getResourceAsStream("dataset/pass.xml"));
    }

}
