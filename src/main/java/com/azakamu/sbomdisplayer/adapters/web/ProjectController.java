package com.azakamu.sbomdisplayer.adapters.web;

import com.azakamu.sbomdisplayer.application.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author janlingen
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectController {

  private final ProjectService projectService;

  @GetMapping
  private String getAllProjects(Model model) {
    model.addAttribute("projects", projectService.getAllProjects());
    return "projects";
  }

  @GetMapping("/{id}")
  private String getAllProjects(@PathVariable Long id, Model model) {
    model.addAttribute("project", projectService.getProjectById(id));
    return "detail-project";
  }
}
