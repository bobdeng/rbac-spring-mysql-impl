package cn.bobdeng.base.rbac.repository;

import org.springframework.data.repository.CrudRepository;

public interface UserPasswordDAO extends CrudRepository<UserPasswordDO, String> {
}
