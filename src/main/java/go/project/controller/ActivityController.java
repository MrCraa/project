package go.project.controller;

import go.project.entity.Activity;
import org.springframework.web.bind.annotation.*;
import go.project.service.ActivityService;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@RestController
@RequestMapping("/activity")
@RequiredArgsConstructor
public class ActivityController {

    @Autowired
    private final ActivityService activityService;

    @PostMapping("/update-finished/{id}")
    public ResponseEntity<Void> updateActivityFinished(@PathVariable Integer id, @RequestBody Map<String, Boolean> payload) {
        Boolean finished = payload.get("finished");
        if (finished != null) {
            activityService.updateActivityFinishedStatus(id, finished);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
    @PostMapping("/add")
    public String addActivity(@RequestParam Integer projectId, @RequestParam String name, RedirectAttributes redirectAttributes) {

        Activity newActivity = activityService.createActivity(projectId, name);


        return "redirect:/project/details/" + projectId;
    }
}
