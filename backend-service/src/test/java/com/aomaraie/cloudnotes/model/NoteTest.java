package com.aomaraie.cloudnotes.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:postgresql://localhost:5432/notesdb",
        "spring.datasource.username=user",
        "spring.datasource.password=password",
        "jakarta.persistence.jdbc.url=jdbc:postgresql://cloud-notes-backend:5432/notes"
})

public class NoteTest {

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
