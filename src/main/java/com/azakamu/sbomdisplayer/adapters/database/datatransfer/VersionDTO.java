package com.azakamu.sbomdisplayer.adapters.database.datatransfer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

/**
 * @author janlingen
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RelationshipProperties
public class VersionDTO {

  @RelationshipId
  private Long id;
  private String version;
  @TargetNode
  private DependencyDTO dependency;
}
