package source.finalschoolfx.models;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class StudClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @OneToMany(mappedBy = "studClass")
    private List<Student> listOfStudents = new ArrayList<>();

    private String classname;

    @ManyToMany()
    @JoinTable(
            name = "teacher_studclasses",
            joinColumns = @JoinColumn(name = "teacher_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "studclass_id", referencedColumnName = "id")
    )
    private List<Teacher> teachers;

    private int countOfStudents;

    public void countStudents() {
        countOfStudents = (int) listOfStudents.stream().count();
    }

}
