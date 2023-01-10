package com.azakamu.sbomdisplayer.unitTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.azakamu.sbomdisplayer.application.services.DependencyService;
import com.azakamu.sbomdisplayer.application.services.ProjectService;
import com.azakamu.sbomdisplayer.application.services.SBomService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author janlingen
 */
@ExtendWith(MockitoExtension.class)
public class SBomServiceTest {

  @Mock
  private DependencyService dependencyService;

  @Mock
  private ProjectService projectService;

  @InjectMocks
  private SBomService sbomService;

  @Test
  void testParseProjectNameGradle() {
    String sbom = "{\"metadata\":{\"component\":{\"name\":\"test-project\"}}, \"components\": []}";
    String expected = "test-project";
    String actual = sbomService.parseProjectNameGradle(sbom);
    assertEquals(expected, actual);
  }
}