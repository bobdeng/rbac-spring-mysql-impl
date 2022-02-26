package cn.bobdeng.base.rbac.permission;

import java.util.Optional;

public interface PermissionSessionGetter {
    Optional<SessionUser> sessionUser();
}
