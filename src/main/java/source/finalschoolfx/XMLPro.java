package source.finalschoolfx;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import source.finalschoolfx.models.Student;
import source.finalschoolfx.transformed.TransformedStudent;

import java.util.ArrayList;
import java.util.List;

@Data
@XmlRootElement(name = "students")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
public class XMLPro {

    @XmlElement(name = "student")
    private List<TransformedStudent> transformedStudentList = new ArrayList<>();

    public void setStudentList(List<TransformedStudent> students) {
        this.transformedStudentList = students;
    }

}
