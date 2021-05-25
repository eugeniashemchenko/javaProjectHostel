package com.hostel.dao.hibernate;

import org.hibernate.SessionFactory;

import com.hostel.dao.PassDao;
import com.hostel.model.Pass;

public class HibernatePassDao extends HibernateGenericDao<Pass> implements PassDao {

    public HibernatePassDao(SessionFactory sessionFactory) {
        super(sessionFactory);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected Class<Pass> getEntityClass() {
        return Pass.class;
    }

}
