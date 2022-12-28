package com.azakamu.sbomdisplayer.adapters.database.implementation;

import com.azakamu.sbomdisplayer.adapters.database.dataaccess.ProjectDAO;
import com.azakamu.sbomdisplayer.adapters.database.datatransfer.ProjectDTO;
import com.azakamu.sbomdisplayer.adapters.database.mapper.ProjectMapper;
import com.azakamu.sbomdisplayer.application.repositories.ProjectRepository;
import com.azakamu.sbomdisplayer.domain.Project;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author janlingen
 */
@Component
@RequiredArgsConstructor
public class ProjectRepositoryImpl implements ProjectRepository {

  private final ProjectDAO dao;
  private final ProjectMapper mapper;

  @Override
  public Project saveProject(Project project) {
    return mapper.toDomain(dao.save(mapper.toDto(project)));
  }

  @Override
  public List<Project> getAllProjects() {
    List<Project> projects = mapper.toDomainList(dao.findAll());
    return projects == null ? Collections.emptyList() : projects;
  }

  @Override
  public Project getProjectById(Long id) {
    return mapper.toDomain(dao.findById(id).orElse(new ProjectDTO()));
  }

  @Override
  public List<Project> getAllProjectsDependingOn(String name) {
    List<Project> projects = mapper.toDomainList(dao.findAll());
    return projects == null ? Collections.emptyList() : projects;
  }

}
