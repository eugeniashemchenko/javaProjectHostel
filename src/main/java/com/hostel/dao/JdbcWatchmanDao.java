package com.hostel.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.sql.DataSource;

import com.hostel.model.Watchman;

import lombok.SneakyThrows;

public class JdbcWatchmanDao extends JdbcGenericDao<Watchman> implements WatchmanDao {

    public JdbcWatchmanDao(DataSource dataSource) {
        super(dataSource);
    }

    private static final String SQL_FIND_BY_ID = "SELECT id, name FROM watchman WHERE id = ?";

    private static final String SQL_CREATE = "INSERT INTO watchman (username, password, fullName) VALUES (?, ?, ?)";

    private static final String SQL_UPDARTE = "UPDATE watchman username = ?, password = ?, fullName =? WHERE id = ?";

    private static final String SQL_FIND_ALL = "SELECT id, name FROM watchman";



    @Override
    protected String getFindByIdSql() {
        return SQL_FIND_BY_ID;
    }

    @Override
    protected EntityMapper<Watchman> getEntityMapper() {
        return new EntityMapper<Watchman>() {

            @Override
            @SneakyThrows
            public Watchman fromResultSet(ResultSet resultSet) {
                return Watchman.builder().id(resultSet.getLong("id"))
                        .username(resultSet.getString("username"))
                        .password(resultSet.getString("password"))
                        .fullName(resultSet.getString("fullName"))
                        .build();
            }

            @Override
            @SneakyThrows
            public void fillStatement(PreparedStatement statement,
                    Watchman entity) {
                statement.setString(1, entity.getUsername());
                statement.setString(2, entity.getPassword());
                statement.setString(3, entity.getFullName());
                if (entity.getId() != null) {
                    statement.setLong(4, entity.getId());
                }

            }
        };
    }

    @Override
    protected String getCreateSql() {
        return SQL_CREATE;
    }

    @Override
    public String getFindAllSql() {
        return SQL_FIND_ALL;
    }

    @Override
    public Watchman findByUsername(String username) {
        throw new UnsupportedOperationException();
    }

}
