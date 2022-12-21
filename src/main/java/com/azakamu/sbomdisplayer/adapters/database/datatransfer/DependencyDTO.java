package com.azakamu.sbomdisplayer.adapters.database.datatransfer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

/**
 * @author janlingen
 */
@Getter
@Setter
@Node("Dependency")
@AllArgsConstructor
@NoArgsConstructor
public class DependencyDTO {

  @Id
  @GeneratedValue
  private Long id;
  private String name;
  private String publisher;
  private String group;
  private String description;
}
