package cn.bobdeng.base.rbac.permission;

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
        if (user.isAdmin()) {
            return;
        }
        if (permission.admin()) {
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
        SessionUser sessionUser = permissionSessionGetter.sessionUser().orElse(null);
        if (sessionUser == null) {
            throw new PermissionDeniedException();
        }
        return Users.userRepository.findById(sessionUser.getTenantId(), sessionUser.getUserId()).orElseThrow(PermissionDeniedException::new);
    }
}
