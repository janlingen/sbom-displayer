package com.azakamu.sbomdisplayer.adapters.web;

import com.azakamu.sbomdisplayer.application.services.DependencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author janlingen
 */

@Controller
@RequiredArgsConstructor
public class DependencyController {

  private final DependencyService dependencyService;

  @GetMapping("/dependencies")
  public String getAllDependencies(Model model){
    model.addAttribute("dependencies", dependencyService.getAllDependencies());
    return "dependencies";
  }
}
