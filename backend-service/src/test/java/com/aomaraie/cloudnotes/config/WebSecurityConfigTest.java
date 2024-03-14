package com.aomaraie.cloudnotes.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:postgresql://localhost:5432/notesdb",
        "spring.datasource.username=user",
        "spring.datasource.password=password"
})

public class WebSecurityConfigTest {

    @InjectMocks
    WebSecurityConfig webSecurityConfig;

    @Mock
    HttpSecurity httpSecurity;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void passwordEncoderReturnsNonNull() {
        assertNotNull(webSecurityConfig.passwordEncoder());
    }
}
