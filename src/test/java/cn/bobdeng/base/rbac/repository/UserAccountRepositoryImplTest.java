package cn.bobdeng.base.rbac.repository;

import cn.bobdeng.base.rbac.IntegrationTest;
import cn.bobdeng.base.user.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class UserAccountRepositoryImplTest extends IntegrationTest {
    @Autowired
    UserAccountDAO userAccountDAO;

    @BeforeEach
    public void setup() {
        userAccountDAO.deleteAll();
    }

    @Test
    public void should_save_account_when_bind() {
        User user = Users.ofTenant(null).newUser(UserName.empty());
        user.bindAccount(new Account("bobdeng"));
        assertThat(userAccountDAO.count(), is(1L));
    }

    @Test
    public void should_return_empty_when_has_no_account() {
        Optional<UserId> accountUserId = Users.accountRepository.findUserByAccount(new Account("bobdeng"));
        assertThat(accountUserId.isEmpty(), is(true));
    }

    @Test
    public void should_return_user_id_when_has_account() {
        userAccountDAO.save(UserAccountDO.builder()
                .account("bobdeng")
                .id("123456")
                .build());

        Optional<UserId> accountUserId = Users.accountRepository.findUserByAccount(new Account("bobdeng"));

        assertThat(accountUserId, is(Optional.of(UserId.of("123456"))));
    }
}