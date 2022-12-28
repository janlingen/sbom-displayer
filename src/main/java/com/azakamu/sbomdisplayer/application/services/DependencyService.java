package com.azakamu.sbomdisplayer.application.services;

import com.azakamu.sbomdisplayer.application.repositories.DependencyRepository;
import com.azakamu.sbomdisplayer.domain.Dependency;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author janlingen
 */

@Service
@RequiredArgsConstructor
public class DependencyService {

  private final DependencyRepository dependencyRepository;

  public Dependency createDependency(String name, String publisher, String group,
      String description) {
    return dependencyRepository.saveDependency(
        new Dependency(null, name, publisher, group, description));
  }

  public List<Dependency> getDependenciesByName(String name) {
    return dependencyRepository.getDependenciesByName(name);
  }

  public List<Dependency> getAllDependencies() {
    return dependencyRepository.getAllDependencies();
  }



}
