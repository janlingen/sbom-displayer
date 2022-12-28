package com.azakamu.sbomdisplayer.adapters.web;

import com.azakamu.sbomdisplayer.application.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author janlingen
 */
@Controller
@RequiredArgsConstructor
public class ProjectController {

  private final ProjectService projectService;

  @GetMapping("/projects")
  private String getAllProjects(Model model) {
    model.addAttribute("projects", projectService.getAllProjects());
    return "projects";
  }
}
