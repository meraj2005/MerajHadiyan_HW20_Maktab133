package ir.maktabsharif.service;

import ir.maktabsharif.model.User;

public interface UserService extends BaseService<Long, User> {
    User findByUsername(String userName);
    User findByEmail(String email);
}
