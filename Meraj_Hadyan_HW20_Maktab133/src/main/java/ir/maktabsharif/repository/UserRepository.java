package ir.maktabsharif.repository;

import ir.maktabsharif.model.*;


import java.util.Optional;

public interface UserRepository extends BaseRepository<Long, User>{
    Optional<User> findByUserName(String userName);
}
