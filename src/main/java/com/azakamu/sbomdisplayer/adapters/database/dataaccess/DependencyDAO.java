package com.azakamu.sbomdisplayer.adapters.database.dataaccess;

import com.azakamu.sbomdisplayer.adapters.database.datatransfer.DependencyDTO;
import com.azakamu.sbomdisplayer.adapters.database.datatransfer.ProjectDTO;
import java.util.List;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * @author janlingen
 */
@Repository
public interface DependencyDAO extends Neo4jRepository<DependencyDTO, Long> {

  List<DependencyDTO> findAllByName(String name);
}
