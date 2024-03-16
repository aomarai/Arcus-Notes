package com.aomaraie.cloudnotes.containers;

import org.testcontainers.containers.PostgreSQLContainer;

public class SharedDatabaseContainer {

    private static final PostgreSQLContainer<?> container;

    static {
        container = new PostgreSQLContainer<>("postgres:16")
                .withDatabaseName("notes")
                .withUsername("user")
                .withPassword("password");
        container.start();
    }

    public static PostgreSQLContainer<?> getInstance() {
        return container;
    }
}
