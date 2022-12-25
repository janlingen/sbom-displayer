package com.azakamu.sbomdisplayer.adapters.database.mapper;

import com.azakamu.sbomdisplayer.adapters.database.datatransfer.DependencyDTO;
import com.azakamu.sbomdisplayer.adapters.database.datatransfer.ProjectDTO;
import com.azakamu.sbomdisplayer.domain.Dependency;
import com.azakamu.sbomdisplayer.domain.Project;
import java.util.List;
import org.mapstruct.Mapper;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Component;

/**
 * @author janlingen
 */
@Mapper(uses = {DependencyMapper.class, VersionMapper.class})
public interface ProjectMapper {

  Project toDomain(ProjectDTO projectDTO);

  ProjectDTO toDto(Project project);

}
