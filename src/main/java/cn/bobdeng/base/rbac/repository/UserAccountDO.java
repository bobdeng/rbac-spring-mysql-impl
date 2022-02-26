package cn.bobdeng.base.rbac.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "t_rbac_user_account")
public class UserAccountDO {
    @Id
    private String id;
    private String account;

}
