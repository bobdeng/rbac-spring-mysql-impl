package cn.bobdeng.base.rbac.repository;

import cn.bobdeng.base.user.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserPasswordRepositoryImpl implements UserPasswordRepository {
    private final UserPasswordDAO userPasswordDAO;

    public UserPasswordRepositoryImpl(UserPasswordDAO userPasswordDAO) {
        this.userPasswordDAO = userPasswordDAO;
        Users.userPasswordRepository = this;
    }

    @Override
    public void save(User user, Password password) {
        userPasswordDAO
                .save(UserPasswordDO.builder()
                        .id(user.id())
                        .password(password.encode())
                        .build());
    }

    @Override
    public Optional<EncodedPassword> findByUser(User user) {
        return userPasswordDAO.findById(user.id())
                .map(userPasswordDO -> new EncodedPassword(userPasswordDO.getPassword()));
    }
}
