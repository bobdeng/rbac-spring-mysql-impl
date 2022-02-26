package cn.bobdeng.base.rbac.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_rbac_user_mobile")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserMobileDO {
    @Id
    private String id;
    private String userId;
    private String mobile;
}
