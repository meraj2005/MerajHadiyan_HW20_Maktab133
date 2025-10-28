package ir.maktabsharif.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import ir.maktabsharif.model.*;
import ir.maktabsharif.repository.*;
import ir.maktabsharif.util.*;

import java.util.Optional;

public class UserRepositoryImpl extends BaseRepositoryImpl<Long, User> implements UserRepository {
    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }


    @Override
    public Optional<User> findByUserName(String userName){
        EntityManager em = EntityManagerProvider.getEntityManager();
        User user = em.createQuery("select u from User u where u.username = :un", User.class)
                .setParameter("un",userName).getSingleResult();
        return Optional.of(user);
    }

}
