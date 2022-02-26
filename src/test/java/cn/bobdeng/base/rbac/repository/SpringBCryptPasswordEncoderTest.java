package cn.bobdeng.base.rbac.repository;

import cn.bobdeng.base.rbac.IntegrationTest;
import cn.bobdeng.base.user.EncodedPassword;
import cn.bobdeng.base.user.Password;
import org.junit.jupiter.api.Test;

import static cn.bobdeng.base.user.Users.passwordEncoder;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SpringBCryptPasswordEncoderTest extends IntegrationTest {
    @Test
    public void password_match() {
        String encode = passwordEncoder.encode("123456");
        EncodedPassword encodedPassword = new EncodedPassword(encode);
        assertThat(passwordEncoder.verify(new Password("123456"), encodedPassword), is(true));
    }
}