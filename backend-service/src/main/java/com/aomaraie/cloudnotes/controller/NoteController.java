package com.aomaraie.cloudnotes.controller;

import com.aomaraie.cloudnotes.exception.ResourceNotFoundException;
import com.aomaraie.cloudnotes.model.Note;
import com.aomaraie.cloudnotes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController @RequestMapping("/api/notes")
public class NoteController {
    final private String noNoteWithIdString = "No note with ID :: ";
    @Autowired
    private NoteRepository noteRepository;
    @GetMapping
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    @GetMapping("/{id}")
    public Note getNote(@PathVariable(value = "id") Long noteId) {
        return noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException(noNoteWithIdString + noteId));
    }

    @PostMapping
    public Note createNote(@RequestBody Note note) {
        return noteRepository.save(note);
    }

    @PutMapping("/{id}")
    public Note updateNote(@PathVariable(value = "id") Long noteId, @RequestBody Note noteDetails) {
        Note note =  noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException(noNoteWithIdString + noteId));
        note.setTitle(noteDetails.getTitle());
        note.setContent(noteDetails.getContent());
        return noteRepository.save(note);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteNote(@PathVariable(value = "id") Long noteId) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException(noNoteWithIdString + noteId));
        noteRepository.delete(note);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
