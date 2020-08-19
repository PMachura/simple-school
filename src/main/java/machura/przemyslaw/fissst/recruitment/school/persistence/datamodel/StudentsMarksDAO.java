package machura.przemyslaw.fissst.recruitment.school.persistence.datamodel;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Table(name = "students_marks")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class StudentsMarksDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private StudentDAO student;

    @ManyToOne
    @JoinColumn(name = "school_subject_id", nullable = false)
    private SchoolSubjectDAO schoolSubject;

    @Column(nullable = false)
    @Min(1)
    @Max(6)
    private Integer mark;

}
