package com.containers_testing;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
public abstract class TestContainer {


    @Container
    private static final MySQLContainer<?> mySqlContainer =
            new MySQLContainer<>("mysql:5.7")
                    .withDatabaseName("Testing_db")
                    .withUsername("bouf")
                    .withPassword(System.getenv("MYSQL_PASSWORD"));

    @DynamicPropertySource
    public static void dataSourceProperties(DynamicPropertyRegistry registre){
        registre.add("spring.datasource.url", mySqlContainer::getJdbcUrl);
        registre.add("spring.datasource.username", mySqlContainer::getUsername);
        registre.add("spring.datasource.password", mySqlContainer::getPassword);
    }


    @Test
    void isMysqlDbStarting() {
        Assertions.assertThat(mySqlContainer.isCreated()).isTrue();
        Assertions.assertThat(mySqlContainer.isRunning()).isTrue();
    }


}
