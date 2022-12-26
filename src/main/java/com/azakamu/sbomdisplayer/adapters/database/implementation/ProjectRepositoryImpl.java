package com.azakamu.sbomdisplayer.adapters.database.implementation;

import com.azakamu.sbomdisplayer.adapters.database.dataaccess.ProjectDAO;
import com.azakamu.sbomdisplayer.adapters.database.mapper.DependencyMapper;
import com.azakamu.sbomdisplayer.adapters.database.mapper.ProjectMapper;
import com.azakamu.sbomdisplayer.application.repositories.ProjectRepository;
import com.azakamu.sbomdisplayer.domain.Project;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

/**
 * @author janlingen
 */
@Component
@RequiredArgsConstructor
public class ProjectRepositoryImpl implements ProjectRepository {

  private final ProjectDAO dao;
  private final ProjectMapper mapper = Mappers.getMapper(ProjectMapper.class);;

  @Override
  public Project saveProject(Project project) {
    return mapper.toDomain(dao.save(mapper.toDto(project)));
  }

  @Override
  public List<Project> getAllProjects() {
    return null;
  }

  @Override
  public List<Project> getProjectsByName(String name) {
    return null;
  }
}
