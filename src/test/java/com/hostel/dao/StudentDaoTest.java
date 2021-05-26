package com.hostel.dao;

import static org.junit.Assert.*;

import java.io.File;

import javax.sql.DataSource;

import org.dbunit.DataSourceBasedDBTestCase;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.Test;

import com.hostel.dao.JdbcStudentDao;
import com.hostel.dao.StudentDao;
import com.hostel.model.Student;

import lombok.SneakyThrows;


public class StudentDaoTest extends DataSourceBasedDBTestCase {
    private JdbcDataSource ds;

    private StudentDao dao = new JdbcStudentDao(getDataSource());

    public void testFindById() {
        Student student = dao.findById(Long.valueOf(1L));
        assertNotNull(student);
        assertEquals(Long.valueOf(1L), student.getId());
        assertEquals("SKU001", student.getSku());
    }

    public void testFindByIdNull() {
        Student student = dao.findById(Long.valueOf(2L));
        assertNull(student);
    }

    @SneakyThrows
    public void testCreate() {

        Student student = Student.builder().sku("SKU002").build();

        student = dao.create(student);
        assertNotNull(student);
        assertNotNull(student.getId());

        assertEquals(2, getConnection().createDataSet().getTable("student").getRowCount());
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
                .getClassLoader().getResourceAsStream("dataset/student.xml"));
    }

}
