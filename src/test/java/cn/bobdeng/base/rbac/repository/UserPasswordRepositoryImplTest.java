package cn.bobdeng.base.rbac.repository;

import cn.bobdeng.base.rbac.IntegrationTest;
import cn.bobdeng.base.user.Password;
import cn.bobdeng.base.user.User;
import cn.bobdeng.base.user.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserPasswordRepositoryImplTest extends IntegrationTest {
    @Autowired
    UserPasswordDAO userPasswordDAO;

    @BeforeEach
    public void setup() {
        userPasswordDAO.deleteAll();
    }

    @Test
    public void should_set_password_when_init_password() {
        User user = new Users().newUser();
        user.setPassword(new Password("123456"));

        assertThat(userPasswordDAO.count(), is(1L));
        assertThat(user.verifyPassword(new Password("123456")), is(true));
    }
}