package com.example.manage_shops.repository;

import com.example.manage_shops.entity.Role;
import com.example.manage_shops.entity.RoleUser;
import com.example.manage_shops.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class CustomUserRepoImp implements CustomUserRepo{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> customSearch(String keyword, String roleName, int idShop) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> root = query.from(User.class);

        Predicate predicate = cb.conjunction(); // Mặc định là true

        // Kiểm tra giá trị của keyword và thêm điều kiện nếu có giá trị
        if (keyword != null && !keyword.isEmpty()) {
            predicate = cb.and(predicate, cb.like(root.get("name"), "%" + keyword + "%"));
        }

        // Kiểm tra giá trị của role và thêm điều kiện nếu có giá trị
        if (roleName != null && !roleName.isEmpty()) {
            Join<User, RoleUser> roleUserJoin = root.join("idUser");
            Join<RoleUser, Role> roleJoin = roleUserJoin.join("idRole");
            predicate = cb.and(predicate, cb.equal(roleJoin.get("roleName"), roleName));
        }

        // Kiểm tra giá trị của idShop và thêm điều kiện nếu có giá trị
        if (idShop > 0) {
            predicate = cb.and(predicate, cb.equal(root.get("idShop"), idShop));
        }

        query.select(root).where(predicate);

        TypedQuery<User> typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultList();
    }
}
