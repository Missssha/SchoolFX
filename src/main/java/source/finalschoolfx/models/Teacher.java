package source.finalschoolfx.models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Teacher{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fullname;

    private String phoneNumber;

    private int age;

    private int workPeriod;

    @ManyToMany(mappedBy = "teachers")
    private List<Discipline> disciplines = new ArrayList<>();

    @ManyToMany(mappedBy = "teachers")
    private List<StudClass> studClasses = new ArrayList<>();

}
