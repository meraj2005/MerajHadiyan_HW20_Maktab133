package ir.maktabsharif.service.impl;

import ir.maktabsharif.model.*;
import ir.maktabsharif.repository.*;
import ir.maktabsharif.service.*;
import jakarta.persistence.NoResultException;


import java.util.Optional;

public class UserServiceImpl extends BaseServiceImpl<Long, User, UserRepository> implements UserService {
    public UserServiceImpl(UserRepository baseRepository) {
        super(baseRepository);
    }


    @Override
    public User findByUsername(String username) {
        Optional<User> userOptional = baseRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new NoResultException("User with this username not Found!");
        }
    }
    @Override
    public User findByEmail(String email){
        Optional<User> userOptional = baseRepository.findByEmail(email);
        if ((userOptional.isPresent())){
            return userOptional.get();
        }else {
            throw new NoResultException("User with this Email not Found!");
        }
    }
}
