package cn.bobdeng.base.rbac.repository;

import cn.bobdeng.base.rbac.IntegrationTest;
import cn.bobdeng.base.user.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserMobileRepositoryImplTest extends IntegrationTest {
    @Autowired
    UserMobileRepository userMobileRepository;
    @Autowired
    UserMobileDAO userMobileDAO;
    private User user;

    @BeforeEach
    public void setup() {
        Users.userMobileRepository = userMobileRepository;
        userMobileDAO.deleteAll();
        user = User.create();
    }

    @Test
    public void should_be_empty_when_no_mobile() {
        List<Mobile> mobiles = userMobileRepository.findByUser(user);
        assertThat(mobiles.size(), is(0));
    }

    @Test
    public void should_has_1_when_has_1_mobile() {
        user.bindMobile(new Mobile("13333334444"));
        List<Mobile> mobiles = userMobileRepository.findByUser(user);
        assertThat(mobiles.size(), is(1));
    }

    @Test
    public void should_find_user_when_has_mobile() {
        Mobile mobile = new Mobile("13333334444");
        user.bindMobile(mobile);
        UserId userId = userMobileRepository.findByMobile(mobile).orElse(null);
        assertThat(userId, is(user.getId()));
    }
}