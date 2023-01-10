package com.azakamu.sbomdisplayer.unitTests;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.azakamu.sbomdisplayer.application.repositories.ProjectRepository;
import com.azakamu.sbomdisplayer.application.services.ProjectService;
import com.azakamu.sbomdisplayer.domain.Dependency;
import com.azakamu.sbomdisplayer.domain.Project;
import com.azakamu.sbomdisplayer.domain.Version;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author janlingen
 */
@ExtendWith(MockitoExtension.class)
public class ProjectServiceTest {

  @Mock
  private ProjectRepository mockProjectRepository;

  @InjectMocks
  private ProjectService projectService;

  @Test
  public void testCreateProject() {
    String name = "test-project";
    List<Version> dependencies = List.of(
        new Version(1L, "2.0.0", new Dependency(1L, "test", "test", "test", "test"))
    );
    Project project = new Project(null, name, dependencies);
    when(mockProjectRepository.saveProject(project)).thenReturn(project);
    Project result = projectService.createProject(name, dependencies);
    verify(mockProjectRepository).saveProject(project);
    assertEquals(project, result);
  }

  @Test
  public void testRemoveProject() {
    Long id = 1L;
    projectService.removeProject(id);
    verify(mockProjectRepository).removeProject(id);
  }

  @Test
  public void testGetAllProjects() {
    List<Project> projects = Arrays.asList(
        new Project(1L, "test-project-1", null),
        new Project(2L, "test-project-2", null)
    );
    when(mockProjectRepository.getAllProjects()).thenReturn(projects);
    List<Project> result = projectService.getAllProjects();
    verify(mockProjectRepository).getAllProjects();
    assertEquals(projects, result);
  }

  @Test
  public void testGetProjectById() {
    Long id = 1L;
    Project project = new Project(id, "test-project", null);
    when(mockProjectRepository.getProjectById(id)).thenReturn(project);
    Project result = projectService.getProjectById(id);
    verify(mockProjectRepository).getProjectById(id);
    assertEquals(project, result);
  }

  @Test
  public void testGetAllProjectsDependingOn() {
    String name = "test-dependency";
    List<Project> projects = Arrays.asList(
        new Project(1L, "test-project-1", null),
        new Project(2L, "test-project-2", null)
    );
    when(mockProjectRepository.getAllProjectsDependingOn(name)).thenReturn(projects);
    List<Project> result = projectService.getAllProjectsDependingOn(name);
    verify(mockProjectRepository).getAllProjectsDependingOn(name);
    assertEquals(projects, result);
  }
}