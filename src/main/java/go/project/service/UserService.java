package go.project.service;


import go.project.entity.LogIn_Out;
import go.project.entity.User;
import go.project.repository.LogIn_OutRepository;
import go.project.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LogIn_OutRepository logInOutRepository;

    public User findUserByUid(String uid) {
        return userRepository.findByUid(uid);
    }


    public String logUserAttendance(String uid) {
        User user = findUserByUid(uid);
        if (user == null) {
            throw new RuntimeException("User not found for NFC UID: " + uid);
        }

        Optional<LogIn_Out> lastLogOpt = user.getLogInOutList().stream()
                .max(Comparator.comparing(LogIn_Out::getLogInTime));

            LocalDateTime theLastAction;
        if (lastLogOpt.isEmpty()) {

            theLastAction = LocalDateTime.of(1970, 1, 1, 0, 0);
        } else {
            theLastAction = lastLogOpt.get().getLogOutTime() != null ?
                    lastLogOpt.get().getLogOutTime() : lastLogOpt.get().getLogInTime();
        }
            if (Duration.between(theLastAction, LocalDateTime.now()).toMinutes() < 1) {
                return "Less than 3 minutes since the last log action.";
            }
            else {
                if (lastLogOpt.isPresent() && lastLogOpt.get().getLogOutTime() == null) {
                    LogIn_Out lastLog = lastLogOpt.get();
                    LocalDateTime loginTime = lastLog.getLogInTime();
                    lastLog.setLogOutTime(LocalDateTime.now());
                    logInOutRepository.save(lastLog);
                    theLastAction = LocalDateTime.now();
                    return "Logged out successfully .";

                } else {
                    LogIn_Out newLog = new LogIn_Out();
                    newLog.setLogInTime(LocalDateTime.now());
                    newLog.setUser(user);
                    logInOutRepository.save(newLog);
                    theLastAction = LocalDateTime.now();
                    return "Logged in successfully.";
                }
            }
    }

//    public Optional<Duration> getTotalWorkingHours(String uid) {
//        User user = findUserByUid(uid);
//        if (user == null) {
//            throw new RuntimeException("User not found for NFC UID: " + uid);
//        }
//        List<LogIn_Out> logList = user.getLogInOutList();
//        Duration totalDuration = Duration.ZERO;
//        for (LogIn_Out log : logList) {
//            if (log.getLogInTime() != null && log.getLogOutTime() != null) {
//                Duration duration = Duration.between(log.getLogInTime(), log.getLogOutTime());
//                totalDuration = totalDuration.plus(duration);
//
//        }
//        }
//        return Optional.ofNullable(totalDuration );
//            }
public Optional<String> getTotalWorkingHours(String uid, LocalDate today) {
    User user = findUserByUid(uid);
    if (user == null) {
        throw new RuntimeException("User not found for NFC UID: " + uid);
    }

    List<LogIn_Out> logList = user.getLogInOutList();


    List<LogIn_Out> todaysLogs = logList.stream()
            .filter(log -> log.getLogInTime() != null && log.getLogOutTime() != null)
            .filter(log -> log.getLogInTime().toLocalDate().equals(today))
            .collect(Collectors.toList());

    Duration totalDuration = Duration.ZERO;
    for (LogIn_Out log : todaysLogs) {
        try {
            Duration duration = Duration.between(log.getLogInTime(), log.getLogOutTime());
            totalDuration = totalDuration.plus(duration);
        } catch (Exception e) {

            throw new RuntimeException("Error calculating duration for log entry: " + uid);
        }
    }

    if (totalDuration.toHours() > 8) {
        return Optional.of("User has worked " + totalDuration.toHours() + " hours today.");
    } else {
        return Optional.of("User has not worked enough hours today. He has worked only " + totalDuration);
    }
}

}



