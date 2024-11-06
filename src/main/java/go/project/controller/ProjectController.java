package go.project.controller;

import go.project.entity.Project;
import go.project.entity.User;
import go.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("project")
public class ProjectController {
    @Autowired
    public ProjectService projectService;

    @GetMapping("/findProjectsById")
    public Optional<Project> getProjectsByUserId(@RequestParam Integer id) {
        return projectService.getProjectsById(id);
    }

    @GetMapping("/findProjectsByUid")
    public List<Project> getProjectsByUid(@RequestParam String uid){
        return projectService.getProjectByUserUid(uid);
    }

    @GetMapping("/all")
    public String getAllProjects(final Model model) {
        List<Project> projects = projectService.findAll();
        model.addAttribute("projects", projects);
        return "all-projects";
    }
}