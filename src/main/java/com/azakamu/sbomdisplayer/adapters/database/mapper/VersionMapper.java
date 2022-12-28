package com.azakamu.sbomdisplayer.adapters.database.mapper;

import com.azakamu.sbomdisplayer.adapters.database.datatransfer.VersionDTO;
import com.azakamu.sbomdisplayer.domain.Version;
import java.util.List;
import org.mapstruct.Mapper;

/**
 * @author janlingen
 */
@Mapper(componentModel = "spring", uses = {DependencyMapper.class})
public interface VersionMapper {

  Version toDomain(VersionDTO versionDTO);

  VersionDTO toDto(Version version);

  List<Version> toDomainList(List<VersionDTO> versionDTOList);

  List<VersionDTO> toDtoList(List<Version> versionList);
}
