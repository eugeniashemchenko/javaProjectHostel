package com.hostel.dao.hibernate;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.hostel.dao.WatchmanDao;
import com.hostel.model.Watchman;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class HibernateWatchmanDao extends HibernateGenericDao<Watchman>
        implements WatchmanDao {

    public HibernateWatchmanDao(SessionFactory sessionFactory) {
        super(sessionFactory);
        log.debug("!!!! SESSION FACTORY - {}", sessionFactory);
    }

    @Override
    protected Class<Watchman> getEntityClass() {
        return Watchman.class;
    }

    @Override
    @Transactional
    public Watchman findByUsername(String username) {
        try {
            Watchman watchman = getCurrentSession()
                    .createQuery("from Watchman d where d.username = :username",
                            Watchman.class)
                    .setParameter("username", username).getSingleResult();

            return watchman;
        } catch (NoResultException e) {
            return null;
        }
    }

}
