package com.hostel.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import com.hostel.model.WatchmenBabushka;

import lombok.SneakyThrows;

public class WatchmenBabushkaDao extends JdbcGenericDao<WatchmenBabushka> {

    public WatchmenBabushkaDao(DataSource dataSource) {
        super(dataSource);
    }

    private static final String SQL_FIND_BY_ID = "SELECT id, name FROM watchmenBabushka WHERE id = ?";

    private static final String SQL_CREATE = "INSERT INTO watchmenBabushka (name) VALUES (?)";



    @Override
    protected String getFindByIdSql() {
        return SQL_FIND_BY_ID;
    }

    @Override
    protected EntityMapper<WatchmenBabushka> getEntityMapper() {
        return new EntityMapper<WatchmenBabushka>() {
            
            @Override
            @SneakyThrows
            public WatchmenBabushka fromResultSet(ResultSet resultSet) {
                return WatchmenBabushka.builder().id(resultSet.getLong("id"))
                        .name(resultSet.getString("name")).build();
            }

            @Override
            @SneakyThrows
            public void fillStatement(PreparedStatement statement,
                    WatchmenBabushka entity) {
                statement.setString(1, entity.getName());
                
            }
        };
    }

    @Override
    protected String getCreateSql() {
        return SQL_CREATE;
    }
}
