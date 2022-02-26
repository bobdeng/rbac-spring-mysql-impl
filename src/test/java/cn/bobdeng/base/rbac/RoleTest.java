package cn.bobdeng.base.rbac;

import cn.bobdeng.base.rbac.repository.RoleDAO;
import cn.bobdeng.base.rbac.repository.RoleDO;
import cn.bobdeng.base.role.*;
import cn.bobdeng.base.user.TenantId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class RoleTest extends IntegrationTest {
    @Autowired
    RoleDAO roleDAO;

    List<RoleDO> findAll() {
        return StreamSupport.stream(roleDAO.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Test
    public void create_role() {
        Roles roles = new Roles();
        RoleName name = new RoleName("系统管理员");
        Function function = new Function("user.create");
        Functions functions = new Functions(Arrays.asList(function));

        roles.newRole(name, functions);

        assertThat(findAll().size(), is(1));
    }

    @Test
    public void find_role() {
        Role role = getRole(null);

        Role roleSaved = Roles.roleRepository.findById(RoleId.of(role.id())).orElse(null);
        assertThat(roleSaved, is(role));
    }

    private Role getRole(TenantId tenantId) {
        Roles roles = new Roles(tenantId);
        RoleName name = new RoleName("系统管理员");
        Function function = new Function("user.create");
        Functions functions = new Functions(Arrays.asList(function));
        Role role = roles.newRole(name, functions);
        return role;
    }

    @Test
    public void listRole() {
        TenantId tenantId = TenantId.of("123");
        getRole(null);
        getRole(tenantId);

        List<Role> rolesOfTenant123 = new Roles(tenantId).list();

        assertThat(rolesOfTenant123.size(), is(1));
    }
}
