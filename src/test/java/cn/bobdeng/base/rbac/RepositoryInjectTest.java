package cn.bobdeng.base.rbac;

import cn.bobdeng.base.role.Roles;
import cn.bobdeng.base.user.Users;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class RepositoryInjectTest extends IntegrationTest {
    @Test
    public void ensureAllRepositoryInjected() {
        assertThat(Users.userPasswordRepository, notNullValue());
        assertThat(Users.userMobileRepository, notNullValue());
        assertThat(Users.userRepository, notNullValue());
        assertThat(Users.passwordEncoder, notNullValue());
        assertThat(Users.userRoleRepository, notNullValue());
        assertThat(Roles.roleRepository, notNullValue());
        assertThat(Users.accountRepository, notNullValue());
    }
}
