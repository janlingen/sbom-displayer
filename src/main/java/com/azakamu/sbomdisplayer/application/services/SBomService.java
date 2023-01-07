package com.azakamu.sbomdisplayer.application.services;

import com.azakamu.sbomdisplayer.domain.Dependency;
import com.azakamu.sbomdisplayer.domain.Project;
import com.azakamu.sbomdisplayer.domain.Version;
import com.jayway.jsonpath.JsonPath;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author janlingen
 */

@Service
@RequiredArgsConstructor
public class SBomService {

  private final DependencyService dependencyService;
  private final ProjectService projectService;

  public Project parseSbomGradle(String sbom) {
    return projectService.createProject(parseProjectNameGradle(sbom),
        parseProjectDependenciesGradle(sbom));
  }

  public String parseProjectNameGradle(String sbom) {
    return JsonPath.read(sbom, "$.metadata.component.name");
  }

  public List<Version> parseProjectDependenciesGradle(String sbom) {
    List<HashMap<String, Object>> dependenciesRaw = JsonPath.read(sbom, "$.components");
    List<Version> dependencies = new ArrayList<>();

    for (HashMap<String, Object> dependency : dependenciesRaw) {
      Dependency currentDependency;
      if (dependencyService.getDependenciesByName(dependency.getOrDefault("name", "n/a").toString())
          .isEmpty() ||
          dependencyService.getDependenciesByName(dependency.getOrDefault("name", "n/a").toString())
              == null) {
        currentDependency = dependencyService.createDependency(
            dependency.getOrDefault("name", "n/a").toString(),
            dependency.getOrDefault("publisher", "n/a").toString(),
            dependency.getOrDefault("group", "n/a").toString(),
            dependency.getOrDefault("description", "n/a").toString());
      } else {
        currentDependency = dependencyService.getDependenciesByName(
            dependency.getOrDefault("name", "n/a").toString()).get(0);
      }
      dependencies.add(new Version(null, dependency.getOrDefault("version", "n/a").toString(),
          currentDependency));
    }
    return dependencies;
  }

}
