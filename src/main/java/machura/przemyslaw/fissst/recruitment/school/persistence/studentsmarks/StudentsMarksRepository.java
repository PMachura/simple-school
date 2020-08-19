package machura.przemyslaw.fissst.recruitment.school.persistence.studentsmarks;

import machura.przemyslaw.fissst.recruitment.school.persistence.datamodel.StudentsMarksDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentsMarksRepository extends JpaRepository<StudentsMarksDAO, Long> {
}
