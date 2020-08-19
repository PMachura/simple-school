package machura.przemyslaw.fissst.recruitment.school.datatransfermodel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Table(name = "school_subject")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class SchoolSubjectDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    @Size(min = 2, max = 50)
    private String name;

    @OneToMany(mappedBy = "schoolSubject")
    private Set<StudentsMarksDAO> studentsMarks;

}
