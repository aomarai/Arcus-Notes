package com.aomaraie.cloudnotes.controller;

import com.aomaraie.cloudnotes.controller.NoteController;
import com.aomaraie.cloudnotes.exception.ResourceNotFoundException;
import com.aomaraie.cloudnotes.model.Note;
import com.aomaraie.cloudnotes.repository.NoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class NoteControllerTest {

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