package com.aomaraie.cloudnotes.repository;

import com.aomaraie.cloudnotes.containers.SharedDatabaseContainer;
import com.aomaraie.cloudnotes.model.Note;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

@DataJpaTest
public class NoteRepositoryTest {

    public static PostgreSQLContainer<?> postgreSQLContainer = SharedDatabaseContainer.getInstance();

    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }

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
