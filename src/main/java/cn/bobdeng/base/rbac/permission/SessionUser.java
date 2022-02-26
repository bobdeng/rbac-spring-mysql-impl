package cn.bobdeng.base.rbac.permission;

import cn.bobdeng.base.user.TenantId;
import cn.bobdeng.base.user.UserId;
import lombok.Getter;

@Getter
public class SessionUser {
    private UserId userId;
    private TenantId tenantId;

    public SessionUser(UserId userId, TenantId tenantId) {
        this.userId = userId;
        this.tenantId = tenantId;
    }

    public String userId() {
        return userId.getId();
    }
}
