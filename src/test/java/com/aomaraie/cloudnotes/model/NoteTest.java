package com.aomaraie.cloudnotes.model;

import com.aomaraie.cloudnotes.model.Note;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
