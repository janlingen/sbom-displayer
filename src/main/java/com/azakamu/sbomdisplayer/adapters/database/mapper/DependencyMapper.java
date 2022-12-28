package com.azakamu.sbomdisplayer.adapters.database.mapper;

import com.azakamu.sbomdisplayer.adapters.database.datatransfer.DependencyDTO;
import com.azakamu.sbomdisplayer.domain.Dependency;
import java.util.List;
import org.mapstruct.Mapper;

/**
 * @author janlingen
 */
@Mapper(componentModel = "spring")
public interface DependencyMapper {

  Dependency toDomain(DependencyDTO dependencyDTO);

  DependencyDTO toDto(Dependency dependency);

  List<Dependency> toDomainList(List<DependencyDTO> dependencyDTOList);

  List<DependencyDTO> toDtoList(List<Dependency> dependencyList);

}
