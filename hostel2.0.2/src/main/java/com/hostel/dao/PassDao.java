package com.hostel.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import com.hostel.model.Pass;

import lombok.SneakyThrows;

public class PassDao extends JdbcGenericDao<Pass> {

    public PassDao(DataSource dataSource) {
        super(dataSource);
    }

    private static final String SQL_FIND_BY_ID = "SELECT id, sku FROM pass WHERE id = ?";

    private static final String SQL_CREATE = "INSERT INTO pass (sku) VALUES (?)";

    @Override
    protected String getFindByIdSql() {
        return SQL_FIND_BY_ID;
    }

    @Override
    protected String getCreateSql() {
        return SQL_CREATE;
    }

    @Override
    protected EntityMapper<Pass> getEntityMapper() {
        
        return new EntityMapper<Pass>() {
            
            @Override
            @SneakyThrows
            public Pass fromResultSet(ResultSet resultSet) {
                Pass pass = Pass.builder().id(resultSet.getLong("id"))
                      .sku(resultSet.getString("sku")).build();
                return pass;
            }

            @Override
            @SneakyThrows
            public void fillStatement(PreparedStatement statement,
                    Pass entity) {
                statement.setString(1, entity.getSku());
            }
        };
    }

}
