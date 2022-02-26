package cn.bobdeng.base.rbac.permission;

import cn.bobdeng.base.rbac.PermissionSessionGetter;
import lombok.Data;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
public class PermissionSessionGetterImpl implements PermissionSessionGetter {
    @Setter
    private String user;

    @Override
    public String sessionUser() {
        return user;
    }
}
