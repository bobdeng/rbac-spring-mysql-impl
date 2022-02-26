package cn.bobdeng.base.rbac.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserAccountDAO extends CrudRepository<UserAccountDO, String> {
    Optional<UserAccountDO> findByAccount(String account);
}
