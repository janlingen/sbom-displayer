package com.azakamu.sbomdisplayer.adapters.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author janlingen
 */
@Controller()
public class ProjectController {

  

  @GetMapping("/projects")
  private String getAllProjects(){

    return "projects";
  }
}
