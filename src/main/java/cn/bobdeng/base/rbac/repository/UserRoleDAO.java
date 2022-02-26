package cn.bobdeng.base.rbac.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRoleDAO extends CrudRepository<UserRoleDO, Integer> {
    Optional<UserRoleDO> findByUserId(String userId);
}
