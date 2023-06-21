package com.example.manage_shops.repository.hql;

import com.example.manage_shops.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
public class UserRepository {

    public List<User> getUser(String keyword, String roleName, int idShop) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            String key = "%"+keyword+"%";
            StringBuilder hql = new StringBuilder();
            hql.append("SELECT u FROM User u JOIN RoleUser ru ON u.id = ru.idUser JOIN Role r ON r.id = ru.idRole WHERE u.name LIKE :keyword");
//            hql.append("SELECT u FROM User u JOIN RoleUser ru ON u.id = ru.idUser JOIN Role r ON r.id = ru.idRole");
//            if (!StringUtils.isEmpty(keyword) || !StringUtils.isEmpty(roleName)) {
//                hql.append(" WHERE ");
//                if (!StringUtils.isEmpty(keyword)) {
//                    hql.append(" u.name LIKE :keyword ");
//                }
//                if (!StringUtils.isEmpty(roleName)) {
//                    if (idShop == 0) {
//                        String appendRole = StringUtils.isEmpty(keyword) ? " r.roleName = :roleName " : " AND r.roleName = :roleName ";
//                        hql.append(appendRole);
//                    }
//                }
//                if (idShop != 0) {
//                    String appendIdShop = StringUtils.isEmpty(keyword) ? " u.idShop = :idShop " : " AND u.idShop = :idShop ";
//                    hql.append(appendIdShop);
//                }
//            }
//            hql.append("SELECT u FROM User u WHERE u.name = :keyword ");
            Query<User> query = session.createQuery(String.valueOf(hql), User.class);
            System.out.println(String.valueOf(hql));
            System.out.println(hql);
            if (!StringUtils.isEmpty(keyword)) {
                query.setParameter("keyword", keyword);
                System.out.println(key);
            }
//            if (!StringUtils.isEmpty(roleName)) {
//                query.setParameter("roleName", roleName);
//            }
//            if (idShop != 0) {
//                query.setParameter("idShop", idShop);
//            }
            session.getTransaction().commit();
            System.out.println(query.getQueryString());
            List<User> userList = query.list();
            return userList;
        }
    }
}
