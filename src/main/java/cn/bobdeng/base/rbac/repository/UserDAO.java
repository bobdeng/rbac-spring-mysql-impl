package cn.bobdeng.base.rbac.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserDAO extends CrudRepository<UserDO, String> {
    List<UserDO> findByTenantId(String tenantId);
}
