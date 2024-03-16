package com.aomaraie.cloudnotes.controller;

import com.aomaraie.cloudnotes.containers.SharedDatabaseContainer;
import com.aomaraie.cloudnotes.exception.ResourceNotFoundException;
import com.aomaraie.cloudnotes.model.Note;
import com.aomaraie.cloudnotes.repository.NoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class NoteControllerTest {

    public static PostgreSQLContainer<?> postgreSQLContainer = SharedDatabaseContainer.getInstance();

    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }

    @InjectMocks
    NoteController noteController;

    @Mock
    NoteRepository noteRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllNotesReturnsNoteList() {
        Note note = new Note();
        when(noteRepository.findAll()).thenReturn(Arrays.asList(note));

        List<Note> result = noteController.getAllNotes();

        assertEquals(1, result.size());
        verify(noteRepository, times(1)).findAll();
    }

    @Test
    public void getNoteReturnsNoteWhenIdExists() {
        Long noteId = 1L;
        Note note = new Note();
        when(noteRepository.findById(noteId)).thenReturn(Optional.of(note));

        Note result = noteController.getNote(noteId);

        assertEquals(note, result);
        verify(noteRepository, times(1)).findById(noteId);
    }

    @Test
    public void getNoteThrowsResourceNotFoundExceptionWhenIdDoesNotExist() {
        Long noteId = 1L;
        when(noteRepository.findById(noteId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> noteController.getNote(noteId));
        verify(noteRepository, times(1)).findById(noteId);
    }

    @Test
    public void createNoteReturnsSavedNote() {
        Note note = new Note();
        when(noteRepository.save(any(Note.class))).thenReturn(note);

        Note result = noteController.createNote(note);

        assertEquals(note, result);
        verify(noteRepository, times(1)).save(note);
    }

    @Test
    public void updateNoteReturnsUpdatedNote() {
        Note note = new Note();
        when(noteRepository.findById(1L)).thenReturn(Optional.of(note));
        when(noteRepository.save(any(Note.class))).thenReturn(note);

        Note result = noteController.updateNote(1L, note);

        assertEquals(note, result);
        verify(noteRepository, times(1)).save(note);
    }

    @Test
    public void updateNoteThrowsResourceNotFoundException() {
        Note note = new Note();
        when(noteRepository.findById(1L)).thenReturn(Optional.empty());

        try {
            noteController.updateNote(1L, note);
        } catch (ResourceNotFoundException ex) {
            assertEquals("No note with ID :: 1", ex.getMessage());
        }
    }

    @Test
    public void deleteNoteReturnsResponseEntity() {
        Note note = new Note();
        when(noteRepository.findById(1L)).thenReturn(Optional.of(note));

        Map<String, Boolean> result = noteController.deleteNote(1L);

        assertTrue(result.get("deleted"));
        verify(noteRepository, times(1)).delete(note);
    }

    @Test
    public void deleteNoteThrowsResourceNotFoundException() {
        when(noteRepository.findById(1L)).thenReturn(Optional.empty());

        try {
            noteController.deleteNote(1L);
        } catch (ResourceNotFoundException ex) {
            assertEquals("No note with ID :: 1", ex.getMessage());
        }
    }
}