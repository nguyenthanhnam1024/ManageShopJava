package com.example.manage_shops.repository.impl;

import com.example.manage_shops.entity.User;
import com.example.manage_shops.repository.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepoImpl {
    public List<User> getListUserByKeyword(String role, String keyword){
        List<User> users;
        String hql;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Begin a unit of work
            session.beginTransaction();
             hql = "Select u FROM User u";
             users = session.createQuery(hql, User.class).list();
            session.getTransaction().commit();
        }
        return users;
    }

}
