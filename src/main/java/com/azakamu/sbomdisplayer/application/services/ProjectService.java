package com.azakamu.sbomdisplayer.application.services;

import com.azakamu.sbomdisplayer.application.repositories.ProjectRepository;
import com.azakamu.sbomdisplayer.domain.Project;
import com.azakamu.sbomdisplayer.domain.Version;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author janlingen
 */

@Service
@RequiredArgsConstructor
public class ProjectService {

  private final ProjectRepository projectRepository;

  public Project createProject(String name, List<Version> dependencies) {
    return projectRepository.saveProject(new Project(null, name, dependencies));
  }

  public List<Project> getAllProjects() {
    return projectRepository.getAllProjects();
  }

  public Project getProjectById(Long id) {
    return projectRepository.getProjectById(id);
  }

}
