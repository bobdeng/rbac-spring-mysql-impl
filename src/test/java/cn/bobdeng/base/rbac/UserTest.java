package cn.bobdeng.base.rbac;

import cn.bobdeng.base.rbac.repository.UserDAO;
import cn.bobdeng.base.rbac.repository.UserDO;
import cn.bobdeng.base.user.TenantId;
import cn.bobdeng.base.user.User;
import cn.bobdeng.base.user.UserRepository;
import cn.bobdeng.base.user.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserTest extends IntegrationTest {
    @Autowired
    UserDAO userDAO;
    @Autowired
    UserRepository userRepository;

    @Test
    public void create_user() {
        User user = Users.ofTenant(new TenantId("10000"))
                .newUser();
        assertThat(user, notNullValue());
        List<UserDO> userDOS = getUserDOS();
        assertThat(userDOS.size(), is(1));
        UserDO actual = userDOS.get(0);
        assertThat(actual.getStatus(), is("active"));
        assertThat(actual.getTenantId(), is("10000"));
    }

    private List<UserDO> getUserDOS() {
        List<UserDO> userDOS = StreamSupport.stream(userDAO.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return userDOS;
    }

    @Test
    public void set_user_status() {
        User user = Users.ofTenant(new TenantId("10000"))
                .newUser();
        user.suspend();
        UserDO actual = getUserDOS().get(0);
        assertThat(actual.getStatus(), is("suspend"));
    }

    @Test
    public void find_user(){
        User user = new Users().newUser();
        User userSaved = userRepository.findById(user.id()).orElse(null);
        assertThat(user.id(),is(userSaved.id()));
        assertThat(user.status(),is(userSaved.status()));
    }
}