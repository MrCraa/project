package go.project.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;


import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String surname;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private NFC nfc;

    @OneToMany(mappedBy = "user" ,cascade = CascadeType.ALL)
    public List<LogIn_Out> logInOutList = new ArrayList<>();

    @ManyToMany(mappedBy = "userList", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Project> projectList = new ArrayList<>();

}