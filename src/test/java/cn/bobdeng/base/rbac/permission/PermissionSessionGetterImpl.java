package cn.bobdeng.base.rbac.permission;

import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PermissionSessionGetterImpl implements PermissionSessionGetter {
    @Setter
    private SessionUser sessionUser;

    @Override
    public Optional<SessionUser> sessionUser() {
        return Optional.ofNullable(sessionUser);
    }
}
