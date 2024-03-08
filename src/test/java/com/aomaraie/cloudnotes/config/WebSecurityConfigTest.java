package com.aomaraie.cloudnotes.config;

import com.aomaraie.cloudnotes.config.WebSecurityConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

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
