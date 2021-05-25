package com.hostel.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.sql.DataSource;

import com.hostel.model.Student;

import lombok.SneakyThrows;

public class JdbcStudentDao extends JdbcGenericDao<Student> implements StudentDao {

    public JdbcStudentDao(DataSource dataSource) {
        super(dataSource);
    }

    private static final String SQL_FIND_BY_ID = "SELECT id, sku FROM student WHERE id = ?";

    private static final String SQL_CREATE = "INSERT INTO student (sku) VALUES (?)";

    @Override
    protected String getFindByIdSql() {
        return SQL_FIND_BY_ID;
    }

    @Override
    protected String getCreateSql() {
        return SQL_CREATE;
    }

    @Override
    protected EntityMapper<Student> getEntityMapper() {

        return new EntityMapper<Student>() {

            @Override
            @SneakyThrows
            public Student fromResultSet(ResultSet resultSet) {
                Student student = Student.builder().id(resultSet.getLong("id"))
                      .sku(resultSet.getString("sku")).build();
                return student;
            }

            @Override
            @SneakyThrows
            public void fillStatement(PreparedStatement statement,
                    Student entity) {
                statement.setString(1, entity.getSku());
            }
        };
    }

    @Override
    public String getFindAllSql() {
        // TODO Auto-generated method stub
        return null;
    }

}
