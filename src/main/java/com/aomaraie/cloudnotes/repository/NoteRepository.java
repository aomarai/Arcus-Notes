package com.aomaraie.cloudnotes.repository;

import com.aomaraie.cloudnotes.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {

}
