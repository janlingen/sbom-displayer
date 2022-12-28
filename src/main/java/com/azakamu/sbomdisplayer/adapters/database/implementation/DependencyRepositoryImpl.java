package com.azakamu.sbomdisplayer.adapters.database.implementation;

import com.azakamu.sbomdisplayer.adapters.database.dataaccess.DependencyDAO;
import com.azakamu.sbomdisplayer.adapters.database.mapper.DependencyMapper;
import com.azakamu.sbomdisplayer.application.repositories.DependencyRepository;
import com.azakamu.sbomdisplayer.domain.Dependency;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author janlingen
 */
@Component
@RequiredArgsConstructor
public class DependencyRepositoryImpl implements DependencyRepository {

  private final DependencyDAO dao;
  private final DependencyMapper mapper;

  @Override
  public Dependency saveDependency(Dependency dependency) {
    return mapper.toDomain(dao.save(mapper.toDto(dependency)));
  }

  @Override
  public List<Dependency> getAllDependencies() {
    List<Dependency> dependencies = mapper.toDomainList(dao.findAll());
    return dependencies == null ? Collections.emptyList() : dependencies;
  }

  @Override
  public List<Dependency> getDependenciesByName(String name) {
    List<Dependency> dependencies = mapper.toDomainList(dao.findAllByName(name));
    return dependencies == null ? Collections.emptyList() : dependencies;
  }
}
