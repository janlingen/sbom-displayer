package com.azakamu.sbomdisplayer.adapters.database.implementation;

import com.azakamu.sbomdisplayer.adapters.database.dataaccess.DependencyDAO;
import com.azakamu.sbomdisplayer.adapters.database.mapper.DependencyMapper;
import com.azakamu.sbomdisplayer.application.repositories.DependencyRepository;
import com.azakamu.sbomdisplayer.domain.Dependency;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

/**
 * @author janlingen
 */
@Component
@RequiredArgsConstructor
public class DependencyRepositoryImpl implements DependencyRepository {

  private final DependencyDAO dao;
  private final DependencyMapper mapper = Mappers.getMapper(DependencyMapper.class);

  @Override
  public Dependency saveDependency(Dependency dependency) {
    return mapper.toDomain(dao.save(mapper.toDto(dependency)));
  }

  @Override
  public List<Dependency> getAllDependencies() {
    return mapper.toDomainList(dao.findAll());
  }

  @Override
  public List<Dependency> getDependenciesByName(String name) {
    return mapper.toDomainList(dao.findAllByName(name));
  }
}
