package cn.bobdeng.base.rbac.repository;

import cn.bobdeng.base.user.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserMobileRepositoryImpl implements UserMobileRepository {
    private final UserMobileDAO userMobileDAO;

    public UserMobileRepositoryImpl(UserMobileDAO userMobileDAO) {
        this.userMobileDAO = userMobileDAO;
        Users.userMobileRepository = this;
    }

    @Override
    public void save(User user, Mobile mobile) {
        userMobileDAO.save(UserMobileDO.builder()
                .userId(user.id())
                .mobile(mobile.number())
                .id(UUID.randomUUID().toString())
                .build());
    }

    @Override
    public List<Mobile> findByUser(User user) {
        return userMobileDAO.findByUserId(user.id())
                .stream()
                .map(userMobileDO -> new Mobile(userMobileDO.getMobile()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserId> findByMobile(Mobile mobile) {
        return userMobileDAO.findByMobile(mobile.number())
                .map(userMobileDO -> UserId.of(userMobileDO.getUserId()));
    }
}
