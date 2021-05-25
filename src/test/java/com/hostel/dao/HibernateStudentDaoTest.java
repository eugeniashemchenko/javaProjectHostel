package com.hostel.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.io.File;

import javax.sql.DataSource;

import org.dbunit.DataSourceBasedDBTestCase;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.github.database.rider.core.DBUnitRule;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.dataset.DataSet;
import com.hostel.dao.StudentDao;
import com.hostel.dao.hibernate.HibernateStudentDao;
import com.hostel.model.Student;

import lombok.SneakyThrows;

@RunWith(JUnit4.class)
@DBUnit(url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", user = "sa")
public class HibernateStudentDaoTest {
    @Rule
    public SessionProvider sessionProvider = SessionProvider.getInstance();

    @Rule
    public DBUnitRule dbUnitRule = DBUnitRule.instance();

    private StudentDao dao = new HibernateStudentDao(sessionProvider.getSessionFactory());

    @Test
    @DataSet(value = "dataset/yml/students.yml", cleanAfter = true)
    public void testFindById() {
        Student student = dao.findById(Long.valueOf(-1L));
        assertThat(student).isNotNull();
        assertThat(student.getId()).isEqualTo(-1L);
        assertThat(student.getSku()).isEqualTo("SKU001");
    }

    @Test
    public void testFindByIdNull() {
        Student student = dao.findById(Long.valueOf(2L));
        assertThat(student).isNull();
    }

    @SneakyThrows
    @Test
    public void testCreate() {

        Student student = Student.builder().sku("SKU002").build();

        dao.create(student);

        assertThat(student).isNotNull();
        assertThat(student.getId()).isNotNull();

    }

}
