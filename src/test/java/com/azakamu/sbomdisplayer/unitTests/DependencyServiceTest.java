package com.azakamu.sbomdisplayer.unitTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.azakamu.sbomdisplayer.application.repositories.DependencyRepository;
import com.azakamu.sbomdisplayer.application.services.DependencyService;
import com.azakamu.sbomdisplayer.domain.Dependency;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author janlingen
 */
@ExtendWith(MockitoExtension.class)
public class DependencyServiceTest {

  @Mock
  private DependencyRepository mockDependencyRepository;

  @InjectMocks
  private DependencyService dependencyService;

  @Test
  public void testCreateDependency() {
    Dependency expected = new Dependency(null, "name", "publisher", "group", "description");
    when(mockDependencyRepository.saveDependency(any(Dependency.class)))
        .thenReturn(expected);
    Dependency actual = dependencyService.createDependency("name", "publisher", "group",
        "description");
    verify(mockDependencyRepository).saveDependency(expected);
    assertEquals(expected, actual);
  }

  @Test
  public void testGetDependenciesByName() {
    Dependency expected = new Dependency(1L, "name", "publisher", "group", "description");
    when(mockDependencyRepository.getDependenciesByName("name"))
        .thenReturn(Collections.singletonList(expected));
    List<Dependency> actual = dependencyService.getDependenciesByName("name");
    verify(mockDependencyRepository).getDependenciesByName("name");
    assertEquals(Collections.singletonList(expected), actual);
  }

  @Test
  public void testGetAllDependencies() {
    Dependency expected = new Dependency(1L, "name", "publisher", "group", "description");
    when(mockDependencyRepository.getAllDependencies())
        .thenReturn(Collections.singletonList(expected));
    List<Dependency> actual = dependencyService.getAllDependencies();
    verify(mockDependencyRepository).getAllDependencies();
    assertEquals(Collections.singletonList(expected), actual);
  }
}
