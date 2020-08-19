package machura.przemyslaw.fissst.recruitment.school.datatransfermodel;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Table(name = "school_class")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class SchoolClassDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    @Size(min = 1, max = 25)
    private String name;

    @OneToMany(mappedBy="schoolClass", fetch = FetchType.LAZY)
    private Set<StudentDAO> students;

}
