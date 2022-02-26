package cn.bobdeng.base.rbac.permission;

import cn.bobdeng.base.rbac.Permission;
import org.springframework.stereotype.Service;

@Service
public class PermissionTestBean {
    @Permission(allow = "user.create")
    public void userCreate() {

    }

    @Permission(admin = true)
    public void adminCreate() {

    }
}
