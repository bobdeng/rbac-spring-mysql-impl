package cn.bobdeng.base.rbac.repository;

import cn.bobdeng.base.user.EncodedPassword;
import cn.bobdeng.base.user.Password;
import cn.bobdeng.base.user.PasswordEncoder;
import cn.bobdeng.base.user.Users;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SpringBCryptPasswordEncoder implements PasswordEncoder {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public SpringBCryptPasswordEncoder() {
        Users.passwordEncoder = this;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public String encode(String rawPassword) {
        return bCryptPasswordEncoder.encode(rawPassword);
    }

    @Override
    public boolean verify(Password password, EncodedPassword encodedPassword) {
        return bCryptPasswordEncoder.matches(password.getRawPassword(), encodedPassword.getEncoded());
    }
}
