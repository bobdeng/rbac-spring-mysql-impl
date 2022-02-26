package cn.bobdeng.base.rbac.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserMobileDAO extends CrudRepository<UserMobileDO, String> {
    List<UserMobileDO> findByUserId(String userId);

    Optional<UserMobileDO> findByMobile(String mobile);
}
