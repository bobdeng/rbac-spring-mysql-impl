package cn.bobdeng.base.rbac.permission;

import cn.bobdeng.base.rbac.IntegrationTest;
import cn.bobdeng.base.role.*;
import cn.bobdeng.base.user.User;
import cn.bobdeng.base.user.UserId;
import cn.bobdeng.base.user.UserRoles;
import cn.bobdeng.base.user.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PermissionTest extends IntegrationTest {
    @Autowired
    PermissionTestBean permissionTestBean;
    @Autowired
    PermissionSessionGetterImpl permissionSessionGetter;

    @Test
    public void should_pass_when_has_permission() {
        User user = new Users().newUser();
        Function userCreateFunction = new Function("user.create");
        Role role = new Roles().newRole(new RoleName("测试"), new Functions(Arrays.asList(userCreateFunction)));
        user.setRoles(new UserRoles(Arrays.asList(RoleId.of(role.id()))));

        permissionSessionGetter.setSessionUser(new SessionUser(user.getId(), null));

        assertDoesNotThrow(() -> permissionTestBean.userCreate());

    }

    @Test
    public void should_throw_when_has_permission() {
        User user = new Users().newUser();
        Function userCreateFunction = new Function("user.delete");
        Role role = new Roles().newRole(new RoleName("测试"), new Functions(Arrays.asList(userCreateFunction)));
        user.setRoles(new UserRoles(Arrays.asList(RoleId.of(role.id()))));
        permissionSessionGetter.setSessionUser(new SessionUser(user.getId(), null));

        assertThrows(PermissionDeniedException.class, () -> permissionTestBean.userCreate());

    }

    @Test
    public void should_throw_when_no_user() {
        permissionSessionGetter.setSessionUser(null);

        assertThrows(PermissionDeniedException.class, () -> permissionTestBean.userCreate());

    }

    @Test
    public void should_throw_when_user_not_found() {
        permissionSessionGetter.setSessionUser(new SessionUser(UserId.of("123"), null));
        assertThrows(PermissionDeniedException.class, () -> permissionTestBean.userCreate());
    }

    @Test
    public void should_throw_when_is_not_admin() {
        User user = new Users().newUser();
        Function userCreateFunction = new Function("user.create");
        Role role = new Roles().newRole(new RoleName("测试"), new Functions(Arrays.asList(userCreateFunction)));
        user.setRoles(new UserRoles(Arrays.asList(RoleId.of(role.id()))));
        permissionSessionGetter.setSessionUser(new SessionUser(user.getId(), null));

        assertThrows(PermissionDeniedException.class, () -> permissionTestBean.adminCreate());

    }

    @Test
    public void should_not_throw_when_is_admin() {
        User user = new Users().newAdmin();
        Function userCreateFunction = new Function("user.create");
        Role role = new Roles().newRole(new RoleName("测试"), new Functions(Arrays.asList(userCreateFunction)));
        user.setRoles(new UserRoles(Arrays.asList(RoleId.of(role.id()))));

        permissionSessionGetter.setSessionUser(new SessionUser(user.getId(), null));

        assertDoesNotThrow(() -> permissionTestBean.adminCreate());

    }

    @Test
    public void should_not_throw_when_is_admin_do_anything() {
        User user = new Users().newAdmin();

        permissionSessionGetter.setSessionUser(new SessionUser(user.getId(), null));

        assertDoesNotThrow(() -> permissionTestBean.userCreate());

    }
}
