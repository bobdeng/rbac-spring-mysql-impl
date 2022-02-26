package cn.bobdeng.base.rbac.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleDAO extends CrudRepository<RoleDO, String> {
    List<RoleDO> findByTenantId(String tenantId);
}
