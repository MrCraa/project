package go.project.controller;

import go.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class LogIn_OutController {

   @Autowired
   public UserService userService;


    @PostMapping("/login")
    public String logAttendance(@RequestParam String uid) {
        return userService.logUserAttendance(uid);
    }

}
