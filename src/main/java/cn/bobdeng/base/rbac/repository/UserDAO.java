package cn.bobdeng.base.rbac.repository;

import org.springframework.data.repository.CrudRepository;

public interface UserDAO extends CrudRepository<UserDO, String> {
}
