package com.example.manage_shops.repository;

import com.example.manage_shops.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
public class UserRepository {

    public List<User> getUser(String keyword, String roleName) {
        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
            // Begin a unit of work
            session.beginTransaction();

            StringBuffer hql = new StringBuffer();
            hql.append("SELECT u FROM User u JOIN RoleUser ru ON u.id = ru.idUser JOIN Role r ON r.id = ru.idRole");
            if (!StringUtils.isEmpty(keyword) || !StringUtils.isEmpty(roleName)) {
                hql.append(" WHERE ");
                if (!StringUtils.isEmpty(keyword)) {
                    hql.append(" u.name LIKE CONCAT('%', :keyword, '%') ");
                }
                if (!StringUtils.isEmpty(roleName)) {
                    String appendRole = StringUtils.isEmpty(keyword) ? " r.roleName = :roleName " : " AND r.roleName = :roleName ";
                    hql.append(appendRole);
                }
            }
            Query query = session.createQuery(String.valueOf(hql));
            query.setParameter("keyword", StringUtils.isEmpty(keyword) ? "" : keyword);
            query.setParameter("roleName", StringUtils.isEmpty(roleName) ? "" : roleName);
            session.getTransaction().commit();
            System.out.println(query.getQueryString());
            List<User> result = query.list();
            return result;
        }
    }
}
