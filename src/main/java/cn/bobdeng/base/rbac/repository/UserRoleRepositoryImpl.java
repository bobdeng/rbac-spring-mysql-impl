package cn.bobdeng.base.rbac.repository;

import cn.bobdeng.base.user.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRoleRepositoryImpl implements UserRoleRepository {
    private final UserRoleDAO userRoleDAO;

    public UserRoleRepositoryImpl(UserRoleDAO userRoleDAO) {
        this.userRoleDAO = userRoleDAO;
        Users.userRoleRepository = this;
    }

    @Override
    public void save(User user, UserRoles userRoles) {
        userRoleDAO.save(UserRoleDO.builder()
                .userId(user.id())
                .roles(userRoles.rolesAsJson())
                .build());
    }

    @Override
    public Optional<UserRoleId> findByUser(User user) {
        return userRoleDAO.findByUserId(user.id())
                .map(userRoleDO -> new UserRoleId(userRoleDO.getId()));
    }

    @Override
    public Optional<UserRoles> findUserRoles(User user) {
        return userRoleDAO.findByUserId(user.id())
                .map(userRoleDO -> UserRoles.fromJson(userRoleDO.getRoles()));
    }

    @Override
    public void saveById(User user, UserRoleId userRoleId, UserRoles userRoles) {
        userRoleDAO.findById(userRoleId.id())
                .ifPresent(userRoleDO -> {
                    userRoleDO.setRoles(userRoles.rolesAsJson());
                    userRoleDAO.save(userRoleDO);
                });
    }
}
