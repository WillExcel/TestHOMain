package com.file;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FileApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)

public class FileApplicationTests {
	@LocalServerPort
	protected int port;
}