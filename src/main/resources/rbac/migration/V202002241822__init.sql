CREATE TABLE `t_rbac_user`
(
    `id`        varchar(36) NOT NULL,
    `tenant_id` varchar(36) DEFAULT NULL,
    `status`    varchar(20) NOT NULL,
    `level`     varchar(10) NOT NULL,
    `name`      varchar(50),
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_rbac_role`
(
    `id`        varchar(36)   NOT NULL,
    `tenant_id` varchar(36) DEFAULT NULL,
    `name`      varchar(20)   NOT NULL,
    `functions` varchar(2000) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_rbac_user_mobile`
(
    `id`      varchar(36) NOT NULL,
    `user_id` varchar(36) DEFAULT NULL,
    `mobile`  varchar(15) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_rbac_user_role`
(
    `id`      int(11) NOT NULL AUTO_INCREMENT,
    `user_id` varchar(36)  NOT NULL,
    `roles`   varchar(500) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_rbac_user_password`
(
    `id`       varchar(36) NOT NULL,
    `password` varchar(100) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4;
