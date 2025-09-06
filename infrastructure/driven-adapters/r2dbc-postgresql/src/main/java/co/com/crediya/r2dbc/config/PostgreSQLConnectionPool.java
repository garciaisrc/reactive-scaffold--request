package co.com.crediya.r2dbc.config;
import io.r2dbc.pool.ConnectionPool;
import io.r2dbc.pool.ConnectionPoolConfiguration;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
@Configuration
public class PostgreSQLConnectionPool {

    private final PostgresqlConnectionProperties properties;

    public PostgreSQLConnectionPool(PostgresqlConnectionProperties properties) {
        this.properties = properties;
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        String url = String.format("r2dbc:postgresql://%s:%s@%s:%d/%s",
                properties.getUsername(),
                properties.getPassword(),
                properties.getHost(),
                properties.getPort(),
                properties.getDatabase());

        ConnectionFactory connectionFactory = ConnectionFactories.get(url);

        ConnectionPoolConfiguration configuration = ConnectionPoolConfiguration.builder(connectionFactory)
                .maxIdleTime(Duration.ofMinutes(30))
                .initialSize(5)
                .maxSize(20)
                .build();

        return new ConnectionPool(configuration);
    }
}