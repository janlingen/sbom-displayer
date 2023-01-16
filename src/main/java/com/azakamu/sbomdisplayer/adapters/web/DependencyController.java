package com.azakamu.sbomdisplayer.adapters.web;

import com.azakamu.sbomdisplayer.application.services.DependencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author janlingen
 */

@Controller
@RequiredArgsConstructor
public class DependencyController {

  private final DependencyService dependencyService;

  @GetMapping("/dependencies")
  public String getAllDependencies(Model model) {
    String name = (String) model.getAttribute("name");
    String version = (String) model.getAttribute("version");
    if (name == null && version == null) {
      model.addAttribute("dependencies", dependencyService.getAllDependencies());
    } else if (version == null) {
      model.addAttribute("dependencies", dependencyService.getDependenciesContaining(name, ""));
    } else if (name == null) {
      model.addAttribute("dependencies", dependencyService.getDependenciesContaining("", version));
    } else {
      model.addAttribute("dependencies",
          dependencyService.getDependenciesContaining(name, version));
    }
    return "dependencies";
  }

  @PostMapping("/search")
  public String getAllDependenciesContaining(RedirectAttributes redirectAttributes,
      @RequestParam("name") String name, @RequestParam("version") String version) {
    redirectAttributes.addFlashAttribute("name", name);
    redirectAttributes.addFlashAttribute("version", version);
    return "redirect:/dependencies";
  }
}
