package com.aomaraie.cloudnotes;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource(properties = {
		"spring.datasource.url=jdbc:postgresql://localhost:5432/notesdb",
		"spring.datasource.username=user",
		"spring.datasource.password=password",
		"jakarta.persistence.jdbc.url=jdbc:postgresql://localhost:5432/notesdb"
})

@SpringBootTest
class CloudNotesApplicationTests {

	@Test
	void contextLoads() {
	}

}
