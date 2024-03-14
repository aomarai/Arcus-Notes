package com.aomaraie.cloudnotes.exception;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:postgresql://localhost:5432/notesdb",
        "spring.datasource.username=user",
        "spring.datasource.password=password"
})

public class ResourceNotFoundExceptionTest {

    @Test
    public void resourceNotFoundExceptionReturnsCorrectMessage() {
        String expectedMessage = "Resource not found";
        ResourceNotFoundException ex = new ResourceNotFoundException(expectedMessage);

        assertEquals(expectedMessage, ex.getMessage());
    }

    @Test
    public void resourceNotFoundExceptionReturnsNullForEmptyMessage() {
        ResourceNotFoundException ex = new ResourceNotFoundException(null);

        assertNull(ex.getMessage());
    }
}
