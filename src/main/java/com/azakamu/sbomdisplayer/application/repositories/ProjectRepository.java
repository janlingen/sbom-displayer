package com.azakamu.sbomdisplayer.application.repositories;

import com.azakamu.sbomdisplayer.domain.Project;
import java.util.List;

/**
 * @author janlingen
 */
public interface ProjectRepository {

  Project saveProject(Project project);

  List<Project> getAllProjects();

  Project getProjectById(Long id);

  List<Project> getAllProjectsDependingOn(String name);

}
