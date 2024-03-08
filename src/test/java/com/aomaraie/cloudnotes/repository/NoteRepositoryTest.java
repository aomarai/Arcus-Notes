package com.aomaraie.cloudnotes.repository;

import com.aomaraie.cloudnotes.model.Note;
import com.aomaraie.cloudnotes.repository.NoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class NoteRepositoryTest {

    @Autowired
    private NoteRepository noteRepository;

    private Note note;

    @BeforeEach
    public void setUp() {
        note = new Note("Test Title", "Test Content");
        noteRepository.save(note);
    }

//    @Test
//    public void findByIdReturnsCorrectNote() { // FIXME
//        Optional<Note> foundNote = noteRepository.findById(note.getId());
//        assertTrue(foundNote.isPresent());
//        assertEquals(note.getTitle(), foundNote.get().getTitle());
//        assertEquals(note.getContent(), foundNote.get().getContent());
//    }
//
//    @Test
//    public void findByIdReturnsEmptyForNonExistentId() { // FIXME
//        Optional<Note> foundNote = noteRepository.findById(Long.MAX_VALUE);
//        assertTrue(foundNote.isEmpty());
//    }
}
