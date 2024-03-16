package com.aomaraie.cloudnotes.model;

import com.aomaraie.cloudnotes.containers.SharedDatabaseContainer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NoteTest {

    public static PostgreSQLContainer<?> postgreSQLContainer = SharedDatabaseContainer.getInstance();

    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }

    private Note note;

    @BeforeEach
    public void setUp() {
        note = new Note();
    }

    @Test
    public void defaultConstructorSetsDefaultTitleAndContent() {
        assertEquals("Default Title", note.getTitle());
        assertEquals("Default Content", note.getContent());
    }

    @Test
    public void parameterizedConstructorSetsTitleAndContent() {
        note = new Note("Test Title", "Test Content");
        assertEquals("Test Title", note.getTitle());
        assertEquals("Test Content", note.getContent());
    }

    @Test
    public void setTitleChangesTitle() {
        note.setTitle("New Title");
        assertEquals("New Title", note.getTitle());
    }

    @Test
    public void setContentChangesContent() {
        note.setContent("New Content");
        assertEquals("New Content", note.getContent());
    }
}
