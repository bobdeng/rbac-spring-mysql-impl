package cn.bobdeng.base.rbac.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "t_rbac_user")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDO {
    @Id
    private String id;
    private String tenantId;
    private String status;
    private String level;
    private String name;
}
