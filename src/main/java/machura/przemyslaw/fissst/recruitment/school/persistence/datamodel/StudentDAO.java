package machura.przemyslaw.fissst.recruitment.school.persistence.datamodel;

import lombok.*;
import org.hibernate.validator.constraints.pl.PESEL;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

@Table(name = "student")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class StudentDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @Size(min = 2, max = 15)
    private String firstName;

    @Column(nullable = false)
    @Size(min = 2, max = 25)
    private String lastName;

    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate birthDate;

    @PESEL
    @Column(nullable = false)
    private String pesel;

    @OneToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private AddressDAO address;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="school_class_id")
    private SchoolClassDAO schoolClass;

    @OneToMany(mappedBy = "student", fetch=FetchType.LAZY)
    private Set<StudentsMarksDAO> marks;

    private boolean dyslexia;

}
