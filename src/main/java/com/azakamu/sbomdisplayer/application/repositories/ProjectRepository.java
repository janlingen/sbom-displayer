package com.azakamu.sbomdisplayer.application.repositories;

import com.azakamu.sbomdisplayer.domain.Project;
import java.util.List;
import java.util.Set;

/**
 * @author janlingen
 */
public interface ProjectRepository {

  Project saveProject(Project project);

  List<Project> getAllProjects();

  List<Project> getProjectsByName(String name);

}
