package cn.bobdeng.base.rbac;

import cn.bobdeng.base.rbac.permission.PermissionDeniedException;
import cn.bobdeng.base.role.Function;
import cn.bobdeng.base.user.User;
import cn.bobdeng.base.user.Users;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
@Aspect
public class PermissionChecker {
    private final PermissionSessionGetter permissionSessionGetter;

    public PermissionChecker(PermissionSessionGetter permissionSessionGetter) {
        this.permissionSessionGetter = permissionSessionGetter;
    }

    @Before("@annotation(permission)")
    public void before(Permission permission) {
        User user = getCurrentUser();
        if (permission.admin()) {
            if (user.isAdmin()) {
                return;
            }
            throw new PermissionDeniedException();
        }
        if (!user.hasAnyPermission(getFunctions(permission))) {
            throw new PermissionDeniedException();
        }
    }

    private Function[] getFunctions(Permission permission) {
        return Stream.of(permission.allow())
                .map(Function::new)
                .toArray(Function[]::new);
    }

    private User getCurrentUser() {
        String userId = permissionSessionGetter.sessionUser();
        if (userId == null) {
            throw new PermissionDeniedException();
        }
        return Users.userRepository.findById(userId).orElseThrow(PermissionDeniedException::new);
    }
}
