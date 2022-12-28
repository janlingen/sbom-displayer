package com.azakamu.sbomdisplayer.adapters.database.mapper;

import com.azakamu.sbomdisplayer.adapters.database.datatransfer.ProjectDTO;
import com.azakamu.sbomdisplayer.domain.Project;
import java.util.List;
import org.mapstruct.Mapper;

/**
 * @author janlingen
 */
@Mapper(componentModel = "spring", uses = {VersionMapper.class})
public interface ProjectMapper {

  Project toDomain(ProjectDTO projectDTO);

  ProjectDTO toDto(Project project);

  List<Project> toDomainList(List<ProjectDTO> projectDTOList);

  List<ProjectDTO> toDtoList(List<Project> projectList);

}
