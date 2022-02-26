package cn.bobdeng.base.rbac.repository;

import cn.bobdeng.base.user.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public final class UserRepositoryImpl implements UserRepository {
    private final UserDAO userDAO;

    public UserRepositoryImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
        Users.userRepository = this;
    }

    @Override
    public User save(Users users, User user) {
        userDAO.save(UserDO.builder()
                .id(user.id())
                .tenantId(users.tenantId())
                .status(user.statusName())
                .level(user.levelName())
                .build());
        return user;
    }

    @Override
    public Optional<User> findById(String id) {
        return userDAO.findById(id)
                .map(userDO -> new User(UserId.of(id), UserStatus.of(userDO.getStatus()), UserLevel.of(userDO.getLevel())));
    }

    @Override
    public void save(User user) {
        userDAO.findById(user.id())
                .ifPresent(userDO -> {
                    userDO.setStatus(user.statusName());
                    userDAO.save(userDO);
                });
    }

}
