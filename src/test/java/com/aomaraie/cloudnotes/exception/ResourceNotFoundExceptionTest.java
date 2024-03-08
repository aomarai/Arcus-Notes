package com.aomaraie.cloudnotes.exception;

import com.aomaraie.cloudnotes.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
