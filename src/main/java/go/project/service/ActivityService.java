package go.project.service;

import go.project.entity.Activity;
import go.project.entity.Project;
import go.project.repository.ActivityRepository;
import go.project.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActivityService {

    @Autowired
    private final ActivityRepository activityRepository;

    @Autowired
    public ProjectRepository projectRepository;

    @Transactional
    public void updateActivityFinishedStatus(Integer id, Boolean finished) {
        Activity activity = activityRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid activity ID"));
        activity.setFinished(finished);
        activityRepository.save(activity);
    }

    @Transactional
    public Activity createActivity(Integer projectId, String name) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid project ID"));

        Activity newActivity = new Activity();
        newActivity.setName(name);
        newActivity.setFinished(false);
        newActivity.setProject(project);


        activityRepository.save(newActivity);
        return newActivity;
    }
}
