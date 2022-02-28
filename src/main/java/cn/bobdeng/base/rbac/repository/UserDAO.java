package cn.bobdeng.base.rbac.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserDAO extends CrudRepository<UserDO, String> {
    List<UserDO> findByTenantId(String tenantId);

    Optional<UserDO> findByTenantIdAndId(String tenantId, String id);
}
