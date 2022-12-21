package com.azakamu.sbomdisplayer.adapters.database.dataaccess;

import com.azakamu.sbomdisplayer.adapters.database.datatransfer.ProjectDTO;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * @author janlingen
 */
@Repository
public interface ProjectDAO extends Neo4jRepository<ProjectDTO, Long> {

}
