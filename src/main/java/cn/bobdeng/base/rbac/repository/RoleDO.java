package cn.bobdeng.base.rbac.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_rbac_role")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDO {
    @Id
    private String id;
    private String tenantId;
    private String name;
    private String functions;
}
