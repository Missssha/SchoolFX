package source.finalschoolfx.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Discipline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String disciplineName;

    @ManyToMany()
    @JoinTable(
            name = "teacher_disciplines",
            joinColumns = @JoinColumn(name = "teacher_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "discipline_id", referencedColumnName = "id")
    )
    private List<Teacher> teachers = new ArrayList<>();




}
