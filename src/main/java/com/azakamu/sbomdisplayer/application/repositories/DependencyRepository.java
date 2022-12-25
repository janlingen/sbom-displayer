package com.azakamu.sbomdisplayer.application.repositories;

import com.azakamu.sbomdisplayer.domain.Dependency;
import java.util.List;

/**
 * @author janlingen
 */
public interface DependencyRepository {

  Dependency saveDependency(Dependency dependency);

  List<Dependency> getAllDependencies();

  List<Dependency> getDependenciesByName(String name);

}
