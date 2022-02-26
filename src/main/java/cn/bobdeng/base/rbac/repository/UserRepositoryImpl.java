package cn.bobdeng.base.rbac.repository;

import cn.bobdeng.base.user.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
                .name(user.name())
                .build());
        return user;
    }

    @Override
    public Optional<User> findById(String id) {
        return userDAO.findById(id)
                .map(this::toEntity);
    }

    private User toEntity(UserDO userDO) {
        UserId userId = UserId.of(userDO.getId());
        UserStatus status = UserStatus.of(userDO.getStatus());
        UserLevel level = UserLevel.of(userDO.getLevel());
        UserName name = new UserName(userDO.getName());
        return new User(userId, status, level, name);
    }

    @Override
    public void save(User user) {
        userDAO.findById(user.id())
                .ifPresent(userDO -> {
                    userDO.setStatus(user.statusName());
                    userDO.setName(user.name());
                    userDAO.save(userDO);
                });
    }

    @Override
    public List<User> all(Users users) {
        return userDAO.findByTenantId(users.tenantId())
                .stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

}
