package com.azakamu.sbomdisplayer.integrationTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.azakamu.sbomdisplayer.application.services.ProjectService;
import com.azakamu.sbomdisplayer.domain.Dependency;
import com.azakamu.sbomdisplayer.domain.Project;
import com.azakamu.sbomdisplayer.domain.Version;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.neo4j.harness.Neo4j;
import org.neo4j.harness.Neo4jBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

/**
 * @author janlingen
 */
@SpringBootTest
public class ProjectServiceTest {

  @Autowired
  private ProjectService projectService;

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
  public void testCreateProject() {
    String name = "test-project";
    List<Version> dependencies = List.of(
        new Version(1L, "2.0.0", new Dependency(1L, "test", "test", "test", "test"))
    );
    Project project = new Project(null, name, dependencies);
    Project result = projectService.createProject(name, dependencies);
    assertEquals(project.name(), result.name());
    assertEquals(project.dependencies(), result.dependencies());
    assertEquals(projectService.getAllProjects().size(), 1);
  }

}
