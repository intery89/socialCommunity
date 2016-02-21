package com.wwb.demo.dao.impl;

import com.wwb.demo.dao.BaseDao;
import com.wwb.demo.dao.UserDao;
import com.wwb.demo.domain.model.User;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class UserDaoImpl {
//    private SessionFactory sessionFactory;
//
//    public UserDaoImpl(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
//    @Override
//    @Transactional
//    public List<User> list() {
//        @SuppressWarnings("unchecked")
//        List<User> listUser = (List<User>) sessionFactory.getCurrentSession()
//                .createCriteria(User.class)
//                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
//
//        return listUser;
//    }

}
