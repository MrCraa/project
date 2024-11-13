package go.project.controller;

import go.project.entity.Project;
import go.project.entity.User;
import go.project.repository.ProjectRepository;
import go.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    public ProjectService projectService;

    @Autowired
    public ProjectRepository projectRepository;

    @GetMapping("/{id}")
    public String getProjectDetails(@PathVariable Integer id, Model model) {
        Project project = projectService.findById(id);

        if (project == null) {
            model.addAttribute("error", "Project not found.");
            return "error";
        }


        model.addAttribute("project", project);

        return "project-details";
    }

    @GetMapping("/all")
    public String getAllProjects(Model model) {
        List<Project> projects = projectService.findAll();
        model.addAttribute("projects", projects);
        return "all-projects";
    }

    @PostMapping("/add")
    public String addProject(@ModelAttribute Project project) {
        projectService.save(project);
        return "redirect:/project/all";
    }


}