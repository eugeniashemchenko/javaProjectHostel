package com.hostel.dao.hibernate;

import org.hibernate.SessionFactory;

import com.hostel.dao.StudentDao;
import com.hostel.model.Student;

public class HibernateStudentDao extends HibernateGenericDao<Student> implements StudentDao {

    public HibernateStudentDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    protected Class<Student> getEntityClass() {
        return Student.class;
    }

}
