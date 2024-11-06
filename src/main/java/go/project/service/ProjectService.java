package go.project.service;

import go.project.entity.Project;
import go.project.entity.User;
import go.project.repository.ProjectRepository;
import go.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    UserRepository userRepository;

    public Optional<Project> getProjectsById(Integer id) {
        return projectRepository.findProjectById(id);

    }

    public List<Project> getProjectByUserUid(String uid){
        User user = userRepository.findByUid(uid);
        return user.getProjectList();
    }


    public List<Project> findAll() {
        List<Project> all = projectRepository.findAll();
        return all;
    }


}
