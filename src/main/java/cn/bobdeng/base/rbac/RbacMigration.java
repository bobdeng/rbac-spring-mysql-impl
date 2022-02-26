package cn.bobdeng.base.rbac;

import org.flywaydb.core.Flyway;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.SQLException;

@Service
public final class RbacMigration {
    private static Flyway flyway = null;
    private final DataSource dataSource;

    public RbacMigration(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    public void init() throws SQLException {
        configFlyway();
        migration();
    }

    public void configFlyway() throws SQLException {
        flyway = Flyway.configure()
                .locations("classpath:rbac/migration")
                .dataSource(dataSource)
                .schemas("test")
                .baselineOnMigrate(true)
                .load();
    }

    public void migration() {
        flyway.migrate();
    }

}
