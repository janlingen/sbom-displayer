package com.azakamu.sbomdisplayer.adapters.database.datatransfer;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

/**
 * @author janlingen
 */
@Getter
@Setter
@Node("Project")
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {

  @Id
  @GeneratedValue
  private Long id;
  private String name;
  @Relationship(type = "dependsOn")
  private List<VersionDTO> dependencies;
}
