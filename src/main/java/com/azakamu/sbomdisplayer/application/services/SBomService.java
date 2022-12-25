package com.azakamu.sbomdisplayer.application.services;

import com.azakamu.sbomdisplayer.domain.Dependency;
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

  public void parseSbomGradle(String sbom) {
    projectService.createProject(parseProjectNameGradle(sbom),
        parseProjectDependenciesGradle(sbom));
  }

  private String parseProjectNameGradle(String sbom) {
    return JsonPath.read(sbom, "$.metadata.component.name");
  }

  private List<Version> parseProjectDependenciesGradle(String sbom) {
    List<HashMap<String, Object>> dependenciesRaw = JsonPath.read(sbom, "$.components");
    List<Version> dependencies = new ArrayList();

    for (HashMap<String, Object> dependency : dependenciesRaw) {
      Dependency currentDependency;
      if (dependencyService.getDependenciesByName(
          dependency.getOrDefault("name", "dummyName").toString()).isEmpty() ||
          dependencyService.getDependenciesByName(
              dependency.getOrDefault("name", "dummyName").toString()) == null) {
        currentDependency = dependencyService.createDependency(
            dependency.getOrDefault("name", "dummyName").toString(),
            dependency.getOrDefault("publisher", "dummyPublisher").toString(),
            dependency.getOrDefault("group", "dummyGroup").toString(),
            dependency.getOrDefault("description", "dummyDescription").toString());
      } else {
        currentDependency = dependencyService.getDependenciesByName(
            dependency.getOrDefault("name", "dummyName").toString()).get(0);
      }
      dependencies.add(new Version(null,
          dependency.getOrDefault("version", "dummyVersion").toString(), currentDependency));
    }
    return dependencies;
  }

}
