package com.azakamu.sbomdisplayer.e2eTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.neo4j.harness.Neo4j;
import org.neo4j.harness.Neo4jBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

/**
 * @author janlingen
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SBomControllerTest {

  @LocalServerPort
  private Integer port;

  @Autowired
  private TestRestTemplate testRestTemplate;

  private static Neo4j embeddedDb;

  @BeforeAll
  static void initializeNeo4j() {
    embeddedDb = Neo4jBuilders.newInProcessBuilder()
        .withDisabledServer()//disable http server
        .build();
  }

  @DynamicPropertySource
  static void neo4jProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.neo4j.uri", embeddedDb::boltURI);
    registry.add("spring.neo4j.authentication.username", () -> "neo4j");
    registry.add("spring.neo4j.authentication.password", () -> null);
  }

  @AfterAll
  static void stopNeo4j() {
    embeddedDb.close();
  }

  @Test
  void pushSBomTest1() {
    String url = "http://localhost:" + port + "/sbom";
    String sbom = "{\"metadata\":{\"component\":{\"name\":\"test-project\"}}, \"components\": [{\n"
        + "            \"publisher\": \"Pivotal Software, Inc.\",\n"
        + "            \"group\": \"org.springframework.boot\",\n"
        + "            \"name\": \"spring-boot-starter-web\",\n"
        + "            \"version\": \"2.7.1\",\n"
        + "            \"description\": \"Starter for building web, including RESTful, applications using Spring MVC. Uses Tomcat as the default embedded container\"}]}";
    ResponseEntity<String> response = testRestTemplate.postForEntity(url, new HttpEntity<>(sbom),
        String.class);
    System.out.println(response.getBody());
    assertEquals(200, response.getStatusCodeValue());
  }

}
