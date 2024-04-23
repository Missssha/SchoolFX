package source.finalschoolfx.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import java.util.List;


@Data
@Entity
@NoArgsConstructor

public class Student{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String fullname;

    @ManyToOne(fetch = FetchType.LAZY)
    public StudClass studClass;

    private int age;

    private String phoneNumber;

    private String rating;
}