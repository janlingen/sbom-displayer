package com.azakamu.sbomdisplayer.application.repositories;

import com.azakamu.sbomdisplayer.domain.Dependency;
import com.azakamu.sbomdisplayer.domain.Project;
import java.util.List;
import java.util.Set;

/**
 * @author janlingen
 */
public interface DependencyRepository {

  Dependency saveDependency(Dependency dependency);

  List<Dependency> getAllDependencies();

  List<Dependency> getDependenciesByName(String name);

}
