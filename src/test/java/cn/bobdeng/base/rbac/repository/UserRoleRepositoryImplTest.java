package cn.bobdeng.base.rbac.repository;

import cn.bobdeng.base.rbac.IntegrationTest;
import cn.bobdeng.base.role.*;
import cn.bobdeng.base.user.User;
import cn.bobdeng.base.user.UserRoleId;
import cn.bobdeng.base.user.UserRoleRepository;
import cn.bobdeng.base.user.UserRoles;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class UserRoleRepositoryImplTest extends IntegrationTest {
    @Autowired
    UserRoleRepository userRoleRepository;

    @Test
    public void should_has_no_role_when_new_user() {
        User user = User.create();
        Optional<UserRoleId> userRoleId = userRoleRepository.findByUser(user);
        assertThat(userRoleId.isPresent(), is(false));
    }

    @Test
    public void should_has_role_when_user_bind_role() {
        User user = User.create();
        Function function = new Function("user.create");
        Role role = new Roles().newRole(new RoleName("角色1"), new Functions(Arrays.asList(function)));
        user.setRoles(new UserRoles(Arrays.asList(role.getId())));

        Optional<UserRoleId> userRoleId = userRoleRepository.findByUser(user);
        assertThat(userRoleId.isPresent(), is(true));
        assertThat(userRoleRepository.findUserRoles(user).isPresent(), is(true));
    }

    @Test
    public void should_update_role_when_user_update_role() {
        User user = User.create();
        Function function = new Function("user.create");
        Role role = new Roles().newRole(new RoleName("角色1"), new Functions(Arrays.asList(function)));
        user.setRoles(new UserRoles(Arrays.asList(role.getId())));

        RoleId updateRole = RoleId.of("123456");
        user.setRoles(new UserRoles(Arrays.asList(updateRole)));

        assertThat(userRoleRepository.findUserRoles(user).get().roles().collect(Collectors.toList()),
                is(Arrays.asList(updateRole)));
    }
}