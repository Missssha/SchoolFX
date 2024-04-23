package source.finalschoolfx.transformed;

import jakarta.xml.bind.annotation.XmlTransient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import source.finalschoolfx.models.StudClass;
import source.finalschoolfx.models.Student;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "student")
public class TransformedStudent {
    private Integer id;
    private String fullname;
    private String studClass;
    private Integer age;
    private String phoneNumber;
    private Student student;
    private String rating;

    public TransformedStudent(Student student){
        this.id = student.getId();
        this.fullname = student.getFullname();
        this.studClass = student.getStudClass().getClassname();
        this.age = student.getAge();
        this.phoneNumber = student.getPhoneNumber();
        this.student = student;
        this.rating = student.getRating();

    }
    @XmlAttribute
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @XmlAttribute
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    @XmlAttribute
    public String getStudClass() {
        return studClass;
    }

    public void setStudClass(String studClass) {
        this.studClass = studClass;
    }
    @XmlAttribute
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    @XmlAttribute
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    @XmlTransient
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
    @XmlAttribute
    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
