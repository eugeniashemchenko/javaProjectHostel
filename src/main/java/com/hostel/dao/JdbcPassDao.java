package com.hostel.dao;

import java.util.List;

import javax.sql.DataSource;

import com.hostel.model.Pass;

public class JdbcPassDao extends JdbcGenericDao<Pass> implements PassDao {

    public JdbcPassDao(DataSource dataSource) {
        super(dataSource);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected String getCreateSql() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected String getFindByIdSql() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected EntityMapper<Pass> getEntityMapper() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getFindAllSql() {
        // TODO Auto-generated method stub
        return null;
    }

}
