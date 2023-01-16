package com.azakamu.sbomdisplayer.adapters.database.dataaccess;

import com.azakamu.sbomdisplayer.adapters.database.datatransfer.DependencyDTO;
import com.azakamu.sbomdisplayer.adapters.database.datatransfer.ProjectDTO;
import java.util.List;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author janlingen
 */
@Repository
public interface DependencyDAO extends Neo4jRepository<DependencyDTO, Long> {

  @Query("MATCH (p:Project) -[v:DEPENDS_ON]-> (d:Dependency) WHERE d.name CONTAINS $name AND v.version CONTAINS $version RETURN d")
  List<DependencyDTO> findDependenciesContaining(@Param("name") String name, @Param("version") String version);

  List<DependencyDTO> findAllByName(String name);
}
