package com.azakamu.sbomdisplayer.adapters.database.dataaccess;

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
public interface ProjectDAO extends Neo4jRepository<ProjectDTO, Long> {

  @Query("MATCH (p:Project) -[v:DEPENDS_ON]-> (d:Dependency) WHERE d.name = $name RETURN p")
  List<ProjectDTO> findProjectsDependingOn(@Param("name") String name);

}
