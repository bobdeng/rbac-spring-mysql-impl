package cn.bobdeng.base.rbac.repository;

import cn.bobdeng.base.user.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAccountRepositoryImpl implements UserAccountRepository {
    private final UserAccountDAO userAccountDAO;

    public UserAccountRepositoryImpl(UserAccountDAO userAccountDAO) {
        this.userAccountDAO = userAccountDAO;
        Users.accountRepository = this;
    }

    @Override
    public void save(User user, Account account) {
        userAccountDAO.save(UserAccountDO.builder()
                .id(user.id())
                .account(account.getName())
                .build());
    }

    @Override
    public Optional<UserId> findUserByAccount(Account account) {
        return userAccountDAO.findByAccount(account.getName())
                .map(userAccountDO -> UserId.of(userAccountDO.getId()));
    }
}
