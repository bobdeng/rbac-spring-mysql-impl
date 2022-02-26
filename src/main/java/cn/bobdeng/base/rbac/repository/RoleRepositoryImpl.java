package cn.bobdeng.base.rbac.repository;

import cn.bobdeng.base.role.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public final class RoleRepositoryImpl implements RoleRepository {
    private final RoleDAO roleDAO;

    public RoleRepositoryImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
            Roles.roleRepository = this;
    }

    @Override
    public void save(Roles roles, Role role) {
        roleDAO.save(RoleDO.builder()
                .id(role.id())
                .tenantId(roles.tenantId())
                .name(role.name())
                .functions(role.functionsAsJson())
                .build());
    }

    @Override
    public Optional<Role> findById(RoleId roleId) {
        return roleDAO.findById(roleId.id())
                .map(toEntity());
    }

    private Function<RoleDO, Role> toEntity() {
        return roleDO -> new Role(RoleId.of(roleDO.getId()), new RoleName(roleDO.getName()), Functions.fromJson(roleDO.getFunctions()));
    }

    @Override
    public List<Role> list(Roles roles) {
        return roleDAO.findByTenantId(roles.tenantId())
                .stream()
                .map(toEntity())
                .collect(Collectors.toList());
    }
}
