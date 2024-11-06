package go.project.controller;


import go.project.entity.User;
import go.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    public UserService userService;

    @GetMapping("/findByUID")
    public User getUserUid(@RequestParam String uid) {
        return userService.findUserByUid(uid);

    }


    @GetMapping("/getTotalWorkHours")
    public String getWorkingHours(@RequestParam String uid,  @RequestParam LocalDate today){
        return  userService.getTotalWorkingHours(uid, today).get().toString();
    }





    }



