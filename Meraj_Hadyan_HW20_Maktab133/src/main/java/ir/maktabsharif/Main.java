package ir.maktabsharif;

import ir.maktabsharif.enums.Role;
import ir.maktabsharif.model.User;
import ir.maktabsharif.repository.impl.UserRepositoryImpl;
import ir.maktabsharif.service.impl.BaseServiceImpl;
import ir.maktabsharif.service.impl.UserServiceImpl;
import ir.maktabsharif.util.PasswordUtil;

public class Main {
    public static void main(String[] args) {
        User user= User.builder()
                .username("admin")
                .email("admin@gmail.com")
                .password("123")
                .role(Role.ADMIN)
                    .build();
        user.setPassword( PasswordUtil.hashPassword(user.getPassword()));
        UserRepositoryImpl userRepository=new UserRepositoryImpl();
        UserServiceImpl userService = new UserServiceImpl(userRepository);
        userService.save(user);

    }

}
