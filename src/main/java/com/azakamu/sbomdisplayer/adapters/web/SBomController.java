package com.azakamu.sbomdisplayer.adapters.web;

import com.azakamu.sbomdisplayer.application.services.SBomService;
import com.azakamu.sbomdisplayer.domain.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author janlingen
 */
@RestController
@RequiredArgsConstructor
public class SBomController {

  private final SBomService sBomService;

  @PostMapping("/sbom")
  public Project pushSBom(@RequestBody String sbom) {
    return sBomService.parseSbomGradle(sbom);
  }

}
